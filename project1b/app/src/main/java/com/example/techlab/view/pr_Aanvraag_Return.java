package com.example.techlab.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.util.DateUtils;

public class pr_Aanvraag_Return extends AppCompatActivity {
    Intent intent;
    TextView prnaam, gebrnaam, aantalpr, status;
    DataManagement dataManagement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pr_aanvraag_return);
        dataManagement = new DataManagement();
        intent = new Intent();
//        intent.getIntExtra("P_id_ProductBorrowlist", -1);
        prnaam = findViewById(R.id.leenavraagProduct);
        gebrnaam = findViewById(R.id.leenavraagUser);
        aantalpr = findViewById(R.id.leenavraagAantal);
        status = findViewById(R.id.productRequestListProductStatus);
        prnaam.setText("Aangevraagde product: "+getIntent().getStringExtra("productnaam_ProductBorrowlist"));
        gebrnaam.setText("Aangevraagd door: "+getIntent().getStringExtra("gebruikernaam_ProductBorrowlist"));
        aantalpr.setText("Aanvraagde aantal: "+getIntent().getIntExtra("aantalaangevr_ProductBorrowlist", -1)+"");
        status.setText("Aanvraag status: "+getIntent().getStringExtra("status_ProductBorrowlist")+"");

//        if(intent.getStringExtra("productnaam_ProductBorrowlist")!=null && intent.getStringExtra("gebruikernaam_ProductBorrowlist") != null && intent.getIntExtra("aantalaangevr_ProductBorrowlist", -1) != -1){
//            prnaam.setText(intent.getStringExtra("productnaam_ProductBorrowlist"));
//            gebrnaam.setText(intent.getStringExtra("gebruikernaam_ProductBorrowlist"));
//            aantalpr.setText(intent.getIntExtra("aantalaangevr_ProductBorrowlist", -1)+"");
//        }


    }

    public void lendProductButton(View view){
        dataManagement.updateBorrowStatus("Geleend", DateUtils.getCurrentDate(), getIntent().getIntExtra("P_id_ProductBorrowlist", -1));
        status.setText("Aanvraag status: Geleend");

    }

    public void returnProductButton(View view){
        dataManagement.DelRequestBorrowItem(getIntent().getIntExtra("P_id_ProductBorrowlist", -1) );

        Intent intent = new Intent(this, AangevraagdItems_UserList.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }







}