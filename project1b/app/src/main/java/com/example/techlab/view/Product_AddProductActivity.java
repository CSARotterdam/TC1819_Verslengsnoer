package com.example.techlab.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.db.DataSource;
import com.example.techlab.db.imageConverter;
import com.example.techlab.model.Electronics;
import com.example.techlab.model.Users;

public class Product_AddProductActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;
    private EditText productId;
    private EditText productManufacturer;
    private EditText productName;
    private EditText productStock;
    private EditText productCategory;
    private EditText productDescription;
    private DataSource dataSource;
    private ImageView productUploadimageView;
    private Bitmap image;
    DataManagement dataManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        productId = findViewById(R.id.productIdTextInput);
        productManufacturer = findViewById(R.id.productManufacturerTextInput);
        productName = findViewById(R.id.productNameTextInput);
        productStock = findViewById(R.id.productStockTextInput);
        productCategory = findViewById(R.id.productCategoryTextInput);
        productDescription = findViewById(R.id.productDescriptionTextInput);
        productUploadimageView = findViewById(R.id.productUploadimageView);
        dataManagement = new DataManagement();

        dataSource = new DataSource(this);
        Intent intent = getIntent();
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

    public void uploadImage(View view){
        Intent intent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent,RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            productUploadimageView.setImageURI(selectedImage);
        }
    }

    public void addNewProductButton(View view){
        // creating new product instance
        Electronics newProduct = new Electronics(
                productId.getText().toString(),
                productManufacturer.getText().toString(),
                productCategory.getText().toString(),
                productName.getText().toString(),
                Integer.parseInt(productStock.getText().toString()),
                0,
                productDescription.getText().toString())
                ;
        image = ((BitmapDrawable)productUploadimageView.getDrawable()).getBitmap();
        int width = image.getWidth()/4;
        int height = image.getHeight()/4;
        dataManagement.addProductData(productId.getText().toString(),productManufacturer.getText().toString()
                ,productCategory.getText().toString(),productName.getText().toString(),Integer.parseInt(productStock.getText().toString())
                ,0,imageConverter.getByte(image));


        // reset form input text field
        productId.setText("");
        productManufacturer.setText("");
        productName.setText("");
        productStock.setText("");
        productCategory.setText("");
        productDescription.setText("");
        Intent intent = new Intent(this, Product_ProductManagementActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent startNewActivity = new Intent(this, Product_ProductManagementActivity.class);
        startActivity(startNewActivity);
    }
}
