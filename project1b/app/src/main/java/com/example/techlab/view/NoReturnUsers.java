package com.example.techlab.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.techlab.R;
import com.example.techlab.adapter.NoReturnUsersAdapter;
import com.example.techlab.model.TelaatGebrItems;
import java.util.ArrayList;

public class NoReturnUsers extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_return_users);

        ArrayList<TelaatGebrItems> TelaatList = new ArrayList<>();
        TelaatList.add(new TelaatGebrItems("TelaatGebr 1", "TelaatStuNum 2", "TelaatPr 3"));
        TelaatList.add(new TelaatGebrItems("TelaatGebr 4", "TelaatStuNum 5", "TelaatPr 6"));
        TelaatList.add(new TelaatGebrItems("TelaatGebr 7", "TelaatStuNum 8", "TelaatPr 9"));

        mRecyclerView = findViewById(R.id.NoreturnUserlist_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutmanager = new LinearLayoutManager(this);
        mAdapter = new NoReturnUsersAdapter(TelaatList);

        mRecyclerView.setLayoutManager(mLayoutmanager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
