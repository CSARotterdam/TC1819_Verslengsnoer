package com.example.techlab.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Users;
import com.example.techlab.util.HashUtils;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {
    TextInputLayout userFirstNameInput, userSurnameInput, schoolEmailInput, passwordInput, confirmPasswordInput;
    DataManagement dataManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userFirstNameInput = findViewById(R.id.firstNameInputLayout);
        userSurnameInput = findViewById(R.id.surnameInputLayout);
        schoolEmailInput = findViewById(R.id.schoolEmailInputLayout);
        passwordInput = findViewById(R.id.passwordInputLayout);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInputLayout);
        dataManagement = new DataManagement();
    }


    public void signUpButtonClicked(View view) {
        // insert new user
        boolean email, passWord, conformPassword, firstName, surname;
        email = emailValidation();
        passWord = passwordValidation();
        firstName = firstNameValidation();
        surname = surnameValidation();
        conformPassword = confirmPasswordValidation();

        if (email && passWord && firstName && surname && conformPassword) {
            byte[] salt = HashUtils.createSalt();
            dataManagement.insertUser(userFirstNameInput.getEditText().getText().toString().trim(), userSurnameInput.getEditText().getText().toString().trim(),
                    schoolEmailInput.getEditText().getText().toString().trim(),salt,passwordInput.getEditText().getText().toString().trim());
            // resetting the  form input text field
            userFirstNameInput.getEditText().setText("");
            userSurnameInput.getEditText().setText("");
            schoolEmailInput.getEditText().setText("");
            passwordInput.getEditText().setText("");
            Toast.makeText(this, "U hebt met succes een nieuw account geregistreerd", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
        }

    }

    private boolean emailValidation() {
        String input = schoolEmailInput.getEditText().getText().toString();
        boolean EmailAlreadyRegisteredCheck = true;
        List<Users> users = dataManagement.getAllUserData();

        for (int i = 0; users.size() > i; i++) {
            if (users.get(i).getSchoolEmail().equals(input)) {
                EmailAlreadyRegisteredCheck = false;
            }
        }

        if (input.isEmpty()) {
            schoolEmailInput.setError("Een leeg invoerveld is niet toegestaan");
            return false;
        } else if ((input.length() != 13)) {
            schoolEmailInput.setError("We accepteren alleen schoolEmail van de hogeschool Rotterdam");
            return false;
        } else if ( !(input.substring(input.length() - 6).matches("@hr.nl"))){
            schoolEmailInput.setError("We accepteren alleen schoolEmail van de hogeschool Rotterdam");
            return false;
        } else if (!(EmailAlreadyRegisteredCheck)) {
            schoolEmailInput.setError("Deze e-mail is al gebruikt voor een ander account");
            return false;
        } else {
            schoolEmailInput.setError(null);
            return true;
        }
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
            userSurnameInput.setError("Een leeg invoerveld is niet toegestaan");
            return false;
        } else if (!(check(input))) {
            userSurnameInput.setError("Achternaam mag alleen uit letters bestaan");
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
