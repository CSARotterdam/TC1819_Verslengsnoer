package com.example.techlab.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.imageHelper.imageConverter;
import com.example.techlab.model.Books;

public class Product_management_book_updateActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 1;
    TextInputLayout bookTitle,bookWriters,bookIsbn,bookPublisher,bookAmount,bookDescription;
    ImageView bookUploadImageView;
    Bitmap resizedImage;
    DataManagement dataManagement;
    Uri selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management_book_update);

        bookTitle = findViewById(R.id.bookTitleUpdateTextInput);
        bookWriters = findViewById(R.id.bookWritersUpdateTextInput);
        bookIsbn = findViewById(R.id.bookIsbnUpdateTextInput);
        bookPublisher = findViewById(R.id.bookPublisherUpdateTextInput);
        bookAmount = findViewById(R.id.bookAmountUpdateTextInput);
        bookDescription = findViewById(R.id.bookDescriptionUpdateTextInput);
        bookUploadImageView = findViewById(R.id.bookUploadimageUpdateView);
        dataManagement = new DataManagement();
        Books book = dataManagement.getBookWithId(getIntent().getIntExtra("ID_",-1));
        bookTitle.getEditText().setText(book.getName());
        bookWriters.getEditText().setText(book.getWriters());
        bookAmount.getEditText().setText(String.valueOf(book.getStock()));
        bookPublisher.getEditText().setText(book.getPublisher());
        bookIsbn.getEditText().setText(book.getISBN());
        bookDescription.getEditText().setText(String.valueOf(book.getDescription()));
        bookUploadImageView.setImageBitmap(imageConverter.getImage(book.getImage()));

    }

    @Override
    protected void onResume(){
        super.onResume();
    }
    @Override
    protected void onPause(){
        super.onPause();
    }

    public void updateImage(View view){
        Intent intent =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent,RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            selectedImage = data.getData();
            bookUploadImageView.setImageURI(selectedImage);
        }
    }

    public void updateBookButton(View view){
        boolean productIdCheck,productManufacturerCheck,productNameCheck,productStockCheck,productCategoryCheck,productDescriptionCheck,imageCheck;
        productIdCheck = productIdValidation();
        productManufacturerCheck = productManufacturerValidation();
        productNameCheck = productNameValidation();
        productStockCheck = productStockValidation();
        productCategoryCheck = productCategoryValidation();
        productDescriptionCheck = productDescriptionValidation();
        if (productIdCheck && productManufacturerCheck && productNameCheck && productStockCheck && productCategoryCheck && productDescriptionCheck ){
            resizedImage = imageConverter.scaleDown(((BitmapDrawable)bookUploadImageView.getDrawable()).getBitmap(),250f,true);
            byte[] imageByte = imageConverter.getByte(resizedImage);
            dataManagement.updateBookData(bookTitle.getEditText().getText().toString(),bookWriters.getEditText().getText().toString()
                    ,bookIsbn.getEditText().getText().toString(),bookPublisher.getEditText().getText().toString(),Integer.parseInt(bookAmount.getEditText().getText().toString())
                    ,bookDescription.getEditText().getText().toString(),imageByte,"book",getIntent().getIntExtra("ID_",-1));

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
        Intent startNewActivity = new Intent(this, Product_managementActivity.class);
        startActivity(startNewActivity);
        finish();
    }
}