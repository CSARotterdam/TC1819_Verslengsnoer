package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.techlab.R;
import com.example.techlab.model.Users;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button Button_Inventory;
    private Button Button_Borrowed;
    private Button BorrowedItemsUserListButton;
    private Button ButtonPrAanvraagUserList;
    private Button ButtonTelaatPrUserList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        drawerLayout = findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView=(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);

        mEditor = mSharedPreferences.edit();

        Buttons();

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        Button_Borrowed = findViewById(R.id.BorrowItemBtn);
        Button_Borrowed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent ShowBorrowItemActivity = new Intent(getBaseContext(), Student_BorrowedActivity.class);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.LogoutMenu){
            mEditor.putString(MainActivity.KEY_ACTIVE_USER, "4ikikikilio.i;5534");
            mEditor.putString(MainActivity.KEY_ACTIVE_USER_PASS,"4ikikikilio.i;5534");
            mEditor.putInt(MainActivity.PREFERENCE_USERID,0);
            mEditor.apply();
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.productmanagementMenu){
            Intent intent = new Intent(getBaseContext(), Product_ProductManagementActivity.class);
            startActivity(intent);
        }
        return false;
    }
}
