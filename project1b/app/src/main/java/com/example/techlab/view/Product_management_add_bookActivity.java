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
import com.example.techlab.util.ImageUtils;

public class Product_management_add_bookActivity extends DrawerMenu {

    private static final int RESULT_LOAD_IMAGE = 1;
    TextInputLayout bookTitle,bookWriters,bookIsbn,bookPublisher,bookAmount,bookDescription;
    ImageView bookUploadimageView;
    Bitmap resizedImage;
    DataManagement dataManagement;
    Uri selectedImage;
    boolean imageSelected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_product_management_add_book, null,false);
        frameLayout.addView(activityView);

        bookTitle = findViewById(R.id.bookTitleTextInput);
        bookWriters = findViewById(R.id.bookWritersTextInput);
        bookIsbn = findViewById(R.id.bookIsbnTextInput);
        bookPublisher = findViewById(R.id.bookPublisherTextInput);
        bookAmount = findViewById(R.id.bookAmountTextInput);
        bookDescription = findViewById(R.id.bookDescriptionTextInput);
        bookUploadimageView = findViewById(R.id.bookUploadimageView);
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
            bookUploadimageView.setImageURI(selectedImage);
            imageSelected= true;
        }
    }

    public void addNewBookButton(View view){
        boolean productIdCheck,productManufacturerCheck,productNameCheck,productStockCheck,productCategoryCheck,productDescriptionCheck,imageCheck;
        productIdCheck = productIdValidation();
        productManufacturerCheck = productManufacturerValidation();
        productNameCheck = productNameValidation();
        productStockCheck = productStockValidation();
        productCategoryCheck = productCategoryValidation();
        productDescriptionCheck = productDescriptionValidation();
        imageCheck = imageSelected;
        if (productIdCheck && productManufacturerCheck && productNameCheck && productStockCheck && productCategoryCheck && productDescriptionCheck && imageCheck){
            resizedImage = ImageUtils.scaleDown(((BitmapDrawable)bookUploadimageView.getDrawable()).getBitmap(),250f,true);
            byte[] imageByte = ImageUtils.getByte(resizedImage);
            dataManagement.InsertBookItem(bookTitle.getEditText().getText().toString(),bookWriters.getEditText().getText().toString()
                    ,bookIsbn.getEditText().getText().toString(),bookPublisher.getEditText().getText().toString(),Integer.parseInt(bookAmount.getEditText().getText().toString())
                    ,bookDescription.getEditText().getText().toString(),imageByte,"Book");

            imageSelected= false;
            // reset form input text field
            bookTitle.getEditText().setText("");
            bookWriters.getEditText().setText("");
            bookIsbn.getEditText().setText("");
            bookPublisher.getEditText().setText("");
            bookAmount.getEditText().setText("");
            bookDescription.getEditText().setText("");
            Intent intent = new Intent(this, Product_managementActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

    }



    private boolean productIdValidation(){
        String input = bookTitle.getEditText().getText().toString().trim();
        if(input.isEmpty()){
            bookTitle.setError("lege invoerveld is niet toegestaan");
            return false;
        }else{
            bookTitle.setError(null);
            return true;
        }
    }
    private boolean productManufacturerValidation(){
        String input = bookWriters.getEditText().getText().toString().trim();
        if(input.isEmpty()){
            bookWriters.setError("lege invoerveld is niet toegestaan");
            return false;
        }else{
            bookWriters.setError(null);
            return true;
        }
    }
    private boolean productNameValidation(){
        String input = bookIsbn.getEditText().getText().toString().trim();
        if(input.isEmpty()){
            bookIsbn.setError("lege invoerveld is niet toegestaan");
            return false;
        }else{
            bookIsbn.setError(null);
            return true;
        }
    }
    private boolean productStockValidation(){
        String input = bookPublisher.getEditText().getText().toString().trim();
        if(input.isEmpty()){
            bookPublisher.setError("lege invoerveld is niet toegestaan");
            return false;
        }else{
            bookPublisher.setError(null);
            return true;
        }
    }
    private boolean productCategoryValidation(){
        String input = bookAmount.getEditText().getText().toString().trim();
        if(input.isEmpty()){
            bookAmount.setError("lege invoerveld is niet toegestaan");
            return false;
        }else{
            bookAmount.setError(null);
            return true;
        }
    }
    private boolean productDescriptionValidation(){
        String input = bookDescription.getEditText().getText().toString().trim();
        if(input.isEmpty()){
            bookDescription.setError("lege invoerveld is niet toegestaan");
            return false;
        }else{
            bookDescription.setError(null);
            return true;
        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Product_managementActivity.class));
        finish();
    }
}
