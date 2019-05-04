package com.example.techlab.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.view.AangevraagdItems_UserList;
import com.example.techlab.view.Itemadapter_loanUsers;

import java.util.ArrayList;

public class AangevraagdItems_UserList_Adapter extends RecyclerView.Adapter<AangevraagdItems_UserList_Adapter.AangevraagdItems_UserListViewHolder> {

    private ArrayList<Itemadapter_loanUsers> mItemadapter_loanUsers;

    public static class AangevraagdItems_UserListViewHolder extends RecyclerView.ViewHolder{

        public TextView mPrnaam;
        public TextView mGebrnaam;

        public AangevraagdItems_UserListViewHolder(@NonNull View itemView) {
            super(itemView);
            mPrnaam = itemView.findViewById(R.id.Productnaam_Textview);
            mGebrnaam = itemView.findViewById(R.id.Gebruiker_Textview);
        }
    }

    public AangevraagdItems_UserList_Adapter(ArrayList<Itemadapter_loanUsers> Itemadapter_loanUsersList){
        mItemadapter_loanUsers = Itemadapter_loanUsersList;
    }


    @NonNull
    @Override
    public AangevraagdItems_UserListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.loan_userlist_layout, viewGroup, false);
        AangevraagdItems_UserListViewHolder AI_ULVH  = new AangevraagdItems_UserListViewHolder(v);
        return AI_ULVH;
    }

    @Override
    public void onBindViewHolder(@NonNull AangevraagdItems_UserListViewHolder holder, int i) {
        Itemadapter_loanUsers  currentItem  = mItemadapter_loanUsers.get(i);

        holder.mPrnaam.setText(currentItem.getPrnaam());
        holder.mGebrnaam.setText(currentItem.getGebrnaam());
    }

    @Override
    public int getItemCount() {
        return mItemadapter_loanUsers.size();
    }
}
