package com.example.beheerderstart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerStart extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.GeleendeProducten);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGeleendeProducten();
            }
        });
    }

    public void openGeleendeProducten() {
        Intent intent = new Intent(this, GeleendeProducten.class);
        startActivity(intent);
    }
}
