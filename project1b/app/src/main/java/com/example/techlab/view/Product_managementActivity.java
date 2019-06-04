package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;

import com.example.techlab.R;
import com.example.techlab.adapter.ProductManagementAdapter;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Products;

import java.util.ArrayList;

public class Product_managementActivity extends DrawerMenu{
    RecyclerView recyclerView;
    DataManagement dataManagement;

    ProductManagementAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_product_management, null,false);
        frameLayout.addView(activityView);

        dataManagement = new DataManagement();
        dataManagement.openDataBaseConnection();
        ArrayList<Products> Productlist = dataManagement.getAllProducts();
        recyclerView = findViewById(R.id.electronicsListItems);
        adapter = new ProductManagementAdapter(Productlist,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Product_managementActivity.this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView  searchView = (SearchView) searchItem.getActionView();
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


    public void addProductPageButton(View view){
        startActivity(new Intent(this, Product_management_add_productActivity.class));
    }
    public void addBOOKPageButton(View view){
        startActivity(new Intent(this, Product_management_add_bookActivity.class));
    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this,  Product_InventoryActivity.class));
    }


}
