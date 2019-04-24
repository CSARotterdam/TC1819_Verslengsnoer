package com.example.techlab.db;

import android.graphics.Bitmap;

import com.example.techlab.model.Electronics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManagement {
    Connection connect;
    String ConnectionResult="";
    Boolean isSuccess=false;

    public ArrayList<Electronics> getAllProductData(){
        ArrayList<Electronics> electronicsList = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                String query = "SELECT * FROM ELECTRONICS";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    electronicsList.add(new Electronics(resultSet.getString("PRODUCT_ID"),resultSet.getString("MANUFACTURER"),resultSet.getString("CATEGORY"),resultSet.getString("PRODUCT_NAME"),resultSet.getInt("STOCK"),resultSet.getInt("AMOUNT_BROKEN"),resultSet.getString("DESCRIPTION")));
                }
                ConnectionResult="successful";
                isSuccess=true;
                connect.close();
            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();

        }
        return electronicsList;

    }
    public void addProductData(String productID, String manufacturer, String category, String productName, int stock, int amountBroken, byte[] image){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                String query = "INSERT INTO ELECTRONICS (PRODUCT_ID, MANUFACTURER, CATEGORY, PRODUCT_NAME, STOCK, AMOUNT_BROKEN, IMAGE) " +
                        "VALUES ("+ productID +","+manufacturer+","+category+","+productName+","+stock+","+amountBroken+","+image+");";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();
        }
    }

}
