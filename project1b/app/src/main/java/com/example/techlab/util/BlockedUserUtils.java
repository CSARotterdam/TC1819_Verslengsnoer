package com.example.techlab.util;

import android.content.Context;
import android.content.Intent;

import com.example.techlab.db.DataManagement;
import com.example.techlab.view.MainActivity;

public class BlockedUserUtils {

    public static void blockFunc(Context context, String email){
        DataManagement dataManagement = new DataManagement();
        if (dataManagement.ifBlocked(email)) {
            context.getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE).edit().putString(MainActivity.KEY_STAY_LOGGED_IN,"off").apply();
            context.getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE).edit().putString(MainActivity.KEY_ACTIVE_USER_STATUS,"blocked").apply();
            context.startActivity(new Intent(context,  MainActivity.class));
        }
    }
}
