package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Borrow;
import com.example.techlab.util.DateUtils;

public class Geleend_Aangevraagd extends AppCompatActivity {
    Intent intent;
    TextView prnaam, gebrnaam, aantalpr, status;
    DataManagement dataManagement;
    CheckBox ProductIsNotDamaged;
    Borrow borrow;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pr_aanvraag_return);
        System.out.println("Aanvraag Returned.java started");
        dataManagement = new DataManagement();
        intent = new Intent();
//        intent.getIntExtra("P_id_ProductBorrowlist", -1);
        prnaam = findViewById(R.id.leenavraagProduct);
        gebrnaam = findViewById(R.id.leenavraagUser);
        aantalpr = findViewById(R.id.leenavraagAantal);
        status = findViewById(R.id.productRequestListProductStatus);
        ProductIsNotDamaged = findViewById(R.id.ProductIsNotDamagedCheckBox);
        borrow = dataManagement.getBorrowDataWithId(getIntent().getIntExtra("P_id_ProductBorrowList",-1));
        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putString(MainActivity.KEY_PRODUCT_ADMINISTER_SPINNER_STATE,borrow.getBorrowStatus());
        mEditor.apply();
        prnaam.setText("Aangevraagde product: " + borrow.getProductName());
        gebrnaam.setText("Aangevraagd door: " + borrow.getmGebrnaam());
        aantalpr.setText("Aanvraagde aantal: " + borrow.getBorrowItemAmount());
        status.setText("Aanvraag status: " + borrow.getBorrowStatus());


        String s = mSharedPreferences.getString(MainActivity.KEY_PRODUCT_ADMINISTER_SPINNER_STATE, "sdfsdf");


//        Button LeenKnop = findViewById(R.id.button3);
//        LeenKnop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addNotification();
//            }
//        });


//        if(intent.getStringExtra("productnaam_ProductBorrowlist")!=null && intent.getStringExtra("gebruikernaam_ProductBorrowlist") != null && intent.getIntExtra("aantalaangevr_ProductBorrowlist", -1) != -1){
//            prnaam.setText(intent.getStringExtra("productnaam_ProductBorrowlist"));
//            gebrnaam.setText(intent.getStringExtra("gebruikernaam_ProductBorrowlist"));
//            aantalpr.setText(intent.getIntExtra("aantalaangevr_ProductBorrowlist", -1)+"");
//        }

    }
////    https://www.youtube.com/watch?reload=9&v=ATERxKKORbY
////    This method creates a Notification that shows
//    private void addNotification(){
////        int currentuserID = mSharedPreferences.getInt(MainActivity.KEY_ACTIVE_USER_ID,-1);
//        int userID = getIntent().getIntExtra("UserID", -1);
////
////        if (currentuserID==userID){
////        }
//
////        Here we build the notification
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.logo_round)
//                .setContentTitle("Leenaanvraag")
//                .setContentText("U mag het product ophalen");
////  When you click on the intent you go to Student_BorrowedActivity.class
//        Intent notification = new Intent(this,Student_BorrowedActivity.class);
//        PendingIntent pending = PendingIntent.getActivity(this, 0,notification, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(pending);
//
////        Notify the system that there is a notification
//
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(userID,builder.build());
//    }

    public void lendProductButton(View view){
        if(borrow.getBorrowStatus().matches(getString(R.string.productStatusPending))){
            dataManagement.lendProduct( DateUtils.getCurrentDate(), borrow.getBorrowItemAmount(),borrow.getBorrowID()
                    ,borrow.getUserID(),borrow.getmProductID());
            borrow = dataManagement.getBorrowDataWithId(getIntent().getIntExtra("P_id_ProductBorrowList",-1));
            if (borrow.getBorrowStatus().matches(getString(R.string.productStatusOnLoan))){
                Toast.makeText(this, "Het product is met succes uitgeleend", Toast.LENGTH_LONG).show();
                status.setText("Aanvraag status: " + borrow.getBorrowStatus());
            }else {
                Toast.makeText(this, "het product uitgelenen is mislukt", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Dit product is staat niet op pending, dus u kunt dit product niet uitlenen", Toast.LENGTH_LONG).show();
        }
    }
    public void returnProductButton(View view){
        if(ProductIsNotDamaged.isChecked()){
            if (borrow.getBorrowStatus().matches(getString(R.string.productStatusOnLoan)) | borrow.getBorrowStatus().matches(getString(R.string.productStatusTeLaat))){
                dataManagement.productReturned( DateUtils.getCurrentDate(),borrow.getBorrowID(),borrow.getBorrowItemAmount(), borrow.getUserID(),borrow.getmProductID());
                borrow = dataManagement.getBorrowDataWithId(getIntent().getIntExtra("P_id_ProductBorrowList",-1));
                if(borrow.getBorrowStatus().matches(getString(R.string.productStatusReturned))){
                    Toast.makeText(this, "Het product is met succes teruggenomen", Toast.LENGTH_LONG).show();
                    status.setText("Aanvraag status: " + borrow.getBorrowStatus());
                }else{
                    Toast.makeText(this, "Het product terugnemen is mislukt", Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(this, "Dit product is niet in bruikleen, dus kunt u dit product niet terugnemen", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Check box is niet aangevinkt", Toast.LENGTH_LONG).show();
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
            Toast.makeText(this, "Dit product is staat niet op pending, dus u kunt dit product niet annuleeren", Toast.LENGTH_LONG).show();
        }
    }
    public void AanvraagAccepteren(View view){
    }

//    @Override
//    public void onBackPressed() {
//        Intent startNewActivity = new Intent(this, AangevraagdItems_UserList.class);
//        startNewActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(startNewActivity);
//        finish();
//    }
}