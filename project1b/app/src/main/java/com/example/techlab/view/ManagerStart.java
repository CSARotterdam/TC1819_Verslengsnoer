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
    }

    public void openListPendingReturn(){
        Intent intent = new Intent(this, ListPendingReturn.class);
        startActivity(intent);
    }
}
