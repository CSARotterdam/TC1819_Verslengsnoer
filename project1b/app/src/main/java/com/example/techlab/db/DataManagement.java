package com.example.techlab.db;

import com.example.techlab.model.Borrow;
import com.example.techlab.model.Electronics;
import com.example.techlab.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DataManagement {
    Connection connect;
    String ConnectionResult = "";
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

    public void InsertRequestBorrowItem(int ProductID, int UserID, int Amount, String Status){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                PreparedStatement pstmt = connect.prepareStatement("Insert into BORROW (ELECTRONICS_P_ID, USERS_P_ID, AMOUNT, STATUS) values (?,?,?,?)");
                pstmt.setInt(1,ProductID);
                pstmt.setInt(2,UserID);
                pstmt.setInt(3,Amount);
                pstmt.setString(4,Status);
                pstmt.execute();
            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();
        }
    }

    public ArrayList<Borrow> getBorrowData(int UserID){
        ArrayList<Borrow> BorrowList = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                String query = "SELECT * FROM BORROW WHERE USERS_P_ID = "+UserID+";";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    BorrowList.add(new Borrow(getProductData(resultSet.getInt("ELECTRONICS_P_ID")).getName(),resultSet.getString("RETURN_DATE"),resultSet.getInt("AMOUNT"),resultSet.getString("STATUS")));
                }
                ConnectionResult="successful";
                isSuccess=true;
                connect.close();
            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();

        }
        return BorrowList;
    }

    public Users getUser(String SchoolEmail){
        ArrayList<Users> UserData = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                String query = "SELECT * FROM USERS WHERE SCHOOL_EMAIL = '"+SchoolEmail+"';";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()) {
                    UserData.add(new Users(resultSet.getString("FIRSTNAME"), resultSet.getString("SURNAME"), resultSet.getString("SCHOOL_EMAIL"), resultSet.getString("PASSWORD"), resultSet.getInt("LOANED_AMOUNT"), resultSet.getString("USER_TYPE"), resultSet.getInt("ID_")));
                }
                ConnectionResult="successful";
                isSuccess=true;
                connect.close();

            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();

        }
        return UserData.get(0);
    }

    // check
    public boolean ifExists(String emailInput, String passwordInput) {
        ArrayList<Users> UserRow = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                String query = "SELECT * FROM USERS WHERE SCHOOL_EMAIL = '"+emailInput+"' and PASSWORD = '"+passwordInput+"';";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()) {
                    UserRow.add(new Users(resultSet.getString("FIRSTNAME"), resultSet.getString("SURNAME"), resultSet.getString("SCHOOL_EMAIL"), resultSet.getString("PASSWORD"), resultSet.getInt("LOANED_AMOUNT"), resultSet.getString("USER_TYPE"), resultSet.getInt("ID_")));
                }
                ConnectionResult = "successful";
                isSuccess = true;
                connect.close();

            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult = ex.getMessage();

        }
        boolean exists = (UserRow.size() > 0);
        return exists;
    }




}
