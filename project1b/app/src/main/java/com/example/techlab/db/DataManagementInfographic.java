package com.example.techlab.db;

import android.util.Log;

import com.github.mikephil.charting.data.PieEntry;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataManagementInfographic {
    Connection connect;
    private static final String TAG = "dataManagement";

    public ArrayList<PieEntry> getMostPopularProductData() {
        ArrayList<PieEntry> Value = new ArrayList<>();

        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null) {
                Log.d(TAG, "Check your internet connection!");
            } else {
                String query = "SELECT * FROM PRODUCTS ORDER BY LOANED_AMOUNT DESC";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                int i = 0;
                while (resultSet.next()) {
                    if (i < 5&&resultSet.getInt("LOANED_AMOUNT")>0) {
                        Value.add(new PieEntry(resultSet.getInt("LOANED_AMOUNT"), resultSet.getString("PRODUCT_NAME")));
                        i++;
                    }else {
                        i=i+resultSet.getInt("LOANED_AMOUNT");
                    }
                }
                if(i>5){
                    Value.add(new PieEntry(i-5, "Andere producten"));
                    connect.close();
                }
            }
        } catch (Exception ex) {
            Value.add(new PieEntry(1, "No data available"));
            Log.d(TAG, ex.toString());
        }
        return Value;
    }
    public ArrayList<PieEntry> getMostActiveUserData() {
        ArrayList<PieEntry> Value = new ArrayList<>();

        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null) {
                Log.d(TAG, "Check your internet connection!");
            } else {
                String query = "SELECT * FROM USERS ORDER BY LOANED_AMOUNT DESC";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                int i = 0;
                while (resultSet.next()) {
                    if (i < 5&&resultSet.getInt("LOANED_AMOUNT")>0) {
                        Value.add(new PieEntry(resultSet.getInt("LOANED_AMOUNT"), resultSet.getString("FIRSTNAME")+" "+resultSet.getString("SURNAME")));
                        i++;
                    }else {
                        i=i+resultSet.getInt("LOANED_AMOUNT");
                    }
                }
                if(i>5){
                    Value.add(new PieEntry(i-5, "Andere gebruikers"));
                    connect.close();
                }
            }
        } catch (Exception ex) {
            Value.add(new PieEntry(1, "No data available"));
            Log.d(TAG, ex.toString());
        }
        return Value;
    }


}
