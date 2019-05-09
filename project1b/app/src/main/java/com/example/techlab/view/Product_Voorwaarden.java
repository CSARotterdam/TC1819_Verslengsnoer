package com.example.techlab.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.techlab.R;

public class Product_Voorwaarden extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_voorwaarden);

//        This makes an html textview
        TextView voorwaarden = (TextView) findViewById(R.id.Voorwaarden);
        voorwaarden.setText(Html.fromHtml(getString(R.string.gebruikersvoorwaarden)));
    }
}
