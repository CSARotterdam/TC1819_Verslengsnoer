package com.example.techlab.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.model.Users;

public class HomePageActivity extends AppCompatActivity {
    TextView username;
    TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Intent intent = getIntent();
        Users activeUser = intent.getParcelableExtra("activeUser");
        username = (TextView) findViewById(R.id.userNameHomePage);
        welcome = (TextView) findViewById(R.id.welcomeTextViewHomePage);
        username.setText(activeUser.getFirstName()+" :) How is your day?");
    }


}
