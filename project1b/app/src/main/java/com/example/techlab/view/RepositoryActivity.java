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

    public void productManagementPageButton(View view){
        Intent startNewActivity = new Intent(this, ProductManagementActivity.class);
        Intent intent = getIntent();
        Users activeUser = intent.getParcelableExtra("activeUser");
        startNewActivity.putExtra("activeUser",activeUser);
        startActivity(startNewActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
