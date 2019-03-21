package com.example.techlab.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.techlab.R;
import com.example.techlab.adapter.ProductAdapter;
import com.example.techlab.databinding.ActivityProductBeheerBinding;
import com.example.techlab.model.Electronics;

import java.util.ArrayList;
import java.util.List;

public class ProductManagementActivity extends AppCompatActivity {
    private ActivityProductBeheerBinding binding;
    private ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_product_beheer);
        adapter = new ProductAdapter(getProductData(),this);

        binding.electronicsListItems.setAdapter(adapter);
        binding.electronicsListItems.setLayoutManager(new LinearLayoutManager(this));

    }

    private List<Electronics> getProductData() {
        List<Electronics> electronics = new ArrayList<>();

        Electronics electronic = new Electronics(
                "124"
                ,"Sony"
                ,"playstation"
                ,50
                , 0
                ,"game"
                );
        electronics.add(electronic);
        electronic = new Electronics(
                "124"
                ,"Sony"
                ,"playstation"
                ,50
                , 0
                ,"game"
        );
        electronics.add(electronic);
        return electronics;
    }
}
