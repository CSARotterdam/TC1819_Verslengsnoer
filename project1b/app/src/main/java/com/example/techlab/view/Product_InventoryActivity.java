package com.example.techlab.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.techlab.R;
import com.example.techlab.adapter.RecyclerViewAdapter;
import com.example.techlab.db.DataManagement;
import com.example.techlab.db.DataSource;
import com.example.techlab.db.imageConverter;
import com.example.techlab.model.Electronics;

import java.util.ArrayList;

public class Product_InventoryActivity extends AppCompatActivity {
    private static final String TAG = "Product_InventoryActivity";

    // Array van de namen en afbeeldingen van elk product
    private ArrayList<Integer> mId = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mProductDescription = new ArrayList<>();
    private ArrayList<Bitmap> mbitmaps = new ArrayList<>();
    DataManagement dataManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventaris);
        //Log.d(TAG, "onCreate: started.");
        dataManagement = new DataManagement();
    }
    // Voeg hier Producten toe!
    // Product Naam + foto URL
    private void initImageBitmaps(){
        //Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        ArrayList<Electronics> products = dataManagement.getAllProductData();
        for (int i =0; products.size() >i ; i++) {
            mNames.add(products.get(i).getName());
            mbitmaps.add(imageConverter.getImage(dataManagement.getImage(products.get(i).getId_())));
            mProductDescription.add(products.get(i).getDescription());
            mId.add(products.get(i).getId_());
        }
        initRecyclerView();
    }

    private void initRecyclerView(){
        //Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mProductDescription,mbitmaps, mId);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume(){
        super.onResume();
        initImageBitmaps();

    }
    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent startNewActivity = new Intent(this, MenuActivity.class);
        startActivity(startNewActivity);
    }
}
