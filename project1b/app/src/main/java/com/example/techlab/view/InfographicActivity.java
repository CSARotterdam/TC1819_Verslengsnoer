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
    PieChart pieChart2;
    DataManagementInfographic dataManagementInfographic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infographic);
        dataManagementInfographic = new DataManagementInfographic();




        pieChart = findViewById(R.id.mostPopularProductChart);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(10,10,10,10);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.animateY(1000, Easing.EaseInOutCubic);
        ArrayList<PieEntry> MostPopularProductValue = dataManagementInfographic.getMostPopularProductData();
        PieDataSet pieDataSet1 = new PieDataSet(MostPopularProductValue, "");
        pieDataSet1.setSliceSpace(1f);
        pieDataSet1.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data = new PieData((pieDataSet1));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.WHITE);
        pieChart.setData(data);

        pieChart2 = findViewById(R.id.mostPopularUserChart);
        pieChart2.getDescription().setEnabled(false);
        pieChart2.setExtraOffsets(10,10,10,10);
        pieChart2.setDragDecelerationFrictionCoef(0.95f);
        pieChart2.setEntryLabelColor(Color.BLACK);
        pieChart2.setTransparentCircleRadius(61f);
        pieChart2.animateY(1000, Easing.EaseInOutCubic);
        ArrayList<PieEntry> MostactiveUserValue = dataManagementInfographic.getMostActiveUserData();
        PieDataSet pieDataSet2 = new PieDataSet(MostactiveUserValue,"");
        pieDataSet2.setSliceSpace(1f);
        pieDataSet2.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data2 = new PieData((pieDataSet2));
        data2.setValueTextSize(10f);
        data2.setValueTextColor(Color.WHITE);
        pieChart2.setData(data2);








    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this,  Product_InventoryActivity.class));
    }

}
