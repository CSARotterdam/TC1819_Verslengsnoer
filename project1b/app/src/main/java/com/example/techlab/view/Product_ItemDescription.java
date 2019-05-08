package com.example.techlab.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.db.imageConverter;
import com.example.techlab.model.Electronics;

import java.util.ArrayList;

public class Product_ItemDescription extends AppCompatActivity {
    private static final String TAG = "Product_ItemDescription";
    private Button Button_Request2Borrow;
    private Button VoorwaardenBtn;
    private ArrayList<Bitmap> mbitmaps = new ArrayList<>();
    private ArrayList<Integer> mSelectedItems = new ArrayList<>();
    private Integer productID;
    DataManagement dataManagement;
    private SharedPreferences mSharedPreferences;

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
        if (getIntent().hasExtra("image") && getIntent().hasExtra("product_name")&& getIntent().hasExtra("product_description") && getIntent().hasExtra("id")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");


            String productName = getIntent().getStringExtra("product_name");
            String productDescription = getIntent().getStringExtra("product_description");
            productID = getIntent().getIntExtra("id",-1);

            int imageInt = Integer.parseInt(getIntent().getStringExtra("image"));

            setImage(imageInt, productName, productDescription);

        }
    }
    private void setImage(int imageInt, String productName, String  productDescription){
        Log.d(TAG, "setImage: setting the image and name to widgets.");


        ArrayList<Electronics> products = dataManagement.getAllProductData();
        for (int i =0; products.size() >i ; i++) {
            mbitmaps.add(imageConverter.getImage(dataManagement.getImage(products.get(i).getId_())));
        }

        TextView name = findViewById(R.id.product_name);
        name.setText(productName);

        TextView description = findViewById(R.id.product_description);
        description.setText(productDescription);

        ImageView image = findViewById(R.id.image);
        image.setImageBitmap(mbitmaps.get(imageInt));
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

    public void Buttons(){
        Button_Request2Borrow = findViewById(R.id.requestBtn);
        Button_Request2Borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder RequestItemAlertDialog = new AlertDialog.Builder(Product_ItemDescription.this);
                RequestItemAlertDialog.setTitle("Aanvraag voor lenen")
//                        .setMessage("Gaat u hiermee akkoord met de voorwaarden?")
                        .setMultiChoiceItems(R.array.AgreeToS, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked){
                                    mSelectedItems.add(which);

                                }
                                else if (mSelectedItems.contains(which)){
                                    mSelectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                        .setCancelable(false)
                        .setPositiveButton("Doorgaan", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (mSelectedItems.size() > 0){
                                Toast.makeText(Product_ItemDescription.this,"Aanvraag verstuurd.",Toast.LENGTH_LONG).show();

                                //DB code here
                                dataManagement.InsertRequestBorrowItem(productID, mSharedPreferences.getInt(MainActivity.PREFERENCE_USERID, 0), 1, "Pending");
                                Intent BorrowActivity = new Intent(getApplicationContext(), Student_BorrowedActivity.class);
                                startActivity(BorrowActivity);}
                                else {
                                    Toast.makeText(Product_ItemDescription.this, "Accepteer de voorwaarden eerst.", Toast.LENGTH_LONG).show();
                                }

                            }
                        })
                        .setNeutralButton("Annuleren", null);

                //Creating dialog box
                AlertDialog dialog  = RequestItemAlertDialog.create();
                dialog.show();
            }
        });

        VoorwaardenBtn = findViewById(R.id.VoorwaardenBtn);
        VoorwaardenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Voorwaarden = new Intent(Product_ItemDescription.this, Product_Voorwaarden.class);
                startActivity(Voorwaarden);
//                String url = "https://hr.nl/";
//                Intent voorwaardenLink = new Intent(Intent.ACTION_VIEW);
//                voorwaardenLink.setData(Uri.parse(url));
//                startActivity(voorwaardenLink);
            }
        });
    }
}
