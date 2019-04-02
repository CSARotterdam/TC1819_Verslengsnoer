package com.example.techlab.view;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.techlab.R;

public class AangevraagdItems_UserList extends AppCompatActivity {

    ListView Gebruikers_loanlist;
    String[] Gebruikers;
    String[] Studentnummer;
    String[] Aantal_Items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user_aangvr);

        Resources res = getResources();
        Gebruikers_loanlist = (ListView)findViewById(R.id.Gebruikers_loanlist);
        Gebruikers = res.getStringArray(R.array.Gebruikers);
        Studentnummer = res.getStringArray(R.array.Studentnummer);
        Aantal_Items = res.getStringArray(R.array.Aantal_Items);

        Itemadapter itemadapter = new Itemadapter(this, Gebruikers, Studentnummer, Aantal_Items);
        Gebruikers_loanlist.setAdapter(itemadapter);

        Gebruikers_loanlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ItemList = new Intent(getApplicationContext(), ItemList.class);
                startActivity(ItemList);
            }
        });

    }
}
