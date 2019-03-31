package com.example.techlab.view;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.techlab.R;
import com.example.techlab.db.DataSource;
import com.example.techlab.db.imageConverter;

import java.util.ArrayList;

public class Des_Arduino extends AppCompatActivity {
    private static final String TAG = "Des_Arduino";
    DataSource dataSource;
    private ArrayList<Bitmap> mbitmaps = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des_arduino);
        dataSource = new DataSource(this);
        Log.d(TAG, "OnCreate: started.");
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
        Intent startNewActivity = new Intent(this, Inventaris.class);
        startActivity(startNewActivity);
    }
}
