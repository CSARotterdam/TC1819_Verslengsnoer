package com.example.techlab.db;

import com.example.techlab.model.Books;
import com.example.techlab.model.Borrow;
import com.example.techlab.model.Electronics;
import com.example.techlab.model.Products;
import com.example.techlab.model.Users;
import com.example.techlab.view.Itemadapter_loanUsers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataManagement {
    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess=false;

    public void insertUser(String firstName,String surname, String SchoolEmail, String Password) {

        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
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
            isSuccess=false;
            ConnectionResult=ex.getMessage();
        }
    }

    public ArrayList<Products> getAllElectronicsData(){
        ArrayList<Products> electronicsList = new ArrayList<>();
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
                    electronicsList.add(new Electronics(resultSet.getString("PRODUCT_ID"),resultSet.getString("MANUFACTURER"),resultSet.getString("CATEGORY"),resultSet.getString("PRODUCT_NAME"),resultSet.getInt("STOCK"),resultSet.getInt("AMOUNT_BROKEN"),resultSet.getString("DESCRIPTION"),resultSet.getInt("ID_"),resultSet.getBytes("IMAGE")));
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

    public ArrayList<Products> getAllElectronicsData(String category){
        ArrayList<Products> electronicsList = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                String query = "SELECT * FROM ELECTRONICS WHERE CONVERT(VARCHAR, CATEGORY) =  '"+category+"';";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    electronicsList.add(new Electronics(resultSet.getString("PRODUCT_ID"),resultSet.getString("MANUFACTURER"),resultSet.getString("CATEGORY"),resultSet.getString("PRODUCT_NAME"),resultSet.getInt("STOCK"),resultSet.getInt("AMOUNT_BROKEN"),resultSet.getString("DESCRIPTION"),resultSet.getInt("ID_"),resultSet.getBytes("IMAGE")));
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
    public ArrayList<Products> getAllBooksData(){
        ArrayList<Products> boookList = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                String query = "SELECT * FROM BOOK";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    boookList.add(new Books(resultSet.getInt("ID_"),resultSet.getString("TITLE"),resultSet.getString("WRITERS"),resultSet.getString("ISBN")
                    ,resultSet.getString("PUBLISHER"),resultSet.getInt("AMOUNT"),resultSet.getString("DESCRIPTION"),resultSet.getString("CATEGORY"),resultSet.getBytes("IMAGE")));
                }
                ConnectionResult="successful";
                isSuccess=true;
                connect.close();
            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();

        }
        return boookList;

    }
    public ArrayList<Products> getAllBooksData(String category){
        ArrayList<Products> boookList = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                String query = "SELECT * FROM BOOK WHERE CONVERT(VARCHAR, CATEGORY) =  '"+category+"'";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    boookList.add(new Books(resultSet.getInt("ID_"),resultSet.getString("TITLE"),resultSet.getString("WRITERS"),resultSet.getString("ISBN")
                            ,resultSet.getString("PUBLISHER"),resultSet.getInt("AMOUNT"),resultSet.getString("DESCRIPTION"),resultSet.getString("CATEGORY"),resultSet.getBytes("IMAGE")));
                }
                ConnectionResult="successful";
                isSuccess=true;
                connect.close();
            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();

        }
        return boookList;

    }
    public ArrayList<Users> getAllUserData(int ID_){
        ArrayList<Users> usersList = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                String query = "SELECT * FROM USERS WHERE NOT ID_ = "+ID_;
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    usersList.add(new Users(resultSet.getString("FIRSTNAME"),resultSet.getString("SURNAME")
                            ,resultSet.getString("SCHOOL_EMAIL"),resultSet.getString("PASSWORD"),resultSet.getInt("LOANED_AMOUNT"),resultSet.getString("USER_TYPE"),resultSet.getInt("ID_")));
                }
                ConnectionResult="successful";
                isSuccess=true;
                connect.close();
            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();

        }
        return usersList;

    }
    public ArrayList<Users> getAllUserData(){
        ArrayList<Users> usersList = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                String query = "SELECT * FROM USERS";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    usersList.add(new Users(resultSet.getString("FIRSTNAME"),resultSet.getString("SURNAME")
                            ,resultSet.getString("SCHOOL_EMAIL"),resultSet.getString("PASSWORD"),resultSet.getInt("LOANED_AMOUNT"),resultSet.getString("USER_TYPE"),resultSet.getInt("ID_")));
                }
                ConnectionResult="successful";
                isSuccess=true;
                connect.close();
            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();

        }
        return usersList;

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
                    electronicsList.add(new Electronics(resultSet.getString("PRODUCT_ID"), resultSet.getString("MANUFACTURER"), resultSet.getString("CATEGORY"), resultSet.getString("PRODUCT_NAME"), resultSet.getInt("STOCK"), resultSet.getInt("AMOUNT_BROKEN"), resultSet.getString("DESCRIPTION"), resultSet.getInt("ID_"),resultSet.getBytes("IMAGE")));
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
                connect.close();

            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();
        }
    }
    public void updateProductData( String manufacturer, String category, String productName, int stock, int amountBroken, String description, byte[] image, int ID_){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                PreparedStatement pstmt = connect.prepareStatement("UPDATE ELECTRONICS SET MANUFACTURER=?,CATEGORY=?,PRODUCT_NAME=?,STOCK=?,AMOUNT_BROKEN=?,DESCRIPTION=?,IMAGE=? WHERE ID_=?");
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
            isSuccess=false;
            ConnectionResult=ex.getMessage();
        }
    }
    public void updateBookData( String title,String writers, String Isbn, String publisher, int amount, String description, byte[] bookImage, String category, int ID_){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                PreparedStatement pstmt = connect.prepareStatement("UPDATE BOOK SET TITLE=?,WRITERS=?,ISBN=?,PUBLISHER=?,AMOUNT=?,DESCRIPTION=?,IMAGE=?,CATEGORY =? WHERE ID_=?");
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
            isSuccess=false;
            ConnectionResult=ex.getMessage();
        }
    }
    public void updateUserStatus( String status, int ID_){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                String query = "UPDATE USERS SET USER_TYPE = '"+status+"' WHERE ID_ = "+ID_+";";

                Statement statement = connect.createStatement();
                statement.executeQuery(query);
                connect.close();
            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();
        }
    }
    public void updateBorrowStatus( String status, int ID_){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                String query = "UPDATE BORROW SET STATUS = '"+status+"' WHERE _ID = "+ID_+";";

                Statement statement = connect.createStatement();
                statement.executeQuery(query);
                connect.close();
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
                connect.close();
            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();
        }
    }
    public void DeleteBook(int ID_){
        try{
        ConnectionHelper connectionHelper = new ConnectionHelper();
        connect = connectionHelper.connection();
        if (connect == null){
            ConnectionResult = "Check your internet access";
        }
        else{


            String query = "DELETE FROM BOOK WHERE ID_ ="+ID_+";";
            Statement statement = connect.createStatement();
            statement.executeQuery(query);
            connect.close();
        }
    }catch(Exception ex){
        isSuccess=false;
        ConnectionResult=ex.getMessage();
    }
}


    public void DeleteUser(int ID_){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{


                String query = "DELETE FROM USERS WHERE ID_ ="+ID_+";";
                Statement statement = connect.createStatement();
                statement.executeQuery(query);
                connect.close();
            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();
        }
    }

    public void InsertRequestBorrowItem(int ProductID, int UserID, int Amount, String Status,String objectType){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{

                PreparedStatement pstmt = connect.prepareStatement("Insert into BORROW (PRODUCTS_P_ID, USERS_P_ID, AMOUNT, STATUS, PRODUCT_TYPE) values (?,?,?,?,?)");
                pstmt.setInt(1,ProductID);
                pstmt.setInt(2,UserID);
                pstmt.setInt(3,Amount);
                pstmt.setString(4,Status);
                pstmt.setString(5,objectType);
                pstmt.execute();
                connect.close();
            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();
        }
    }
    public void InsertBookItem(String title,String writers, String Isbn, String publisher, int amount, String description, byte[] bookImage, String category){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                PreparedStatement pstmt = connect.prepareStatement("Insert into BOOK (TITLE, WRITERS, ISBN, PUBLISHER, AMOUNT, DESCRIPTION, IMAGE, CATEGORY) values (?,?,?,?,?,?,?,?)");
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
            isSuccess=false;
            ConnectionResult=ex.getMessage();
        }
    }

    public void DelRequestBorrowItem(int getmPKID){
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                String query = "DELETE FROM BORROW WHERE _ID ="+getmPKID+";";
                Statement statement = connect.createStatement();
                statement.executeQuery(query);
                connect.close();
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
                    String productName;
                    String productType;
                    byte[] image;
                    if(resultSet.getString("PRODUCT_TYPE").matches("book")){
                        productName = getBookWithId(resultSet.getInt("PRODUCTS_P_ID")).getName();
                        image = getBookWithId(resultSet.getInt("PRODUCTS_P_ID")).getImage();
                        productType = "book";
                    }else{
                        productName = getProductData(resultSet.getInt("PRODUCTS_P_ID")).getName();
                        image = getProductData(resultSet.getInt("PRODUCTS_P_ID")).getImage();
                        productType = "electronic";
                    }
                    BorrowList.add(new Borrow(productName,
                            resultSet.getString("RETURN_DATE"),
                            resultSet.getInt("AMOUNT"),
                            resultSet.getString("STATUS"),
                            resultSet.getInt("_ID"),
                            productType,
                            image,
                            getUserWithId(resultSet.getInt("USERS_P_ID")).getFirstName() + " " + getUserWithId(resultSet.getInt("USERS_P_ID")).getSurname()));

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

    public Users getUserWithEmail(String SchoolEmail){
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
    public Users getUserWithId(int Id){
        ArrayList<Users> UserData = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                String query = "SELECT * FROM USERS WHERE ID_ = "+Id+";";
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
    public Books getBookWithId(int Id){
        ArrayList<Books> books = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                String query = "SELECT * FROM BOOK WHERE ID_ = "+Id+";";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()) {
                    books.add(new Books(resultSet.getInt("ID_"), resultSet.getString("TITLE"), resultSet.getString("WRITERS"), resultSet.getString("ISBN"), resultSet.getString("PUBLISHER"), resultSet.getInt("AMOUNT"), resultSet.getString("DESCRIPTION"),"book",resultSet.getBytes("IMAGE")));
                }
                ConnectionResult="successful";
                isSuccess=true;
                connect.close();

            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();

        }
        return books.get(0);
    }


    public ArrayList<Borrow> getBorrowDataList(){
        ArrayList<Borrow> loanUsersList = new ArrayList<>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connection();
            if (connect == null){
                ConnectionResult = "Check your internet access";
            }
            else{
                String query = "SELECT * FROM BORROW ";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while(resultSet.next()){
                    String productName;
                    String productType;
                    byte[] image;
                    if(resultSet.getString("PRODUCT_TYPE").matches("book")){
                        productName = getBookWithId(resultSet.getInt("PRODUCTS_P_ID")).getName();
                        image = getBookWithId(resultSet.getInt("PRODUCTS_P_ID")).getImage();
                        productType = "book";
                    }else{
                        productName = getProductData(resultSet.getInt("PRODUCTS_P_ID")).getName();
                        image = getProductData(resultSet.getInt("PRODUCTS_P_ID")).getImage();
                        productType = "electronic";
                    }
                    loanUsersList.add(new Borrow(productName,
                            resultSet.getString("RETURN_DATE"),
                            resultSet.getInt("AMOUNT"),
                            resultSet.getString("STATUS"),
                            resultSet.getInt("_ID"),
                            productType,
                            image,
                            getUserWithId(resultSet.getInt("USERS_P_ID")).getFirstName() + " " + getUserWithId(resultSet.getInt("USERS_P_ID")).getSurname()));
                }
                ConnectionResult="successful";
                isSuccess=true;
                connect.close();
            }
        }catch(Exception ex){
            isSuccess=false;
            ConnectionResult=ex.getMessage();

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
