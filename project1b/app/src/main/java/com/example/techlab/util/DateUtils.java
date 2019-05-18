package com.example.techlab.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    public static Timestamp getCurrentDate() {
        java.util.Date utilDate = Calendar.getInstance().getTime();
        return new java.sql.Timestamp(utilDate.getTime());
    }

    public static String getCurrentDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }
}
