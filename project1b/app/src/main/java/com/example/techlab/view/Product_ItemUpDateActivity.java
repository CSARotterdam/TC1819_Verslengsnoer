package com.example.techlab.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.techlab.R;
import com.example.techlab.db.DataSource;
import com.example.techlab.model.Electronics;
import com.example.techlab.model.Users;

public class Product_ItemUpDateActivity extends AppCompatActivity {
    EditText productManufacturer;
    EditText productName;
    EditText productStock;
    EditText productCategory;
    EditText productDescription;
    EditText amountBroken;
    DataSource dataSource;
    Users activeUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_up_date);

        productManufacturer = findViewById(R.id.productManufacturerTextInputUpDate);
        productName = findViewById(R.id.productNameTextInputUpDate);
        productStock = findViewById(R.id.productStockTextInputUpDate);
        productCategory = findViewById(R.id.productCategoryTextInputUpDate);
        productDescription = findViewById(R.id.productDescriptionTextInputUpDate);
        amountBroken = findViewById(R.id.amountBrokenTextInputUpdate);
        Intent intent = getIntent();
        activeUser = intent.getParcelableExtra("activeUser");
        dataSource = new DataSource(this);


    }
    protected void onResume(){
        super.onResume();
        dataSource.open();

        Electronics electronics = dataSource.getProduct(getIntent().getStringExtra("id"));
        productManufacturer.setText(electronics.getProductManufacturer());
        productName.setText(electronics.getName());
        productStock.setText(String.valueOf(electronics.getStock()));
        productCategory.setText(electronics.getCategory());
        productDescription.setText(electronics.getDescription());
        amountBroken.setText(String.valueOf(electronics.getAmountBroken()));

    }
    @Override
    protected void onPause(){
        super.onPause();
        dataSource.close();
    }
    public void upDateProductButton(View view){
        Electronics electronics = dataSource.getProduct(getIntent().getStringExtra("id"));
        // creating new product instance
        Electronics newProduct = new Electronics(
                electronics.getProductId(),
                productManufacturer.getText().toString(),
                productName.getText().toString(),
                Integer.parseInt(productStock.getText().toString()),
                Integer.parseInt(amountBroken.getText().toString()),
                productCategory.getText().toString(),
                productDescription.getText().toString());
        // upDate new product

        dataSource.updateElectronic(newProduct,getIntent().getStringExtra("id"));
        // reset form input text field

        Intent intent = new Intent(this, Product_ItemManagementActivity.class);
        intent.putExtra("id",getIntent().getStringExtra("id"));
        intent.putExtra("productID",electronics.getProductId());
        productManufacturer.setText("");
        productName.setText("");
        productStock.setText("");
        productCategory.setText("");
        productDescription.setText("");
        amountBroken.setText("");
        intent.putExtra("activeUser",activeUser);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, Product_ProductManagementActivity.class);
        intent.putExtra("activeUser",activeUser);
        startActivity(intent);
    }
}
