package com.example.techlab.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.techlab.R;
import com.example.techlab.db.DataSource;
import com.example.techlab.model.Users;


public class InLogActivity extends AppCompatActivity {


    DataSource dataSource;
    EditText loginEmailInput;
    EditText LoginPasswordInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataSource = new DataSource(this);
        loginEmailInput = (EditText) findViewById(R.id.loginEmailInput);
        LoginPasswordInput = (EditText) findViewById(R.id.loginPasswordInput);




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

    public void signUpPageButton(View view){
        Intent startNewActivity = new Intent(this, SignUpActivity.class);
        startActivity(startNewActivity);
    }

    public void loginButton(View view){
        if (dataSource.ifExists(loginEmailInput.getText().toString(),LoginPasswordInput.getText().toString())) {
            Intent startNewActivity = new Intent(this, RepositoryActivity.class);
            Users users =dataSource.getUser(loginEmailInput.getText().toString());
            startNewActivity.putExtra("activeUser",users);
            LoginPasswordInput.setText("");
            loginEmailInput.setText("");
            startActivity(startNewActivity);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent startNewActivity = new Intent(this, InLogActivity.class);
        startActivity(startNewActivity);
    }
}
