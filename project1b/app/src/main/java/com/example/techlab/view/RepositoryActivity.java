package com.example.techlab.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.model.Users;

public class RepositoryActivity extends AppCompatActivity {
    TextView username;
    TextView welcome;
    private Users activeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        username = (TextView) findViewById(R.id.userNameHomePage);
        welcome = (TextView) findViewById(R.id.welcomeTextViewHomePage);
        Intent intent = getIntent();
        activeUser = intent.getParcelableExtra("activeUser");
        username.setText(activeUser.getFirstName()+" :) How is your day?");



    }

    public void BorrowItemBtn(View view){
        Intent ShowBorrowItemActivity = new Intent(this,GeleendeProductActivity.class);
        startActivity(ShowBorrowItemActivity);
    }

    public void InventarisBtn(View view){
        Intent ShowInventarisActivity = new Intent(this,InventarisActivity.class);
        startActivity(ShowInventarisActivity);
    }
    
    public void productManagementPageButton(View view){
        Intent startNewActivity = new Intent(this, ProductManagementActivity.class);
        startNewActivity.putExtra("activeUser",activeUser);
        startActivity(startNewActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, InLogActivity.class);
        intent.putExtra("activeUser",activeUser);
        startActivity(intent);
    }
}
