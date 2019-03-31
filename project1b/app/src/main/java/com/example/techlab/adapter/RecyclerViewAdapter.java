package com.example.techlab.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.techlab.R;
import com.example.techlab.view.Product_ItemDescription;

import java.util.ArrayList;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    // This is for debugging
    private static final String TAG = "RecyclerViewAdapter";

    // Array van de product Namen en fotos
    private ArrayList<String> mProductNames = new ArrayList<>();
    private ArrayList<String> mProductDescription = new ArrayList<>();
    private Context mContext;
    private ArrayList<Bitmap> mBitmaps = new ArrayList<>();

    public RecyclerViewAdapter(Context context, ArrayList<String> productNames, ArrayList<String> productDescription, ArrayList<Bitmap> bitmaps){
        mProductNames = productNames;
        mProductDescription = productDescription;
        mContext = context;
        mBitmaps = bitmaps;
    }

    // This recycles the viewholder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        // Here we get the images
        holder.image.setImageBitmap(mBitmaps.get(position));
        // Set the product name
        holder.productName.setText(mProductNames.get(position));
//        holder.productDescription.setText(mProductDescription.get(position)); // It crashes here

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mProductNames.get(position));

                Intent intent = new Intent(mContext, Product_ItemDescription.class);
                intent.putExtra("image",String.valueOf(position));
                intent.putExtra("product_name", mProductNames.get(position));
                intent.putExtra("product_description", mProductDescription.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    // counts total products in array
    public int getItemCount() {
        return mProductNames.size();
    }

    // It holds each product in memory and adds new products
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView productName;
        TextView productDescription;
        RelativeLayout parentLayout;

        // Verwijst naar de id
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            productName = itemView.findViewById(R.id.product_name);
            productDescription = itemView.findViewById(R.id.product_description);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
