package com.example.techlableenapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.techlableenapp.model.Products;
import com.example.techlableenapp.model.Users;

public class UserDataSourse {

    private SQLiteDatabase mDatabase;
    private TechlabDatabaseHelper mTechlabDatabaseHelper;
    private Context mContext;

    public UserDataSourse(Context context){
        mContext = context;
        mTechlabDatabaseHelper = new TechlabDatabaseHelper(mContext);
    }

    // Open
    public  void open() throws SQLException{
        mDatabase = mTechlabDatabaseHelper.getWritableDatabase();
    }

    // Close
    public  void  close(){
        mDatabase.close();
    }

    // Insert
    public void insertUser(Users user){
        ContentValues values = new ContentValues();
        values.put(mTechlabDatabaseHelper.COLUMN_FIRSTNAME, user.getFirstName());
        values.put(mTechlabDatabaseHelper.COLUMN_SURNAME, user.getSurname());
        values.put(mTechlabDatabaseHelper.COLUMN_SCHOOLEMAIL, user.getSchoolEmail());
        values.put(mTechlabDatabaseHelper.COLUMN_PASSWORD, user.getPassword());

        mDatabase.insert(mTechlabDatabaseHelper.USER_TABLE_NAME,null,values);
    }

    public void insertProduct(Products product){
        ContentValues values = new ContentValues();
        values.put(mTechlabDatabaseHelper.COLUMN_PRODUCT_ID, product.getProductId()) ;
        values.put(mTechlabDatabaseHelper.COLUMN_PRODUCT_CATEGORY, product.getProductCategory()) ;
        values.put(mTechlabDatabaseHelper.COLUMN_PRODUCT_MANUFACTURER, product.getProductManufacturer()) ;
        values.put(mTechlabDatabaseHelper.COLUMN_PRODUCT_NAME, product.getProductName()) ;
        values.put(mTechlabDatabaseHelper.COLUMN_PRODUCT_STOCK, product.getProductStock()) ;
        values.put(mTechlabDatabaseHelper.COLUMN_AMOUNT_BROKEN, product.getAmountBroken()) ;
        mDatabase.insert(mTechlabDatabaseHelper.PRODUCT_TABLE_NAME,null,values);
    }

    // Select
    public Cursor selectAllUsers(){
        Cursor cursor = (Cursor) mDatabase.query(
                TechlabDatabaseHelper.USER_TABLE_NAME,
                new String[]{TechlabDatabaseHelper.COLUMN_PRODUCT_NAME},
                null,
                null,
                null,
                null,
                null

        );
        return cursor;

    }

    // Update

    // Delete

    // Check

    public boolean ifExists(String emailInput,String passwordInput)
    {
        Cursor cursor = null;
        String checkQuery = "SELECT * FROM " + mTechlabDatabaseHelper.USER_TABLE_NAME + " WHERE "
                + mTechlabDatabaseHelper.COLUMN_SCHOOLEMAIL + " =\"" + emailInput + "\" and "
                + mTechlabDatabaseHelper.COLUMN_PASSWORD + " = \"" + passwordInput + "\";";
        cursor = mDatabase.rawQuery(checkQuery,null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;



    }
}
