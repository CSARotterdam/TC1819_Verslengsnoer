package com.example.techlab.model;


public class Borrow {
    private String mGebrnaam;
    private byte[] mImageResource;
    private String mProductName;
    private String mRequestDate;
    private String mBorrowDate;
    private int mProductAmount;
    private String mProductStatus;
    private int mPKID;
    String mProductType;
    int mUserID;

    public Borrow(String PName, String RequestDate,String borrowDate, int PAmount, String PStatus, int PKID, String ProductType, byte[] image, String Gebrnaam, int UserID){
        mGebrnaam = Gebrnaam;
        mImageResource = image;
        mProductName = PName;
        mRequestDate = RequestDate;
        mBorrowDate = borrowDate;
        mProductAmount = PAmount;
        mProductStatus = PStatus;
        mPKID = PKID;
        mProductType =ProductType;
        mUserID=UserID;
    }

    public String getmBorrowDate() {
        return mBorrowDate;
    }

    public String getmGebrnaam() {
        return mGebrnaam;
    }

    public String getmProductType() {
        return mProductType;
    }

    public byte[] getImageResource(){
        return mImageResource;
    }

    public String getProductName(){
        return mProductName;
    }

    public String getRequestDate(){
        return mRequestDate;
    }


    public int getBorrowItemAmount(){
        return mProductAmount;
    }

    public String getBorrowStatus(){
        return mProductStatus;
    }

    public int getUserID() {
        return mUserID;
    }

    public int getmPKID() {
        return mPKID;
    }
}
