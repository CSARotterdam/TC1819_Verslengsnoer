package com.example.techlab.db;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    String IP,DB,UserName,Password;
    @SuppressLint("NewApi")
    public Connection connection(){
        IP = "techlabapp.database.windows.net";
        DB = "techLab";
        UserName = "techLab";
        Password = "987654321asd@";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        java.sql.Connection connection = null;
        String ConnectionURL;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://techlabapp.database.windows.net:1433;DatabaseName=techlabapp;user=techLab@techlabapp;password="+Password+";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error 1: ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error 1: ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error 3: ", e.getMessage());
        }
        return connection;
    }

}
