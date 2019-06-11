package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Borrow;
import com.example.techlab.util.DateUtils;

public class Geleend_Aangevraagd extends DrawerMenu {
    Intent intent;
    TextView prnaam, gebrnaam, aantalpr, status, productReturnDate, productLoanDate, productRequestDate;
    Button productRequestCancelButton, productTakeBackButton, productLendButton;
    DataManagement dataManagement;
    CheckBox ProductIsNotDamaged;
    Borrow borrow;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_borrow_status_info, null,false);
        frameLayout.addView(activityView);

        System.out.println("Aanvraag Returned.java started");
        dataManagement = new DataManagement();
        intent = new Intent();

//        intent.getIntExtra("P_id_ProductBorrowlist", -1);
        prnaam = findViewById(R.id.leenavraagProduct);
        gebrnaam = findViewById(R.id.leenavraagUser);
        aantalpr = findViewById(R.id.leenavraagAantal);
        status = findViewById(R.id.productRequestListProductStatus);
        productRequestCancelButton = findViewById(R.id.productRequestCancelButton);
        productTakeBackButton = findViewById(R.id.productTakeBackButton);
        productLendButton = findViewById(R.id.productLendButton);
        ProductIsNotDamaged = findViewById(R.id.ProductIsDamagedCheckBox);
        productReturnDate =findViewById(R.id.productReturnDate);
        productLoanDate = findViewById(R.id.productLoanDate);
        productRequestDate = findViewById(R.id.productRequestDate);
        borrow = dataManagement.getBorrowDataWithId(getIntent().getIntExtra("P_id_ProductBorrowList",-1));
        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putString(MainActivity.KEY_PRODUCT_ADMINISTER_SPINNER_STATE,borrow.getBorrowStatus());
        mEditor.apply();
        prnaam.setText("Aangevraagde product: " + borrow.getProductName());
        gebrnaam.setText("Aangevraagd door: " + borrow.getmGebrnaam());
        aantalpr.setText("Aanvraagde aantal: " + borrow.getBorrowItemAmount());
        status.setText("Aanvraag status: " + borrow.getBorrowStatus());
        if (borrow.getBorrowStatus().matches(getString(R.string.productStatusTeLaat))) {
            status.setTextColor(Color.parseColor("#d8041d"));
            productLendButton.setVisibility(View.GONE);
            productLoanDate.setText("Geleend op: "+borrow.getmBorrowDate());
            productRequestDate.setText("Aangevraagd op: "+borrow.getRequestDate());
            productReturnDate.setVisibility(View.GONE);
            productRequestCancelButton.setVisibility(View.GONE);
        }else if (borrow.getBorrowStatus().matches(getString(R.string.productStatusPending))){
            productTakeBackButton.setVisibility(View.GONE);
            ProductIsNotDamaged.setVisibility(View.GONE);
            productLoanDate.setVisibility(View.GONE);
            productRequestDate.setText("Aangevraagd op: "+borrow.getRequestDate());
            productReturnDate.setVisibility(View.GONE);
        }else if(borrow.getBorrowStatus().matches(getString(R.string.productStatusOnLoan))){
            productRequestCancelButton.setVisibility(View.GONE);
            productLendButton.setVisibility(View.GONE);
            productRequestDate.setText("Aangevraagd op: "+borrow.getRequestDate());
            productLoanDate.setText("Geleend op: "+borrow.getmBorrowDate());
            productReturnDate.setVisibility(View.GONE);
        }else{
            ProductIsNotDamaged.setVisibility(View.GONE);
            productTakeBackButton.setVisibility(View.GONE);
            productRequestCancelButton.setVisibility(View.GONE);
            productLendButton.setVisibility(View.GONE);
            productRequestDate.setText("Aangevraagd op: "+borrow.getRequestDate());
               productLoanDate.setText("Geleend op: "+borrow.getmBorrowDate());
             productReturnDate.setText("Teruggebracht op: "+borrow.getReturnDate());
        }

//        if(intent.getStringExtra("productnaam_ProductBorrowlist")!=null && intent.getStringExtra("gebruikernaam_ProductBorrowlist") != null && intent.getIntExtra("aantalaangevr_ProductBorrowlist", -1) != -1){
//            prnaam.setText(intent.getStringExtra("productnaam_ProductBorrowlist"));
//            gebrnaam.setText(intent.getStringExtra("gebruikernaam_ProductBorrowlist"));
//            aantalpr.setText(intent.getIntExtra("aantalaangevr_ProductBorrowlist", -1)+"");
//        }

    }

    public void lendProductButton(View view){
        if(borrow.getBorrowStatus().matches(getString(R.string.productStatusPending))){
            dataManagement.lendProduct( DateUtils.getCurrentDate(), borrow.getBorrowItemAmount(),borrow.getBorrowID()
                    ,borrow.getUserID(),borrow.getmProductID());
            borrow = dataManagement.getBorrowDataWithId(getIntent().getIntExtra("P_id_ProductBorrowList",-1));
            if (borrow.getBorrowStatus().matches(getString(R.string.productStatusOnLoan))){
                Toast.makeText(this, "Het product is met succes uitgeleend", Toast.LENGTH_LONG).show();
                status.setText("Status: " + borrow.getBorrowStatus());
            }else {
                Toast.makeText(this, "Product uitlenen actie is mislukt", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Dit product staat niet op pending, dus u kunt dit product niet uitlenen", Toast.LENGTH_LONG).show();
        }
    }
    public void returnProductButton(View view){
            if (borrow.getBorrowStatus().matches(getString(R.string.productStatusOnLoan)) | borrow.getBorrowStatus().matches(getString(R.string.productStatusTeLaat))){
                if(ProductIsNotDamaged.isChecked()){
                    dataManagement.brokenProductReturned(DateUtils.getCurrentDate(),borrow.getBorrowID(),borrow.getBorrowItemAmount(), borrow.getUserID(),borrow.getmProductID());
                }else{
                    dataManagement.productReturned( DateUtils.getCurrentDate(),borrow.getBorrowID(),borrow.getBorrowItemAmount(), borrow.getUserID(),borrow.getmProductID());
                }
                borrow = dataManagement.getBorrowDataWithId(getIntent().getIntExtra("P_id_ProductBorrowList",-1));
                if(borrow.getBorrowStatus().matches(getString(R.string.productStatusReturned))){
                    status.setTextColor(getResources().getColor(R.color.black));
                    Toast.makeText(this, "Het product is met succes teruggenomen", Toast.LENGTH_LONG).show();
                    status.setText("Status: " + borrow.getBorrowStatus());
                }else{
                    Toast.makeText(this, "Het product terugnemen is mislukt", Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(this, "Dit product is niet in bruikleen, dus kunt u dit product niet terugnemen", Toast.LENGTH_LONG).show();
            }
    }

    public void productCancelButton(View view){
        if (borrow.getBorrowStatus().matches(getString(R.string.productStatusPending))){
            dataManagement.DeleteRequestBorrowItem(borrow.getBorrowID(),borrow.getBorrowItemAmount(),borrow.getmProductID());
            Intent intent = new Intent(this, AangevraagdItems_UserList.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            Toast.makeText(this, "het product is met succes geannuleerd", Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Dit product staat niet op pending, dus u kunt dit product niet annuleeren", Toast.LENGTH_LONG).show();
        }
    }
}