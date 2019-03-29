package com.example.techlab.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.techlab.R;
import com.example.techlab.model.Users;

public class RepositoryActivity extends AppCompatActivity {
    private Users activeUser;
    private Button Button_Inventory;
    private Button ProductBeheerButton;
    private Button BorrowedItemsUserListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        Buttons();


    }

    public void BorrowItemBtn(View view){
        Intent ShowBorrowItemActivity = new Intent(this,GeleendeProductActivity.class);
        startActivity(ShowBorrowItemActivity);
    }

//    public void InventoryPageButton(View view){
//    //        Intent intent = new Intent(this, Inventaris.class);
//    //        startActivity(intent);
//        Intent ShowInventarisActivity = new Intent(getBaseContext(),Inventaris.class);
//        startActivity(ShowInventarisActivity);
    
    public void productManagementPageButton(View view){
        Intent startNewActivity = new Intent(this, ProductManagementActivity.class);
        startNewActivity.putExtra("activeUser",activeUser);
        startActivity(startNewActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("activeUser",activeUser);
        startActivity(intent);
    }
    public void Buttons(){
        Button_Inventory = findViewById(R.id.Button_Inventory);
        Button_Inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Inventaris.class);
                startActivity(intent);
            }
        });
        ProductBeheerButton = findViewById(R.id.productBeheer);
        ProductBeheerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startNewActivity = new Intent(getBaseContext(), ProductManagementActivity.class);
                startActivity(startNewActivity);
            }
        });
        BorrowedItemsUserListButton = findViewById(R.id.BorrowedItemsUserListBtn);
        BorrowedItemsUserListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent borrow = new Intent(getBaseContext(), BorrowedItemsUserList.class);
                startActivity(borrow);
            }
        });

    }

}
