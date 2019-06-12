package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.example.techlab.R;
import com.example.techlab.adapter.UsersManagementAdapter;
import com.example.techlab.databinding.ActivityUsersManagementBinding;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Users;

import java.util.ArrayList;

public class Users_managementActivity extends DrawerMenu {
    private static final String FILTER_OPTION = "FILTER_OPTION";
    private RecyclerView mRecyclerView;
    SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    RecyclerView.LayoutManager mLayoutManager;

    DataManagement dataManagement;
    UsersManagementAdapter adapter;
    ArrayList<Users> userList;

//    ActivityUsersManagementBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_users_management, null,false);
        frameLayout.addView(activityView);
        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        dataManagement = new DataManagement();
        userList = new ArrayList<>();

        adapter = new UsersManagementAdapter(userList,this);

        mRecyclerView = findViewById(R.id.usersListItem);
        mLayoutManager = new LinearLayoutManager(Users_managementActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
        mEditor.putString(FILTER_OPTION,"ALL");
        mEditor.apply();
    }

    @Override
    protected void onResume(){
        super.onResume();

        Spinner CategorySpinner = findViewById(R.id.CategoryUsersBtn);
        final String SpinnerState = mSharedPreferences.getString(FILTER_OPTION, "");
        int spinnerPosition;
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.UsersCategory, android.R.layout.simple_dropdown_item_1line);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        CategorySpinner.setAdapter(adapter2);

        if (SpinnerState.matches("BLOCKED")){
            spinnerPosition = 1;
        }
        else{
            spinnerPosition = 0;
        }

        CategorySpinner.setSelection(spinnerPosition);
        CategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().matches("Alle geblokkeerde gebruikers")) {
                    userList = dataManagement.getBlockedUsers(mSharedPreferences.getInt(MainActivity.KEY_ACTIVE_USER_ID,-1));
                    mEditor.putString(FILTER_OPTION,"BLOCKED");
                }else{
                    // By default: show All users.
                    userList = dataManagement.getAllUserDataExceptFor(mSharedPreferences.getInt(MainActivity.KEY_ACTIVE_USER_ID,-1));
                    mEditor.putString(FILTER_OPTION,"ALL");
                }

                adapter = new UsersManagementAdapter(userList, Users_managementActivity.this);
                mRecyclerView.setAdapter(adapter);
                mEditor.apply();
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
        startActivity(new Intent(this,  Product_InventoryActivity.class));
        finish();
    }
}
