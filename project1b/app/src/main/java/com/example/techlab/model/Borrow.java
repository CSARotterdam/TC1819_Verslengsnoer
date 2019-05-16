package com.example.techlab.model;


public class Borrow {
    private String mGebrnaam;
    private byte[] mImageResource;
    private String mProductName;
    private String mTurnInDate;
    private int mProductAmount;
    private String mProductStatus;
    private int mPKID;
    String mProductType;

    public Borrow(String PName, String TurnInDate, int PAmount, String PStatus, int PKID, String ProductType, byte[] image, String Gebrnaam){
        mGebrnaam = Gebrnaam;
        mImageResource = image;
        mProductName = PName;
        mTurnInDate = TurnInDate;
        mProductAmount = PAmount;
        mProductStatus = PStatus;
        mPKID = PKID;
        mProductType =ProductType;
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

    public String getTurnInDate(){
        return mTurnInDate;
    }

    public void setTurnInDate( String RequestedDate){
        this.mTurnInDate = RequestedDate;
    }

    public int getBorrowItemAmount(){
        return mProductAmount;
    }

    public String getBorrowStatus(){
        return mProductStatus;
    }

    public void setBorrowStatus(String borrowStatus){
        this.mProductStatus = borrowStatus;
    }

    public int getmPKID() {
        return mPKID;
    }
}
