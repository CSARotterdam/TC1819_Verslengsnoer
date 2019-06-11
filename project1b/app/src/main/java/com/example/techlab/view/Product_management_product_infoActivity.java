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
import com.example.techlab.model.Electronics;
import com.example.techlab.util.AlertDialogUtils;
import com.example.techlab.util.BlockedUserUtils;
import com.example.techlab.util.ImageUtils;

public class Product_management_product_infoActivity extends DrawerMenu {
    TextView productName, productId, productStock, productCategory,productAmountBroken
            ,productManufacturer, productDescription;
    ImageView productManagementImageView;
    DataManagement dataManagement;
    Electronics product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_product_management_product_info, null,false);
        frameLayout.addView(activityView);

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
        BlockedUserUtils.blockFunc(this,getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE).getString(MainActivity.KEY_ACTIVE_USER_EMAIL, ""));

        product = dataManagement.getProductWithId(getIntent().getIntExtra("ID_",-1));
        productName.setText(product.getName());
        productId.setText(product.getProductId());
        productStock.setText(String.valueOf(product.getStock()));
        productCategory.setText(product.getCategory());
        productAmountBroken.setText(String.valueOf(product.getAmountBroken()));
        productManufacturer.setText(product.getProductManufacturer());
        productDescription.setText(product.getDescription());
        Bitmap decodedByte = ImageUtils.getImage(product.getImage());
        productManagementImageView.setImageBitmap(decodedByte);
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
    public void deleteProduct(View view){
        if (product.getProductOnLoan()==0) {
            dataManagement.DeleteProduct(getIntent().getIntExtra("ID_", -1));
            Intent startNewActivity = new Intent(this, Product_managementActivity.class);
            startNewActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startNewActivity);
            finish();
        }else {
            AlertDialogUtils.alertDialog(this,"Mislukt !!","Er zijn nog steeds producten die uitgeleend zijn !");
        }
    }
    public void upDateProduct(View view){
        Intent startNewActivity = new Intent(this, Product_management_product_UpdateActivity.class);
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
