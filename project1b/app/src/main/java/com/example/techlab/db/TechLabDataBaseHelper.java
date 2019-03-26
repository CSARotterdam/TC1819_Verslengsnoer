package com.example.techlab.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TechLabDataBaseHelper extends SQLiteOpenHelper {
    //ALL TABLE NAMES
    public static final String USER_TABLE_NAME = "USERS";
    public static final String ELECTRONICS_TABLE_NAME = "ELECTRONICS";
    public static final String BOOKS_TABLE_NAME = "BOOKS";

    //USER TABLE
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_FIRSTNAME = "FIRSTNAME";
    public static final String COLUMN_SURNAME = "SURNAME";
    public static final String COLUMN_SCHOOLEMAIL = "SCHOOL_EMAIL";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_LOANED_AMOUNT = "LOANED_AMOUNT";
    public static final String COLUMN_USER_TYPE = "USER_TYPE";

    //ELECTRONICS TABLE
    public static final String COLUMN_ELECTRONICS_ID = "PRODUCT_ID";
    public static final String COLUMN_AMOUNT_BROKEN = "AMOUNT_BROKEN";
    public static final String COLUMN_ELECTRONICS_MANUFACTURER = "MANUFACTURER";

    //BOOKS TABLE
    public static final String COLUMN_BOOK_ISBN = "ISBN";
    public static final String COLUMN_BOOK_PUBLISHER = "PUBLISHER";
    public static final String COLUMN_BOOK_WRITERS = "WRITERS";

    //BOTH BOOKS & ELECTRONICS TABLE
    public static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    public static final String COLUMN_STOCK = "STOCK";
    public static final String COLUMN_CATEGORY = "CATEGORY";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";

    private static final  String DB_NAME = "techlab.db";
    private static final int DB_VERSION = 2;
    //USER TABLE CREATE IF NOT EXIST
    private static final String CREATE_USER_TABLE=
            "CREATE TABLE " + USER_TABLE_NAME + " (" +
                    COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_FIRSTNAME +" TEXT, " +
                    COLUMN_SURNAME + " TEXT, " +
                    COLUMN_SCHOOLEMAIL + " TEXT, "+
                    COLUMN_PASSWORD + " TEXT, "+
                    COLUMN_LOANED_AMOUNT + " INTEGER, "+
                    COLUMN_USER_TYPE + " TEXT"+
                    ");";

    //ELECTRONICS TABLE CREATE IF NOT EXIST
    private static final String CREATE_ELECTRONICS_TABLE =
            "CREATE TABLE " + ELECTRONICS_TABLE_NAME + "(" +
                    COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_ELECTRONICS_ID +" TEXT, " +
                    COLUMN_PRODUCT_NAME + " TEXT, "+
                    COLUMN_ELECTRONICS_MANUFACTURER + " TEXT, " +
                    COLUMN_STOCK + " INTEGER, "+
                    COLUMN_AMOUNT_BROKEN + " INTEGER, "+
                    COLUMN_CATEGORY + " TEXT, "+
                    COLUMN_DESCRIPTION + " TEXT "+
                    ");";

    private static final String CREATE_BOOKS_TABLE =
            "CREATE TABLE " + BOOKS_TABLE_NAME + "(" +
                    COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_BOOK_ISBN + " TEXT, "+
                    COLUMN_PRODUCT_NAME + " TEXT, "+
                    COLUMN_BOOK_WRITERS+ " TEXT, "+
                    COLUMN_BOOK_PUBLISHER+ "TEXT,"+
                    COLUMN_CATEGORY + " TEXT, "+
                    COLUMN_STOCK + " INTEGER, "+
                    COLUMN_DESCRIPTION + " TEXT "+
                    ");";

    // To ALTER TABLE TO ADD COLUMN TO EXISTING TABLE.
    //    private static final String DB_ALTER =
    //            "ALTER TABLE " + ELECTRONICS_TABLE_NAME + " ADD COLUMN "+COLUMN_PRODUCT_DESCRIPTION+" TEXT";


    public TechLabDataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_ELECTRONICS_TABLE);
        db.execSQL(CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
