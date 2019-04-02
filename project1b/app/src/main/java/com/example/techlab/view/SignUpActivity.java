package com.example.techlab.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.techlab.R;
import com.example.techlab.model.Users;
import com.example.techlab.db.DataSource;

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
        userFirstNameInput = findViewById(R.id.firstNameInput);
        userSurnameInput = findViewById(R.id.surnameInput);
        schoolEmailInput = findViewById(R.id.schoolEmailInput);
        passwordInput = findViewById(R.id.passwordInput);
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
                passwordInput.getText().toString(),
                0,
                "student");
        // insert new user
        dataSource.insertUser(user);
        // reset form input text field
        userFirstNameInput.setText("");
        userSurnameInput.setText("");
        schoolEmailInput.setText("");
        passwordInput.setText("");
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }




}
