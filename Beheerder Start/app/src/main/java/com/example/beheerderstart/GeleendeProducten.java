package com.example.beheerderstart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GeleendeProducten extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geleende_producten);

        button = (Button) findViewById(R.id.GebruikerF);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGebruikerF();
            }
        });
    }

    public void openGebruikerF() {
        Intent intent = new Intent(this, GebruikerF.class);
        startActivity(intent);
    }
}
