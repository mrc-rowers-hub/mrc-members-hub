package com.codeaddi.row_your_boat.controller.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String formatDate(Date date){
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEE MMM dd HH:mm");
        return outputFormat.format(date);
    }
}
