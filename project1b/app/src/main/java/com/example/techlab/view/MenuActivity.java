package com.example.techlab.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.techlab.R;
import com.example.techlab.model.Users;

public class MenuActivity extends AppCompatActivity {
    private Users activeUser;
    private Button Button_Inventory;
    private Button ProductBeheerButton;
    private Button Button_Borrowed;
    private Button BorrowedItemsUserListButton;
    private Button ButtonPrAanvraagUserList;
    private Button ButtonTelaatPrUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        Buttons();
    }

    public void productManagementPageButton(View view){
        Intent startNewActivity = new Intent(this, Product_ProductManagementActivity.class);
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
                Intent ShowInventarisActivity = new Intent(getBaseContext(), Product_InventoryActivity.class);
                startActivity(ShowInventarisActivity);
            }
        });
        ProductBeheerButton = findViewById(R.id.productBeheer);
        ProductBeheerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startNewActivity = new Intent(getBaseContext(), Product_ProductManagementActivity.class);
                startActivity(startNewActivity);
            }
        });
        Button_Borrowed = findViewById(R.id.BorrowItemBtn);
        Button_Borrowed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent ShowBorrowItemActivity = new Intent(getBaseContext(), Product_BorrowedActivity.class);
                startActivity(ShowBorrowItemActivity);
            }
        });
        BorrowedItemsUserListButton = findViewById(R.id.BorrowedItemsUserListBtn);
        BorrowedItemsUserListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent borrow = new Intent(getBaseContext(), Admin_BorrowedItemsUserList.class);
                startActivity(borrow);
            }
        });

        ButtonPrAanvraagUserList = findViewById(R.id.AangevrPrButton);
        ButtonPrAanvraagUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PrAanvraagUsers = new Intent(getBaseContext(), AangevraagdItems_UserList.class);
                startActivity(PrAanvraagUsers);
            }
        });

        ButtonTelaatPrUserList = findViewById(R.id.TelaatButton);
        ButtonTelaatPrUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PrTelaatUsers = new Intent(getBaseContext(), NoReturnUsers.class);
                startActivity(PrTelaatUsers);
            }
        });

    }

}
