package com.codeaddi.row_your_boat.controller.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtil {

  private static final String dateFormat = "EEE MMM dd yyyy HH:mm";
  private static final String dateOnlyFormat = "dd/MM/yyyy";
  private static final String timeOnlyFormat = "HH:mm";

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

  public static String extractDateOnly(String formattedDate) {
    Date date = getDateFromFormattedString(formattedDate);
    SimpleDateFormat dateFormat = new SimpleDateFormat(dateOnlyFormat);
    return dateFormat.format(date);
  }

  public static String extractMilitaryTime(String formattedDate) {
    Date date = getDateFromFormattedString(formattedDate);
    SimpleDateFormat timeFormat = new SimpleDateFormat(timeOnlyFormat);
    return timeFormat.format(date);
  }

  public static String extractMilitaryTimePlus2Hours(String formattedDate) {
    Date date = getDateFromFormattedString(formattedDate);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.HOUR_OF_DAY, 2);
    SimpleDateFormat timeFormat = new SimpleDateFormat(timeOnlyFormat);
    return timeFormat.format(calendar.getTime());
  }
}
