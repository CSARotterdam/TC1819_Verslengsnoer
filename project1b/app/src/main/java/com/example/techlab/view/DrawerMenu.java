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
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;

public class DrawerMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private TextView menuUserName, menuUserStatus;
    DataManagement dataManagement;
    View headerView;

    //Source: https://stackoverflow.com/questions/26216088/drawer-layout-not-closing-on-back-pressed-depending-on-support-v4-lib
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_menu);
        dataManagement = new DataManagement();
        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        navigationView = findViewById(R.id.drawer_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = findViewById(R.id.drawerMenu);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.Open,R.string.Close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        headerView = navigationView.getHeaderView(0);
        menuUserName = headerView.findViewById(R.id.menuUserName);
        menuUserStatus = headerView.findViewById(R.id.menuUserStatus);


    }
    @Override
    protected void onResume() {
        super.onResume();
        actionBarDrawerToggle.syncState();
        menuButtonManager();
        dataManagement.StatusTeLaat();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.LogoutMenu){
            mEditor.clear();
            mEditor.putString(MainActivity.KEY_ACTIVE_USER_STATUS,"-");
            mEditor.putString(MainActivity.KEY_ACTIVE_USER_NAME,"");
            mEditor.apply();

            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.productmanagementMenu){
            Intent intent = new Intent(getBaseContext(), Product_managementActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.inventarisMenu){
            Intent intent = new Intent(getBaseContext(), Product_InventoryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.borrowedProductMenu){
            Intent intent = new Intent(getBaseContext(), Student_Geleend_Aangevraagd.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.userManagementMenu){
            Intent intent = new Intent(getBaseContext(), Users_managementActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.ProductAdministratieMenu){
            Intent intent = new Intent(getBaseContext(), Product_administration.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.userAccountSetting){
            Intent intent = new Intent(getBaseContext(), User_Account_SettingsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.infographic){
            Intent intent = new Intent(getBaseContext(), InfographicActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.contact) {
            Intent intent = new Intent(getBaseContext(), Contact.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.voorwaardenMenu){
            Intent intent = new Intent(getBaseContext(), Product_Voorwaarden.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        if (id == R.id.HomeMenu){
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        return false;
    }
    public void menuButtonManager(){
        Menu menu = navigationView.getMenu();
        String status = mSharedPreferences.getString(MainActivity.KEY_ACTIVE_USER_STATUS, "-");
        String statusTxt = "Status: "+status;
        menuUserStatus.setText(statusTxt);
        String nameTxt = "Hi "+mSharedPreferences.getString(MainActivity.KEY_ACTIVE_USER_NAME, "")+" :)";
        menuUserName.setText(nameTxt);

        if(status.matches("-")) {
            menuUserName.setVisibility(View.GONE);
            menuUserStatus.setVisibility(View.GONE);
            menu.findItem(R.id.inventarisMenu).setVisible(false);
            menu.findItem(R.id.borrowedProductMenu).setVisible(false);
            menu.findItem(R.id.userAccountSetting).setVisible(false);
            menu.findItem(R.id.LogoutMenu).setVisible(false);
            menu.findItem(R.id.productmanagementMenu).setVisible(false);
            menu.findItem(R.id.userManagementMenu).setVisible(false);
            menu.findItem(R.id.ProductAdministratieMenu).setVisible(false);
            menu.findItem(R.id.infographic).setVisible(false);
        }

        if(status.matches("student")){
            menu.findItem(R.id.productmanagementMenu).setVisible(false);
            menu.findItem(R.id.userManagementMenu).setVisible(false);
            menu.findItem(R.id.ProductAdministratieMenu).setVisible(false);
            menu.findItem(R.id.infographic).setVisible(false);
            menu.findItem(R.id.HomeMenu).setVisible(false);
        }
        if (status.matches("beheerder")){
            menu.findItem(R.id.userManagementMenu).setVisible(false);
            menu.findItem(R.id.HomeMenu).setVisible(false);
        }
        if (status.matches("admin")){
            menu.findItem(R.id.HomeMenu).setVisible(false);
        }
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
