package com.example.techlableenapp.model;

public class Users {
    private String firstName;
    private String surname;
    private String schoolEmail;
    private String password;
    private int loanedAmount;

    public Users(String firstName, String surname, String schoolEmail, String password ,int loanedAmount) {
        this.firstName = firstName;
        this.surname = surname;
        this.schoolEmail = schoolEmail;
        this.password = password;
        this.loanedAmount = loanedAmount;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLoanedAmount() {
        return loanedAmount;
    }

    public void setLoanedAmount(int loanedAmount) {
        this.loanedAmount = loanedAmount;
    }
}
