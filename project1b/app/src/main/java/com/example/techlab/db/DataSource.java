package com.example.techlab.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.techlab.model.Books;
import com.example.techlab.model.Electronics;
import com.example.techlab.model.Products;
import com.example.techlab.model.Users;

public class DataSource {

    private SQLiteDatabase mDatabase;
    private TechLabDataBaseHelper mTechLabDataBaseHelper;
    private Context mContext;

    public DataSource(Context context){
        mContext = context;
        mTechLabDataBaseHelper = new TechLabDataBaseHelper(mContext);
    }

    // Open
    public  void open() throws SQLException{
        mDatabase = mTechLabDataBaseHelper.getWritableDatabase();
    }

    // Close
    public  void  close(){
        mDatabase.close();
    }

    // Insert
    public void insertUser(Users user){
        ContentValues values = new ContentValues();
        values.put(mTechLabDataBaseHelper.COLUMN_FIRSTNAME, user.getFirstName());
        values.put(mTechLabDataBaseHelper.COLUMN_SURNAME, user.getSurname());
        values.put(mTechLabDataBaseHelper.COLUMN_SCHOOLEMAIL, user.getSchoolEmail());
        values.put(mTechLabDataBaseHelper.COLUMN_PASSWORD, user.getPassword());
        values.put(mTechLabDataBaseHelper.COLUMN_LOANED_AMOUNT,user.getLoanedAmount());
        values.put(mTechLabDataBaseHelper.COLUMN_USER_TYPE,user.getUserType());

        mDatabase.insert(mTechLabDataBaseHelper.USER_TABLE_NAME,null,values);
    }

    public void insertProduct(Electronics product){
        ContentValues values = new ContentValues();
        values.put(mTechLabDataBaseHelper.COLUMN_ELECTRONICS_ID, product.getProductId()) ;
        values.put(mTechLabDataBaseHelper.COLUMN_CATEGORY, product.getCategory()) ;
        values.put(mTechLabDataBaseHelper.COLUMN_ELECTRONICS_MANUFACTURER, product.getProductManufacturer()) ;
        values.put(mTechLabDataBaseHelper.COLUMN_PRODUCT_NAME, product.getName()) ;
        values.put(mTechLabDataBaseHelper.COLUMN_STOCK, product.getStock()) ;
        values.put(mTechLabDataBaseHelper.COLUMN_AMOUNT_BROKEN, product.getAmountBroken()) ;
        values.put(mTechLabDataBaseHelper.COLUMN_DESCRIPTION, product.getDescription()) ;
        mDatabase.insert(mTechLabDataBaseHelper.ELECTRONICS_TABLE_NAME,null,values);
    }

    public void insertBooks(Books product){
        ContentValues values = new ContentValues();
        values.put(mTechLabDataBaseHelper.COLUMN_BOOK_ISBN, product.getISBN()) ;
        values.put(mTechLabDataBaseHelper.COLUMN_CATEGORY, product.getCategory()) ;
        values.put(mTechLabDataBaseHelper.COLUMN_BOOK_PUBLISHER, product.getPublisher()) ;
        values.put(mTechLabDataBaseHelper.COLUMN_PRODUCT_NAME, product.getName()) ;
        values.put(mTechLabDataBaseHelper.COLUMN_STOCK, product.getStock()) ;
        values.put(mTechLabDataBaseHelper.COLUMN_BOOK_WRITERS, product.getWriters());
        mDatabase.insert(mTechLabDataBaseHelper.BOOKS_TABLE_NAME,null,values);
    }

    // Select
    public Cursor selectAllUsers(){
        Cursor cursor =  mDatabase.query(
                mTechLabDataBaseHelper.USER_TABLE_NAME,
                new String[]{mTechLabDataBaseHelper.COLUMN_SCHOOLEMAIL,mTechLabDataBaseHelper.COLUMN_FIRSTNAME,mTechLabDataBaseHelper.COLUMN_LOANED_AMOUNT},
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    public Cursor selectAllproduct(){
        Cursor cursor =  mDatabase.query(
                mTechLabDataBaseHelper.ELECTRONICS_TABLE_NAME,
                new String[]{mTechLabDataBaseHelper.COLUMN_ELECTRONICS_ID
                        ,mTechLabDataBaseHelper.COLUMN_ELECTRONICS_MANUFACTURER
                        ,mTechLabDataBaseHelper.COLUMN_PRODUCT_NAME
                        ,mTechLabDataBaseHelper.COLUMN_STOCK
                        ,mTechLabDataBaseHelper.COLUMN_AMOUNT_BROKEN
                        ,mTechLabDataBaseHelper.COLUMN_CATEGORY
                        ,mTechLabDataBaseHelper.COLUMN_DESCRIPTION},
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }


    public Cursor selectUsersWithLoanedProduct(){
        String whereClause = mTechLabDataBaseHelper.COLUMN_LOANED_AMOUNT + " > 0";

        Cursor cursor = (Cursor) mDatabase.query(
                mTechLabDataBaseHelper.USER_TABLE_NAME,
                new String[]{mTechLabDataBaseHelper.COLUMN_SURNAME},
                whereClause,
                null,
                null,
                null,
                null

        );
        return cursor;
    }
    //get user object
    public Users getUser(String userEmail){
        String checkQuery = "SELECT * FROM " + mTechLabDataBaseHelper.USER_TABLE_NAME + " WHERE "
                + mTechLabDataBaseHelper.COLUMN_SCHOOLEMAIL + " =\"" + userEmail + "\";";
        Cursor cursor = mDatabase.rawQuery(checkQuery,null);
        cursor.moveToFirst();
        Users user = new Users(cursor.getString(1),cursor.getString(2),cursor.getString(3)
                ,cursor.getString(4),cursor.getInt(5),cursor.getString(6));
        return user;
    }
    public Electronics getProduct(String id){
        String checkQuery = "SELECT * FROM " + mTechLabDataBaseHelper.ELECTRONICS_TABLE_NAME + " WHERE "
                + mTechLabDataBaseHelper.COLUMN_ID + " = " + id + ";";
        Cursor cursor = mDatabase.rawQuery(checkQuery,null);
        cursor.moveToFirst();
        Electronics product = new Electronics(cursor.getString(1),cursor.getString(2),cursor.getString(3)
                ,cursor.getInt(4),cursor.getInt(5),cursor.getString(6),cursor.getString(7));
        return product;
    }
    public Cursor getProductCursor(String productID){
        String checkQuery = "SELECT * FROM " + mTechLabDataBaseHelper.ELECTRONICS_TABLE_NAME + " WHERE "
                + mTechLabDataBaseHelper.COLUMN_ELECTRONICS_ID + " =\"" + productID + "\";";
        Cursor cursor = mDatabase.rawQuery(checkQuery,null);
        return cursor;
    }

    // Update
    public void updateUser(Users user){
        String whereClause = mTechLabDataBaseHelper.COLUMN_SCHOOLEMAIL + " = "
                + user.getSchoolEmail();

        ContentValues values = new ContentValues();
        values.put(mTechLabDataBaseHelper.COLUMN_FIRSTNAME, user.getFirstName());
        values.put(mTechLabDataBaseHelper.COLUMN_SURNAME, user.getSurname());
        values.put(mTechLabDataBaseHelper.COLUMN_SCHOOLEMAIL, user.getSchoolEmail());
        values.put(mTechLabDataBaseHelper.COLUMN_PASSWORD, user.getPassword());
        values.put(mTechLabDataBaseHelper.COLUMN_LOANED_AMOUNT,user.getLoanedAmount());
        mDatabase.update(
                mTechLabDataBaseHelper.USER_TABLE_NAME,
                values,
                whereClause,
                null
        );
    }

    public void updateElectronic(Electronics electronic,String id){

        String whereClause = mTechLabDataBaseHelper.COLUMN_ID + " = "
                + id;

        ContentValues values = new ContentValues();
        values.put(mTechLabDataBaseHelper.COLUMN_DESCRIPTION, electronic.getDescription());
        values.put(mTechLabDataBaseHelper.COLUMN_CATEGORY, electronic.getCategory());
        values.put(mTechLabDataBaseHelper.COLUMN_ELECTRONICS_MANUFACTURER, electronic.getProductManufacturer());
        values.put(mTechLabDataBaseHelper.COLUMN_ELECTRONICS_ID, electronic.getProductId());
        values.put(mTechLabDataBaseHelper.COLUMN_PRODUCT_NAME,electronic.getName());
        values.put(mTechLabDataBaseHelper.COLUMN_STOCK,electronic.getStock());
        values.put(mTechLabDataBaseHelper.COLUMN_AMOUNT_BROKEN,electronic.getAmountBroken());


        mDatabase.update(
                mTechLabDataBaseHelper.ELECTRONICS_TABLE_NAME,
                values,
                whereClause,
                null
        );
    }

    // Delete
    public void deleteUser(Users user){
        String whereClause = mTechLabDataBaseHelper.COLUMN_SCHOOLEMAIL + " = "
                + user.getSchoolEmail();
        mDatabase.delete(
                mTechLabDataBaseHelper.USER_TABLE_NAME,
                whereClause,
                null
        );
    }

    public void deleteProduct(String id){
        String whereClause = mTechLabDataBaseHelper.COLUMN_ID + " = "
                + id;
        mDatabase.delete(
                mTechLabDataBaseHelper.ELECTRONICS_TABLE_NAME,
                whereClause,
                null
        );
    }

    // check
    public boolean ifExists(String emailInput,String passwordInput)
    {
        String checkQuery = "SELECT * FROM " + mTechLabDataBaseHelper.USER_TABLE_NAME + " WHERE "
                + mTechLabDataBaseHelper.COLUMN_SCHOOLEMAIL + " =\"" + emailInput + "\" and "
                + mTechLabDataBaseHelper.COLUMN_PASSWORD + " = \"" + passwordInput + "\";";
        Cursor cursor = mDatabase.rawQuery(checkQuery,null);
        boolean exists = (cursor.getCount() > 0);
        return exists;
    }
}
