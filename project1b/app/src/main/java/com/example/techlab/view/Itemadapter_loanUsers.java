package com.example.techlab.view;


public class Itemadapter_loanUsers {
    private String mPrnaam;
    private String mGebrnaam;

    public Itemadapter_loanUsers(String Prnaam, String Gebrnaam){
        mPrnaam = Prnaam;
        mGebrnaam = Gebrnaam;
    }

    public String getPrnaam() {
        return mPrnaam;
    }

    public String getGebrnaam() {
        return mGebrnaam;
    }
}
