package com.example.techlab.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.techlab.R;
import com.example.techlab.adapter.AangevraagdItems_UserList_Adapter;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Borrow;

import java.util.ArrayList;

public class AangevraagdItems_UserList extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutmanager;
    DataManagement dataManagement;
    ArrayList<Borrow> loanUsersList;
    Spinner CategorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user_aangvr);
        dataManagement = new DataManagement();
        loanUsersList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.AangevraagUserlist_Recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutmanager = new LinearLayoutManager(AangevraagdItems_UserList.this);
        mAdapter = new AangevraagdItems_UserList_Adapter(AangevraagdItems_UserList.this,loanUsersList);
        mRecyclerView.setLayoutManager(mLayoutmanager);
        mRecyclerView.setAdapter(mAdapter);


        CategorySpinner = findViewById(R.id.BorrowCategoryButton);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.BorrowCategory, android.R.layout.simple_dropdown_item_1line);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        CategorySpinner.setAdapter(adapter2);
        CategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().matches("Alle aangevraagde producten")){
                    loanUsersList  = dataManagement.getBorrowDataListWithStatus(getString(R.string.productStatusPending));
                }else if(parent.getSelectedItem().toString().matches("Alle Uitgeleende producten")){
                    loanUsersList  = dataManagement.getBorrowDataListWithStatus(getString(R.string.productStatusOnLoan));
                }else if(parent.getSelectedItem().toString().matches("Alle teruggebrachte producten")){
                    loanUsersList  = dataManagement.getBorrowDataListWithStatus(getString(R.string.productStatusReturned));
                }

                mAdapter = new AangevraagdItems_UserList_Adapter(AangevraagdItems_UserList.this,loanUsersList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent startNewActivity = new Intent(this, Product_InventoryActivity.class);
        startActivity(startNewActivity);
    }

}