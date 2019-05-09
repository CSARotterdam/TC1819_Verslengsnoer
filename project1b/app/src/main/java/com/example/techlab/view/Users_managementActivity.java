package com.example.techlab.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.example.techlab.R;
import com.example.techlab.adapter.UsersManagementAdapter;
import com.example.techlab.databinding.ActivityUsersManagementBinding;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Users;

import java.util.ArrayList;

public class Users_managementActivity extends AppCompatActivity {
    DataManagement dataManagement;
    ActivityUsersManagementBinding binding;
    UsersManagementAdapter adapter;
    SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_users_management);
        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);
        dataManagement = new DataManagement();
        ArrayList<Users> userList = dataManagement.getAllUserData(mSharedPreferences.getInt(MainActivity.PREFERENCE_USERID,-1));
        adapter = new UsersManagementAdapter(userList,this);
        binding.usersListItem.setAdapter(adapter);
        binding.usersListItem.setLayoutManager(new LinearLayoutManager(this));
        binding.usersListItem.setNestedScrollingEnabled(false);
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
}
