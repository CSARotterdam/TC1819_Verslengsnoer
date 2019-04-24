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
import com.example.techlab.db.DataSource;
import com.example.techlab.model.Electronics;
import com.example.techlab.model.Users;

import java.util.ArrayList;

public class Product_ProductManagementActivity extends AppCompatActivity {

    DataSource dataSource;
    private ActivityProductBeheerBinding binding;
    private ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_product_beheer);
        dataSource = new DataSource(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        dataSource.open();

        Cursor cursor = dataSource.selectAllproduct();
        ArrayList<Electronics> productData = getProductData(cursor);
        adapter = new ProductAdapter(productData,this);

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

    protected ArrayList<Electronics> getProductData(Cursor cursor) {

        ArrayList<Electronics> electronicsList = new ArrayList<>();

        cursor.moveToFirst();
        for (int i = cursor.getCount(); i > 0; i--) {
            electronicsList.add(new Electronics(
                    cursor.getString(0)
                    , cursor.getString(1)
                    , cursor.getString(2)
                    ,cursor.getInt(3)
                    ,cursor.getInt(4)
                    ,cursor.getString(5)
                    ,cursor.getString(6)));
            if (i>1){
                cursor.moveToNext();
            }
        }
        return electronicsList;
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent startNewActivity = new Intent(this, MenuActivity.class);
        startActivity(startNewActivity);
    }
}
