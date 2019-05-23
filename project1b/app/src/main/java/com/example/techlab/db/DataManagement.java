package com.example.techlab.db;

import android.util.Log;

import com.example.techlab.model.Books;
import com.example.techlab.model.Borrow;
import com.example.techlab.model.Electronics;
import com.example.techlab.model.Products;
import com.example.techlab.model.Users;
import com.example.techlab.util.DateUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;



public class DataManagement {
    Connection connect;
    private static final String TAG = "dataManagement";
    public void insertUser(String firstName,String surname, String SchoolEmail, String Password) {
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                PreparedStatement pstmt = connect.prepareStatement("Insert into USERS (FIRSTNAME, SURNAME, SCHOOL_EMAIL, PASSWORD, LOANED_AMOUNT, USER_TYPE) " +
                        "values ( ?,?,?,?,?,?)");
                pstmt.setString(1,firstName);
                pstmt.setString(2,surname);
                pstmt.setString(3,SchoolEmail);
                pstmt.setString(4,Password);
                pstmt.setInt(5,0);
                pstmt.setString(6,"student");
                pstmt.execute();
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
    }
    public ArrayList<Products> getAllProducts(){
        ArrayList<Products> electronicsList = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                String query = "SELECT * FROM PRODUCTS ";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    if (resultSet.getString("CATEGORY").matches("Book")){
                        electronicsList.add(new Books(resultSet.getInt("ID_"),resultSet.getString("PRODUCT_NAME"),resultSet.getString("WRITERS"),resultSet.getString("ISBN")
                                ,resultSet.getString("PUBLISHER"),resultSet.getInt("STOCK"),resultSet.getString("DESCRIPTION"),resultSet.getString("CATEGORY"),resultSet.getBytes("IMAGE")));
                    }else{
                        electronicsList.add(new Electronics(resultSet.getString("PRODUCT_ID"),resultSet.getString("MANUFACTURER"),resultSet.getString("CATEGORY"),resultSet.getString("PRODUCT_NAME"),resultSet.getInt("STOCK"),resultSet.getInt("AMOUNT_BROKEN"),resultSet.getString("DESCRIPTION"),resultSet.getInt("ID_"),resultSet.getBytes("IMAGE")));
                    }
                }
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
        return electronicsList;
    }

    public ArrayList<Products> getAllProducts(String category){
        ArrayList<Products> electronicsList = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                String query = "SELECT * FROM PRODUCTS WHERE CONVERT(VARCHAR, CATEGORY) =  '"+category+"';";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    if (resultSet.getString("CATEGORY").matches("Book")){
                        electronicsList.add(new Books(resultSet.getInt("ID_"),resultSet.getString("PRODUCT_NAME"),resultSet.getString("WRITERS"),resultSet.getString("ISBN")
                                ,resultSet.getString("PUBLISHER"),resultSet.getInt("STOCK"),resultSet.getString("DESCRIPTION"),resultSet.getString("CATEGORY"),resultSet.getBytes("IMAGE")));
                    }else{
                        electronicsList.add(new Electronics(resultSet.getString("PRODUCT_ID"),resultSet.getString("MANUFACTURER"),resultSet.getString("CATEGORY"),resultSet.getString("PRODUCT_NAME"),resultSet.getInt("STOCK"),resultSet.getInt("AMOUNT_BROKEN"),resultSet.getString("DESCRIPTION"),resultSet.getInt("ID_"),resultSet.getBytes("IMAGE")));
                    }
                }
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
        return electronicsList;
    }

    public ArrayList<Users> getAllUserData(int ID_){
        ArrayList<Users> usersList = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                String query = "SELECT * FROM USERS WHERE NOT ID_ = "+ID_;
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    usersList.add(new Users(resultSet.getString("FIRSTNAME"),resultSet.getString("SURNAME")
                            ,resultSet.getString("SCHOOL_EMAIL"),resultSet.getString("PASSWORD"),resultSet.getInt("LOANED_AMOUNT"),resultSet.getString("USER_TYPE"),resultSet.getInt("ID_"), resultSet.getInt("BLOCKED")));
                }
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
        return usersList;
    }
    public ArrayList<Users> getAllUserData(){
        ArrayList<Users> usersList = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                String query = "SELECT * FROM USERS";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    usersList.add(new Users(resultSet.getString("FIRSTNAME"),resultSet.getString("SURNAME")
                            ,resultSet.getString("SCHOOL_EMAIL"),resultSet.getString("PASSWORD"),resultSet.getInt("LOANED_AMOUNT"),resultSet.getString("USER_TYPE"),resultSet.getInt("ID_"),resultSet.getInt("BLOCKED")));
                }
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
        return usersList;
    }

    public Electronics getProductWithId(int id_){
        ArrayList<Electronics> electronicsList = new ArrayList<>();
        try{

            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                String query = "SELECT * FROM PRODUCTS WHERE ID_ = "+id_+";";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()) {
                    electronicsList.add(new Electronics(resultSet.getString("PRODUCT_ID"), resultSet.getString("MANUFACTURER"), resultSet.getString("CATEGORY"), resultSet.getString("PRODUCT_NAME"), resultSet.getInt("STOCK"), resultSet.getInt("AMOUNT_BROKEN"), resultSet.getString("DESCRIPTION"), resultSet.getInt("ID_"),resultSet.getBytes("IMAGE")));
                }
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
        return electronicsList.get(0);
    }

    public void addProductData(String productID, String manufacturer, String category, String productName, int stock, int amountBroken, byte[] image, String description){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                PreparedStatement pstmt = connect.prepareStatement("Insert into PRODUCTS (PRODUCT_ID, MANUFACTURER, CATEGORY, PRODUCT_NAME, STOCK, AMOUNT_BROKEN, DESCRIPTION, IMAGE) " +
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
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
    }
    public void updateProductData( String manufacturer, String category, String productName, int stock, int amountBroken, String description, byte[] image, int ID_){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                PreparedStatement pstmt = connect.prepareStatement("UPDATE PRODUCTS SET MANUFACTURER=?,CATEGORY=?,PRODUCT_NAME=?,STOCK=?,AMOUNT_BROKEN=?,DESCRIPTION=?,IMAGE=? WHERE ID_=?");
                pstmt.setString(1,manufacturer);
                pstmt.setString(2,category);
                pstmt.setString(3,productName);
                pstmt.setInt(4,stock);
                pstmt.setInt(5,amountBroken);
                pstmt.setString(6,description);
                pstmt.setBytes(7,image);
                pstmt.setInt(8,ID_);

                pstmt.executeUpdate();
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
    }
    public void updateBookData( String title,String writers, String Isbn, String publisher, int amount, String description, byte[] bookImage, String category, int ID_){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                PreparedStatement pstmt = connect.prepareStatement("UPDATE PRODUCTS SET PRODUCT_NAME=?,WRITERS=?,ISBN=?,PUBLISHER=?,STOCK=?,DESCRIPTION=?,IMAGE=?,CATEGORY =? WHERE ID_=?");
                pstmt.setString(1,title);
                pstmt.setString(2,writers);
                pstmt.setString(3,Isbn);
                pstmt.setString(4,publisher);
                pstmt.setInt(5,amount);
                pstmt.setString(6,description);
                pstmt.setBytes(7,bookImage);
                pstmt.setString(8,category);
                pstmt.setInt(9,ID_);

                pstmt.executeUpdate();
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
    }
    public void updateUserStatus( String status, int ID_){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                String query = "UPDATE USERS SET USER_TYPE = '"+status+"' WHERE ID_ = "+ID_+";";

                Statement statement = connect.createStatement();
                statement.executeQuery(query);
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
    }
    public void setBlockUser( int block, int ID_){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                String query = "UPDATE USERS SET BLOCKED = '"+block+"' WHERE ID_ = "+ID_+";";

                Statement statement = connect.createStatement();
                statement.executeQuery(query);
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
    }

    public boolean ifBlocked(String email){
        boolean result = false;
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                String query = "SELECT BLOCKED FROM USERS WHERE SCHOOL_EMAIL = '"+email+"' and BLOCKED = "+1+";";

                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    result = true;
                }
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
        return result;
    }

    public void updateUserPassword( String Password, int ID_){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                PreparedStatement pstmt = connect.prepareStatement("UPDATE USERS SET PASSWORD=? WHERE ID_=?");
                pstmt.setString(1,Password);
                pstmt.setInt(2,ID_);
                pstmt.executeUpdate();
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
    }
    public void updateUserNames(String firstName, String surname, int ID_){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                PreparedStatement pstmt = connect.prepareStatement("UPDATE USERS SET FIRSTNAME=?,SURNAME=? WHERE ID_=?");
                pstmt.setString(1,firstName);
                pstmt.setString(2,surname);
                pstmt.setInt(3,ID_);
                pstmt.executeUpdate();
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
    }
    public void updateBorrowStatus( String status,Timestamp Borrow_DateTime, int ID_){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                PreparedStatement pstmt = connect.prepareStatement("UPDATE BORROW SET STATUS=?,BORROW_DATE=? WHERE _ID=?");
                pstmt.setString(1,status);
                pstmt.setTimestamp(2,Borrow_DateTime);
                pstmt.setInt(3,ID_);
                pstmt.executeUpdate();
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
    }
    public void DeleteProduct(int ID_){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                String query = "DELETE FROM PRODUCTS WHERE ID_ ="+ID_+";";
                Statement statement = connect.createStatement();
                statement.executeQuery(query);
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
    }



    public void DeleteUser(int ID_){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                String query = "DELETE FROM USERS WHERE ID_ ="+ID_+";";
                Statement statement = connect.createStatement();
                statement.executeQuery(query);
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
    }

    public void InsertRequestBorrowItem(int ProductID, int UserID, int Amount, String Status, Timestamp BorrowRequestDateTime){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{

                PreparedStatement pstmt = connect.prepareStatement("Insert into BORROW (PRODUCTS_P_ID, USERS_P_ID, AMOUNT, STATUS, REQUEST_BORROW_DATE) values (?,?,?,?,?)");
                pstmt.setInt(1,ProductID);
                pstmt.setInt(2,UserID);
                pstmt.setInt(3,Amount);
                pstmt.setString(4,Status);
                pstmt.setTimestamp(5,BorrowRequestDateTime);
                pstmt.execute();
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
    }
    public void InsertBookItem(String title,String writers, String Isbn, String publisher, int amount, String description, byte[] bookImage, String category){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                PreparedStatement pstmt = connect.prepareStatement("Insert into PRODUCTS (PRODUCT_NAME, WRITERS, ISBN, PUBLISHER, STOCK, DESCRIPTION, IMAGE, CATEGORY) values (?,?,?,?,?,?,?,?)");
                pstmt.setString(1,title);
                pstmt.setString(2,writers);
                pstmt.setString(3,Isbn);
                pstmt.setString(4,publisher);
                pstmt.setInt(5,amount);
                pstmt.setString(6,description);
                pstmt.setBytes(7,bookImage);
                pstmt.setString(8,category);

                pstmt.execute();
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
    }

    public void DelRequestBorrowItem(int getmPKID){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                String query = "DELETE FROM BORROW WHERE _ID ="+getmPKID+";";
                Statement statement = connect.createStatement();
                statement.executeQuery(query);
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
    }

    public ArrayList<Borrow> getBorrowData(int UserID){
        ArrayList<Borrow> BorrowList = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                String query = "SELECT * FROM BORROW WHERE USERS_P_ID = "+UserID+";";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    Products product = getProductWithId(resultSet.getInt("PRODUCTS_P_ID"));
                    Users user = getUserWithId(resultSet.getInt("USERS_P_ID"));
                    String RequestDate;
                    String BorrowDate;

                    if (resultSet.getDate("REQUEST_BORROW_DATE")==null){
                        RequestDate = "Niet beschikbaar";
                    }else{
                        RequestDate = DateUtils.getCurrentDate(resultSet.getTimestamp("REQUEST_BORROW_DATE"));
                    }

                    if (resultSet.getDate("BORROW_DATE")==null){
                        BorrowDate = "..............";
                    }else{
                        BorrowDate = DateUtils.getCurrentDate(resultSet.getTimestamp("BORROW_DATE"));
                    }

                    BorrowList.add(new Borrow(
                            product.getName(),
                            RequestDate,
                            BorrowDate,
                            resultSet.getInt("AMOUNT"),
                            resultSet.getString("STATUS"),
                            resultSet.getInt("_ID"),
                            product.getImage(),
                            user.getFirstName() + " " + user.getSurname(),
                            resultSet.getInt("USERS_P_ID")));
                }
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
        return BorrowList;
    }
    public Users getUserWithEmail(String SchoolEmail){
        ArrayList<Users> UserData = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                String query = "SELECT * FROM USERS WHERE SCHOOL_EMAIL = '"+SchoolEmail+"';";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()) {
                    UserData.add(new Users(resultSet.getString("FIRSTNAME"), resultSet.getString("SURNAME"), resultSet.getString("SCHOOL_EMAIL"), resultSet.getString("PASSWORD"), resultSet.getInt("LOANED_AMOUNT"), resultSet.getString("USER_TYPE"), resultSet.getInt("ID_"),resultSet.getInt("BLOCKED")));
                }
                connect.close();

            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
        return UserData.get(0);
    }
    public Users getUserWithId(int Id){
        ArrayList<Users> UserData = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                String query = "SELECT * FROM USERS WHERE ID_ = "+Id+";";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()) {
                    UserData.add(new Users(resultSet.getString("FIRSTNAME"), resultSet.getString("SURNAME"), resultSet.getString("SCHOOL_EMAIL"), resultSet.getString("PASSWORD"), resultSet.getInt("LOANED_AMOUNT"), resultSet.getString("USER_TYPE"), resultSet.getInt("ID_"),resultSet.getInt("BLOCKED")));
                }
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
        return UserData.get(0);
    }
    public Books getBookWithId(int Id){
        ArrayList<Books> books = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                String query = "SELECT * FROM PRODUCTS WHERE ID_ = "+Id+";";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()) {
                    books.add(new Books(resultSet.getInt("ID_"), resultSet.getString("PRODUCT_NAME"), resultSet.getString("WRITERS"), resultSet.getString("ISBN"), resultSet.getString("PUBLISHER"), resultSet.getInt("STOCK"), resultSet.getString("DESCRIPTION"),"book",resultSet.getBytes("IMAGE")));
                }
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
        return books.get(0);
    }
    public ArrayList<Borrow> getBorrowDataList(){
        ArrayList<Borrow> loanUsersList = new ArrayList<>();
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
                    Products product = getProductWithId(resultSet.getInt("PRODUCTS_P_ID"));
                    Users user = getUserWithId(resultSet.getInt("USERS_P_ID"));
                    String RequestDate;
                    String BorrowDate;

                    if (resultSet.getDate("REQUEST_BORROW_DATE")==null){
                        RequestDate = "";
                    }else{
                        RequestDate = DateUtils.getCurrentDate(resultSet.getTimestamp("REQUEST_BORROW_DATE"));
                    }
                    if (resultSet.getDate("BORROW_DATE")==null){
                        BorrowDate = "";
                    }else{
                        BorrowDate = DateUtils.getCurrentDate(resultSet.getTimestamp("BORROW_DATE"));
                    }
                    loanUsersList.add(new Borrow(
                            product.getName(),
                            RequestDate,
                            BorrowDate,
                            resultSet.getInt("AMOUNT"),
                            resultSet.getString("STATUS"),
                            resultSet.getInt("_ID"),
                            product.getImage(),
                            user.getFirstName() + " " + user.getSurname()
                            ,resultSet.getInt("USERS_P_ID")));
                }
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
        return loanUsersList;
    }

    // check
    public boolean ifExists(String emailInput, String passwordInput) {
        ArrayList<Users> UserRow = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                Log.d(TAG,"Check your internet connection!");
            }
            else{
                String query = "SELECT * FROM USERS WHERE SCHOOL_EMAIL = '"+emailInput+"' and PASSWORD = '"+passwordInput+"';";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()) {
                    UserRow.add(new Users(resultSet.getString("FIRSTNAME"), resultSet.getString("SURNAME"), resultSet.getString("SCHOOL_EMAIL"), resultSet.getString("PASSWORD"), resultSet.getInt("LOANED_AMOUNT"), resultSet.getString("USER_TYPE"), resultSet.getInt("ID_"),resultSet.getInt("BLOCKED")));
                }
                connect.close();
            }
        }catch(Exception ex){
            Log.d(TAG,ex.toString());
        }
        boolean exists = (UserRow.size() > 0);
        return exists;
    }
}
