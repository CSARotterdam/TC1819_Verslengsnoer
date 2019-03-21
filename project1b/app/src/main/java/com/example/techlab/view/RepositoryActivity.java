package com.example.techlab.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import com.example.techlab.R;
import com.example.techlab.model.Users;



public class RepositoryActivity extends AppCompatActivity {

    private Button button;
    TextView username;
    TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        Intent intent = getIntent();
        Users activeUser = intent.getParcelableExtra("activeUser");
        username = (TextView) findViewById(R.id.userNameHomePage);
        welcome = (TextView) findViewById(R.id.welcomeTextViewHomePage);
        username.setText(activeUser.getFirstName()+" :) How is your day?");

        button = (Button) findViewById(R.id.managerStart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openManagerStart();
            }
        });
    }

    public void openManagerStart() {
        Intent intent = new Intent(this, ManagerStart.class);
        startActivity(intent);
    }
}
