package com.example.techlab.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.techlab.R;
import com.example.techlab.model.Borrow;
import com.example.techlab.adapter.BorrowAdapter;

import java.util.ArrayList;

public class GeleendeProductActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geleende_product);

//        This needs to be done through database
        ArrayList<Borrow> BorrowItemList = new ArrayList<>();
        BorrowItemList.add(new Borrow(R.drawable.ic_android, "IPhone", "23-3-2019", "1", "Pending"));
        BorrowItemList.add(new Borrow(R.drawable.ic_camera, "Camera", "23-3-2019", "2", "Pending"));
        BorrowItemList.add(new Borrow(R.drawable.ic_videogame, "PS4", "23-3-2019", "1", "Geleend"));

        mRecyclerView = findViewById(R.id.BorrowItemRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new BorrowAdapter(BorrowItemList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void BorrowItemRecyclerView(View view){

    }
}
