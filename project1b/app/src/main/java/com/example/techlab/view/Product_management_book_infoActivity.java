package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Books;
import com.example.techlab.util.AlertDialogUtils;
import com.example.techlab.util.BlockedUserUtils;
import com.example.techlab.util.ImageUtils;

public class Product_management_book_infoActivity extends DrawerMenu {

    TextView Title, writers, isbn, publisher,amount, description;
    ImageView bookManagementImageView;
    DataManagement dataManagement;
    Books book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_product_management_book_info, null,false);
        frameLayout.addView(activityView);
        Title = findViewById(R.id.bookTitleTextView);
        writers = findViewById(R.id.bookWritersTextView);
        isbn = findViewById(R.id.bookIsbnTextView);
        publisher = findViewById(R.id.bookPublisherTextView);
        amount = findViewById(R.id.bookAmountTextView);
        description = findViewById(R.id.bookDescriptionTextView);
        bookManagementImageView = findViewById(R.id.bookManagementImageView);
        dataManagement = new DataManagement();

    }

    @Override
    protected void onResume(){
        super.onResume();
        BlockedUserUtils.blockFunc(this,getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE).getString(MainActivity.KEY_ACTIVE_USER_EMAIL, ""),"Uw account is geblokkeerd, neem contact met TechLab.");

        book = dataManagement.getBookWithId(getIntent().getIntExtra("ID_",-1));
        Title.setText(book.getName());
        writers.setText(book.getWriters());
        isbn.setText(String.valueOf(book.getISBN()));
        publisher.setText(book.getPublisher());
        amount.setText(String.valueOf(book.getStock()));
        description.setText(book.getDescription());
        Bitmap decodedByte = ImageUtils.getImage(book.getImage());
        bookManagementImageView.setImageBitmap(decodedByte);
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
    public void bookDeleteProduct(View view){
        if (book.getProductOnLoan()==0) {
            dataManagement.DeleteProduct(getIntent().getIntExtra("ID_",-1));
            Intent startNewActivity = new Intent(this, Product_managementActivity.class);
            startNewActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startNewActivity);
            finish();
        }else {
            AlertDialogUtils.alertDialog(this,"Mislukt !!","Er zijn nog steeds boeken die uitgeleend zijn!");
        }
    }
    public void bookUpDateProduct(View view){
        Intent startNewActivity = new Intent(this, Product_management_book_updateActivity.class);
        startNewActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startNewActivity.putExtra("ID_",getIntent().getIntExtra("ID_",-1));
        startActivity(startNewActivity);
        finish();
    }
    @Override
    public void onBackPressed() {
        Intent startNewActivity = new Intent(this, Product_managementActivity.class);
        startNewActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startNewActivity);
        finish();
    }


}