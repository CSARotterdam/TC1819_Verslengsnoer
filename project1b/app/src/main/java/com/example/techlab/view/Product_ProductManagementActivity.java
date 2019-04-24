package com.example.techlab.view;

import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.techlab.R;
import com.example.techlab.adapter.ProductAdapter;
import com.example.techlab.databinding.ActivityProductBeheerBinding;
import com.example.techlab.db.DataManagement;
import com.example.techlab.db.DataSource;
import com.example.techlab.model.Electronics;
import com.example.techlab.model.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Product_ProductManagementActivity extends AppCompatActivity {

    DataSource dataSource;
    DataManagement dataManagement;
    private ActivityProductBeheerBinding binding;
    private ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_product_beheer);
        dataSource = new DataSource(this);
        dataManagement = new DataManagement();
    }

    @Override
    protected void onResume(){
        super.onResume();
        dataSource.open();

        Cursor cursor = dataSource.selectAllproduct();
        ArrayList<Electronics> electronicsList = dataManagement.getAllProductData();
        adapter = new ProductAdapter(electronicsList,this);

        binding.electronicsListItems.setAdapter(adapter);
        binding.electronicsListItems.setLayoutManager(new LinearLayoutManager(this));
        binding.electronicsListItems.setNestedScrollingEnabled(false);
    }
    @Override
    protected void onPause(){
        super.onPause();
        dataSource.close();
    }

    public void addProductPageButton(View view){
        Intent startNewActivity = new Intent(this, Product_AddProductActivity.class);
        startActivity(startNewActivity);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent startNewActivity = new Intent(this, MenuActivity.class);
        startActivity(startNewActivity);
    }
}
