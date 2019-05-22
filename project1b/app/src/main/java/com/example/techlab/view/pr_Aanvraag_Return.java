package com.example.techlab.view;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Users;
import com.example.techlab.util.DateUtils;

public class pr_Aanvraag_Return extends AppCompatActivity {
    Intent intent;
    TextView prnaam, gebrnaam, aantalpr, status;
    DataManagement dataManagement;
    CheckBox ProductIsNotDamaged;
    SharedPreferences mSharedPreferences;


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
        ProductIsNotDamaged = findViewById(R.id.ProductIsNotDamagedCheckBox);
        prnaam.setText("Aangevraagde product: "+getIntent().getStringExtra("productnaam_ProductBorrowlist"));
        gebrnaam.setText("Aangevraagd door: "+getIntent().getStringExtra("gebruikernaam_ProductBorrowlist"));
        aantalpr.setText("Aanvraagde aantal: "+getIntent().getIntExtra("aantalaangevr_ProductBorrowlist", -1)+"");
        status.setText("Aanvraag status: "+getIntent().getStringExtra("status_ProductBorrowlist")+"");
        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);


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
////        int currentuserID = mSharedPreferences.getInt(MainActivity.PREFERENCE_USERID,-1);
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
        dataManagement.updateBorrowStatus("Geleend", DateUtils.getCurrentDate(), getIntent().getIntExtra("P_id_ProductBorrowlist", -1));
        Toast.makeText(this, "Het product is met succes uitgeleend", Toast.LENGTH_LONG).show();
        status.setText("Aanvraag status: Geleend");

    }

    public void returnProductButton(View view){
        if(ProductIsNotDamaged.isChecked()){
            dataManagement.DelRequestBorrowItem(getIntent().getIntExtra("P_id_ProductBorrowlist", -1) );
            Intent intent = new Intent(this, AangevraagdItems_UserList.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            Toast.makeText(this, "het product is met succes teruggenomen", Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        }
    }

    public void annuleerbButton(View view){
        dataManagement.updateBorrowStatus("Pending", DateUtils.getCurrentDate(), getIntent().getIntExtra("P_id_ProductBorrowlist", -1));
        status.setText("Aanvraag status: Pending");
    }

    public void AanvraagAccepteren(View view){

    }
}