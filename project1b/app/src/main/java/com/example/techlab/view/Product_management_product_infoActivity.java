package com.example.techlab.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.db.imageConverter;
import com.example.techlab.model.Electronics;

public class Product_management_product_infoActivity extends AppCompatActivity {
    TextView productName, productId, productStock, productCategory,productAmountBroken
            ,productManufacturer, productDescription;
    ImageView productManagementImageView;
    DataManagement dataManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management_product_info);
        productName = findViewById(R.id.productNameTextView);
        productId = findViewById(R.id.productIdTextView);
        productStock = findViewById(R.id.productStockTextView);
        productCategory = findViewById(R.id.productCategoryTextView);
        productAmountBroken = findViewById(R.id.prodyctAmountBrokenTextView);
        productManufacturer = findViewById(R.id.productManufacturerTextView);
        productDescription = findViewById(R.id.productDescriptionTextView);
        productManagementImageView = findViewById(R.id.productManagementImageView);
        dataManagement = new DataManagement();

    }

    @Override
    protected void onResume(){
        super.onResume();
        Electronics product = dataManagement.getProductData(getIntent().getIntExtra("ID_",-1));
        productName.setText(product.getName());
        productId.setText(product.getProductId());
        productStock.setText(String.valueOf(product.getStock()));
        productCategory.setText(product.getCategory());
        productAmountBroken.setText(String.valueOf(product.getAmountBroken()));
        productManufacturer.setText(product.getProductManufacturer());
        productDescription.setText(product.getDescription());
        byte[] Image = dataManagement.getImage(getIntent().getIntExtra("ID_",-1));
        Bitmap decodedByte = imageConverter.getImage(Image);
        productManagementImageView.setImageBitmap(decodedByte);
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
    public void deleteProduct(View view){
        dataManagement.DeleteProduct(getIntent().getIntExtra("ID_",-1));
        Intent startNewActivity = new Intent(this, Product_managementActivity.class);
        startActivity(startNewActivity);
    }
    public void upDateProduct(View view){
        Intent startNewActivity = new Intent(this, Product_management_product_UpdateActivity.class);
        startNewActivity.putExtra("ID_",getIntent().getIntExtra("ID_",-1));
        startActivity(startNewActivity);
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent startNewActivity = new Intent(this, Product_managementActivity.class);
        startActivity(startNewActivity);
    }

}
