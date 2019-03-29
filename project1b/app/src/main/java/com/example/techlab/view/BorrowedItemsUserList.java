package com.example.techlab.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.techlab.R;

public class BorrowedItemsUserList extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowed_items_user_list);

        button = (Button) findViewById(R.id.GebruikerA);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGebruikerA();
            }
        });
    }

    public void openGebruikerA() {
        Intent intent = new Intent(this, GebruikerA.class);
        startActivity(intent);
    }
}

