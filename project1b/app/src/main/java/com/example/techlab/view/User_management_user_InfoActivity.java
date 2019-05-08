package com.example.techlab.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Users;

public class User_management_user_InfoActivity extends AppCompatActivity {
    TextView name, surname, userEmail, userStatus, productOnLoanAmount;
    DataManagement dataManagement;
    Users user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management_user__info);
        name = findViewById(R.id.userManagementUserInfoUserNameDisplay);
        surname = findViewById(R.id.userManagementUserInfoUserLastNameDisplay);
        userEmail = findViewById(R.id.userManagementUserInfoUserEmailDisplay);
        userStatus = findViewById(R.id.userManagementUserInfoUserStatusDisplay);
        productOnLoanAmount = findViewById(R.id.userManagementUserInfoProductOnLoanAmountDisplay);
        dataManagement = new DataManagement();
        user = dataManagement.getUserWithId(getIntent().getIntExtra("ID_",-1));
        name.setText(user.getFirstName());
        surname.setText(user.getSurname());
        userEmail.setText(user.getSchoolEmail());
        productOnLoanAmount.setText(Integer.toString(user.getLoanedAmount()));
        userStatus.setText(user.getUserType());


    }
    public void deleteUser(View view){
        dataManagement.DeleteUser(getIntent().getIntExtra("ID_",-1));
        Intent startNewActivity = new Intent(this, Users_managementActivity.class);
        startNewActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startNewActivity);
        finish();
    }
    public void updateUserStatusToBeheerder(View view){
        dataManagement.updateUserStatus("beheerder",getIntent().getIntExtra("ID_",-1));
        Intent startNewActivity = new Intent(this, User_management_user_InfoActivity.class);
        startNewActivity.putExtra("ID_",user.getId());
        startNewActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startNewActivity);
        finish();

    }
    public void updateUserStatusToStudent(View view){
        dataManagement.updateUserStatus("student",getIntent().getIntExtra("ID_",-1));
        Intent startNewActivity = new Intent(this, User_management_user_InfoActivity.class);
        startNewActivity.putExtra("ID_",user.getId());
        startNewActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startNewActivity);
        finish();

    }

}
