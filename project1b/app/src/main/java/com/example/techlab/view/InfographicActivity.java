package com.example.techlab.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.techlab.R;
import com.example.techlab.db.DataManagementInfographic;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class InfographicActivity extends AppCompatActivity {
    PieChart pieChart;
    DataManagementInfographic dataManagementInfographic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infographic);
        dataManagementInfographic = new DataManagementInfographic();
        ArrayList<PieEntry> Value = dataManagementInfographic.getBorrowDataList();
        pieChart = findViewById(R.id.mostPopularProductChart);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(10,10,10,10);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setEntryLabelColor(Color.BLACK);

        pieChart.setTransparentCircleRadius(61f);
        pieChart.animateY(1000, Easing.EaseInOutCubic);
        PieDataSet dataSet = new PieDataSet(Value, "Meest populaire producten");
        dataSet.setSliceSpace(1f);

        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.WHITE);
        pieChart.setData(data);




    }
    @Override
    public void onBackPressed() {
        finish();
        Intent startNewActivity = new Intent(this,  Product_InventoryActivity.class);
        startActivity(startNewActivity);
    }

}
