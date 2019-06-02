package com.example.techlab.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.model.Borrow;
import com.example.techlab.view.Geleend_Aangevraagd;

import java.util.ArrayList;
import java.util.List;

public class AangevraagdItems_UserList_Adapter extends RecyclerView.Adapter<AangevraagdItems_UserList_Adapter.AangevraagdItems_UserListViewHolder> implements Filterable {
    private Context mContext;
    private ArrayList<Borrow> mBorrowItemlist;
    private ArrayList<Borrow> mBorrowItemlistfull;

    public static class AangevraagdItems_UserListViewHolder extends RecyclerView.ViewHolder{

        LinearLayout parentLayout;
        public TextView mPrnaam;
        public TextView mGebrnaam;

        public AangevraagdItems_UserListViewHolder(@NonNull View itemView) {
            super(itemView);
            mPrnaam = itemView.findViewById(R.id.Productnaam_Textview);
            mGebrnaam = itemView.findViewById(R.id.Gebruiker_Textview);
            parentLayout = itemView.findViewById(R.id.pr_aanvraag_returnOnclick);
        }
    }

    public AangevraagdItems_UserList_Adapter(Context context ,ArrayList<Borrow> Itemadapter_loanUsersList){
        mBorrowItemlist = Itemadapter_loanUsersList;
        this.mBorrowItemlistfull = new ArrayList<>(Itemadapter_loanUsersList);
        mContext = context;
    }


    @NonNull
    @Override
    public AangevraagdItems_UserListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.template_loan_userlist, viewGroup, false);
        AangevraagdItems_UserListViewHolder AI_ULVH  = new AangevraagdItems_UserListViewHolder(v);
        return AI_ULVH;
    }

    @Override
    public void onBindViewHolder(@NonNull AangevraagdItems_UserListViewHolder holder, int i) {
        final Borrow  currentItem  = mBorrowItemlist.get(i);

        holder.mPrnaam.setText(currentItem.getProductName());
        holder.mGebrnaam.setText(currentItem.getmGebrnaam());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Geleend_Aangevraagd.class);
//                int s = currentItem.getmProductID();
//                String sd = currentItem.getProductName();
//                String sdf = currentItem.getmGebrnaam();
//                int srt = currentItem.getBorrowItemAmount();

                intent.putExtra("P_id_ProductBorrowList",currentItem.getBorrowID());

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBorrowItemlist.size();
    }
    @Override
    public Filter getFilter() {
        return borrowFilter;
    }

    private Filter borrowFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Borrow> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(mBorrowItemlistfull);
            } else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Borrow borrow : mBorrowItemlistfull){
                    if(borrow.getProductName().toLowerCase().contains(filterPattern)||borrow.getmGebrnaam().toLowerCase().contains(filterPattern)){
                        filteredList.add(borrow);
                    }

                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mBorrowItemlist.clear();
            mBorrowItemlist.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

}