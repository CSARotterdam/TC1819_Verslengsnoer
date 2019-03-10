package com.example.techlableenapp.view;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.techlableenapp.Controller.UserManagementItemAdapter;
import com.example.techlableenapp.R;
import com.example.techlableenapp.db.DataSource;
import com.example.techlableenapp.db.TechlabDatabaseHelper;
import com.example.techlableenapp.model.UserExampleItem;

import java.util.ArrayList;

public class UserManagementActivity extends AppCompatActivity {
    DataSource dataSource;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);
        dataSource = new DataSource(this);



    }

    @Override
    protected void onResume(){
        super.onResume();
        dataSource.open();
        Cursor cursor = dataSource.selectAllUsers();
        userManagementItemFiller(cursor);
    }
    @Override
    protected void onPause(){
        super.onPause();
        dataSource.close();
    }

    private void userManagementItemFiller(Cursor cursor){

        ArrayList<UserExampleItem> userExampleList = new ArrayList<>();

        cursor.moveToFirst();
        for (int i = cursor.getCount(); i > 0; i--) {
            userExampleList.add(new UserExampleItem(cursor.getString(0), cursor.getString(1), cursor.getInt(2)));
            if (i>1){
                cursor.moveToNext();
            }
        }

        recyclerView = findViewById(R.id.userRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new UserManagementItemAdapter(userExampleList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
