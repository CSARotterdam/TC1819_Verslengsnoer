package com.example.techlableenapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.techlableenapp.Users;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME = "users";
    private static final String COL_ID = "id";
    private static final String COL_USERNAME = "username";
    private static final String COL_SURNAME = "surname";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COL_USERNAME + " TEXT ," +
                COL_SURNAME + " TEXT ," +
                COL_EMAIL + " TEXT ," +
                COL_PASSWORD + " TEXT " +
                ");";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUsers(Users users){
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, users.getFirstName()) ;
        values.put(COL_SURNAME, users.getSurname()) ;
        values.put(COL_EMAIL, users.getSchoolEmail()) ;
        values.put(COL_PASSWORD, users.getPassword()) ;
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void deleteUsers(String userEmail ){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COL_EMAIL + " =\"" + userEmail + "\";" );

    }

    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0)
        {
            c.moveToFirst();
            do {
                dbString += c.getString(c.getColumnIndex("username"));
                dbString += " \n";
            } while (c.moveToNext());
            c.close();
        }
        return dbString;
    }

    public boolean ifExists(String emailInput,String passwordInput)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = null;
        String checkQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_EMAIL + " =\"" + emailInput + "\" and "
                + COL_PASSWORD + " = \"" + passwordInput + "\";";
        cursor = db.rawQuery(checkQuery,null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}
