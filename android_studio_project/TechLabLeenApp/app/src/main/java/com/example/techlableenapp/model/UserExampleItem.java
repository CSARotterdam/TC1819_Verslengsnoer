package com.example.techlableenapp.model;

public class UserExampleItem {
    private String UserManagementItemUserEmail;
    private String UserManagementItemUserName;
    private int UserManagementItemUserProductLoanedAmount;

    public UserExampleItem(String userManagementItemUserEmail,
                           String userManagementItemUserName,
                           int userManagementItemUserProductLoanedAmount) {
        UserManagementItemUserEmail = userManagementItemUserEmail;
        UserManagementItemUserName = userManagementItemUserName;
        UserManagementItemUserProductLoanedAmount = userManagementItemUserProductLoanedAmount;
    }

    public String getUserManagementItemUserEmail() {
        return UserManagementItemUserEmail;
    }

    public String getUserManagementItemUserName() {
        return UserManagementItemUserName;
    }

    public int getUserManagementItemUserProductLoanedAmount() {
        return UserManagementItemUserProductLoanedAmount;
    }
}
