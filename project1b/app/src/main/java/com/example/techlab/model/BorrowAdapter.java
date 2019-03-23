package com.example.techlab.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.techlab.R;

import java.util.ArrayList;

public class BorrowAdapter extends RecyclerView.Adapter<BorrowAdapter.BorrowViewHolder> {

    private ArrayList<Borrow> mBorrowItemList;

    public static class BorrowViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mProductName;
        public TextView mTurnInDate;
        public TextView mProductAmount;
        public TextView mProductStatus;

        public BorrowViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.ListItemImage);
            mProductName = itemView.findViewById(R.id.ListItemText_ProductName);
            mTurnInDate = itemView.findViewById(R.id.ListItemText_TurnInDate);
            mProductAmount = itemView.findViewById(R.id.ListItemText_ProductAmount);
            mProductStatus = itemView.findViewById(R.id.ListItemText_ProductStatus);
        }
    }

    public BorrowAdapter(ArrayList<Borrow> BorrowItemList){
        mBorrowItemList = BorrowItemList;
    }


    @NonNull
    @Override
    public BorrowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_borrow_item_template, parent, false);
        BorrowViewHolder evh = new BorrowViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull BorrowViewHolder borrowViewHolder, int position_index) {
        Borrow currentItem = mBorrowItemList.get(position_index);
        borrowViewHolder.mImageView.setImageResource(currentItem.getImageResource());
        borrowViewHolder.mProductName.setText(currentItem.getProductName());
        borrowViewHolder.mTurnInDate.setText(currentItem.getTurnInDate());
        borrowViewHolder.mProductAmount.setText(currentItem.getBorrowItemAmount());
        borrowViewHolder.mProductStatus.setText(currentItem.getBorrowStatus());
    }

    @Override
    public int getItemCount() {
       return mBorrowItemList.size();
    }
}
