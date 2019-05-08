package com.example.techlab.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.techlab.R;
import com.example.techlab.databinding.ActivityInventoryItemBinding;
import com.example.techlab.db.imageConverter;
import com.example.techlab.model.Books;
import com.example.techlab.model.Electronics;
import com.example.techlab.model.Products;
import com.example.techlab.view.Product_ItemDescription;

import java.util.List;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    // This is for debugging
    private static final String TAG = "RecyclerViewAdapter";

    // Array van de product Namen en fotos
    private Context mContext;
    private List<Products> products;

    public RecyclerViewAdapter(Context context,List<Products> products){
        mContext = context;
        this.products = products;
    }

    // This recycles the viewholder
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ActivityInventoryItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext())
                        , R.layout.activity_inventory_item
                        ,viewGroup
                        ,false);
        return new RecyclerViewAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called.");
        final Products product = products.get(i);
        // Here we get the images
        viewHolder.image.setImageBitmap(imageConverter.getImage(product.getImage()));

        viewHolder.productItemBinding.setProduct(product);
//        holder.productDescription.setText(mProductDescription.get(position)); // It crashes here

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Product_ItemDescription.class);
                intent.putExtra("id", Integer.valueOf(product.getId_()));
                if (product instanceof Books){
                    intent.putExtra("object_type","book");
                }
                if (product instanceof Electronics){
                    intent.putExtra("object_type","electronic");
                }
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    // counts total products in array
    public int getItemCount() {
        return products.size();
    }

    // It holds each product in memory and adds new products
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        RelativeLayout parentLayout;
        public ActivityInventoryItemBinding productItemBinding;

        public ViewHolder(ActivityInventoryItemBinding ProductItemBinding) {
            super(ProductItemBinding.getRoot());
            productItemBinding =ProductItemBinding;
            image = itemView.findViewById(R.id.image);
            parentLayout = itemView.findViewById(R.id.inventoryProductItemView);
        }
    }
}
