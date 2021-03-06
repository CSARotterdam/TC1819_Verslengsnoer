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
    private static final String TAG = "sql error: ";


    public void insertUser(String firstName,String surname, String SchoolEmail,byte[] salt,String password) {
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG," Check your internet connection!"); }
            else{
                PreparedStatement pstmt = connect.prepareStatement("Insert into USERS (FIRSTNAME, SURNAME, SCHOOL_EMAIL, LOANED_AMOUNT, USER_TYPE,SALT) " +
                        "values ( ?,?,?,?,?,?)");
                pstmt.setString(1,firstName);
                pstmt.setString(2,surname);
                pstmt.setString(3,SchoolEmail);
                pstmt.setInt(4,0);
                pstmt.setString(5,"student");
                pstmt.setBytes(6,salt);
                pstmt.execute();
                connect.createStatement().executeUpdate("UPDATE USERS  SET HASH = HASHBYTES('MD5', '"+password+"'+CAST(SALT AS NVARCHAR(36))) WHERE SCHOOL_EMAIL= '"+SchoolEmail+"'");
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
    }


    public ArrayList<Products> getAllProducts(){
        ArrayList<Products> electronicsList = new ArrayList<>();
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                ResultSet resultSet = connect.createStatement().executeQuery("SELECT * FROM PRODUCTS ");
                while(resultSet.next()){
                    if (resultSet.getString("CATEGORY").matches("Book")){
                        electronicsList.add(new Books(resultSet.getInt("ID_"),resultSet.getString("PRODUCT_NAME"),resultSet.getString("WRITERS"),resultSet.getString("ISBN")
                                ,resultSet.getString("PUBLISHER"),resultSet.getInt("STOCK"),resultSet.getString("DESCRIPTION"),resultSet.getString("CATEGORY"),resultSet.getBytes("IMAGE")
                                ,resultSet.getInt("PRODUCTS_ON_LOAN")));
                    }else{
                        electronicsList.add(new Electronics(resultSet.getString("PRODUCT_ID"),resultSet.getString("MANUFACTURER"),resultSet.getString("CATEGORY"),
                                resultSet.getString("PRODUCT_NAME"),resultSet.getInt("STOCK"),resultSet.getInt("AMOUNT_BROKEN"),resultSet.getString("DESCRIPTION"),
                                resultSet.getInt("ID_"),resultSet.getBytes("IMAGE"),resultSet.getInt("PRODUCTS_ON_LOAN")));
                    }
                }
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
        return electronicsList;
    }


    public ArrayList<Products> getAllProducts(String category){
        ArrayList<Products> electronicsList = new ArrayList<>();
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTS WHERE CONVERT(VARCHAR, CATEGORY) =  '"+category+"';");
                while(resultSet.next()){
                    if (resultSet.getString("CATEGORY").matches("Book")){
                        electronicsList.add(new Books(resultSet.getInt("ID_"),resultSet.getString("PRODUCT_NAME"),resultSet.getString("WRITERS"),resultSet.getString("ISBN")
                                ,resultSet.getString("PUBLISHER"),resultSet.getInt("STOCK"),resultSet.getString("DESCRIPTION"),resultSet.getString("CATEGORY"),resultSet.getBytes("IMAGE")
                                ,resultSet.getInt("PRODUCTS_ON_LOAN")));
                    }else{
                        electronicsList.add(new Electronics(resultSet.getString("PRODUCT_ID"),resultSet.getString("MANUFACTURER"),resultSet.getString("CATEGORY"),
                                resultSet.getString("PRODUCT_NAME"),resultSet.getInt("STOCK"),resultSet.getInt("AMOUNT_BROKEN"),
                                resultSet.getString("DESCRIPTION"),resultSet.getInt("ID_"),resultSet.getBytes("IMAGE")
                                ,resultSet.getInt("PRODUCTS_ON_LOAN")));
                    }
                }
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
        return electronicsList;
    }


    public ArrayList<Users> getAllUserDataExceptFor(int ID_){
        ArrayList<Users> usersList = new ArrayList<>();
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                ResultSet resultSet = connect.createStatement().executeQuery("SELECT * FROM USERS WHERE NOT ID_ = "+ID_);
                while(resultSet.next()){
                    usersList.add(new Users(resultSet.getString("FIRSTNAME"),resultSet.getString("SURNAME")
                            ,resultSet.getString("SCHOOL_EMAIL"),resultSet.getInt("LOANED_AMOUNT"),resultSet.getString("USER_TYPE"),resultSet.getInt("ID_"), resultSet.getInt("BLOCKED"),resultSet.getInt("PRODUCTS_ON_LOAN")));
                }
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
        return usersList;
    }


    public Electronics getProductWithId(int id_){
        ArrayList<Electronics> electronicsList = new ArrayList<>();
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                ResultSet resultSet = connect.createStatement().executeQuery("SELECT * FROM PRODUCTS WHERE ID_ = "+id_+";");
                while(resultSet.next()) {
                    electronicsList.add(new Electronics(resultSet.getString("PRODUCT_ID"), resultSet.getString("MANUFACTURER"), resultSet.getString("CATEGORY"),
                            resultSet.getString("PRODUCT_NAME"), resultSet.getInt("STOCK"), resultSet.getInt("AMOUNT_BROKEN"),
                            resultSet.getString("DESCRIPTION"), resultSet.getInt("ID_"),resultSet.getBytes("IMAGE")
                            ,resultSet.getInt("PRODUCTS_ON_LOAN")));
                }
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
        if (electronicsList.size()==0){
            return null;
        }else{
            return electronicsList.get(0);
        }
    }


    public void addProductData(String productID, String manufacturer, String category, String productName, int stock, int amountBroken, byte[] image, String description){
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
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
                pstmt.executeUpdate();
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
    }


    public void updateProductData( String manufacturer, String category, String productName, int stock, int amountBroken, String description, byte[] image, int ID_){
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
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
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
    }


    public void updateBookData( String title,String writers, String Isbn, String publisher, int amount, String description, byte[] bookImage, String category, int ID_){
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
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
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
    }


    public void updateUserStatus( String status, int ID_){
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                connect.createStatement().executeUpdate("UPDATE USERS SET USER_TYPE = '"+status+"' WHERE ID_ = "+ID_+";");
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
    }

    public String getUserTypeByEmail(String email){
        String result = "";
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                ResultSet resultSet = connect.createStatement().executeQuery("SELECT USER_TYPE FROM USERS WHERE SCHOOL_EMAIL = '"+email+"';");
                while (resultSet.next()) {
                    result = resultSet.getString("USER_TYPE");
                }
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
        return result;
    }

    public void setBlockUser( int block, int ID_){
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                connect.createStatement().executeUpdate("UPDATE USERS SET BLOCKED = '"+block+"' WHERE ID_ = "+ID_+";");
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
    }


    public boolean ifBlocked(String email){
        boolean result = false;
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                ResultSet resultSet = connect.createStatement().executeQuery("SELECT BLOCKED FROM USERS WHERE SCHOOL_EMAIL = '"+email+"' and BLOCKED = "+1+";");
                while (resultSet.next()) {
                    result = true;
                }
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
        return result;
    }


    public ArrayList<Users> getBlockedUsers(int ID_){
        ArrayList<Users> usersList = new ArrayList<>();
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT FIRSTNAME,SURNAME,ID_ FROM USERS WHERE BLOCKED = "+1+" and WHERE NOT ID_ = "+ID_);
                while(resultSet.next()){
                    usersList.add(new Users(resultSet.getString("FIRSTNAME"), resultSet.getString("SURNAME"), resultSet.getInt("ID_")));
                }
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
        return usersList;
    }


    public void updateUserPassword( String Password, int ID_){
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                connect.createStatement().executeUpdate("UPDATE USERS  SET HASH = HASHBYTES('MD5', '"+Password+"'+CAST(SALT AS NVARCHAR(36))) WHERE ID_= '"+ID_+"'");
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
    }


    public void updateUserNames(String firstName, String surname, int ID_){
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                PreparedStatement pstmt = connect.prepareStatement("UPDATE USERS SET FIRSTNAME=?,SURNAME=? WHERE ID_=?");
                pstmt.setString(1,firstName);
                pstmt.setString(2,surname);
                pstmt.setInt(3,ID_);
                pstmt.executeUpdate();
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
    }


    public void productReturned( Timestamp returnDate, int borrowID_, int amount, int userID_, int productID_){
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                PreparedStatement pstmt = connect.prepareStatement("UPDATE BORROW SET STATUS=?,RETURN_DATE=? WHERE ID_=?");
                pstmt.setString(1,"Teruggebracht");
                pstmt.setTimestamp(2,returnDate);
                pstmt.setInt(3,borrowID_);
                pstmt.executeUpdate();
                PreparedStatement pstmt2 = connect.prepareStatement("UPDATE USERS SET PRODUCTS_ON_LOAN = PRODUCTS_ON_LOAN - ? WHERE ID_=?");
                pstmt2.setInt(1,amount);
                pstmt2.setInt(2,userID_);
                pstmt2.executeUpdate();
                PreparedStatement pstmt3 = connect.prepareStatement("UPDATE PRODUCTS SET PRODUCTS_ON_LOAN = PRODUCTS_ON_LOAN - ? WHERE ID_=?");
                pstmt3.setInt(1,amount);
                pstmt3.setInt(2,productID_);
                pstmt3.executeUpdate();
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
    }


    public void brokenProductReturned( Timestamp returnDate, int borrowID_, int amount, int userID_, int productID_){
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                PreparedStatement pstmt = connect.prepareStatement("UPDATE BORROW SET STATUS=?,RETURN_DATE=? WHERE ID_=?");
                pstmt.setString(1,"Teruggebracht");
                pstmt.setTimestamp(2,returnDate);
                pstmt.setInt(3,borrowID_);
                pstmt.executeUpdate();
                PreparedStatement pstmt2 = connect.prepareStatement("UPDATE USERS SET PRODUCTS_ON_LOAN = PRODUCTS_ON_LOAN - ? WHERE ID_=?");
                pstmt2.setInt(1,amount);
                pstmt2.setInt(2,userID_);
                pstmt2.executeUpdate();
                PreparedStatement pstmt3 = connect.prepareStatement("UPDATE PRODUCTS SET PRODUCTS_ON_LOAN = PRODUCTS_ON_LOAN - ?, STOCK = STOCK - ?, AMOUNT_BROKEN = AMOUNT_BROKEN + ? WHERE ID_=?");
                pstmt3.setInt(1,amount);
                pstmt3.setInt(2,amount);
                pstmt3.setInt(3,amount);
                pstmt3.setInt(4,productID_);
                pstmt3.executeUpdate();
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
    }


    public void lendProduct( Timestamp Borrow_DateTime,int amount, int borrowID_,int userID_,int productID_){
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                PreparedStatement pstmt = connect.prepareStatement("UPDATE BORROW SET STATUS=?,BORROW_DATE=? WHERE ID_=?");
                pstmt.setString(1,"Geleend");
                pstmt.setTimestamp(2,Borrow_DateTime);
                pstmt.setInt(3,borrowID_);
                pstmt.executeUpdate();
                PreparedStatement pstmt2 = connect.prepareStatement("UPDATE USERS SET LOANED_AMOUNT = LOANED_AMOUNT + ?,PRODUCTS_ON_LOAN = PRODUCTS_ON_LOAN + ? WHERE ID_ = ?");
                pstmt2.setInt(1,amount);
                pstmt2.setInt(2,amount);
                pstmt2.setInt(3,userID_);
                pstmt2.executeUpdate();
                PreparedStatement pstmt3 = connect.prepareStatement("UPDATE PRODUCTS SET LOANED_AMOUNT = LOANED_AMOUNT + ? WHERE ID_ = ?");
                pstmt3.setInt(1,amount);
                pstmt3.setInt(2,productID_);
                pstmt3.executeUpdate();
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
    }


    public void DeleteProduct(int ID_){
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                connect.createStatement().executeUpdate("DELETE FROM PRODUCTS WHERE ID_ ="+ID_+";");
                connect.createStatement().executeQuery("DELETE FROM BORROW WHERE PRODUCTS_P_ID ="+ID_+";");
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
    }


    public void DeleteUser(int ID_){
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                connect.createStatement().executeQuery("DELETE FROM USERS WHERE ID_ ="+ID_+";");
                connect.createStatement().executeQuery("DELETE FROM BORROW WHERE USERS_P_ID ="+ID_+";");
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
    }


    public void InsertRequestBorrowItem(int ProductID, int UserID, int Amount, String Status, Timestamp BorrowRequestDateTime){
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                PreparedStatement pstmt = connect.prepareStatement("Insert into BORROW (PRODUCTS_P_ID, USERS_P_ID, AMOUNT, STATUS, REQUEST_BORROW_DATE) values (?,?,?,?,?)");
                pstmt.setInt(1,ProductID);
                pstmt.setInt(2,UserID);
                pstmt.setInt(3,Amount);
                pstmt.setString(4,Status);
                pstmt.setTimestamp(5,BorrowRequestDateTime);
                pstmt.execute();
                PreparedStatement pstmt3 = connect.prepareStatement("UPDATE PRODUCTS SET PRODUCTS_ON_LOAN = PRODUCTS_ON_LOAN + ? WHERE ID_=?");
                pstmt3.setInt(1,Amount);
                pstmt3.setInt(2,ProductID);
                pstmt3.executeUpdate();
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
    }


    public void InsertBookItem(String title,String writers, String Isbn, String publisher, int amount, String description, byte[] bookImage, String category){
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
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

                pstmt.executeUpdate();
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
    }


    public void DeleteRequestBorrowItem(int borrowID, int amount, int productID){
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                PreparedStatement pstmt3 = connect.prepareStatement("UPDATE PRODUCTS SET PRODUCTS_ON_LOAN = PRODUCTS_ON_LOAN - ? WHERE ID_=?");
                pstmt3.setInt(1,amount);
                pstmt3.setInt(2,productID);
                pstmt3.executeUpdate();
                connect.createStatement().executeUpdate("DELETE FROM BORROW WHERE ID_ ="+borrowID+";");
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
    }


    public ArrayList<Borrow> getBorrowDataListWithStatus(String status){
        ArrayList<Borrow> loanUsersList = new ArrayList<>();
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                ResultSet resultSet = connect.createStatement().executeQuery("SELECT * FROM BORROW WHERE CONVERT(VARCHAR, STATUS) = '"+status+"'");

                while(resultSet.next()){
                    Products product = getProductWithId(resultSet.getInt("PRODUCTS_P_ID"));
                    Users user = getUserWithId(resultSet.getInt("USERS_P_ID"));
                    String RequestDate;
                    String BorrowDate;
                    String returndate;

                    if (resultSet.getDate("REQUEST_BORROW_DATE")==null){ RequestDate = "";
                    }else{ RequestDate = DateUtils.getCurrentDate(resultSet.getTimestamp("REQUEST_BORROW_DATE"));
                    }
                    if (resultSet.getDate("BORROW_DATE")==null){ BorrowDate = "";
                    }else{ BorrowDate = DateUtils.getCurrentDate(resultSet.getTimestamp("BORROW_DATE"));
                    }
                    if(resultSet.getDate("RETURN_DATE")==null){ returndate = "";
                    }else{ returndate = DateUtils.getCurrentDate(resultSet.getTimestamp("RETURN_DATE"));
                    }
                    loanUsersList.add(new Borrow(
                            product.getName(),
                            RequestDate,
                            BorrowDate,
                            resultSet.getInt("AMOUNT"),
                            resultSet.getString("STATUS"),
                            resultSet.getInt("PRODUCTS_P_ID"),
                            product.getImage(),
                            user.getFirstName() + " " + user.getSurname(),
                            resultSet.getInt("USERS_P_ID"),
                            resultSet.getInt("ID_"),
                            returndate));
                }
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
        return loanUsersList;
    }
    public ArrayList<Borrow> getBorrowDataListWithStatus(String status, String status2){
        ArrayList<Borrow> loanUsersList = new ArrayList<>();
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                ResultSet resultSet = connect.createStatement().executeQuery("SELECT * FROM BORROW WHERE CONVERT(VARCHAR, STATUS) = '"+status+"' OR CONVERT(VARCHAR, STATUS) = '"+status2+"'");

                while(resultSet.next()){
                    Products product = getProductWithId(resultSet.getInt("PRODUCTS_P_ID"));
                    Users user = getUserWithId(resultSet.getInt("USERS_P_ID"));
                    String RequestDate;
                    String BorrowDate;
                    String returndate;

                    if (resultSet.getDate("REQUEST_BORROW_DATE")==null){ RequestDate = "";
                    }else{ RequestDate = DateUtils.getCurrentDate(resultSet.getTimestamp("REQUEST_BORROW_DATE"));
                    }
                    if (resultSet.getDate("BORROW_DATE")==null){ BorrowDate = "";
                    }else{ BorrowDate = DateUtils.getCurrentDate(resultSet.getTimestamp("BORROW_DATE"));
                    }
                    if(resultSet.getDate("RETURN_DATE")==null){ returndate = "";
                    }else{ returndate = DateUtils.getCurrentDate(resultSet.getTimestamp("RETURN_DATE"));
                    }
                    loanUsersList.add(new Borrow(
                            product.getName(),
                            RequestDate,
                            BorrowDate,
                            resultSet.getInt("AMOUNT"),
                            resultSet.getString("STATUS"),
                            resultSet.getInt("PRODUCTS_P_ID"),
                            product.getImage(),
                            user.getFirstName() + " " + user.getSurname(),
                            resultSet.getInt("USERS_P_ID"),
                            resultSet.getInt("ID_"),
                            returndate));
                }
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
        return loanUsersList;
    }


    public void StatusTeLaat(){
        try{
            connect = new ConnectionHelper().connection();

            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                String dbLocation ="AWS";
                String dateTime;
                if (dbLocation.matches("AWS")) {
                    dateTime = "DATEADD(HOUR,2,CURRENT_TIMESTAMP)";
                }
                else if(dbLocation.matches("SCHOOL")){
                    dateTime = "CURRENT_TIMESTAMP";
                }
                else{ dateTime = "GETDATE()"; }
                connect.prepareStatement("UPDATE BORROW SET STATUS='Te Laat' WHERE CONVERT(varchar(8),STATUS)='Geleend' and CONVERT(VARCHAR(8),"+dateTime+",108)>'17:00'").executeUpdate();
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
    }


    public boolean GebruikerTeLaat(int UserID){
        boolean telaatprod = false;
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                ResultSet resultSet = connect.createStatement().executeQuery("Select * from borrow where users_p_id="+UserID+" and convert(varchar(8),status)='Te Laat';");
                telaatprod = resultSet.next();
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
        return telaatprod;
    }


    public ArrayList<Borrow> getBorrowDataWithUserId(int UserID,String status){
        ArrayList<Borrow> BorrowList = new ArrayList<>();
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                ResultSet resultSet = connect.createStatement().executeQuery("SELECT * FROM BORROW WHERE USERS_P_ID = "+UserID+" AND CONVERT(VARCHAR, STATUS) = '"+status+"';");
                while(resultSet.next()){
                    Products product = getProductWithId(resultSet.getInt("PRODUCTS_P_ID"));
                    Users user = getUserWithId(resultSet.getInt("USERS_P_ID"));
                    String RequestDate;
                    String BorrowDate;
                    String returndate;

                    if (resultSet.getDate("REQUEST_BORROW_DATE")==null){
                        RequestDate = "Niet beschikbaar";
                    }else{ RequestDate = DateUtils.getCurrentDate(resultSet.getTimestamp("REQUEST_BORROW_DATE"));
                    }
                    if (resultSet.getDate("BORROW_DATE")==null){
                        BorrowDate = "";
                    }else{ BorrowDate = DateUtils.getCurrentDate(resultSet.getTimestamp("BORROW_DATE"));
                    }
                    if(resultSet.getDate("RETURN_DATE")==null){
                        returndate = "";
                    }else{ returndate = DateUtils.getCurrentDate(resultSet.getTimestamp("RETURN_DATE"));
                    }
                    BorrowList.add(new Borrow(
                            product.getName(),
                            RequestDate,
                            BorrowDate,
                            resultSet.getInt("AMOUNT"),
                            resultSet.getString("STATUS"),
                            resultSet.getInt("PRODUCTS_P_ID"),
                            product.getImage(),
                            user.getFirstName() + " " + user.getSurname(),
                            resultSet.getInt("USERS_P_ID"),
                            resultSet.getInt("ID_"),
                            returndate));
                }
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
        return BorrowList;
    }


    public ArrayList<Borrow> getBorrowDataWithUserId(int UserID,String status, String status2, String status3){
        ArrayList<Borrow> BorrowList = new ArrayList<>();
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                String query = "SELECT * FROM BORROW WHERE USERS_P_ID = "+UserID+" AND CONVERT(VARCHAR, STATUS) = '"+status+"' or USERS_P_ID = "+UserID+" AND CONVERT(VARCHAR, STATUS) = '"+status2+"' or USERS_P_ID = "+UserID+" AND CONVERT(VARCHAR, STATUS) = '"+status3+"'";
                ResultSet resultSet = connect.createStatement().executeQuery(query);
                while(resultSet.next()){
                    Products product = getProductWithId(resultSet.getInt("PRODUCTS_P_ID"));
                    Users user = getUserWithId(resultSet.getInt("USERS_P_ID"));
                    String RequestDate;
                    String BorrowDate;
                    String returndate;

                    if (resultSet.getDate("REQUEST_BORROW_DATE")==null){
                        RequestDate = "Niet beschikbaar";
                    }else{ RequestDate = DateUtils.getCurrentDate(resultSet.getTimestamp("REQUEST_BORROW_DATE"));
                    }
                    if (resultSet.getDate("BORROW_DATE")==null){
                        BorrowDate = "";
                    }else{ BorrowDate = DateUtils.getCurrentDate(resultSet.getTimestamp("BORROW_DATE"));
                    }
                    if(resultSet.getDate("RETURN_DATE")==null){
                        returndate = "";
                    }else{ returndate = DateUtils.getCurrentDate(resultSet.getTimestamp("RETURN_DATE"));
                    }

                    BorrowList.add(new Borrow(
                            product.getName(),
                            RequestDate,
                            BorrowDate,
                            resultSet.getInt("AMOUNT"),
                            resultSet.getString("STATUS"),
                            resultSet.getInt("PRODUCTS_P_ID"),
                            product.getImage(),
                            user.getFirstName() + " " + user.getSurname(),
                            resultSet.getInt("USERS_P_ID"),
                            resultSet.getInt("ID_"),
                            returndate));
                }
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
        return BorrowList;
    }


    public Borrow getBorrowDataWithId(int BorrowListID){
        ArrayList<Borrow> BorrowList = new ArrayList<>();
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                ResultSet resultSet = connect.createStatement().executeQuery("SELECT * FROM BORROW WHERE ID_ = "+BorrowListID+";");
                while(resultSet.next()){
                    Products product = getProductWithId(resultSet.getInt("PRODUCTS_P_ID"));
                    Users user = getUserWithId(resultSet.getInt("USERS_P_ID"));
                    String RequestDate;
                    String BorrowDate;
                    String returndate;

                    if (resultSet.getDate("REQUEST_BORROW_DATE")==null){
                        RequestDate = "Niet beschikbaar";
                    }else{ RequestDate = DateUtils.getCurrentDate(resultSet.getTimestamp("REQUEST_BORROW_DATE"));
                    }
                    if (resultSet.getDate("BORROW_DATE")==null){
                        BorrowDate = "";
                    }else{ BorrowDate = DateUtils.getCurrentDate(resultSet.getTimestamp("BORROW_DATE"));
                    }
                    if(resultSet.getDate("RETURN_DATE")==null){
                        returndate = "";
                    }else{ returndate = DateUtils.getCurrentDate(resultSet.getTimestamp("RETURN_DATE"));
                    }
                    BorrowList.add(new Borrow(
                            product.getName(),
                            RequestDate,
                            BorrowDate,
                            resultSet.getInt("AMOUNT"),
                            resultSet.getString("STATUS"),
                            resultSet.getInt("PRODUCTS_P_ID"),
                            product.getImage(),
                            user.getFirstName() + " " + user.getSurname(),
                            resultSet.getInt("USERS_P_ID"),
                            resultSet.getInt("ID_"),
                            returndate));
                }
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
        if (BorrowList.size()==0){
            return null;
        }else{
            return BorrowList.get(0);
        }
    }


    public Users getUserWithEmail(String SchoolEmail){
        ArrayList<Users> UserData = new ArrayList<>();
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }{
                ResultSet resultSet = connect.createStatement().executeQuery("SELECT * FROM USERS WHERE SCHOOL_EMAIL = '"+SchoolEmail+"';");
                if(resultSet.next()) {
                    UserData.add(new Users(resultSet.getString("FIRSTNAME"), resultSet.getString("SURNAME"), resultSet.getString("SCHOOL_EMAIL"), resultSet.getInt("LOANED_AMOUNT"), resultSet.getString("USER_TYPE"), resultSet.getInt("ID_"),resultSet.getInt("BLOCKED"),resultSet.getInt("PRODUCTS_ON_LOAN")));
                }
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
        if (UserData.size()==0){
            return null;
        }else{
            return UserData.get(0);
        }
    }


    public Users getUserWithId(int Id){
        ArrayList<Users> UserData = new ArrayList<>();
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                ResultSet resultSet = connect.createStatement().executeQuery("SELECT * FROM USERS WHERE ID_ = "+Id+";");
                while(resultSet.next()) {
                    UserData.add(new Users(resultSet.getString("FIRSTNAME"), resultSet.getString("SURNAME"), resultSet.getString("SCHOOL_EMAIL"), resultSet.getInt("LOANED_AMOUNT"), resultSet.getString("USER_TYPE"), resultSet.getInt("ID_"),resultSet.getInt("BLOCKED"),resultSet.getInt("PRODUCTS_ON_LOAN")));
                }
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
        return UserData.get(0);
    }


    public Books getBookWithId(int Id){
        ArrayList<Books> books = new ArrayList<>();
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                ResultSet resultSet = connect.createStatement().executeQuery("SELECT * FROM PRODUCTS WHERE ID_ = "+Id+";");
                while(resultSet.next()) {
                    books.add(new Books(resultSet.getInt("ID_"), resultSet.getString("PRODUCT_NAME"), resultSet.getString("WRITERS"), resultSet.getString("ISBN"),
                            resultSet.getString("PUBLISHER"), resultSet.getInt("STOCK"), resultSet.getString("DESCRIPTION"),
                            "book",resultSet.getBytes("IMAGE") ,resultSet.getInt("PRODUCTS_ON_LOAN")));
                }
                connect.close();
            }
        }catch(Exception ex){ Log.d(TAG,ex.toString()); }
        if (books.size()==0){
            return null;
        }else{
            return books.get(0);
        }
    }


    public boolean ifExists(String emailInput, String password) {   // check
        boolean exist= false;
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS WHERE HASH=HASHBYTES('MD5', '"+password+"'+CAST(SALT AS NVARCHAR(36))) and SCHOOL_EMAIL = '"+emailInput+"'");
                exist = resultSet.next();
                connect.close();
            }
        }catch(Exception ex){
            exist = false;
            Log.d(TAG,ex.toString());
        }
        return exist;
    }


    public boolean ifExists(String emailInput) {
        boolean exist= false;
        try{
            connect = new ConnectionHelper().connection();
            if (connect == null){ Log.d(TAG,"Check your internet connection!"); }
            else{
                ResultSet resultSet = connect.createStatement().executeQuery("SELECT * FROM USERS WHERE SCHOOL_EMAIL = '"+emailInput+"'");
                exist = resultSet.next();
                connect.close();
            }
        }catch(Exception ex){
            exist = false;
            Log.d(TAG,ex.toString());
        }
        return exist;
    }
}