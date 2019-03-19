package com.example.techlableenapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.techlableenapp.R;
import com.example.techlableenapp.model.Users;
import com.example.techlableenapp.db.DataSource;

public class SignUpActivity extends AppCompatActivity {
    EditText userFirstNameInput;
    EditText userSurnameInput;
    EditText schoolEmailInput;
    EditText passwordInput;
    DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userFirstNameInput = (EditText) findViewById(R.id.firstNameInput);
        userSurnameInput = (EditText) findViewById(R.id.surnameInput);
        schoolEmailInput = (EditText) findViewById(R.id.schoolEmailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        dataSource = new DataSource(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        dataSource.open();

    }
    @Override
    protected void onPause(){
        super.onPause();
        dataSource.close();
    }

    public void signUpButtonClicked(View view){
        // creating new user instance
        Users user = new Users(
                userFirstNameInput.getText().toString(),
                userSurnameInput.getText().toString(),
                schoolEmailInput.getText().toString(),
                passwordInput.getText().toString()
                ,0,
                "student");
                // insert new user
                dataSource.insertUser(user);
                // reset form input text field
                userFirstNameInput.setText("");
                userSurnameInput.setText("");
                schoolEmailInput.setText("");
                passwordInput.setText("");
    }




}
