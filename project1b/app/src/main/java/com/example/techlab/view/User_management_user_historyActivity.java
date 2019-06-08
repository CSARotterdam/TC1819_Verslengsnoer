package com.example.techlab.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.example.techlab.R;
import com.example.techlab.adapter.BorrowAdapter;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Borrow;

import java.util.ArrayList;

public class User_management_user_historyActivity extends DrawerMenu {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    DataManagement dataManagement;
    private SharedPreferences mSharedPreferences;
    ArrayList<Borrow> borrowItemList;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_user_management_user_history, null,false);
        frameLayout.addView(activityView);
        userId = getIntent().getIntExtra("ID_",-1);
        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);
        dataManagement = new DataManagement();
        borrowItemList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.userManagementUserHistoryRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(User_management_user_historyActivity.this);
        mAdapter = new BorrowAdapter(borrowItemList, User_management_user_historyActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        Spinner CategorySpinner = findViewById(R.id.userManagementBorrowCategoryButton);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.BorrowCategoryUserManagement, android.R.layout.simple_dropdown_item_1line);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        CategorySpinner.setAdapter(adapter2);

        CategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().matches("Alle producten in bruikleen")){
                    dataManagement.StatusTeLaat();
                    borrowItemList = dataManagement.getBorrowDataWithUserId(userId,getString(R.string.productStatusPending),getString(R.string.productStatusOnLoan),getString(R.string.productStatusTeLaat));
                } else if(parent.getSelectedItem().toString().matches("activiteiten geschiedenis")){
                    borrowItemList = dataManagement.getBorrowDataWithUserId(userId,getString(R.string.productStatusReturned));
                }
                mAdapter = new BorrowAdapter(borrowItemList, User_management_user_historyActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
