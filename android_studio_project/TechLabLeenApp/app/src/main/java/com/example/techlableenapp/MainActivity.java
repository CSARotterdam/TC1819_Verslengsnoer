package com.example.techlableenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {


    DatabaseHelper databaseHelper;
    EditText loginEmailInput;
    EditText LoginPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this, null, null, 1);
        loginEmailInput = (EditText) findViewById(R.id.loginEmailInput);
        LoginPasswordInput = (EditText) findViewById(R.id.loginPasswordInput);

    }

    public void signUpPageButton(View view){
        Intent startNewActivity = new Intent(this,SignUpActivity.class);
        startActivity(startNewActivity);
    }

    public void loginButton(View view){
        if (databaseHelper.ifExists(loginEmailInput.getText().toString(),LoginPasswordInput.getText().toString())) {
            LoginPasswordInput.setText("");
            loginEmailInput.setText("");
            Intent startNewActivity = new Intent(this, HomePageActivity.class);
            startActivity(startNewActivity);
        }
    }






}
