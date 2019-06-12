package com.example.techlab.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.techlab.db.DataManagement;

public class blockfunc {
    DataManagement dataManagement;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    mSharedPreferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);
    mEditor = mSharedPreferences.edit();

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
            //ISSUES HERE WITH NULL REF
            mEditor.putString(MainActivity.KEY_ACTIVE_USER_STATUS, dataManagement.getUserWithEmail(email).getUserType()).apply();
            return false;
        }
    }
    protected void ShowBlockDialog(String title){
        AlertDialog.Builder RequestItemAlertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage("Uw account wordt uitgelogd en is geblokkeerd, neem contact met TechLab.")
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
