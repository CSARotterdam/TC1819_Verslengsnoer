package com.example.techlab.model;


public class Borrow {
    private String mGebrnaam;
    private byte[] mImageResource;
    private String mProductName;
    private String mRequestDate;
    private String mBorrowDate;
    private int mProductAmount;
    private String mProductStatus;
    private int mProductID;
    private int mBorrowID;
    int mUserID;




    public Borrow(String PName, String RequestDate, String borrowDate, int PAmount, String PStatus, int ProductID , byte[] image, String Gebrnaam, int UserID, int BorrowID){
        mGebrnaam = Gebrnaam;
        mImageResource = image;
        mProductName = PName;
        mRequestDate = RequestDate;
        mBorrowDate = borrowDate;
        mProductAmount = PAmount;
        mProductStatus = PStatus;
        mProductID = ProductID;
        mUserID = UserID;
        mBorrowID = BorrowID;

    }

    public int getBorrowID() {
        return mBorrowID;
    }



    public String getmBorrowDate() {
        return mBorrowDate;
    }

    public String getmGebrnaam() {
        return mGebrnaam;
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

    public int getmProductID() {
        return mProductID;
    }
}
