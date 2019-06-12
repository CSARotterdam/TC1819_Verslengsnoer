package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.techlab.R;

public class Contact extends DrawerMenu {

    /*
    Gemaakt door Guan
    bron voor image resize: https://developer.android.com/reference/android/widget/ImageView.ScaleType
    bron voor email link klikbaar maken: https://stackoverflow.com/questions/10464954/how-to-make-an-email-address-clickable

    Date used: 1 June 2019
    */
    private SharedPreferences mSharedPreferences;
    ImageView imageView;
    TextView address, postcode, country, floor, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);

        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_contact, null,false);
        frameLayout.addView(activityView);

        imageView = findViewById(R.id.HRLocImage);
        imageView.setImageResource(R.drawable.hr_contact);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        address = findViewById(R.id.Address);
        address.setTextIsSelectable(true);
        address.setText(getResources().getString(R.string.address));

        postcode = findViewById(R.id.Postcode);
        postcode.setTextIsSelectable(true);
        postcode.setText(getResources().getString(R.string.postcode));

        country = findViewById(R.id.Country);
        country.setText(getResources().getString(R.string.country));

        floor = findViewById(R.id.Floor);
        floor.setText(getResources().getString(R.string.floor));

        email = findViewById(R.id.ContactEmail);
        email.setTextIsSelectable(true);
        email.setText(getResources().getString(R.string.ContactEmail));


    }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        finish();
        if (mSharedPreferences.getString(MainActivity.KEY_ACTIVE_USER_STATUS, "").length() > 0 |mSharedPreferences.getString(MainActivity.KEY_ACTIVE_USER_STATUS, "").matches("loggedOut")) {
            startActivity(new Intent(this, Product_InventoryActivity.class));
        }else{
            startActivity(new Intent(this, MainActivity.class));
        }
    }

}
