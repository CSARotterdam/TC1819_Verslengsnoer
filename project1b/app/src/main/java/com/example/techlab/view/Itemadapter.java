package com.example.techlab.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.techlab.R;

public class Itemadapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] Gebruikers;
    String[] Studentnummer;
    String[] Aantal_Items;

    public Itemadapter(Context c, String[] Ge, String[] St, String[] Ai){
        Gebruikers = Ge;
        Studentnummer = St;
        Aantal_Items = Ai;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return Gebruikers.length;
    }

    @Override
    public Object getItem(int position) {
        return Gebruikers[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.loan_userlist_detail, null);
        TextView gebruiker_Textview = v.findViewById(R.id.gebruiker_Textview);
        TextView studentennummer_Textview = v.findViewById(R.id.studentennummer_Textview);
        TextView aantal_it_Textview = v.findViewById(R.id.aantal_it_Textview);

        String Gebr = Gebruikers[position];
        String StuNum = Studentnummer[position];
        String AantalI = Aantal_Items[position];

        gebruiker_Textview.setText(Gebr);
        studentennummer_Textview.setText(StuNum);
        aantal_it_Textview.setText(AantalI);
        return v;
    }
}
