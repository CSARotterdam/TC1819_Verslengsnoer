package com.example.techlab.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.techlab.R;

public class Des_Arduino extends AppCompatActivity {
    private static final String TAG = "Des_Arduino";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des_arduino);
        Log.d(TAG, "OnCreate: started.");

        getIncomingIntent();
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
}
