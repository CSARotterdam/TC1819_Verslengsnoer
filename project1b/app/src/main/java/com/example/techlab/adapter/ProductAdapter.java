package com.example.techlab.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.techlab.R;
import com.example.techlab.databinding.ActivityProductItemBinding;
import com.example.techlab.model.Electronics;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Electronics> electronics;
    private Context context;

    public ProductAdapter(List<Electronics> electronics, Context context) {
        this.electronics = electronics;
        this.context = context;
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
        Electronics electronic = electronics.get(i);
        viewHolder.productItemBinding.setElectronicsItem(electronic);
    }

    @Override
    public int getItemCount() {
        return electronics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // Binding variables
        public ActivityProductItemBinding productItemBinding;

        public ViewHolder(ActivityProductItemBinding productLayoutBinding) {
            super(productLayoutBinding.getRoot());
            productItemBinding = productLayoutBinding;

        }
    }

}
