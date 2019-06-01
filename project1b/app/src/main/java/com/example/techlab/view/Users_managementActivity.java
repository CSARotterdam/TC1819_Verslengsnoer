package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.techlab.R;
import com.example.techlab.adapter.UsersManagementAdapter;
import com.example.techlab.databinding.ActivityUsersManagementBinding;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Users;

import java.util.ArrayList;

public class Users_managementActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    SharedPreferences mSharedPreferences;
    RecyclerView.LayoutManager mLayoutManager;

    DataManagement dataManagement;
    UsersManagementAdapter adapter;
    ArrayList<Users> userList;

    ActivityUsersManagementBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_users_management);
//        setContentView(R.layout.activity_users_management);
        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);
        dataManagement = new DataManagement();
        userList = new ArrayList<>();

//        System.out.println("Users_managementActivity: On Create started");
        adapter = new UsersManagementAdapter(userList,this);

        mRecyclerView = findViewById(R.id.usersListItem);
        mLayoutManager = new LinearLayoutManager(Users_managementActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        System.out.println(getApplicationContext().toString()+" onResume started...");
        Spinner CategorySpinner = findViewById(R.id.CategoryUsersBtn);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.UsersCategory, android.R.layout.simple_dropdown_item_1line);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        CategorySpinner.setAdapter(adapter2);
        CategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getSelectedItem().toString().matches("Alle geblokkeerde gebruikers")){
                    userList = dataManagement.getBlockedUsers();
                }else{
                    // By default: show All users.
                    userList = dataManagement.getAllUserDataExceptFor(1);
                }
                adapter = new UsersManagementAdapter(userList, Users_managementActivity.this);
                mRecyclerView.setAdapter(adapter);

                binding.usersListItem.setAdapter(adapter);
                binding.usersListItem.setLayoutManager(new LinearLayoutManager(Users_managementActivity.this));
                binding.usersListItem.setNestedScrollingEnabled(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this,  Product_InventoryActivity.class));
    }
}
