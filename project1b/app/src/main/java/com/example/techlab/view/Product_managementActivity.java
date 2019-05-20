package com.example.techlab.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.example.techlab.R;
import com.example.techlab.adapter.ProductManagementAdapter;
import com.example.techlab.databinding.ActivityProductManagementBinding;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Products;

import java.util.ArrayList;

public class Product_managementActivity extends AppCompatActivity {
    DataManagement dataManagement;
    ActivityProductManagementBinding binding;
    ProductManagementAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_management);
        dataManagement = new DataManagement();
        ArrayList<Products> Productlist = dataManagement.getAllElectronicsData();
        ArrayList<Products> bookList = dataManagement.getAllBooksData();
        for (int i = 0; i < bookList.size(); i++){
            Productlist.add(bookList.get(i));
        }
        adapter = new ProductManagementAdapter(Productlist,this);
        binding.electronicsListItems.setAdapter(adapter);
        binding.electronicsListItems.setLayoutManager(new LinearLayoutManager(this));
        binding.electronicsListItems.setNestedScrollingEnabled(false);
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
        Intent startNewActivity = new Intent(this, Product_management_add_productActivity.class);
        startActivity(startNewActivity);
    }
    public void addBOOKPageButton(View view){
        Intent startNewActivity = new Intent(this, Product_management_add_bookActivity.class);
        startActivity(startNewActivity);
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent startNewActivity = new Intent(this,  Product_InventoryActivity.class);
        startActivity(startNewActivity);
    }


}
