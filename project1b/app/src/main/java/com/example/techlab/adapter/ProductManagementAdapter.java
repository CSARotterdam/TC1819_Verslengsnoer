package com.example.techlab.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;

import com.example.techlab.R;
import com.example.techlab.databinding.ActivityProductManagementItemBinding;
import com.example.techlab.model.Books;
import com.example.techlab.model.Electronics;
import com.example.techlab.model.Products;
import com.example.techlab.view.Product_management_book_infoActivity;
import com.example.techlab.view.Product_management_product_infoActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductManagementAdapter extends RecyclerView.Adapter<ProductManagementAdapter.ViewHolder> implements Filterable {
    private List<Products> products;
    private List<Products> productsListFull;
    private Context context;
    public ProductManagementAdapter(List<Products> products, Context context) {
        this.products = products;
        this.context = context;
        this.productsListFull = new ArrayList<>(products);
    }
    @NonNull
    @Override
    public ProductManagementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ActivityProductManagementItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext())
                , R.layout.activity_product_management_item
                ,viewGroup
                ,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Products product = products.get(i);

        viewHolder.productItemBinding.setProduct(product);
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(product instanceof Electronics){
                    Intent intent = new Intent(context, Product_management_product_infoActivity.class);
                    intent.putExtra("ID_",product.getId_());
                    context.startActivity(intent);
                }
                if(product instanceof Books){
                    Intent intent = new Intent(context, Product_management_book_infoActivity.class);
                    intent.putExtra("ID_",product.getId_());
                    context.startActivity(intent);

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public Filter getFilter() {
        return productsFilter;
    }
    private Filter productsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Products> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(productsListFull);
            } else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Products product : productsListFull){
                    if(product.getName().toLowerCase().contains(filterPattern)||product.getCategory().toLowerCase().contains(filterPattern)){
                        filteredList.add(product);
                    }

                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            products.clear();
            products.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout relativeLayout;
        // Binding variables
        public ActivityProductManagementItemBinding productItemBinding;

        public ViewHolder(ActivityProductManagementItemBinding productLayoutBinding) {
            super(productLayoutBinding.getRoot());
            productItemBinding = productLayoutBinding;
            relativeLayout = itemView.findViewById(R.id.productItemView);

        }
    }

}
