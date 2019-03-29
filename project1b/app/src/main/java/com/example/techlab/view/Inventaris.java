package com.example.techlab.view;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.techlab.R;
import com.example.techlab.db.DataSource;
import com.example.techlab.db.imageConverter;
import com.example.techlab.model.Users;

import java.util.ArrayList;

public class Inventaris extends AppCompatActivity {
    private static final String TAG = "Inventaris";

    // Array van de namen en afbeeldingen van elk product
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mProductDescription = new ArrayList<>();
    private ArrayList<Bitmap> mbitmaps = new ArrayList<>();
    DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventaris);
        Log.d(TAG, "onCreate: started.");
        dataSource = new DataSource(this);


    }
    // Voeg hier Producten toe!
    // Product Naam + foto URL
    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        Cursor cursor = dataSource.selectAllproduct();
        cursor.moveToFirst();
        for (int i = cursor.getCount(); i > 0; i--) {
            mNames.add(cursor.getString(2));
            mbitmaps.add(imageConverter.getImage(cursor.getBlob(7)));
            mProductDescription.add(cursor.getString(6));
            if (i>1){
                cursor.moveToNext();
            }
        }



        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        com.example.techlab.view.RecyclerViewAdapter adapter = new com.example.techlab.view.RecyclerViewAdapter(this, mNames, mProductDescription,mbitmaps);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume(){
        super.onResume();
        dataSource.open();
        initImageBitmaps();

    }
    @Override
    protected void onPause(){
        super.onPause();
        dataSource.close();
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent startNewActivity = new Intent(this, RepositoryActivity.class);
        startActivity(startNewActivity);
    }
}
