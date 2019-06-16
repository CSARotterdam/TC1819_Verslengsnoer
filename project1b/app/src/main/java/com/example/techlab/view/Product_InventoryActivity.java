package com.example.techlab.view;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.example.techlab.R;
import com.example.techlab.adapter.RecyclerViewAdapter;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Products;
import com.example.techlab.util.CheckBlockUtils;

import java.util.ArrayList;

public class Product_InventoryActivity extends DrawerMenu{

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Products> products;
    DataManagement dataManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        frameLayout.addView(layoutInflater.inflate(R.layout.activity_inventory, null,false));

        dataManagement = new DataManagement();
        products = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapter(Product_InventoryActivity.this, products);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Product_InventoryActivity.this));

        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    //  https://www.youtube.com/watch?reload=9&v=ATERxKKORbY
    private void addNotification(){
        //  Here we build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.mipmap.logo_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.logo_round))
                .setContentTitle("Product(en) Te Laat")
                .setContentText("Tik om te late producten te zien")
                .setOngoing(true)       //You can see the notification in lockscreen
                .setVibrate(new long[] {0,100})
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setChannelId("1")
                .setAutoCancel(true);       //clear notification after click

        //  When you click on the notification you go to Student_Geleend_Aangevraagd screen
        Intent notification = new Intent(this,Student_Geleend_Aangevraagd.class);
        builder.setContentIntent(PendingIntent.getActivity(this, 0,notification, PendingIntent.FLAG_UPDATE_CURRENT));

        //  Notify the system that there is a notification
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1,builder.build());
    }

    @Override
    protected void onResume() {
        CheckBlockUtils.ExecuteCheckBlock(this, mSharedPreferences.getString(MainActivity.KEY_ACTIVE_USER_EMAIL,""),"Inventaris");
        super.onResume();
        if (dataManagement.GebruikerTeLaat(mSharedPreferences.getInt(MainActivity.KEY_ACTIVE_USER_ID, -1))) {
            addNotification();
        }

        // https://developer.android.com/guide/topics/ui/controls/spinner
        Spinner CategorySpinner = findViewById(R.id.CategoryBttn);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.ProductCategory, android.R.layout.simple_dropdown_item_1line);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        CategorySpinner.setAdapter(adapter2);
        CategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getSelectedItem().toString().matches("Alle Producten")) {
                    products = dataManagement.getAllProducts();
                } else {
                    products = dataManagement.getAllProducts(parent.getSelectedItem().toString());
                }
                adapter = new RecyclerViewAdapter(Product_InventoryActivity.this, products);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    @Override
    public void onBackPressed() { moveTaskToBack(true); }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) { return false; }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
}