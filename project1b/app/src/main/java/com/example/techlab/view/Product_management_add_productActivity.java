package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.util.BlockedUserUtils;
import com.example.techlab.util.ImageUtils;

public class Product_management_add_productActivity extends DrawerMenu {
    private static final int RESULT_LOAD_IMAGE = 1;
    TextInputLayout productId,productManufacturer,productName,productStock,productCategory,productDescription;
    ImageView productUploadimageView;
    Bitmap resizedImage;
    DataManagement dataManagement;
    Uri selectedImage;
    boolean imageSelected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_product_management_add_product, null,false);
        frameLayout.addView(activityView);

        productId = findViewById(R.id.productIdTextInput);
        productManufacturer = findViewById(R.id.productManufacturerTextInput);
        productName = findViewById(R.id.productNameTextInput);
        productStock = findViewById(R.id.productStockTextInput);
        productCategory = findViewById(R.id.productCategoryTextInput);
        productDescription = findViewById(R.id.productDescriptionTextInput);
        productUploadimageView = findViewById(R.id.productUploadimageView);
        dataManagement = new DataManagement();

    }

    public void uploadImage(View view){
        Intent intent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent,RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            selectedImage = data.getData();
            productUploadimageView.setImageURI(selectedImage);
            imageSelected= true;
        }
    }

    public void addNewProductButton(View view){
        boolean productIdCheck,productManufacturerCheck,productNameCheck,productStockCheck,productCategoryCheck,productDescriptionCheck,imageCheck;
        productIdCheck = productIdValidation();
        productManufacturerCheck = productManufacturerValidation();
        productNameCheck = productNameValidation();
        productStockCheck = productStockValidation();
        productCategoryCheck = productCategoryValidation();
        productDescriptionCheck = productDescriptionValidation();
        imageCheck = imageSelected;
        if (productIdCheck && productManufacturerCheck && productNameCheck && productStockCheck && productCategoryCheck && productDescriptionCheck && imageCheck){
            resizedImage = ImageUtils.scaleDown(((BitmapDrawable)productUploadimageView.getDrawable()).getBitmap(),250f,true);
            byte[] imageByte = ImageUtils.getByte(resizedImage);
            dataManagement.addProductData(productId.getEditText().getText().toString(),productManufacturer.getEditText().getText().toString()
                    ,productCategory.getEditText().getText().toString(),productName.getEditText().getText().toString(),Integer.parseInt(productStock.getEditText().getText().toString())
                    ,0,imageByte,productDescription.getEditText().getText().toString());

            imageSelected= false;
            // reset form input text field
            productId.getEditText().setText("");
            productManufacturer.getEditText().setText("");
            productName.getEditText().setText("");
            productStock.getEditText().setText("");
            productCategory.getEditText().setText("");
            productDescription.getEditText().setText("");
            Intent intent = new Intent(this, Product_managementActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

    }



    private boolean productIdValidation(){
        String input = productId.getEditText().getText().toString().trim();
        if(input.isEmpty()){
            productId.setError("lege invoerveld is niet toegestaan");
            return false;
        }else{
            productId.setError(null);
            return true;
        }
    }
    private boolean productManufacturerValidation(){
        String input = productManufacturer.getEditText().getText().toString().trim();
        if(input.isEmpty()){
            productManufacturer.setError("lege invoerveld is niet toegestaan");
            return false;
        }else{
            productManufacturer.setError(null);
            return true;
        }
    }
    private boolean productNameValidation(){
        String input = productName.getEditText().getText().toString().trim();
        if(input.isEmpty()){
            productName.setError("lege invoerveld is niet toegestaan");
            return false;
        }else{
            productName.setError(null);
            return true;
        }
    }
    private boolean productStockValidation(){
        String input = productStock.getEditText().getText().toString().trim();
        if(input.isEmpty()){
            productStock.setError("lege invoerveld is niet toegestaan");
            return false;
        }else{
            productStock.setError(null);
            return true;
        }
    }
    private boolean productCategoryValidation(){
        String input = productCategory.getEditText().getText().toString().trim();
        if(input.isEmpty()){
            productCategory.setError("lege invoerveld is niet toegestaan");
            return false;
        }else{
            productCategory.setError(null);
            return true;
        }
    }
    private boolean productDescriptionValidation(){
        String input = productDescription.getEditText().getText().toString().trim();
        if(input.isEmpty()){
            productDescription.setError("lege invoerveld is niet toegestaan");
            return false;
        }else{
            productDescription.setError(null);
            return true;
        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Product_managementActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BlockedUserUtils.blockFunc(this,getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE).getString(MainActivity.KEY_ACTIVE_USER_EMAIL, ""));
    }
}
