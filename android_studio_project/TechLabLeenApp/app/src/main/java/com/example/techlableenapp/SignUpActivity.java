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
    EditText deleteUser;
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
        deleteUser = (EditText) findViewById(R.id.deleteUser);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        databaseText = (TextView) findViewById(R.id.datadaseText);
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

    public void deleteUser(View view){
        String userEmail = deleteUser.getText().toString();
        databaseHelper.deleteUsers(userEmail);

    }

    public void printDatabase(){
        String dbString = databaseHelper.databaseToString();
        databaseText.setText(dbString);
        userFirstNameInput.setText("");
        userSurnameInput.setText("");
        schoolEmailInput.setText("");
        passwordInput.setText("");
    }


}
