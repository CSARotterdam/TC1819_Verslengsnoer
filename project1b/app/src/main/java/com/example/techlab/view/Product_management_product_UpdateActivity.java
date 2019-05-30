package com.example.techlab.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.util.ImageUtils;
import com.example.techlab.model.Electronics;

public class Product_management_product_UpdateActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;
    EditText productManufacturer;
    EditText productName;
    EditText productStock;
    EditText productCategory;
    EditText productDescription;
    EditText amountBroken;
    DataManagement dataManagement;
    Uri selectedImage;
    ImageView productUpdateImageView;
    Bitmap resizedImage;
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
        productUpdateImageView = findViewById(R.id.productImageUpdateView);
        dataManagement = new DataManagement();

        Electronics electronics = dataManagement.getProductWithId(getIntent().getIntExtra("ID_",-1));
        productManufacturer.setText(electronics.getProductManufacturer());
        productName.setText(electronics.getName());
        productStock.setText(String.valueOf(electronics.getStock()));
        productCategory.setText(electronics.getCategory());
        productDescription.setText(electronics.getDescription());
        amountBroken.setText(String.valueOf(electronics.getAmountBroken()));
        productUpdateImageView.setImageBitmap(ImageUtils.getImage(electronics.getImage()));
    }


    public void updateProductImage(View view){
        Intent intent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent,RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            selectedImage = data.getData();
            productUpdateImageView.setImageURI(selectedImage);
        }
    }

    public void upDateProductButton(View view){
        resizedImage = ImageUtils.scaleDown(((BitmapDrawable)productUpdateImageView.getDrawable()).getBitmap(),250f,true);
        byte[] imageByte = ImageUtils.getByte(resizedImage);
        dataManagement.updateProductData( productManufacturer.getText().toString(), productCategory.getText().toString(),
                productName.getText().toString(),Integer.parseInt(productStock.getText().toString()),
                Integer.parseInt(amountBroken.getText().toString()), productDescription.getText().toString(),
                imageByte,getIntent().getIntExtra("ID_",-1));

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
