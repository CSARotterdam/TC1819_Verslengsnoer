package com.example.techlab.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.techlab.R;
import com.example.techlab.adapter.AangevraagdItems_UserList_Adapter;
import com.example.techlab.db.DataManagement;

import java.util.ArrayList;

public class AangevraagdItems_UserList extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutmanager;
    DataManagement dataManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user_aangvr);
        dataManagement = new DataManagement();

//        ArrayList<Itemadapter_loanUsers> loanUsersList  = new ArrayList<>();
//        loanUsersList.add(new Itemadapter_loanUsers("Poduct1", "Gebruiker1"));
//        loanUsersList.add(new Itemadapter_loanUsers("Poduct2", "Gebruiker1"));
//        loanUsersList.add(new Itemadapter_loanUsers("Poduct3", "Gebruiker2"));

        ArrayList<Itemadapter_loanUsers> loanUsersList  = dataManagement.getBorrowDataList();

        mRecyclerView = findViewById(R.id.AangevraagUserlist_Recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutmanager = new LinearLayoutManager(this);
        mAdapter = new AangevraagdItems_UserList_Adapter(loanUsersList);

        mRecyclerView.setLayoutManager(mLayoutmanager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent startNewActivity = new Intent(this, Product_InventoryActivity.class);
        startActivity(startNewActivity);
    }

}