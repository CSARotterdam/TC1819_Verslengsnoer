package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Borrow;
import com.example.techlab.adapter.BorrowAdapter;

import java.util.ArrayList;

public class Student_BorrowedActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    DataManagement dataManagement;
    private SharedPreferences mSharedPreferences;
    ArrayList<Borrow> BorrowItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geleende_product);
        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);
        dataManagement = new DataManagement();
        BorrowItemList = dataManagement.getBorrowData(mSharedPreferences.getInt(MainActivity.PREFERENCE_USERID,-1));

        mRecyclerView = findViewById(R.id.BorrowItemRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new BorrowAdapter(BorrowItemList, this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent startNewActivity = new Intent(this,  Product_InventoryActivity.class);
        startActivity(startNewActivity);
    }
}
