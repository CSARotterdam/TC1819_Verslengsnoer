package com.example.techlab.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.techlab.db.DataManagement;

import static android.content.Context.MODE_PRIVATE;

public class blockfunc {
    DataManagement dataManagement;
    private String email;
    Context context;


    public blockfunc(String email, Context context) {
        System.out.println("BlockSys function executed");
        this.email = email;
        this.context = context;
    }

    protected boolean ifblocked(){
        dataManagement = new DataManagement();
        if (dataManagement.ifBlocked(email)) {
            return true;
        }
        else{
            //Update user status for every page opened
            if(context.getSharedPreferences(MainActivity.PREFERENCES_FILE, MODE_PRIVATE).getString(MainActivity.KEY_ACTIVE_USER_EMAIL,"").length() > 0) {
                context.getSharedPreferences(MainActivity.PREFERENCES_FILE, MODE_PRIVATE).edit().putString(MainActivity.KEY_ACTIVE_USER_STATUS, dataManagement.getUserTypeByEmail(email)).apply(); // Update status on every time a page opens.
            }
            return false;
        }
    }
    protected void ShowBlockDialog(String title){
        AlertDialog.Builder RequestItemAlertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage("Uw account is geblokkeerd, neem contact met TechLab.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setCancelable(false);
        //Creating dialog box
        RequestItemAlertDialog.create().show();
    }

    protected void Redirect(String CurrentPage){
        if(!CurrentPage.equals("Mainactivity")){
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
    }
}
