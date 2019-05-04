package com.example.techlab.model;


import java.util.Date;

public class Borrow {

    private int mImageResource;
    private String mProductName;
    private String mTurnInDate;
    private int mProductAmount;
    private String mProductStatus;

    public Borrow(String PName, String TurnInDate, int PAmount, String PStatus){
//        mImageResource = imageResource;
        mProductName = PName;
        mTurnInDate = TurnInDate;
        mProductAmount = PAmount;
        mProductStatus = PStatus;
    }

    public int getImageResource(){
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

}
