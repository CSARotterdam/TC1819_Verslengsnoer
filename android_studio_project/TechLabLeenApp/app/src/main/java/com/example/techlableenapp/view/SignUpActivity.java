package com.example.techlableenapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.techlableenapp.R;
import com.example.techlableenapp.model.Users;
import com.example.techlableenapp.db.UserDataSourse;

public class SignUpActivity extends AppCompatActivity {
    EditText userFirstNameInput;
    EditText userSurnameInput;
    EditText schoolEmailInput;
    EditText passwordInput;
    UserDataSourse userDataSourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userFirstNameInput = (EditText) findViewById(R.id.firstNameInput);
        userSurnameInput = (EditText) findViewById(R.id.surnameInput);
        schoolEmailInput = (EditText) findViewById(R.id.schoolEmailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        userDataSourse = new UserDataSourse(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        userDataSourse.open();

    }
    @Override
    protected void onPause(){
        super.onPause();
        userDataSourse.close();
    }

    public void signUpButtonClicked(View view){
        // creating new user instance
        Users user = new Users(
                userFirstNameInput.getText().toString(),
                userSurnameInput.getText().toString(),
                schoolEmailInput.getText().toString(),
                passwordInput.getText().toString());
                // insert new user
                userDataSourse.insertUser(user);
                // reset form input text field
                userFirstNameInput.setText("");
                userSurnameInput.setText("");
                schoolEmailInput.setText("");
                passwordInput.setText("");
    }




}
