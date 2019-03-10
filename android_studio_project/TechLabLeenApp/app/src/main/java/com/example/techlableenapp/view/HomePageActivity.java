package com.example.techlableenapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.techlableenapp.R;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void UserManagementActivityButton(View view){
        Intent startNewActivity = new Intent(this, UserManagementActivity.class);
        startActivity(startNewActivity);
    }
}
