package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.adapter.RecyclerViewAdapter;
import com.example.techlab.db.DataManagement;
import com.example.techlab.db.imageConverter;
import com.example.techlab.model.Electronics;
import com.example.techlab.model.Users;

import java.util.ArrayList;

public class Product_InventoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final String TAG = "Product_InventoryActivity";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private TextView  menuUserName, menuUserStatus;
    Users user;
    View headerView;

    // Array van de namen en afbeeldingen van elk product
    private ArrayList<Integer> mId = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mProductDescription = new ArrayList<>();
    private ArrayList<Bitmap> mbitmaps = new ArrayList<>();
    DataManagement dataManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventaris);
        //Log.d(TAG, "onCreate: started.");
        dataManagement = new DataManagement();
        navigationView=(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        drawerLayout = findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.syncState();
        headerView = navigationView.getHeaderView(0);
        menuUserName = headerView.findViewById(R.id.menuUserName);
        menuUserStatus = headerView.findViewById(R.id.menuUserStatus);

        initImageBitmaps();
    }
//    "test"
    // Voeg hier Producten toe!
    // Product Naam + foto URL
    private void initImageBitmaps(){
        //Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        ArrayList<Electronics> products = dataManagement.getAllProductData();
        for (int i =0; products.size() >i ; i++) {
            mNames.add(products.get(i).getName());
            mbitmaps.add(imageConverter.getImage(dataManagement.getImage(products.get(i).getId_())));
            mProductDescription.add(products.get(i).getDescription());
            mId.add(products.get(i).getId_());
        }
        initRecyclerView();
    }

    private void initRecyclerView(){
        //Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mProductDescription,mbitmaps, mId);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.LogoutMenu){
            mEditor.putString(MainActivity.KEY_ACTIVE_USER_EMAIL, "4ikikikilio.i;5534");
            mEditor.putString(MainActivity.KEY_ACTIVE_USER_PASS,"4ikikikilio.i;5534");
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
        Menu menu = navigationView.getMenu();

        menuUserStatus.setText(user.getUserType());
        menuUserName.setText(user.getFirstName());


        if(user.getUserType().matches("student")){
            menu.findItem(R.id.productmanagementMenu).setVisible(false);
            menu.findItem(R.id.userManagementMenu).setVisible(false);
            menu.findItem(R.id.ProductAdministratieMenu).setVisible(false);
        }
        if(user.getUserType().matches("beheerder")){
            menu.findItem(R.id.userManagementMenu).setVisible(false);
        }
    }


}
