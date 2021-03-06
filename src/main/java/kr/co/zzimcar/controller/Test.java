package kr.co.zzimcar.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Test {

  public static void main(String[] args) {
//    int month = 7;
//    int year = 2021;

//    Calendar cal = Calendar.getInstance();

//

//    for (int i = 1 ; i<=31 ; i++) {
//      System.out.println(i+" = "+getCurrentWeekOfMonth(2022,5,i));
//    }


    System.out.println(getWeekInMonths(2021,7));



  }


//  public static String getCurrentWeekOfMonth(int year, int month, int day)  {
//    int subtractFirstWeekNumber = subWeekNumberIsFirstDayAfterThursday(year, month, day);
//    int subtractLastWeekNumber = addMonthIsLastDayBeforeThursday(year, month, day);
//
//    // 마지막 주차에서 다음 달로 넘어갈 경우 다음달의 1일을 기준으로 정해준다.
//    // 추가로 다음 달 첫째주는 목요일부터 시작하는 과반수의 일자를 포함하기 때문에 한주를 빼지 않는다.
//    if (subtractLastWeekNumber > 0) {
//      day = 1;
//      subtractFirstWeekNumber = 0;
//    }
//
//    if (subtractFirstWeekNumber < 0)  {
//      Calendar calendar = Calendar.getInstance(Locale.KOREA);
//      calendar.set(year, month - 1, day);
//      calendar.add(Calendar.DATE, -1);
//
//
//
//      return getCurrentWeekOfMonth(calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH) + 1), calendar.get(Calendar.DATE));
//    }
//
//    Calendar calendar = Calendar.getInstance(Locale.KOREA);
//    calendar.setFirstDayOfWeek(Calendar.MONDAY);
//    calendar.setMinimalDaysInFirstWeek(1);
//    calendar.set(year, month - (1 - subtractLastWeekNumber), day);
//
//    int dayOfWeekForFirstDayOfMonth = calendar.get(Calendar.WEEK_OF_MONTH) - subtractFirstWeekNumber;
//
//    //    return (calendar.get(Calendar.MONTH) + 1) + "," + dayOfWeekForFirstDayOfMonth;
//    return (calendar.get(Calendar.MONTH) + 1) + "," + dayOfWeekForFirstDayOfMonth;
//  }
//  public static int subWeekNumberIsFirstDayAfterThursday(int year, int month, int day)  {
//    Calendar calendar = Calendar.getInstance(Locale.KOREA);
//    calendar.set(year, month - 1, day);
//    calendar.set(Calendar.DAY_OF_MONTH, 1);
//    calendar.setFirstDayOfWeek(Calendar.MONDAY);
//
//    int weekOfDay = calendar.get(Calendar.DAY_OF_WEEK);
//
//    if ((weekOfDay >= Calendar.MONDAY) && (weekOfDay <= Calendar.THURSDAY)) {
//      return 0;
//    } else if (day == 1 && (weekOfDay < Calendar.MONDAY || weekOfDay > Calendar.TUESDAY))  {
//      return -1;
//    } else {
//      return 1;
//    }
//  }
//  public static int addMonthIsLastDayBeforeThursday(int year, int month, int day) {
//    Calendar calendar = Calendar.getInstance(Locale.KOREA);
//    calendar.setFirstDayOfWeek(Calendar.MONDAY);
//    calendar.set(year, month - 1, day);
//
//    int currentWeekNumber = calendar.get(Calendar.WEEK_OF_MONTH);
//    int maximumWeekNumber = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
//
//    if (currentWeekNumber == maximumWeekNumber) {
//      calendar.set(year, month - 1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//      int maximumDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//
//      if (maximumDayOfWeek < Calendar.THURSDAY && maximumDayOfWeek > Calendar.SUNDAY) {
//        return 1;
//      } else {
//        return 0;
//      }
//    } else {
//      return 0;
//    }
//  }

  public static List<LocalDate> getWeekInMonths(int year, int month) {

    List<LocalDate> result = new ArrayList<>();

    Calendar cal = Calendar.getInstance();

    cal.set(Calendar.YEAR, year);
    cal.set(Calendar.MONTH, month - 1);

    for (int week = 1; week < cal.getMaximum(Calendar.WEEK_OF_MONTH); week++) {
      cal.set(Calendar.WEEK_OF_MONTH, week);

      cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
      int startDay = cal.get(Calendar.DAY_OF_MONTH);

      cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
      int endDay = cal.get(Calendar.DAY_OF_MONTH)+1;

      if (week == 1 && startDay >= 7) {
        startDay = 1;
      }

      if (week == cal.getMaximum(Calendar.WEEK_OF_MONTH) - 1) {
        cal.set(Calendar.MONTH, month - 1);
        endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
      }

      result.add(LocalDate.of(year,month,startDay));
      result.add(LocalDate.of(year,month,endDay));

      if (month == 2 && week == 4 && endDay>=28) {
        return result;
      }
    }

    return result;
  }

  private static int getWeekOfYear(String date) {
    Calendar calendar = Calendar.getInstance();
    String[] dates = date.split("-");
    int year = Integer.parseInt(dates[0]);
    int month = Integer.parseInt(dates[1]);
    int day = Integer.parseInt(dates[2]);
    calendar.set(year, month - 1, day);

    return calendar.get(Calendar.WEEK_OF_MONTH);
  }

  //////////////////////////////////////////////

  public static String getCurrentWeekOfMonth(int year, int month, int day)  {
    int subtractFirstWeekNumber = subWeekNumberIsFirstDayAfterThursday(year, month, day);
    int subtractLastWeekNumber = addMonthIsLastDayBeforeThursday(year, month, day);

    // 마지막 주차에서 다음 달로 넘어갈 경우 다음달의 1일을 기준으로 정해준다.
    // 추가로 다음 달 첫째주는 목요일부터 시작하는 과반수의 일자를 포함하기 때문에 한주를 빼지 않는다.

    Calendar calendar = Calendar.getInstance(Locale.KOREA);
    calendar.setFirstDayOfWeek(Calendar.MONDAY);
    calendar.setMinimalDaysInFirstWeek(1);
    calendar.set(year, month - (1 - subtractLastWeekNumber), day);

    int dayOfWeekForFirstDayOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);

    //    return (calendar.get(Calendar.MONTH) + 1) + "," + dayOfWeekForFirstDayOfMonth;
    return (calendar.get(Calendar.MONTH) + 1) + "," + dayOfWeekForFirstDayOfMonth;
  }

  public static int subWeekNumberIsFirstDayAfterThursday(int year, int month, int day)  {
    Calendar calendar = Calendar.getInstance(Locale.KOREA);
    calendar.set(year, month - 1, day);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    calendar.setFirstDayOfWeek(Calendar.MONDAY);

    int weekOfDay = calendar.get(Calendar.DAY_OF_WEEK);

    return 1;
//    if ((weekOfDay >= Calendar.MONDAY) && (weekOfDay <= Calendar.SUNDAY)) {
//      return 0;
//    } else if (day == 1 && (weekOfDay < Calendar.MONDAY || weekOfDay > Calendar.SUNDAY))  {
//      return -1;
//    } else {
//      return 1;
//    }
  }

  public static int addMonthIsLastDayBeforeThursday(int year, int month, int day) {
    Calendar calendar = Calendar.getInstance(Locale.KOREA);
    calendar.setFirstDayOfWeek(Calendar.MONDAY);
    calendar.set(year, month - 1, day);

    int currentWeekNumber = calendar.get(Calendar.WEEK_OF_MONTH);
    int maximumWeekNumber = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);

    if (currentWeekNumber == maximumWeekNumber) {
      calendar.set(year, month - 1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
      int maximumDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

      if (maximumDayOfWeek < Calendar.MONDAY && maximumDayOfWeek > Calendar.SUNDAY) {
        return 1;
      } else {
        return 0;
      }
    } else {
      return 0;
    }
  }
}
