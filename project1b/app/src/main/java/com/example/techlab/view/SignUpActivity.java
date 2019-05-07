package com.example.techlab.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;

public class SignUpActivity extends AppCompatActivity {
    TextInputLayout userFirstNameInput;
    TextInputLayout userSurnameInput;
    TextInputLayout schoolEmailInput;
    TextInputLayout passwordInput;
    DataManagement dataManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userFirstNameInput = findViewById(R.id.firstNameInputLayout);
        userSurnameInput = findViewById(R.id.surnameInputLayout);
        schoolEmailInput = findViewById(R.id.schoolEmailInputLayout);
        passwordInput = findViewById(R.id.passwordInputLayout);
        dataManagement = new DataManagement();
    }


    public void signUpButtonClicked(View view) {
        // insert new user
        boolean email, passWord, firstName, surname;
        email = emailValidation();
        passWord = passwordValidation();
        firstName = firstNameValidation();
        surname = surnameValidation();
        if (email && passWord && firstName && surname) {
            dataManagement.insertUser(userFirstNameInput.getEditText().getText().toString().trim(), userSurnameInput.getEditText().getText().toString().trim(),
                    schoolEmailInput.getEditText().getText().toString().trim(), passwordInput.getEditText().getText().toString().trim());
            // resetting the  form input text field
            userFirstNameInput.getEditText().setText("");
            userSurnameInput.getEditText().setText("");
            schoolEmailInput.getEditText().setText("");
            passwordInput.getEditText().setText("");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    private boolean emailValidation() {
        String input = schoolEmailInput.getEditText().getText().toString().trim().toLowerCase();
        if (input.isEmpty()) {
            schoolEmailInput.setError("lege invoerveld is niet toegestaan");
            return false;

        } else if ((input.length() != 13)) {
            schoolEmailInput.setError("ingevulde email is niet correct \nWe accepteren alleen schoolEmail van de hogeschool Rotterdam");
            return false;
        } else if (!(input.substring(input.length() - 6).matches("@hr.nl"))) {
            schoolEmailInput.setError("ingevulde email is niet correct \nWe accepteren alleen schoolEmail van de hogeschool Rotterdam");
            return false;
        } else {
            schoolEmailInput.setError(null);
            return true;
        }
    }

    private boolean passwordValidation() {
        String input = passwordInput.getEditText().getText().toString().trim();
        if (input.isEmpty()) {
            passwordInput.setError("lege invoerveld is niet toegestaan");
            return false;
        } else if (!(input.length() >= 8)) {
            passwordInput.setError("ingevulde wachtwoord is niet sterk genoeg  \nWachtwoord moet Minimaal acht tekens lang zijn");
            return false;
        } else {
            passwordInput.setError(null);
            return true;
        }

    }

    private boolean firstNameValidation() {
        String input = userFirstNameInput.getEditText().getText().toString().trim();
        if (input.isEmpty()) {
            userFirstNameInput.setError("lege invoerveld is niet toegestaan");
            return false;
        } else if (!(check(input))) {
            userFirstNameInput.setError("Voor voornaam mag je alleen letters gebruiken");
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
            userSurnameInput.setError("lege invoerveld is niet toegestaan");
            return false;
        } else if (!(check(input))) {
            userSurnameInput.setError("Voor achternaam mag je alleen letters gebruiken");
            return false;
        } else if ((input.length() == 1)) {
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
            if ((!Character.isLetter(character) && !(character + "").equals(" "))) {
                return false;
            }
        }
        return true;
    }


}
