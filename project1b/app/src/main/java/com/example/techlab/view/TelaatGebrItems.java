package com.example.techlab.view;

public class TelaatGebrItems {

    private String mTelaatGebr;
    private String mTelaatStuNum;
    private String mTelaatPr;

    public TelaatGebrItems(String TelaatGebr, String TelaatStuNum, String TelaatPr){
        mTelaatGebr = TelaatGebr;
        mTelaatStuNum = TelaatStuNum;
        mTelaatPr = TelaatPr;
    }

    public String getTelaatGebr(){
        return mTelaatGebr;
    }

    public String getTelaatStuNum(){
        return mTelaatStuNum;
    }

    public String getTelaatPr(){
        return mTelaatPr;
    }
}
