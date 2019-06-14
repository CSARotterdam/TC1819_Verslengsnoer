package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Users;

public class MainActivity extends DrawerMenu {
    protected static final String PREFERENCES_FILE = "com.example.techlab.preferences";
    protected static final String KEY_ACTIVE_USER_ID = "CurrentUserID";
    public static final String KEY_ACTIVE_USER_EMAIL = "keyActiveUserEmail";
    protected static final String KEY_STAY_LOGGED_IN = "keyStayLoggedInCBStatus";
    public static final String KEY_ACTIVE_USER_STATUS = "keyActiveUserRoleStatus";
    protected static final String KEY_PRODUCT_ADMINISTER_SPINNER_STATE = "keyProductAdministrationFilterState";
    public static final String KEY_ACTIVE_USER_NAME = "keyActiveUserFirstName";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    EditText loginEmailInput;
    EditText loginPasswordInput;
    TextInputLayout userNameInputLayout, passwordInputLayout;
    CheckBox stayLoggedInCheckBox;
    DataManagement dataManagement;

    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Menu for Contact page:
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityview = layoutInflater.inflate(R.layout.activity_main, null,false);
        frameLayout.addView(activityview);

        ctx = this;
        loginEmailInput = findViewById(R.id.loginEmailInput);
        loginPasswordInput = findViewById(R.id.loginPasswordInput);
        mSharedPreferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        stayLoggedInCheckBox = findViewById(R.id.stayLoggedInCheckBox);
        userNameInputLayout = findViewById(R.id.textInputLayout);
        passwordInputLayout = findViewById(R.id.passwordInputLayout);
        dataManagement = new DataManagement();

        ImageView logo = findViewById(R.id.TechLabLogo);
        int ImageResource = getResources().getIdentifier("@drawable/techlablogo_small", null, this.getPackageName());
        logo.setImageResource(ImageResource);

        if (!(mSharedPreferences.getString(KEY_STAY_LOGGED_IN,"").matches("on"))){
            ClearPreference();
        }
    }

    @Override
    protected void onResume() {
        // Show shared preference in log
//        System.out.println("Main Activity: "+mSharedPreferences.getAll());

        if (!(mSharedPreferences.getString(KEY_ACTIVE_USER_EMAIL,"").isEmpty())) {
            blockfunc blocked = new blockfunc(mSharedPreferences.getString(KEY_ACTIVE_USER_EMAIL, ""), ctx);
            if (blocked.ifblocked()) {
                ClearPreference();
                blocked.ShowBlockDialog("Uitgelogd");
            } else if (mSharedPreferences.getString(KEY_STAY_LOGGED_IN, "").matches("on")) {
                startActivity(new Intent(this, Product_InventoryActivity.class));
            }
        }
        else {
            ClearPreference();
        }
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        loginPasswordInput.setText("");
        loginEmailInput.setText("");
        mEditor.apply();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }

    public void signUpPageButton(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }
    public void loginButton(View view) {
        userNameInputLayout.setError(null);
        if (dataManagement.ifExists(loginEmailInput.getText().toString().trim())) {
            if (dataManagement.ifExists(loginEmailInput.getText().toString().trim().toLowerCase(),
                loginPasswordInput.getText().toString().trim())) {
                    if (!(dataManagement.ifBlocked(loginEmailInput.getText().toString()))) {
                        Users user = dataManagement.getUserWithEmail(loginEmailInput.getText().toString());
                        Intent startNewActivity = new Intent(getBaseContext(), Product_InventoryActivity.class);
                        mEditor.putString(KEY_ACTIVE_USER_EMAIL, loginEmailInput.getText().toString());
                        mEditor.putInt(KEY_ACTIVE_USER_ID, user.getId());
                        mEditor.putString(KEY_ACTIVE_USER_STATUS, user.getUserType());
                        mEditor.putString(KEY_ACTIVE_USER_NAME, user.getFirstName());
                        if (stayLoggedInCheckBox.isChecked()) {
                            mEditor.putString(KEY_STAY_LOGGED_IN, "on");
                        }
                        startActivity(startNewActivity);
                    }
                    else {
                        blockfunc blocked = new blockfunc(mSharedPreferences.getString(KEY_ACTIVE_USER_EMAIL, ""),ctx);
                        blocked.ShowBlockDialog("Inlog mislukt!");
                    }
                }
            else {
                passwordInputLayout.setError("het wachtwoord dat je hebt ingevoerd is onjuist");
            }
        }
        else {
            userNameInputLayout.setError("Het e-mailadres dat je hebt ingevoerd is onjuist");
        }
    }

    public void ClearPreference(){
        mSharedPreferences.edit().clear().apply();
        mSharedPreferences.edit().putString(MainActivity.KEY_ACTIVE_USER_STATUS, "-").apply();
        mSharedPreferences.edit().putString(MainActivity.KEY_ACTIVE_USER_NAME, "").apply();
    }

    @Override
    public void onBackPressed() { moveTaskToBack(true); }
}
