package com.example.techlab.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.model.TelaatGebrItems;

import java.util.ArrayList;

public class NoReturnUsersAdapter extends RecyclerView.Adapter<NoReturnUsersAdapter.NoReturnUsersViewholder> {

    private ArrayList<TelaatGebrItems> mTelaatGebrList;

    public static class NoReturnUsersViewholder extends RecyclerView.ViewHolder{

        public TextView mTelaatGebr;
        public TextView mTelaatStuNum;
        public TextView mTelaatPr;

        public NoReturnUsersViewholder(@NonNull View itemView) {
            super(itemView);
            mTelaatGebr = itemView.findViewById(R.id.gebruikers_Textview);
            mTelaatStuNum = itemView.findViewById(R.id.studentennummers_Textview);
            mTelaatPr = itemView.findViewById(R.id.telaat_pr_Textview);
        }
    }

    public NoReturnUsersAdapter(ArrayList<TelaatGebrItems> TelaatGebrList){
        mTelaatGebrList = TelaatGebrList;
    }

    @NonNull
    @Override
    public NoReturnUsersViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.template_noreturn_users, viewGroup, false);
       NoReturnUsersViewholder NRUVh = new NoReturnUsersViewholder(v);
       return NRUVh;
    }

    @Override
    public void onBindViewHolder(@NonNull NoReturnUsersViewholder holder, int i) {
        TelaatGebrItems currentItem = mTelaatGebrList.get(i);

        holder.mTelaatGebr.setText(currentItem.getTelaatGebr());
        holder.mTelaatStuNum.setText(currentItem.getTelaatStuNum());
        holder.mTelaatPr.setText(currentItem.getTelaatPr());
    }

    @Override
    public int getItemCount() { return mTelaatGebrList.size(); }
}
