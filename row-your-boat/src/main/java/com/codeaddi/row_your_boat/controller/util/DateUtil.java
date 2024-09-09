package com.codeaddi.row_your_boat.controller.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtil {

  private static final String dateFormat = "EEE MMM dd yyyy HH:mm";

  public static String formatDate(Date date) {
    SimpleDateFormat outputFormat = new SimpleDateFormat(dateFormat);
    return outputFormat.format(date);
  }

  public static Date getDateFromFormattedString(String formattedDate) {
    SimpleDateFormat inputFormat = new SimpleDateFormat(dateFormat);
    try {
      return inputFormat.parse(formattedDate);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }
}
