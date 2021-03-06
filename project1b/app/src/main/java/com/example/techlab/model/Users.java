package com.example.techlab.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Users implements Parcelable  {
    private String firstName;
    private String surname;
    private String schoolEmail;
    private String password;
    private int loanedAmount;
    private int productOnLoan;
    private String userType;
    private int id;
    private int blockStatus;

    public Users(String firstName, String surname, String schoolEmail,int loanedAmount ,String userType, int _id, int block, int productOnLoan) {
        this.firstName = firstName;
        this.surname = surname;
        this.schoolEmail = schoolEmail;
        this.loanedAmount = loanedAmount;
        this.userType = userType;
        this.id = _id;
        this.blockStatus = block;
        this.productOnLoan =productOnLoan;
    }
    //overload Users for Blocked list
    public Users(String firstName, String surname, int _id) {
        this.firstName = firstName;
        this.surname = surname;
        this.id = _id;
    }



    protected Users(Parcel in) {
        firstName = in.readString();
        surname = in.readString();
        schoolEmail = in.readString();
        password = in.readString();
        loanedAmount = in.readInt();
        userType = in.readString();
        blockStatus = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    public String getUserType() {
        return userType;
    }

    public int getProductOnLoan() {
        return productOnLoan;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSchoolEmail() {
        return schoolEmail;
    }

    public void setSchoolEmail(String schoolEmail) {
        this.schoolEmail = schoolEmail;
    }

    public String getPassword() {
        return password;
    }


    public int getLoanedAmount() {
        return loanedAmount;
    }

    public void setLoanedAmount(int loanedAmount) {
        this.loanedAmount = loanedAmount;
    }

    public int getBlockStatus() {
        return blockStatus;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(surname);
        dest.writeString(schoolEmail);
        dest.writeInt(loanedAmount);
        dest.writeString(userType);
        dest.writeInt(blockStatus);
    }
}
