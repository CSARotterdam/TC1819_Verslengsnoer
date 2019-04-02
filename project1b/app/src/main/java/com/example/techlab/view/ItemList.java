package com.example.techlab.view;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.techlab.R;

public class ItemList extends AppCompatActivity {

    ListView ItemList;
    String[] Items_Gebruikers;
    String[] Id_Items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Resources res = getResources();
        ItemList = (ListView)findViewById(R.id.ItemList);
        Items_Gebruikers = res.getStringArray(R.array.Items_Gebruikers);
        Id_Items = res.getStringArray(R.array.Id_Items);

        Itemadapter_ItemList Itemadapter_ItemList = new Itemadapter_ItemList(this, Items_Gebruikers, Id_Items);
        ItemList.setAdapter(Itemadapter_ItemList);

        ItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent Loanitem = new Intent(getApplicationContext(), LoanItem.class);
                startActivity(Loanitem);
            }
        });

    }
}
