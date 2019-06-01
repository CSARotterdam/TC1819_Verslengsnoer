package com.example.techlab.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.techlab.R;

public class Contact extends AppCompatActivity {

    /*
    Gemaakt door Guan
    bron voor image resize: https://developer.android.com/reference/android/widget/ImageView.ScaleType
    bron voor email link klikbaar maken: https://stackoverflow.com/questions/10464954/how-to-make-an-email-address-clickable

    Date used: 1 June 2019
    */

    ImageView imageView;
    TextView address, postcode, country, floor, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

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

}
