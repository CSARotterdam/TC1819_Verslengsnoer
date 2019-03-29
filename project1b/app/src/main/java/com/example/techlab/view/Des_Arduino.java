package com.example.techlab.view;

import android.content.DialogInterface;
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

import com.bumptech.glide.Glide;
import com.example.techlab.R;

public class Des_Arduino extends AppCompatActivity {
    private static final String TAG = "Des_Arduino";
    private Button Button_Request2Borrow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des_arduino);

        getIncomingIntent();
        Buttons();
    }

    // checks for incoming intent
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        // checks if there is a intent
        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("product_name")&& getIntent().hasExtra("product_description")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String imageUrl = getIntent().getStringExtra("image_url");
            String productName = getIntent().getStringExtra("product_name");
            String productDescription = getIntent().getStringExtra("product_description");

            setImage(imageUrl, productName, productDescription);
        }
    }
    private void setImage(String imageUrl, String productName, String  productDescription){
        Log.d(TAG, "setImage: setting the image and name to widgets.");

        TextView name = findViewById(R.id.product_name);
        name.setText(productName);

        TextView description = findViewById(R.id.product_description);
        description.setText(productDescription);

        ImageView image = findViewById(R.id.image);
        Glide.with(this).asBitmap().load(imageUrl).into(image);
    }

    public void Buttons(){
        Button_Request2Borrow = findViewById(R.id.requestBtn);
        Button_Request2Borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder RequestItemAlertDialog = new AlertDialog.Builder(Des_Arduino.this);
                RequestItemAlertDialog.setTitle("Aanvraag voor lenen")
                        .setMessage("Gaat u hiermee akkoord met de voorwaarden?")
                        .setCancelable(true)
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Des_Arduino.this,"Aanvraag verstuurd.",Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog dialog  = RequestItemAlertDialog.create();
                dialog.show();
            }
        });
    }
}
