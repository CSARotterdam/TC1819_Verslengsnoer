package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Users;


//Settings page
public class User_information_changeActivity extends DrawerMenu {
    TextInputLayout userFirstNameInput, userSurnameInput, passwordInput, confirmPasswordInput;
    DataManagement dataManagement;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_user_information_change, null,false);
        frameLayout.addView(activityView);
    userFirstNameInput = findViewById(R.id.AccountSettingFirstNameInputLayout);
    userSurnameInput = findViewById(R.id.AccountSettingSurnameInputLayout);
    passwordInput = findViewById(R.id.AccountSettingPasswordInputLayout);
    confirmPasswordInput = findViewById(R.id.AccountSettingConfirmPasswordInputLayout);
    mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);
    mEditor = mSharedPreferences.edit();
    dataManagement = new DataManagement();
    Users user = dataManagement.getUserWithId(mSharedPreferences.getInt(MainActivity.KEY_ACTIVE_USER_ID,-1));
    userFirstNameInput.getEditText().setText(user.getFirstName());
    userSurnameInput.getEditText().setText(user.getSurname());
}


    public void ChangeMyUserPassword(View view) {
        // insert new user
        boolean passWord, conformPassword;

        passWord = passwordValidation();

        conformPassword = confirmPasswordValidation();

        if ( passWord && conformPassword) {
            dataManagement.updateUserPassword(confirmPasswordInput.getEditText().getText().toString(),mSharedPreferences.getInt(MainActivity.KEY_ACTIVE_USER_ID,-1) );

            mEditor.putString(MainActivity.KEY_ACTIVE_USER_PASS, confirmPasswordInput.getEditText().getText().toString());

            // resetting the  form input text field
            userFirstNameInput.getEditText().setText("");
            userSurnameInput.getEditText().setText("");
            passwordInput.getEditText().setText("");
            confirmPasswordInput.getEditText().setText("");
            Toast.makeText(this, "Het wachtwoord  is met succes gewijzigd", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, User_information_changeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }
    public void ChangeMyUserNames(View view) {
        boolean  firstName, surname;
        firstName = firstNameValidation();
        surname = surnameValidation();
        if ( firstName && surname ) {
            dataManagement.updateUserNames(userFirstNameInput.getEditText().getText().toString().trim(), userSurnameInput.getEditText().getText().toString().trim(), mSharedPreferences.getInt(MainActivity.KEY_ACTIVE_USER_ID, -1));
            userFirstNameInput.getEditText().setText("");
            userSurnameInput.getEditText().setText("");
            passwordInput.getEditText().setText("");
            confirmPasswordInput.getEditText().setText("");
            Toast.makeText(this, "De gebruikersnaam is met succes gewijzigd", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, User_information_changeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, Product_InventoryActivity.class));
        finish();
    }

    private boolean passwordValidation() {
        String input = passwordInput.getEditText().getText().toString();
        if (input.isEmpty()) {
            passwordInput.setError("Een leeg invoerveld is niet toegestaan");
            return false;
        } else if (!(input.length() >= 8)) {
            passwordInput.setError("Ingevulde wachtwoord is niet sterk genoeg");
            return false;
        } else {
            passwordInput.setError(null);
            return true;
        }
    }

    private boolean confirmPasswordValidation() {
        String input = confirmPasswordInput.getEditText().getText().toString();
        if (input.isEmpty()) {
            confirmPasswordInput.setError("Een leeg invoerveld is niet toegestaan");
            return false;
        } else if (!(input.matches(passwordInput.getEditText().getText().toString()))) {
            confirmPasswordInput.setError("Wachtwoorden komen niet overeen");
            return false;
        } else {
            confirmPasswordInput.setError(null);
            return true;
        }
    }

    private boolean firstNameValidation() {
        String input = userFirstNameInput.getEditText().getText().toString().trim();
        if (input.isEmpty()) {
            userFirstNameInput.setError("Een leeg invoerveld is niet toegestaan");
            return false;
        } else if (!(check(input))) {
            userFirstNameInput.setError("U mag alleen letters gebruiken");
            return false;
        } else if ((input.length() == 1)) {
            userFirstNameInput.setError("Voornaam moet langer zijn dan één letter");
            return false;
        } else {
            userFirstNameInput.setError(null);
            return true;
        }
    }

    private boolean surnameValidation() {
        String input = userSurnameInput.getEditText().getText().toString().trim();
        if (input.isEmpty()) {
            userSurnameInput.setError("een leeg invoerveld is niet toegestaan");
            return false;
        } else if (!(check(input))) {
            userSurnameInput.setError("U mag alleen letters gebruiken");
            return false;
        } else if ((input.length() <= 1)) {
            userSurnameInput.setError("Achternaam moet langer zijn dan één letter");
            return false;
        } else {
            userSurnameInput.setError(null);
            return true;
        }
    }

    public boolean check(String s) {
        if (s == null) {
            return false;
        }
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char character = s.charAt(i);
            if ((!Character.isLetter(character) && !(character + "").equals(" ") && !(character + "").equals("-"))) {
                return false;
            }
        }
        return true;
    }


}
