package com.example.techlab.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Electronics;
import com.example.techlab.model.Users;

public class Product_management_product_UpdateActivity extends AppCompatActivity {
    EditText productManufacturer;
    EditText productName;
    EditText productStock;
    EditText productCategory;
    EditText productDescription;
    EditText amountBroken;
    Users activeUser;
    DataManagement dataManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management_product_up_date);

        productManufacturer = findViewById(R.id.productManufacturerTextInputUpDate);
        productName = findViewById(R.id.productNameTextInputUpDate);
        productStock = findViewById(R.id.productStockTextInputUpDate);
        productCategory = findViewById(R.id.productCategoryTextInputUpDate);
        productDescription = findViewById(R.id.productDescriptionTextInputUpDate);
        amountBroken = findViewById(R.id.amountBrokenTextInputUpdate);
        dataManagement = new DataManagement();


    }
    protected void onResume(){
        super.onResume();
        System.out.println(getIntent().getIntExtra("ID_",-1));
        Electronics electronics = dataManagement.getProductData(getIntent().getIntExtra("ID_",-1));
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
    }
    public void upDateProductButton(View view){
        dataManagement.updateProductData( productManufacturer.getText().toString(), productCategory.getText().toString(),
                productName.getText().toString(),Integer.parseInt(productStock.getText().toString()), Integer.parseInt(amountBroken.getText().toString()), productDescription.getText().toString(),getIntent().getIntExtra("ID_",-1));

        // reset form input text field
        Intent intent = new Intent(this, Product_management_product_infoActivity.class);
        productManufacturer.setText("");
        productName.setText("");
        productStock.setText("");
        productCategory.setText("");
        productDescription.setText("");
        amountBroken.setText("");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ID_",getIntent().getIntExtra("ID_",-1));
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, Product_managementActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
