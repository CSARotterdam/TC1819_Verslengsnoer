package com.example.techlab.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.techlab.db.DataManagement;

public class blockfunc {


    public blockfunc(String email, final Context context){
        System.out.println("BlockSys function executed");
        DataManagement dataManagement;
        dataManagement = new DataManagement();
        if (dataManagement.ifBlocked(email)) {
            AlertDialog.Builder RequestItemAlertDialog = new AlertDialog.Builder(context)
                    .setTitle("Uitgelogd")
                    .setMessage("Uw account wordt uitgelogd en is geblokkeerd, neem contact met TechLab.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            context.startActivity(new Intent(context, MainActivity.class));
                        }
                    })
                    .setCancelable(false);
            //Creating dialog box
            RequestItemAlertDialog.create().show();
        }
    }
}
