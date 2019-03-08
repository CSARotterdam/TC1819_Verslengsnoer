package com.example.techlableenapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {
    EditText userFirstNameInput;
    EditText userSurnameInput;
    EditText schoolEmailInput;
    EditText passwordInput;
    Button signUpButton;
    TextView databaseText;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userFirstNameInput = (EditText) findViewById(R.id.firstNameInput);
        userSurnameInput = (EditText) findViewById(R.id.surnameInput);
        schoolEmailInput = (EditText) findViewById(R.id.schoolEmailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        databaseHelper = new DatabaseHelper(this, null, null, 1);

    }
    public void signUpButtonClicked(View view){
        Users users = new Users(userFirstNameInput.getText().toString(),
                userSurnameInput.getText().toString(),
                schoolEmailInput.getText().toString(),
                passwordInput.getText().toString());
                databaseHelper.addUsers(users);
                userFirstNameInput.setText("");
                userSurnameInput.setText("");
                schoolEmailInput.setText("");
                passwordInput.setText("");


    }




}
