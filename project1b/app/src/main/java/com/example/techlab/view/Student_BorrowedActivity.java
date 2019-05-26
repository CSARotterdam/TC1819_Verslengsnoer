package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.techlab.R;
import com.example.techlab.adapter.BorrowAdapter;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Borrow;

import java.util.ArrayList;

public class Student_BorrowedActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    DataManagement dataManagement;
    private SharedPreferences mSharedPreferences;
    ArrayList<Borrow> borrowItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geleende_product);
        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);
        dataManagement = new DataManagement();
        borrowItemList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.BorrowItemRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(Student_BorrowedActivity.this);
        mAdapter = new BorrowAdapter(borrowItemList, Student_BorrowedActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        Spinner CategorySpinner = findViewById(R.id.BorrowCategoryButton);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.BorrowCategoryStudent, android.R.layout.simple_dropdown_item_1line);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        CategorySpinner.setAdapter(adapter2);

        CategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().matches("Alle aangevraagde en geleende producten")){
                    borrowItemList = dataManagement.getBorrowDataWithUserId(mSharedPreferences.getInt(MainActivity.KEY_ACTIVE_USER_ID,-1),getString(R.string.productStatusPending),getString(R.string.productStatusOnLoan));
                } else if(parent.getSelectedItem().toString().matches("Alle teruggebrachte producten")){
                    borrowItemList = dataManagement.getBorrowDataWithUserId(mSharedPreferences.getInt(MainActivity.KEY_ACTIVE_USER_ID,-1),getString(R.string.productStatusReturned));
                }
                mAdapter = new BorrowAdapter(borrowItemList, Student_BorrowedActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent startNewActivity = new Intent(this,  Product_InventoryActivity.class);
        startActivity(startNewActivity);
    }
}
