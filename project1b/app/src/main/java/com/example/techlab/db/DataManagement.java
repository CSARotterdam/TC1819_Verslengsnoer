package com.example.techlab.db;

import com.example.techlab.model.Electronics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
                    electronicsList.add(new Electronics(resultSet.getString("PRODUCT_ID"),resultSet.getString("MANUFACTURER"),resultSet.getString("CATEGORY"),resultSet.getString("PRODUCT_NAME"),resultSet.getInt("STOCK"),resultSet.getInt("AMOUNT_BROKEN"),resultSet.getString("DESCRIPTION"),resultSet.getInt("ID_")));
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
    public Electronics getProductData(int id_){
        ArrayList<Electronics> electronicsList = new ArrayList<>();
        try{

            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                String query = "SELECT * FROM ELECTRONICS WHERE ID_ = "+id_+";";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()) {
                    electronicsList.add(new Electronics(resultSet.getString("PRODUCT_ID"), resultSet.getString("MANUFACTURER"), resultSet.getString("CATEGORY"), resultSet.getString("PRODUCT_NAME"), resultSet.getInt("STOCK"), resultSet.getInt("AMOUNT_BROKEN"), resultSet.getString("DESCRIPTION"), resultSet.getInt("ID_")));
                }
                ConnectionResult="successful";
                isSuccess=true;
                connect.close();

            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();

        }
        return electronicsList.get(0);
    }
    public byte[] getImage(int id_) {
        ArrayList<byte[]> resultImage = new ArrayList<>();
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null) {
                ConnectionResult = "Check your internet access";
            } else {
                String query = "SELECT IMAGE FROM ELECTRONICS WHERE ID_ = " + id_ + ";";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()) {

                    resultImage.add(resultSet.getBytes("IMAGE") );
                }
                ConnectionResult = "successful";
                isSuccess = true;
                connect.close();

            }
        } catch (Exception ex) {
            isSuccess = false;
            ConnectionResult = ex.getMessage();

        }
        return resultImage.get(0);
    }

    public void addProductData(String productID, String manufacturer, String category, String productName, int stock, int amountBroken, byte[] image, String description){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                PreparedStatement pstmt = connect.prepareStatement("Insert into ELECTRONICS (PRODUCT_ID, MANUFACTURER, CATEGORY, PRODUCT_NAME, STOCK, AMOUNT_BROKEN, DESCRIPTION, IMAGE) " +
                        "values ( ?,?,?,?,?,?,?,?)");
                pstmt.setString(1,productID);
                pstmt.setString(2,manufacturer);
                pstmt.setString(3,category);
                pstmt.setString(4,productName);
                pstmt.setInt(5,stock);
                pstmt.setInt(6,amountBroken);
                pstmt.setString(7,description);
                pstmt.setBytes(8,image);
                pstmt.execute();

            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();
        }
    }
    public void updateProductData( String manufacturer, String category, String productName, int stock, int amountBroken, String description, int ID_){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                String query = "UPDATE ELECTRONICS SET MANUFACTURER = '"+manufacturer+"', CATEGORY= '"+category+"', PRODUCT_NAME= '"+productName+"', " +
                        "STOCK="+stock+", AMOUNT_BROKEN="+amountBroken+", DESCRIPTION= '"+description+"' WHERE ID_ = "+ID_+";";

                Statement statement = connect.createStatement();
                statement.executeQuery(query);
            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();
        }
    }
    public void DeleteProduct(int ID_){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{


                String query = "DELETE FROM ELECTRONICS WHERE ID_ ="+ID_+";";
                Statement statement = connect.createStatement();
                statement.executeQuery(query);
            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();
        }
    }


}
