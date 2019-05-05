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
import com.example.techlab.db.DataManagement;


public class MainActivity extends AppCompatActivity {
    protected static final String PREFERENCE_USERID = "CurrentUserID";
    protected static final String PREFERENCES_FILE = "com.example.techlab.preferences";
    protected static final String KEY_ACTIVE_USER_EMAIL = "keyActiveUser";
    protected static final String KEY_ACTIVE_USER_PASS = "keyActiveUserPass";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    EditText loginEmailInput;
    EditText loginPasswordInput;
    CheckBox stayLoggedInCheckBox;
    DataManagement dataManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginEmailInput = (EditText) findViewById(R.id.loginEmailInput);
        loginPasswordInput = (EditText) findViewById(R.id.loginPasswordInput);
        mSharedPreferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        stayLoggedInCheckBox = findViewById(R.id.stayLoggedInCheckBox);

        dataManagement = new DataManagement();

        ImageView logo = (ImageView) findViewById(R.id.TechLabLogo);
        int ImageResource = getResources().getIdentifier("@drawable/logo", null, this.getPackageName());
        logo.setImageResource(ImageResource);
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (dataManagement.ifExists(mSharedPreferences.getString(KEY_ACTIVE_USER_EMAIL, ""),mSharedPreferences.getString(KEY_ACTIVE_USER_PASS,""))) {
            Intent intent = new Intent(this,Product_InventoryActivity.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        loginPasswordInput.setText("");
        loginEmailInput.setText("");
        mEditor.apply();
    }


    public void signUpPageButton(View view){
        Intent startNewActivity = new Intent(this, SignUpActivity.class);
        startActivity(startNewActivity);
    }

    public void loginButton(View view){
        if (dataManagement.ifExists(loginEmailInput.getText().toString(),loginPasswordInput.getText().toString())) {
            Intent startNewActivity = new Intent(getBaseContext(), Product_InventoryActivity.class);
            mEditor.putString(KEY_ACTIVE_USER_EMAIL, loginEmailInput.getText().toString());
            if (stayLoggedInCheckBox.isChecked()){
                mEditor.putString(KEY_ACTIVE_USER_PASS, loginPasswordInput.getText().toString());
            }
            mEditor.putInt(PREFERENCE_USERID, dataManagement.getUserWithEmail(loginEmailInput.getText().toString()).getId());
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
