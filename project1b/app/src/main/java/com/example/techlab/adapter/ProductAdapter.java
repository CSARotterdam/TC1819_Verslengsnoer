package com.example.techlab.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.techlab.R;
import com.example.techlab.databinding.ActivityProductItemBinding;
import com.example.techlab.model.Electronics;
import com.example.techlab.model.Users;
import com.example.techlab.view.ProductDeleteAndUpDateActivity;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Electronics> electronics;
    private Context context;
    private Users activeUser;

    public ProductAdapter(List<Electronics> electronics, Context context, Users activeUser) {
        this.electronics = electronics;
        this.context = context;
        this.activeUser = activeUser;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ActivityProductItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext())
                , R.layout.activity_product_item
                ,viewGroup
                ,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Electronics electronic = electronics.get(i);
        viewHolder.productItemBinding.setElectronicsItem(electronic);
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDeleteAndUpDateActivity.class);
                intent.putExtra("productID",electronic.getProductId());
                intent.putExtra("activeUser",activeUser);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return electronics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout relativeLayout;
        // Binding variables
        public ActivityProductItemBinding productItemBinding;

        public ViewHolder(ActivityProductItemBinding productLayoutBinding) {
            super(productLayoutBinding.getRoot());
            productItemBinding = productLayoutBinding;
            relativeLayout = itemView.findViewById(R.id.productItemView);

        }
    }

}
