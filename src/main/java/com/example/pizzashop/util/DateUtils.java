package com.example.pizzashop.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
//    public static final String ISO_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static String formatDateISO(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }
}
