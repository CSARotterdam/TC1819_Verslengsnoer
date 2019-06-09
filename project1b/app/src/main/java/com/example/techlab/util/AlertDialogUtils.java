package com.example.techlab.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class AlertDialogUtils {
    //POP UP in User Management user InfoActivity class
    public static void alertDialogProductOnLoanIsNotZero(Context context,String Message) {

        AlertDialog.Builder RequestItemAlertDialog = new AlertDialog.Builder(context)
                .setTitle("Mislukt !!")
                .setMessage(Message)
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })

//               You can't click outside the popup to cancel
                .setCancelable(false);

        //Creating dialog box
        RequestItemAlertDialog.create().show();
    }
}
