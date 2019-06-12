package com.example.techlab.util;

import android.content.Context;

import com.example.techlab.view.blockfunc;

public class CheckBlockUtils {
    public static void ExecuteCheckBlock(Context context, String email, String page){
        System.out.println(page);
        blockfunc blocked = new blockfunc(email, context);
        if (blocked.ifblocked()) {
            System.out.println("Blocked USER");
            blocked.Redirect(page);
        }
    }
}
