package com.example.techlableenapp;

public class Users {
    private String firstName;
    private String surname;
    private String schoolEmail;
    private String password;

    public Users(String firstName, String surname, String schoolEmail, String password) {
        this.firstName = firstName;
        this.surname = surname;
        this.schoolEmail = schoolEmail;
        this.password = password;
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
}
