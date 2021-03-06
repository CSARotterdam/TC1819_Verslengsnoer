package com.example.techlableenapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TechlabDatabaseHelper extends SQLiteOpenHelper {
    public static final String USER_TABLE_NAME = "USERS";

    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_FIRSTNAME = "FIRSTNAME";
    public static final String COLUMN_SURNAME = "SURNAME";
    public static final String COLUMN_SCHOOLEMAIL = "SCHOOL_EMAIL";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_LOANED_AMOUNT = "LOANED_AMOUNT";
    public static final String COLUMN_USER_TYPE = "USER_TYPE";

    public static final String PRODUCT_TABLE_NAME = "PRODUCT";

    public static final String COLUMN_PRODUCT_ID = "PRODUCT_ID";
    public static final String COLUMN_PRODUCT_MANUFACTURER = "PRODUCT_MANUFACTURER";
    public static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    public static final String COLUMN_PRODUCT_STOCK = "PRODUCT_STOCK";
    public static final String COLUMN_AMOUNT_BROKEN = "PRODUCT_AMOUNT_BROKEN";
    public static final String COLUMN_PRODUCT_CATEGORY = "PRODUCT_CATEGORY";




    private static final  String DB_NAME = "techlab.db";
    private static final int DB_VERSION = 1;

    private static final String CREATE_USER_DB=
            "CREATE TABLE " + USER_TABLE_NAME + " (" +
                    COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_FIRSTNAME +" TEXT, " +
                    COLUMN_SURNAME + " TEXT, " +
                    COLUMN_SCHOOLEMAIL + " TEXT, "+
                    COLUMN_PASSWORD + " TEXT, "+
                    COLUMN_LOANED_AMOUNT + " INTEGER, "+
                    COLUMN_USER_TYPE + " TEXT"+
                    ");";

    private static final String CREATE_PRODUCT_DB =
            "CREATE TABLE " + PRODUCT_TABLE_NAME + "(" +
                    COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_PRODUCT_ID +" TEXT, " +
                    COLUMN_PRODUCT_MANUFACTURER + " TEXT, " +
                    COLUMN_PRODUCT_NAME + " TEXT, "+
                    COLUMN_PRODUCT_STOCK + " INTEGER, "+
                    COLUMN_AMOUNT_BROKEN + " INTEGER, "+
                    COLUMN_PRODUCT_CATEGORY + " TEXT "+
                    ");";


    public TechlabDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_DB);
        db.execSQL(CREATE_PRODUCT_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
