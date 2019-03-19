package com.example.inventaris;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.BRaspberry);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openDescription();
            }
        });

    }
    public void openDescription() {
        Intent intent = new Intent(this, Des.class);
        startActivity(intent);
    }
}
