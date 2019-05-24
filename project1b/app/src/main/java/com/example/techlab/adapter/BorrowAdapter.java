package com.example.techlab.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.techlab.util.ImageUtils;
import com.example.techlab.model.Borrow;

import java.util.ArrayList;

public class BorrowAdapter extends RecyclerView.Adapter<BorrowAdapter.BorrowViewHolder> {

    private ArrayList<Borrow> mBorrowItemList;
    private Context context;
    DataManagement dataManagement = new DataManagement();

    public static class BorrowViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        private TextView mProductName,mBorrow,mProductAmount,mProductStatus,mRequest;

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

        }
    }

    public BorrowAdapter(ArrayList<Borrow> BorrowItemList, Context context) {
        mBorrowItemList = BorrowItemList;
        this.context = context;
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
        final Borrow currentItem = mBorrowItemList.get(position_index);
        final int position = position_index;
        borrowViewHolder.mImageView.setImageBitmap(ImageUtils.getImage(currentItem.getImageResource()));
        borrowViewHolder.mProductName.setText(currentItem.getProductName());
        borrowViewHolder.mBorrow.setText(currentItem.getmBorrowDate());
        borrowViewHolder.mRequest.setText(currentItem.getRequestDate());
        borrowViewHolder.mProductAmount.setText(Integer.toString(currentItem.getBorrowItemAmount()));
        borrowViewHolder.mProductStatus.setText(currentItem.getBorrowStatus());
        if (currentItem.getBorrowStatus().matches("Pending")) {
            borrowViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder DelRequestItemAlertDialog = new AlertDialog.Builder(context);
                    DelRequestItemAlertDialog.setTitle("Aanvraag intrekken?")
                            .setCancelable(false)
                            .setPositiveButton("Intrekken", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(context, "Aanvraag geannuleerd.", Toast.LENGTH_LONG).show();

                                    //DB code here
                                    dataManagement.DelRequestBorrowItem(currentItem.getmProductID());
                                    mBorrowItemList.remove(position);
                                    notifyDataSetChanged(); //Update view.
                                }
                            })
                            .setNegativeButton("Terug", null);
                    
                    //Creating dialog box
                    AlertDialog dialog = DelRequestItemAlertDialog.create();
                    dialog.show();
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return mBorrowItemList.size();
    }
}
