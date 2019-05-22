package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Users;

public class User_management_user_InfoActivity extends AppCompatActivity {
    TextView name, surname, userEmail, userStatus, productOnLoanAmount, statusBlock;
    DataManagement dataManagement;
    Users user;
    Button statusUpdateToSudent, statusUpdateToBeheerder, BlockBtn, UnBlockBtn;
    SharedPreferences sharedPreferences;

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
        statusUpdateToBeheerder = findViewById(R.id.userStatusUpDateBeheerderButton);
        statusUpdateToSudent = findViewById(R.id.userStatusUpDateStudentButton);
        statusBlock = findViewById(R.id.BlockStatusText);
        BlockBtn = findViewById(R.id.BlockUser);
        UnBlockBtn = findViewById(R.id.UnBlockUser);
        sharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);
        statusButtonManager();
    }

    private void statusButtonManager() {
        if (user.getUserType().toLowerCase().matches("student")){
            statusUpdateToSudent.setVisibility(View.GONE);
        }else if (user.getUserType().toLowerCase().matches("beheerder")){
            statusUpdateToBeheerder.setVisibility(View.GONE);
        }

        //Block/UnBlock buttons display.
        if (user.getBlockStatus() == 0) {
            UnBlockBtn.setVisibility(View.GONE);
            statusBlock.setText("Niet geblokkeerd.");
            findViewById(R.id.BlockStatusField).setBackgroundColor(getResources().getColor(R.color.Green));
        }else if (user.getBlockStatus() == 1){
            BlockBtn.setVisibility(View.GONE);
            statusBlock.setText("!!!GEBLOKKEERD!!!");
            statusBlock.setTypeface(statusBlock.getTypeface(), Typeface.BOLD); //Source: https://stackoverflow.com/questions/6200533/set-textview-style-bold-or-italic
            findViewById(R.id.BlockStatusField).setBackgroundColor(getResources().getColor(R.color.Red));
        }
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
    public void BlockUser(View view){
        System.out.println("Block user btn pressed");
        dataManagement.setBlockUser(1,getIntent().getIntExtra("ID_",-1));
        BlockBtn.setVisibility(View.GONE);
        UnBlockBtn.setVisibility(View.VISIBLE);
        statusBlock.setText("!!!GEBLOKKEERD!!!");
        statusBlock.setTypeface(statusBlock.getTypeface(), Typeface.BOLD);
        findViewById(R.id.BlockStatusField).setBackgroundColor(getResources().getColor(R.color.Red));

    }
    public void UnBlockUser(View view){
        System.out.println("UnBlock user btn pressed");
        dataManagement.setBlockUser(0,getIntent().getIntExtra("ID_",-1));
        UnBlockBtn.setVisibility(View.GONE);
        BlockBtn.setVisibility(View.VISIBLE);
        statusBlock.setText("Niet geblokkeerd.");
        findViewById(R.id.BlockStatusField).setBackgroundColor(getResources().getColor(R.color.Green));

    }

}
