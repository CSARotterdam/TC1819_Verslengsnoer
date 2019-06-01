package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.adapter.RecyclerViewAdapter;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Products;

import java.util.ArrayList;

//Inventaris Page
public class Product_InventoryActivity extends DrawerMenu{

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private TextView menuUserName, menuUserStatus;

    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    View headerView;
    ArrayList<Products> products;
    DataManagement dataManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // findViewById(R.id.progressBar).setVisibility(View.GONE);
        // recyclerView.setVisibility(View.VISIBLE);
        // adapter.notifyDataSetChanged();

        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_inventory, null,false);
        frameLayout.addView(activityView);

        dataManagement = new DataManagement();
        products = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapter(Product_InventoryActivity.this, products);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Product_InventoryActivity.this));


        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();


        Spinner CategorySpinner = findViewById(R.id.CategoryBttn);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.ProductCategory,
                android.R.layout.simple_dropdown_item_1line);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        CategorySpinner.setAdapter(adapter2);
        CategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getSelectedItem().toString().matches("Alle Producten")) {
                    products = dataManagement.getAllProducts();
                } else {
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
    // public class SpinnerActivity extends Activity implements
    // AdapterView.OnItemSelectedListener {
    // public void onItemSelected(AdapterView<?> parent, View view, int pos, long
    // id) {
    // // An item was selected. You can retrieve the selected item using
    // // parent.getItemAtPosition(pos)
    // Toast.makeText(Product_InventoryActivity.this,
    // parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
    // }
    // public void onNothingSelected(AdapterView<?> parent) {
    // // Another interface callback
    // }
    // }

    @Override
    public void onBackPressed() {
        Intent startNewActivity = new Intent(this, Product_InventoryActivity.class);
        startNewActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startNewActivity);
        finish();
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


}