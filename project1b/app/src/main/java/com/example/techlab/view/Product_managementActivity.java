package com.example.techlab.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    TextInputLayout searchInput;

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
        searchInput = findViewById(R.id.searchTextInput);
    }


    public void searchByProductNameButton(View view){
        Intent startNewActivity = new Intent(this, Product_managementActivity.class);
        startActivity(startNewActivity);
    }

    public void addProductPageButton(View view){
        Intent startNewActivity = new Intent(this, Product_management_add_productActivity.class);
        startActivity(startNewActivity);
    }
    public void addBOOKPageButton(View view){
        Intent startNewActivity = new Intent(this, Product_management_add_bookActivity.class);
        startActivity(startNewActivity);
    }


}
