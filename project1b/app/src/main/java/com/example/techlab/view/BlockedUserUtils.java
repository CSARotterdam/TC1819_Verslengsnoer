package com.example.techlab.view;

import android.content.Context;
import android.content.Intent;

import com.example.techlab.db.DataManagement;
import com.example.techlab.util.AlertDialogUtils;

public class BlockedUserUtils {

    public static void blockFunc(Context context, String email,String message){
        DataManagement dataManagement = new DataManagement();
        if (dataManagement.ifBlocked(email)) {

            context.getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE).edit().putString(MainActivity.KEY_STAY_LOGGED_IN,"off");
            AlertDialogUtils.alertDialog(context,"Uitgelogd",message);
            context.startActivity(new Intent(context,  MainActivity.class));
        }
    }
}
