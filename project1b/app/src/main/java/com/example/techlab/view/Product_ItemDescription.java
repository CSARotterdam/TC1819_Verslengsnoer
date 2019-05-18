package com.example.techlab.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.util.DateUtils;
import com.example.techlab.util.ImageUtils;
import com.example.techlab.model.Products;
import com.ms.square.android.expandabletextview.ExpandableTextView;

//import java.util.ArrayList; ////Belongs to the code of Guan Version 1

public class Product_ItemDescription extends AppCompatActivity {
    private static final String TAG = "Product_ItemDescription";
    private Button Button_Request2Borrow;
//    private Button VoorwaardenBtn; //Belongs to Guan Version 1
//    private ArrayList<Integer> mSelectedItems = new ArrayList<>(); //Belongs to Guan Version 1
    private Integer productID;
    DataManagement dataManagement;
    private SharedPreferences mSharedPreferences;
    String objectType;
    Products product;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate: started.");
        setContentView(R.layout.activity_item_description);
        dataManagement =  new DataManagement();
        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);
        Buttons();
    }

    // checks for incoming intent
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        // checks if there is a intent
        if (getIntent().hasExtra("object_type") && getIntent().hasExtra("id")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");
            objectType = getIntent().getStringExtra("object_type");
            productID = getIntent().getIntExtra("id",-1);
            if (objectType.matches("book")){
                product = dataManagement.getBookWithId(productID);
            }if(objectType.matches("electronic")){
                product = dataManagement.getProductData(productID);
            }
            pageContentFill(product.getName(), product.getDescription(),product.getImage());

        }
    }
    private void pageContentFill(String productName, String productDescription, byte[] imageByte ){
        Log.d(TAG, "pageContentFill: setting the image and name to widgets.");

        TextView name = findViewById(R.id.product_name);
        name.setText(productName);

        ExpandableTextView description = findViewById(R.id.expand_text_view);
        description.setText(productDescription);

        ImageView image = findViewById(R.id.image);
        image.setImageBitmap(ImageUtils.getImage(imageByte));
    }
    @Override
    protected void onResume(){
        super.onResume();
        getIncomingIntent();
    }
    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent startNewActivity = new Intent(this, Product_InventoryActivity.class);
        startActivity(startNewActivity);
    }

    //all Buttons
    public void Buttons(){
        Button_Request2Borrow = findViewById(R.id.requestBtn);
        Button_Request2Borrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guan Popup Version 2 to match all screen.
                LayoutInflater li = LayoutInflater.from(Product_ItemDescription.this);
                View contentVoorwaarden = li.inflate(R.layout.custom_borrow_alertdialog, null);
                AlertDialog.Builder RequestItemAlertDialog = new AlertDialog.Builder(Product_ItemDescription.this);
                RequestItemAlertDialog.setView(contentVoorwaarden);
                RequestItemAlertDialog.setTitle("Aanvraag voor lenen")
                        .setNeutralButton("Annuleer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick( DialogInterface dialog, int which){
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Akkoord", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Product_ItemDescription.this, "Aanvraag verstuurd.", Toast.LENGTH_LONG).show();
                                //DB code here
                                dataManagement.InsertRequestBorrowItem(productID, mSharedPreferences.getInt(MainActivity.PREFERENCE_USERID, 0), 1, "Pending", objectType, DateUtils.getCurrentDate());
                                Intent BorrowActivity = new Intent(Product_ItemDescription.this, Student_BorrowedActivity.class);
                                startActivity(BorrowActivity);
                            }
                        })
                        .setCancelable(false);

                TextView textmsg = contentVoorwaarden.findViewById(R.id.AlertDialogText);
                textmsg.setText("Ga akkoord met de voorwaarden als je dit product wilt lenen.");

                Button voorwaardenBtn = contentVoorwaarden.findViewById(R.id.AlertDialogButtonVoorwaarden);

                voorwaardenBtn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent Voorwaarden = new Intent(Product_ItemDescription.this, Product_Voorwaarden.class);
                        startActivity(Voorwaarden);
                    }
                });

                //Creating dialog box
                AlertDialog dialog  = RequestItemAlertDialog.create();
                dialog.show();
            }

//Saloua version
//        Button_Request2Borrow.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder RequestItemAlertDialog = new AlertDialog.Builder(Product_ItemDescription.this);
//                RequestItemAlertDialog.setTitle("Aanvraag voor lenen")
//                .setMessage("Ga akkoord met de voorwaarden als je dit product wilt lenen.")
//                .setCancelable(false)
//                .setNeutralButton("Annuleer",null)
//                .setNegativeButton("Voorwaarden", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent Voorwaarden = new Intent(Product_ItemDescription.this, Product_Voorwaarden.class);
//                        startActivity(Voorwaarden);
//                    }
//                })
//                .setPositiveButton("Akkoord", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(Product_ItemDescription.this,"Aanvraag verstuurd.",Toast.LENGTH_LONG).show();
//                        //DB code here
//                        dataManagement.InsertRequestBorrowItem(productID, mSharedPreferences.getInt(MainActivity.PREFERENCE_USERID, 0), 1, "Pending",objectType);
//                        Intent BorrowActivity = new Intent(getApplicationContext(), Student_BorrowedActivity.class);
//                        startActivity(BorrowActivity);
//                    }
//                });
//
//                //Creating dialog box
//                AlertDialog dialog  = RequestItemAlertDialog.create();
//                dialog.show();
//                dialog.getWindow().setLayout(1100, 600);
//            }}
//        )
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Guan version1
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder RequestItemAlertDialog = new AlertDialog.Builder(Product_ItemDescription.this);
//                RequestItemAlertDialog.setTitle("Aanvraag voor lenen")
//                        .setMessage("Gaat u hiermee akkoord met de voorwaarden?")
//                        .setMultiChoiceItems(R.array.AgreeToS, null, new DialogInterface.OnMultiChoiceClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                                if (isChecked){
//                                    mSelectedItems.add(which);
//
//                                }
//                                else if (mSelectedItems.contains(which)){
//                                    mSelectedItems.remove(Integer.valueOf(which));
//                                }
//                            }
//                        })
//                        .setCancelable(false)
//                        .setPositiveButton("Doorgaan", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                if (mSelectedItems.size() > 0){
//                                Toast.makeText(Product_ItemDescription.this,"Aanvraag verstuurd.",Toast.LENGTH_LONG).show();
//
//                                //DB code here
//                                dataManagement.InsertRequestBorrowItem(productID, mSharedPreferences.getInt(MainActivity.PREFERENCE_USERID, 0), 1, "Pending",objectType);
//                                Intent BorrowActivity = new Intent(getApplicationContext(), Student_BorrowedActivity.class);
//                                startActivity(BorrowActivity);}
//                                else {
//                                    Toast.makeText(Product_ItemDescription.this, "Accepteer de voorwaarden eerst.", Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        })
//                        .setNeutralButton("Annuleren", null);
//
//                //Creating dialog box
//                AlertDialog dialog  = RequestItemAlertDialog.create();
//                dialog.show();
//            }
        });

//        VoorwaardenBtn = findViewById(R.id.VoorwaardenBtn);
//        VoorwaardenBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent Voorwaarden = new Intent(Product_ItemDescription.this, Product_Voorwaarden.class);
//                startActivity(Voorwaarden);
//            }
//        });
    }
}
