package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.techlab.R;
import com.example.techlab.db.DataSource;
import com.example.techlab.model.Users;


public class MainActivity extends AppCompatActivity {
    private static final String PREFERENCES_FILE = "com.example.techlab.preferences";
    private static final String KEY_ACTIVE_USER = "keyActiveUser";
    private static final String KEY_ACTIVE_USER_PASS = "keyActiveUserPass";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    DataSource dataSource;
    EditText loginEmailInput;
    EditText loginPasswordInput;
    CheckBox stayLoggedInCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataSource = new DataSource(this);
        loginEmailInput = (EditText) findViewById(R.id.loginEmailInput);
        loginPasswordInput = (EditText) findViewById(R.id.loginPasswordInput);
        mSharedPreferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        stayLoggedInCheckBox = findViewById(R.id.stayLoggedInCheckBox);

        ImageView logo = (ImageView) findViewById(R.id.TechLabLogo);
        int ImageResource = getResources().getIdentifier("@drawable/logo", null, this.getPackageName());
        logo.setImageResource(ImageResource);


    }

    @Override
    protected void onResume(){
        super.onResume();
        dataSource.open();
        if (dataSource.ifExists(mSharedPreferences.getString(KEY_ACTIVE_USER, ""),mSharedPreferences.getString(KEY_ACTIVE_USER_PASS,""))) {
            Intent intent = new Intent(this,MenuActivity.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        dataSource.close();
        loginPasswordInput.setText("");
        loginEmailInput.setText("");
        mEditor.apply();
    }


    public void signUpPageButton(View view){
        Intent startNewActivity = new Intent(this, SignUpActivity.class);
        startActivity(startNewActivity);
    }

    public void loginButton(View view){
        if (dataSource.ifExists(loginEmailInput.getText().toString(),loginPasswordInput.getText().toString())) {
            Intent startNewActivity = new Intent(getBaseContext(), MenuActivity.class);
            Users users =dataSource.getUser(loginEmailInput.getText().toString());
            if (stayLoggedInCheckBox.isChecked()){
                mEditor.putString(KEY_ACTIVE_USER, loginEmailInput.getText().toString());
                mEditor.putString(KEY_ACTIVE_USER_PASS, loginPasswordInput.getText().toString());
            }
            startActivity(startNewActivity);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);
    }
}
