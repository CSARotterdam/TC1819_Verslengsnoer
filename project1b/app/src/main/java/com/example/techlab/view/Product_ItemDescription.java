package com.example.techlab.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.techlab.R;
import com.example.techlab.db.DataSource;
import com.example.techlab.db.imageConverter;

import java.util.ArrayList;

public class Product_ItemDescription extends AppCompatActivity {
    private static final String TAG = "Product_ItemDescription";
    private Button Button_Request2Borrow;
    DataSource dataSource;
    private ArrayList<Bitmap> mbitmaps = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des_arduino);
        Buttons();
        dataSource = new DataSource(this);
    }

    // checks for incoming intent
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        // checks if there is a intent
        if (getIntent().hasExtra("image") && getIntent().hasExtra("product_name")&& getIntent().hasExtra("product_description")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");


            String productName = getIntent().getStringExtra("product_name");
            String productDescription = getIntent().getStringExtra("product_description");

            int imageInt = Integer.parseInt(getIntent().getStringExtra("image"));


            setImage(imageInt, productName, productDescription);

        }
    }
    private void setImage(int imageInt, String productName, String  productDescription){
        Log.d(TAG, "setImage: setting the image and name to widgets.");

        Cursor cursor = dataSource.selectAllproduct();
        cursor.moveToFirst();
        for (int i = cursor.getCount(); i > 0; i--) {
            mbitmaps.add(imageConverter.getImage(cursor.getBlob(7)));
            if (i>1){
                cursor.moveToNext();
            }
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
        dataSource.open();
        getIncomingIntent();

    }
    @Override
    protected void onPause(){
        super.onPause();
        dataSource.close();
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
                        .setMessage("Gaat u hiermee akkoord met de voorwaarden?")
                        .setCancelable(true)
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Product_ItemDescription.this,"Aanvraag verstuurd.",Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog dialog  = RequestItemAlertDialog.create();
                dialog.show();
            }
        });
    }
}
