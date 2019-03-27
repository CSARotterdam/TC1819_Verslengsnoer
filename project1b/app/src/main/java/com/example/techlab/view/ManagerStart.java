package com.example.techlab.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.techlab.R;

public class ManagerStart extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_start);

        button =(Button) findViewById(R.id.confirmReturnBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListPendingReturn();
            }
        });

        button = (Button) findViewById(R.id.confirmLoanBtn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openUserListLoans();
            }
        });

    }

    public void openListPendingReturn(){
        Intent intent = new Intent(this, ListPendingReturn.class);
        startActivity(intent);
    }

    public void openUserListLoans(){
        Intent intent = new Intent(this, UserList.class);
        startActivity(intent);
    }
}
