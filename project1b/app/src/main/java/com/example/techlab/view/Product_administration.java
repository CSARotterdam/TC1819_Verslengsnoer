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
import com.example.techlab.adapter.AangevraagdItems_UserList_Adapter;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Borrow;
import com.example.techlab.util.CheckBlockUtils;

import java.util.ArrayList;

public class Product_administration extends DrawerMenu {
    private RecyclerView mRecyclerView;
    private AangevraagdItems_UserList_Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutmanager;

    DataManagement dataManagement;
    ArrayList<Borrow> loanUsersList;
    Spinner CategorySpinner;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_list_user_aangvr, null,false);
        frameLayout.addView(activityView);
        dataManagement = new DataManagement();
        loanUsersList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.AangevraagUserlist_Recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutmanager = new LinearLayoutManager(Product_administration.this);
        mAdapter = new AangevraagdItems_UserList_Adapter(Product_administration.this,loanUsersList);
        mRecyclerView.setLayoutManager(mLayoutmanager);
        mRecyclerView.setAdapter(mAdapter);

        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putString(MainActivity.KEY_PRODUCT_ADMINISTER_SPINNER_STATE,getString(R.string.productStatusPending));
        mEditor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        CheckBlockUtils.ExecuteCheckBlock(this, mSharedPreferences.getString(MainActivity.KEY_ACTIVE_USER_EMAIL,""),"Product_Administrations");

        int spinnerPosition;
        String spinnerState = mSharedPreferences.getString(MainActivity.KEY_PRODUCT_ADMINISTER_SPINNER_STATE,"");
        if(spinnerState.matches(getString(R.string.productStatusTeLaat))){
            spinnerPosition = 3;
        } else if (spinnerState.matches(getString(R.string.productStatusReturned))){
            spinnerPosition = 2;
        }else if (spinnerState.matches(getString(R.string.productStatusOnLoan))){
            spinnerPosition = 1;
        }else {
            spinnerPosition = 0;
        }
        CategorySpinner = findViewById(R.id.BorrowCategoryButton);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.BorrowCategory, android.R.layout.simple_dropdown_item_1line);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        CategorySpinner.setAdapter(adapter2);
        CategorySpinner.setSelection(spinnerPosition);
        CategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem= parent.getSelectedItem().toString();
                if (selectedItem.matches("Alle aangevraagde producten")){
                    loanUsersList  = dataManagement.getBorrowDataListWithStatus(getString(R.string.productStatusPending));
                }else if(selectedItem.matches("Alle Uitgeleende producten")){
                    dataManagement.StatusTeLaat();
                    loanUsersList  = dataManagement.getBorrowDataListWithStatus(getString(R.string.productStatusOnLoan),getString(R.string.productStatusTeLaat));
                }else if(selectedItem.matches("Alle teruggebrachte producten")){
                    loanUsersList  = dataManagement.getBorrowDataListWithStatus(getString(R.string.productStatusReturned));
                }else if(selectedItem.matches("Alle Te Late producten")){
                    dataManagement.StatusTeLaat();
                    loanUsersList  = dataManagement.getBorrowDataListWithStatus(getString(R.string.productStatusTeLaat));
                }

                mAdapter = new AangevraagdItems_UserList_Adapter(Product_administration.this,loanUsersList);
                mRecyclerView.setAdapter(mAdapter);
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
                mAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Product_InventoryActivity.class));
        finish();
    }
}