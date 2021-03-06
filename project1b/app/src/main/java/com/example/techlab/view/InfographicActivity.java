package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.techlab.R;
import com.example.techlab.db.DataManagementInfographic;
import com.example.techlab.util.CheckBlockUtils;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class InfographicActivity extends DrawerMenu  {
    PieChart pieChart;
    PieChart pieChart2;
    DataManagementInfographic dataManagementInfographic;
    private SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Source: https://www.youtube.com/watch?v=MiVx3AQD_PI

        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_infographic, null,false);
        frameLayout.addView(activityView);

        dataManagementInfographic = new DataManagementInfographic();
        mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return ((int) value) + " ";
            }
        };

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
        pieDataSet1.setColors(ColorTemplate.createColors(getResources(),
                new int[]{R.color.red_400,
                R.color.deep_purple_400,
                        R.color.light_blue_400,
                        R.color.green_400,
                        R.color.teal_400,
                        R.color.deep_orange_400,
                        R.color.pink_400,
                        R.color.cyan_400,
                        R.color.purple_400,
                        R.color.lime_400}));
        PieData data = new PieData((pieDataSet1));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.WHITE);
        data.setValueFormatter(formatter);
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
        pieDataSet2.setColors(ColorTemplate.createColors(getResources(),
                new int[]{R.color.red_400,
                        R.color.deep_purple_400,
                        R.color.light_blue_400,
                        R.color.green_400,
                        R.color.teal_400,
                        R.color.deep_orange_400,
                        R.color.pink_400,
                        R.color.cyan_400,
                        R.color.purple_400,
                        R.color.lime_400}));
        PieData data2 = new PieData((pieDataSet2));
        data2.setValueTextSize(10f);
        data2.setValueTextColor(Color.WHITE);
        data2.setValueFormatter(formatter);
        pieChart2.setData(data2);

    }

    @Override
    protected void onResume(){
        super.onResume();
        CheckBlockUtils.ExecuteCheckBlock(this, mSharedPreferences.getString(MainActivity.KEY_ACTIVE_USER_EMAIL,""),"InfoGraphic");
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,  Product_InventoryActivity.class));
        finish();
    }


}
