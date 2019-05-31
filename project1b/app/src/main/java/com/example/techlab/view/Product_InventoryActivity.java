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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.adapter.RecyclerViewAdapter;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Products;
import com.example.techlab.model.Users;

import java.util.ArrayList;

public class Product_InventoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private TextView  menuUserName, menuUserStatus;

    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    View headerView;
    ArrayList<Products> products;
    DataManagement dataManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        findViewById(R.id.progressBar).setVisibility(View.GONE);
//        recyclerView.setVisibility(View.VISIBLE);
//        adapter.notifyDataSetChanged();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        //Log.d(TAG, "onCreate: started.");
        dataManagement = new DataManagement();
        products = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapter(Product_InventoryActivity.this, products);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(Product_InventoryActivity.this));
        navigationView=findViewById(R.id.navigation_view);
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
        menuButtonManager();

        Spinner CategorySpinner = findViewById(R.id.CategoryBttn);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.ProductCategory, android.R.layout.simple_dropdown_item_1line);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        CategorySpinner.setAdapter(adapter2);
        CategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getSelectedItem().toString().matches("Alle Producten")){
                    products = dataManagement.getAllProducts();
                }else{
                    products = dataManagement.getAllProducts(parent.getSelectedItem().toString());
                }

                adapter = new RecyclerViewAdapter(Product_InventoryActivity.this, products);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
//    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
//        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//            // An item was selected. You can retrieve the selected item using
//            // parent.getItemAtPosition(pos)
//            Toast.makeText(Product_InventoryActivity.this, parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
//        }
//        public void onNothingSelected(AdapterView<?> parent) {
//            // Another interface callback
//        }
//    }

    @Override
    public void onBackPressed() {
        Intent startNewActivity = new Intent(this,  Product_InventoryActivity.class);
        startNewActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startNewActivity);
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.LogoutMenu){
            mEditor.putString(MainActivity.KEY_ACTIVE_USER_EMAIL, "4ikikikilio.i;5534");
            mEditor.putString(MainActivity.KEY_ACTIVE_USER_PASS,"4ikikikilio.i;5534");
            mEditor.putInt(MainActivity.KEY_ACTIVE_USER_ID,0);
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
            Intent intent = new Intent(getBaseContext(), AangevraagdItems_UserList.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        if (id == R.id.userAccountSetting){
            Intent intent = new Intent(getBaseContext(), User_information_changeActivity.class);
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
        return false;
    }
    public void menuButtonManager(){
        Menu menu = navigationView.getMenu();

        Users user = dataManagement.getUserWithEmail(mSharedPreferences.getString(MainActivity.KEY_ACTIVE_USER_EMAIL,""));

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