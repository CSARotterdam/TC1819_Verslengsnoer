package com.example.techlab.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.techlab.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;

public class InfographicActivity extends AppCompatActivity {
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infographic);
        pieChart = findViewById(R.id.mostPopularProductChart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(10,10,10,10);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.animateY(1000, Easing.EaseInOutCubic);


//
//        PieDataSet dataSet = new PieDataSet(Value, "Counties");
//        dataSet.setSliceSpace(1f);
//
//
//        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
//        PieData data = new PieData((dataSet));
//        data.setValueTextSize(10f);
//        data.setValueTextColor(Color.WHITE);
//        pieChart.setData(data);




    }
    @Override
    public void onBackPressed() {
        finish();
        Intent startNewActivity = new Intent(this,  Product_InventoryActivity.class);
        startActivity(startNewActivity);
    }

}
