package com.example.techlab.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.techlab.R;
import com.example.techlab.db.DataSource;
import com.example.techlab.model.Electronics;

public class AddProductActivity extends AppCompatActivity {
    EditText productId;
    EditText productManufacturer;
    EditText productName;
    EditText productStock;
    EditText productCategory;
    DataSource dataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        productId = findViewById(R.id.productIdTextInput);
        productManufacturer = findViewById(R.id.productManufacturerTextInput);
        productName = findViewById(R.id.productNameTextInput);
        productStock = findViewById(R.id.productStockTextInput);
        productCategory = findViewById(R.id.productCategoryTextInput);
        dataSource = new DataSource(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        dataSource.open();

    }
    @Override
    protected void onPause(){
        super.onPause();
        dataSource.close();
    }

    public void addNewProductButton(View view){
        // creating new product instance
        Electronics newProduct = new Electronics(
                productId.getText().toString(),
                productManufacturer.getText().toString(),
                productName.getText().toString(),
                Integer.parseInt(productStock.getText().toString()),
                0,
                productCategory.getText().toString());
        // insert new product
        dataSource.insertProduct(newProduct);
        // reset form input text field
        productId.setText("");
        productManufacturer.setText("");
        productName.setText("");
        productStock.setText("");
        productCategory.setText("");
    }
}
