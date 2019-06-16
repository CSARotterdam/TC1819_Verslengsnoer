package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.util.CheckBlockUtils;

public class Product_Voorwaarden extends AppCompatActivity{
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_voorwaarden);

        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);

        // https://www.myandroidsolutions.com/2017/09/29/android-html-textview/#.XQX834gzaUk
        // This makes an html textview
        TextView voorwaarden = findViewById(R.id.Voorwaarden);
        voorwaarden.setText(Html.fromHtml(getString(R.string.gebruikersvoorwaarden)));
    }

    @Override
    protected void onResume(){
        if (mSharedPreferences.getString(MainActivity.KEY_ACTIVE_USER_EMAIL, "").length() > 0) {
            CheckBlockUtils.ExecuteCheckBlock(this, mSharedPreferences.getString(MainActivity.KEY_ACTIVE_USER_EMAIL,""),"Voorwaarden");
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if((mSharedPreferences.getString(MainActivity.KEY_ACTIVE_USER_EMAIL,"").length()>0)){
            super.onBackPressed();
        }
        else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
