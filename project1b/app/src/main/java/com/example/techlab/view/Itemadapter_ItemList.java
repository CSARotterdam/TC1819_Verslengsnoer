package com.example.techlab.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.techlab.R;

public class Itemadapter_ItemList extends BaseAdapter {

    LayoutInflater mInflater;
    String[] Items_Gebruikers;
    String[] Id_Items;

    public  Itemadapter_ItemList(Context c, String[] Ig, String[] Id){
        Items_Gebruikers = Ig;
        Id_Items = Id;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return Items_Gebruikers.length;
    }

    @Override
    public Object getItem(int position) {
        return Items_Gebruikers[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.itemlist_detail, null);
        TextView Itemname_Textview = v.findViewById(R.id.Itemname_Textview);
        TextView ItemId_Textview = v.findViewById(R.id.ItemId_Textview);

        String ItemGebr = Items_Gebruikers[position];
        String IdItems =Id_Items[position];

        Itemname_Textview.setText(ItemGebr);
        ItemId_Textview.setText(IdItems);
        return v;
    }
}
