package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button BorrowedItemsUserListButton;
    private Button ButtonPrAanvraagUserList;
    private Button ButtonTelaatPrUserList;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    DataManagement dataManagement;


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
        dataManagement =new DataManagement();
        mEditor = mSharedPreferences.edit();
        menuButtonManager();

        Buttons();

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
            mEditor.putString(MainActivity.KEY_ACTIVE_USER_EMAIL, "uiwedfjklfddfjkloit'kk546582354%^$%%$#%$@");
            mEditor.putString(MainActivity.KEY_ACTIVE_USER_PASS,"uiwedfjklfddfjkloit'kk546582354%^$%%$#%$@");
            mEditor.putInt(MainActivity.PREFERENCE_USERID,0);
            mEditor.apply();
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.productmanagementMenu){
            Intent intent = new Intent(getBaseContext(), Product_managementActivity.class);
            startActivity(intent);
        }
        if (id == R.id.inventarisMenu){
            Intent intent = new Intent(getBaseContext(), Product_InventoryActivity.class);
            startActivity(intent);
        }
        if (id == R.id.borrowedProductMenu){
            Intent intent = new Intent(getBaseContext(), Student_BorrowedActivity.class);
            startActivity(intent);
        }
        if (id == R.id.userManagementMenu){
            Intent intent = new Intent(getBaseContext(), Users_managementActivity.class);
            startActivity(intent);
        }
        if (id == R.id.ProductAdministratieMenu){
            Intent intent = new Intent(getBaseContext(), AangevraagdItems_UserList.class);
            startActivity(intent);
        }


        return false;
    }
    public void menuButtonManager(){
        if(dataManagement.getUserWithEmail(mSharedPreferences.getString(MainActivity.KEY_ACTIVE_USER_EMAIL,"")).getUserType().matches("student")){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.productmanagementMenu).setVisible(false);
            menu.findItem(R.id.userManagementMenu).setVisible(false);
        }
        if(dataManagement.getUserWithEmail(mSharedPreferences.getString(MainActivity.KEY_ACTIVE_USER_EMAIL,"")).getUserType().matches("beheerder")){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.userManagementMenu).setVisible(false);
        }

    }

}
