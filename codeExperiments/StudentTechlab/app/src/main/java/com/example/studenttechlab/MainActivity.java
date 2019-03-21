package com.example.studenttechlab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");
        ImageView logo = (ImageView) findViewById(R.id.TechlabIconImg);

        int ImageResource = getResources().getIdentifier("@drawable/logo", null, this.getPackageName());
        logo.setImageResource(ImageResource);
    }
}
