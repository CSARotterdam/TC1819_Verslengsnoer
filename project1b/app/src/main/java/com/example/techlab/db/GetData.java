package com.example.techlab.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetData {
    Connection connect;
    String ConnectionResult="";
    Boolean isSuccess=false;

    public List<Map<String,String>> getdata(){
        List<Map<String,String>> data = null;
        data = new ArrayList<Map<String,String>>();

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
                    Map<String,String> dataNum = new HashMap<String,String>();
                    dataNum.put("ID", resultSet.getString("ID_"));
                    dataNum.put("PRODUCT_ID", resultSet.getString("PRODUCT_ID"));
                    dataNum.put("MANUFACTURER", resultSet.getString("MANUFACTURER"));
                    dataNum.put("CATEGORY", resultSet.getString("CATEGORY"));
                    dataNum.put("PRODUCT_NAME", resultSet.getString("PRODUCT_NAME"));
                    dataNum.put("STOCK", resultSet.getString("STOCK"));
                    dataNum.put("AMOUNT_BROKEN", resultSet.getString("AMOUNT_BROKEN"));
                    data.add(dataNum);
                }
                ConnectionResult="successful";
                isSuccess=true;
                connect.close();
            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();

        }
        return data;

    }

}
