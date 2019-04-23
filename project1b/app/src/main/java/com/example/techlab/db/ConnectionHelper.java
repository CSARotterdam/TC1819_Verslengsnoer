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
        IP = "145.24.222.204,8080";
        DB = "TechLab";
        UserName = "0958654";
        Password = "S77d4h";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        java.sql.Connection connection = null;
        String ConnectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + IP +";databaseName="+ DB + ";user=" + UserName+ ";password=" + Password + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error 1: ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error 2: ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error 3: ", e.getMessage());
        }
        return connection;
    }

}
