package com.example.techlab.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.model.Users;

public class RepositoryActivity extends AppCompatActivity {
    TextView username;
    TextView welcome;
    private Users activeUser;
    private Button Button_Inventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);

        Button_Inventory =  (Button) findViewById(R.id.Button_Inventory);
        Button_Inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Inventaris();
            }
        });
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

    public void InventoryPageButton(View view){
        Intent intent = new Intent(this, Inventaris.class);
        startActivity(intent);
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
