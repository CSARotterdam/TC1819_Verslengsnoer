package com.example.techlab.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Borrow;
import com.example.techlab.util.ImageUtils;

import java.util.ArrayList;

public class BorrowAdapter extends RecyclerView.Adapter<BorrowAdapter.BorrowViewHolder> {

    private ArrayList<Borrow> mBorrowItemList;
    private Context context;
    DataManagement dataManagement = new DataManagement();

    public static class BorrowViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        private TextView mProductName,mBorrow,mProductAmount,mProductStatus,mRequest, mreturnDate,
                ListItemText_borrow,ListItemText_TurnInDate,returnDateField;

        RelativeLayout relativeLayout;

        public BorrowViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.ListItemImage);
            mProductName = itemView.findViewById(R.id.ListItemText_ProductName);
            mBorrow = itemView.findViewById(R.id.ListItemText_ProductBorrow);
            mRequest = itemView.findViewById(R.id.ListItemText_ProductRequest);
            mProductAmount = itemView.findViewById(R.id.ListItemText_ProductAmount);
            mProductStatus = itemView.findViewById(R.id.ListItemText_ProductStatus);
            relativeLayout = itemView.findViewById(R.id.BorrowedItemContainer);
            mreturnDate = itemView.findViewById(R.id.returnDate);

            ListItemText_borrow = itemView.findViewById(R.id.ListItemText_borrow);
            ListItemText_TurnInDate = itemView.findViewById(R.id.ListItemText_TurnInDate);
            returnDateField = itemView.findViewById(R.id.returnDateField);
        }
    }

    public BorrowAdapter(ArrayList<Borrow> BorrowItemList, Context context) {
        mBorrowItemList = BorrowItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public BorrowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_borrow_item, parent, false);
        BorrowViewHolder evh = new BorrowViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull BorrowViewHolder borrowViewHolder, int position_index) {
        final Borrow currentItem = mBorrowItemList.get(position_index);
        final int position = position_index;
        borrowViewHolder.mImageView.setImageBitmap(ImageUtils.getImage(currentItem.getImageResource()));
        borrowViewHolder.mProductName.setText(currentItem.getProductName());
        borrowViewHolder.mProductAmount.setText(Integer.toString(currentItem.getBorrowItemAmount()));
        borrowViewHolder.mProductStatus.setText(currentItem.getBorrowStatus());
        if (currentItem.getBorrowStatus().matches(context.getString(R.string.productStatusPending))){
            borrowViewHolder.mBorrow.setVisibility(View.GONE);
            borrowViewHolder.mreturnDate.setVisibility(View.GONE);
            borrowViewHolder.mRequest.setText(currentItem.getRequestDate());
            borrowViewHolder.ListItemText_borrow.setVisibility(View.GONE);
            borrowViewHolder.returnDateField.setVisibility(View.GONE);
            borrowViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder DelRequestItemAlertDialog = new AlertDialog.Builder(context);
                    DelRequestItemAlertDialog.setTitle("Aanvraag annuleren?")
                            .setCancelable(false)
                            .setPositiveButton("Annuleren", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(context, "Aanvraag geannuleerd.", Toast.LENGTH_LONG).show();

                                    //DB code here
                                    dataManagement.DeleteRequestBorrowItem(currentItem.getBorrowID(),currentItem.getBorrowItemAmount(),currentItem.getmProductID());
                                    mBorrowItemList.remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position, mBorrowItemList.size());

                                }
                            })
                            .setNegativeButton("Terug", null);

                    //Creating dialog box
                    DelRequestItemAlertDialog.create().show();
                }
            });
        }else if (currentItem.getBorrowStatus().matches(context.getString(R.string.productStatusOnLoan))){
            borrowViewHolder.mBorrow.setText(currentItem.getmBorrowDate());
            borrowViewHolder.mRequest.setText(currentItem.getRequestDate());
            borrowViewHolder.mreturnDate.setVisibility(View.GONE);
            borrowViewHolder.returnDateField.setVisibility(View.GONE);
        }else if (currentItem.getBorrowStatus().matches(context.getString(R.string.productStatusTeLaat))){
            borrowViewHolder.mBorrow.setText(currentItem.getmBorrowDate());
            borrowViewHolder.mRequest.setText(currentItem.getRequestDate());
            borrowViewHolder.mreturnDate.setVisibility(View.GONE);
            borrowViewHolder.returnDateField.setVisibility(View.GONE);
            borrowViewHolder.mProductStatus.setTextColor(Color.parseColor("#d8041d"));
        }else if (currentItem.getBorrowStatus().matches(context.getString(R.string.productStatusReturned))){
            borrowViewHolder.mBorrow.setText(currentItem.getmBorrowDate());
            borrowViewHolder.mRequest.setText(currentItem.getRequestDate());
            borrowViewHolder.mreturnDate.setText(currentItem.getReturnDate());
        }
    }

    @Override
    public int getItemCount() {
        return mBorrowItemList.size();
    }

}
