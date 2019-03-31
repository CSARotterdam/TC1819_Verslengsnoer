package com.example.techlab.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.techlab.R;

public class Admin_GebruikerA extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gebruiker);

        button = (Button) findViewById(R.id.GeleendeItem1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPendingItem1GebruikerA();
            }
        });
    }

    public void openPendingItem1GebruikerA() {
        Intent intent = new Intent(this, Admin_PendingItem1GebruikerA.class);
        startActivity(intent);
    }
}
