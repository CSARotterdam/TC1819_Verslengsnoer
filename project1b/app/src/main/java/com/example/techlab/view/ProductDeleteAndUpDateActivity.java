package com.example.techlab.view;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.db.DataSource;
import com.example.techlab.model.Electronics;
import com.example.techlab.model.Users;

public class ProductDeleteAndUpDateActivity extends AppCompatActivity {
    TextView productName, productId, productStock, productCategory,productAmountBroken
            ,productManufacturer, productDescription;
    DataSource dataSource;
    Users activeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_delete_and_up_date);
        dataSource = new DataSource(this);
        productName = findViewById(R.id.productNameTextView);
        productId = findViewById(R.id.productIdTextView);
        productStock = findViewById(R.id.productStockTextView);
        productCategory = findViewById(R.id.productCategoryTextView);
        productAmountBroken = findViewById(R.id.prodyctAmountBrokenTextView);
        productManufacturer = findViewById(R.id.productManufacturerTextView);
        productDescription = findViewById(R.id.productDescriptionTextView);
        Intent intent = getIntent();
        activeUser = intent.getParcelableExtra("activeUser");

    }

    @Override
    protected void onResume(){
        super.onResume();
        dataSource.open();
        Cursor cursor = dataSource.getProductCursor(getIntent().getStringExtra("productID"));
        cursor.moveToFirst();
        Electronics product =dataSource.getProduct(String.valueOf(cursor.getInt(0)));
        productName.setText(product.getName());
        productId.setText(product.getProductId());
        productStock.setText(String.valueOf(product.getStock()));
        productCategory.setText(product.getProductCategory());
        productAmountBroken.setText(String.valueOf(product.getAmountBroken()));
        productManufacturer.setText(product.getProductManufacturer());
        productDescription.setText(product.getDescription());

    }


    @Override
    protected void onPause(){
        super.onPause();
        dataSource.close();
    }
    public void deleteProduct(View view){
        Cursor cursor = dataSource.getProductCursor(getIntent().getStringExtra("productID"));
        cursor.moveToFirst();
        dataSource.deleteProduct(String.valueOf(cursor.getInt(0)));
        Intent startNewActivity = new Intent(this, ProductManagementActivity.class);
        startNewActivity.putExtra("activeUser",activeUser);
        startActivity(startNewActivity);
    }
    public void upDateProduct(View view){
        Intent startNewActivity = new Intent(this, ProductUpDateActivity.class);
        Cursor cursor = dataSource.getProductCursor(getIntent().getStringExtra("productID"));
        cursor.moveToFirst();
        startNewActivity.putExtra("id",String.valueOf(cursor.getInt(0)));
        startNewActivity.putExtra("activeUser",activeUser);

        startActivity(startNewActivity);
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent startNewActivity = new Intent(this, ProductManagementActivity.class);
        startNewActivity.putExtra("activeUser",activeUser);
        startActivity(startNewActivity);
    }

}
