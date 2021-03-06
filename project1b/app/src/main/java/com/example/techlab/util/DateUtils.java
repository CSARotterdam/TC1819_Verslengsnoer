package com.example.techlab.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
    public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm";


    public static Timestamp getCurrentDate() {
        java.util.Date utilDate = Calendar.getInstance().getTime();
        return new java.sql.Timestamp(utilDate.getTime());
    }

    public static String getCurrentDate(Timestamp date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(date);
    }

}
