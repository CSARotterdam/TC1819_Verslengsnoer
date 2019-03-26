package com.example.techlab.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TechLabDataBaseHelper extends SQLiteOpenHelper {
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
    public static final String COLUMN_PRODUCT_ISBN = "PRODUCT_ISBN";
    public static final String COLUMN_PRODUCT_PUBLISHER = "PRODUCT_PUBLISHER";
    public static final String COLUMN_PRODUCT_WRITER1 = "PRODUCT_WRITER1";
    public static final String COLUMN_PRODUCT_WRITER2 = "PRODUCT_WRITER2";
    public static final String COLUMN_PRODUCT_WRITER3 = "PRODUCT_WRITER3";
    public static final String COLUMN_PRODUCT_WRITER4 = "PRODUCT_WRITER4";
    public static final String COLUMN_PRODUCT_DESCRIPTION = "PRODUCT_DESCRIPTION";





    private static final  String DB_NAME = "techlab.db";
    private static final int DB_VERSION = 2;

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
                    COLUMN_PRODUCT_CATEGORY + " TEXT, "+
                    COLUMN_PRODUCT_WRITER1 + " TEXT, "+
                    COLUMN_PRODUCT_WRITER2 + " TEXT, "+
                    COLUMN_PRODUCT_WRITER3 + " TEXT, "+
                    COLUMN_PRODUCT_WRITER4 + " TEXT, "+
                    COLUMN_PRODUCT_ISBN + " TEXT, "+
                    COLUMN_PRODUCT_PUBLISHER +" TEXT, "+
                    COLUMN_PRODUCT_DESCRIPTION + " TEXT "+
                    ");";
    private static final String DB_ALTER =
            "ALTER TABLE " + PRODUCT_TABLE_NAME + " ADD COLUMN "+COLUMN_PRODUCT_DESCRIPTION+" TEXT";


    public TechLabDataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_DB);
        db.execSQL(CREATE_PRODUCT_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DB_ALTER);
    }
}
