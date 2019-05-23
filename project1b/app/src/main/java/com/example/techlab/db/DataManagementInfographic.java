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

    public ArrayList<PieEntry> getBorrowDataList(){
        ArrayList<PieEntry> Value = new ArrayList<>();
        int Product1 = 0,Product2 = 0,Product3 = 0,Product4 = 0,Product5 = 0;
        String ProductName1 = "",ProductName2 = "",ProductName3 = "",ProductName4 = "",ProductName5 = "";
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                String query = "SELECT * FROM BORROW ";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){

                }
                Value.add(new PieEntry(Product1 ,ProductName1));
                Value.add(new PieEntry(Product2 ,ProductName2));
                Value.add(new PieEntry(Product3 ,ProductName3));
                Value.add(new PieEntry(Product4 ,ProductName4));
                Value.add(new PieEntry(Product5 ,ProductName5));


                connect.close();
            }
        }catch(Exception ex){
            Value.add(new PieEntry(1 ,"No data available"));
            Log.d(TAG,ex.toString());
        }
        return Value;
    }

}
