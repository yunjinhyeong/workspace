  package kr.co.zzimcar.controller;

import kr.co.zzimcar.dto.MemberTaskDto;
import kr.co.zzimcar.dto.WeeklyTasks;
import kr.co.zzimcar.dto.task.Task;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

  public class test {
  public static void main(String[] args) {
    //    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    //    // int thisWeek = getWeekOfYear(sdf.format(new Date()));
    //
    //    int thisWeek = getWeekOfYear("2021-06-30");
    //    System.out.println(thisWeek);

    int count = 0;
    int month = 6;
    int year = 2021;
    //    Calendar cal = Calendar.getInstance();
    //    cal.set(year,month-1,1);
    ////    System.out.println("디테일"+getCurrentWeekOfMonth(2021,6,20));
    //    System.out.println("가"+cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    //    for(int i =1 ; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH) + 1 ; i++) {
    //      System.out.println("나"+getCurrentWeekOfMonth(year,month,i));
    ////      System.out.println("i의 값"+i);
    //      count++;
    //    }
    //    System.out.println("다"+count);
    //    System.out.println("라"+getCurrentWeekOfMonth(year,month,cal.getActualMaximum(Calendar.DAY_OF_MONTH)));


//        List<String> result = getWeekInMonths(year,month);    // 일단 1픽
//
//        try {
//          SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
//          SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
//          for (int i = 0 ; i<result.size() ; i++) {
//            // String 타입을 Date 타입으로 변환
//            Date formatDate = dtFormat.parse(result.get(i));
//            // Date타입의 변수를 새롭게 지정한 포맷으로 변환
//            String strNewDtFormat = newDtFormat.format(formatDate);
//            System.out.println(strNewDtFormat);
//          }
//        }catch (ParseException e) {
//          e.printStackTrace();
//        }


    //    try {
    //      String strDate = "20200806";
    //      SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
    //      SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
    //      // String 타입을 Date 타입으로 변환
    //      Date formatDate = dtFormat.parse(strDate);
    //      // Date타입의 변수를 새롭게 지정한 포맷으로 변환
    //      String strNewDtFormat = newDtFormat.format(formatDate);
    //      System.out.println("포맷 전 : " + strDate);
    //      System.out.println("포맷 후 : " + strNewDtFormat);
    //    }catch (ParseException e) {
    //      e.printStackTrace();
    //    }

    //////////////////////////// stream ///////////////////////////////
    //    List<String> names = Arrays.asList("jeong", "pro", "jdk", "java");
    //    long iv = 0;
    //    // 기존의 코딩 방식
    //    for (String name : names) {
    //      if (name.contains("o")) {
    //        iv++;
    //      }
    //    }
    //    System.out.println("iv : " + iv); // 2
    //
    //    // 스트림 이용한 방식
    //    iv = 0;
    //    iv = names.stream().filter(x -> x.contains("o")).count();
    //    System.out.println("iv : " + iv);
    //////////////////////////// map ///////////////////////////////
    //    Map<String, Object> map = new HashMap<>();
    //    map.put("Seq", "4");
    //    map.put("testNumber", "0101");
    //
    //    List<Map> list2 = new ArrayList<>();
    //    list2.add(map);
    //
    //    Map<String, Object> list = new HashMap<>();
    //    list.put("list", list2);
    //
    //    System.out.println("list : " + list);
    //    System.out.println("list.get(list) : " + list.get("list"));
    //
    //    List<Map> temp = (List<Map>) list.get("list");
    //
    //    System.out.println("temp : " + temp);
    //    System.out.println("temp.get(0) :" + temp.get(0));
    //
    //    Map<String, Object> tMap = temp.get(0);
    //
    //    System.out.println("tMap : " + tMap);
    //    System.out.println("tMap.get(Seq) :" + tMap.get("Seq"));
//////////////////////////// map note ///////////////////////////////
    String testDay = "2021-06-09";
    LocalDate date = LocalDate.parse(testDay, DateTimeFormatter.ISO_DATE);
    System.out.println("date="+ date+ "입니다. type은 " +date.getClass().getName());

    Map<String, List<Object>> map = new HashMap<>();
    //    2단계
    //// w 세팅
    List<Task> w1 = new ArrayList<>();
    List<Task> w2 = new ArrayList<>();
    List<Task> w3 = new ArrayList<>();
    List<Task> w4 = new ArrayList<>();
    List<Task> w5 = new ArrayList<>();

    List<Task> ww1 = new ArrayList<>();
    List<Task> ww2 = new ArrayList<>();
    List<Task> ww3 = new ArrayList<>();
    List<Task> ww4 = new ArrayList<>();
    List<Task> ww5 = new ArrayList<>();

    LocalDate test2 = date.minusDays(2);
    System.out.println("test2="+ test2+ "입니다. type은 " +test2.getClass().getName());
    if (date.isAfter(test2)) {
      System.out.println("이후입니다.");
    }
    if (date.isEqual(test2)) {
      System.out.println("같습니다.");
    }
    if (date.isBefore(test2)) {
      System.out.println("과거입니다.");
    }

    w1.add(new Task(date, date,"경영지원 내용1"));
    w1.add(new Task(date, date,"경영지원 내용2"));
    w1.add(new Task(date, date,"경영지원 내용3"));
    w1.add(new Task(date, date,"경영지원 내용4"));
    w1.add(new Task(date, date,"경영지원 내용5"));
    w1.add(new Task(date, date,"경영지원 내용6"));
    w1.add(new Task(date, date,"경영지원 내용7"));
    w1.add(new Task(date, date,"경영지원 내용8"));
    w1.add(new Task(date, date,"경영지원 내용9"));
    w1.add(new Task(date, date,"경영지원 내용10"));

    ww1.add(new Task(date, date,"개발팀 내용1"));
    ww1.add(new Task(date, date,"개발팀 내용2"));
    ww1.add(new Task(date, date,"개발팀 내용3"));
    ww1.add(new Task(date, date,"개발팀 내용4"));
    ww1.add(new Task(date, date,"개발팀 내용5"));
    ww1.add(new Task(date, date,"개발팀 내용6"));
    ww1.add(new Task(date, date,"개발팀 내용7"));
    ww1.add(new Task(date, date,"개발팀 내용8"));
    ww1.add(new Task(date, date,"개발팀 내용9"));
    ww1.add(new Task(date, date,"개발팀 내용10"));


    //// w 세팅 /
    List<MemberTaskDto> operationTasksList = new ArrayList<>();
    operationTasksList.add(new MemberTaskDto("임진숙", w1, w2, w3, w4));
    operationTasksList.add(new MemberTaskDto("김영범", w1, w2, w3, w4));
    operationTasksList.add(new MemberTaskDto("유승민", w1, w2, w3, w4));

    List<MemberTaskDto> developTasksList = new ArrayList<>();
    developTasksList.add(new MemberTaskDto("이욱세", ww1, ww2, ww3, ww4));
    developTasksList.add(new MemberTaskDto("박혜미", ww1, ww2, ww3, ww4));
    developTasksList.add(new MemberTaskDto("이정수", ww1, ww2, ww3, ww4));

    List<Object> departmentList = new ArrayList<>();
    departmentList.add(new WeeklyTasks("경영지원", operationTasksList));
    departmentList.add(new WeeklyTasks("개발팀", developTasksList));

    map.put("departmentList", departmentList);

    //    map.put("memberTasksList", memberTasksList);


    System.out.println(map);



  }




  public static int subWeekNumberIsFirstDayAfterThursday(int year, int month, int day)  {
    Calendar calendar = Calendar.getInstance(Locale.KOREA);
    calendar.set(year, month - 1, day);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    calendar.setFirstDayOfWeek(Calendar.MONDAY);

    int weekOfDay = calendar.get(Calendar.DAY_OF_WEEK);

    if ((weekOfDay >= Calendar.MONDAY) && (weekOfDay <= Calendar.THURSDAY)) {
      return 0;
    } else if (day == 1 && (weekOfDay < Calendar.MONDAY || weekOfDay > Calendar.TUESDAY))  {
      return -1;
    } else {
      return 1;
    }
  }

  /**
   * 해당 날짜가 마지막 주에 해당하고 마지막주의 마지막날짜가 목요일보다 작으면
   * 다음달로 넘겨야 한다 +1
   * 목요일보다 크거나 같을 경우 이번달로 결정한다 +0
   * @param year
   * @param month
   * @param day
   * @return
   */
  public static int addMonthIsLastDayBeforeThursday(int year, int month, int day) {
    Calendar calendar = Calendar.getInstance(Locale.KOREA);
    calendar.setFirstDayOfWeek(Calendar.MONDAY);
    calendar.set(year, month - 1, day);

    int currentWeekNumber = calendar.get(Calendar.WEEK_OF_MONTH);
    int maximumWeekNumber = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);

    if (currentWeekNumber == maximumWeekNumber) {
      calendar.set(year, month - 1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
      int maximumDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

      if (maximumDayOfWeek < Calendar.THURSDAY && maximumDayOfWeek > Calendar.SUNDAY) {
        return 1;
      } else {
        return 0;
      }
    } else {
      return 0;
    }
  }

  /**
   * 해당 날짜의 주차를 반환
   * @param year
   * @param month
   * @param day
   * @return
   */
  public static String getCurrentWeekOfMonth(int year, int month, int day)  {
    int subtractFirstWeekNumber = subWeekNumberIsFirstDayAfterThursday(year, month, day);
    int subtractLastWeekNumber = addMonthIsLastDayBeforeThursday(year, month, day);

    // 마지막 주차에서 다음 달로 넘어갈 경우 다음달의 1일을 기준으로 정해준다.
    // 추가로 다음 달 첫째주는 목요일부터 시작하는 과반수의 일자를 포함하기 때문에 한주를 빼지 않는다.
    if (subtractLastWeekNumber > 0) {
      day = 1;
      subtractFirstWeekNumber = 0;
    }

    if (subtractFirstWeekNumber < 0)  {
      Calendar calendar = Calendar.getInstance(Locale.KOREA);
      calendar.set(year, month - 1, day);
      calendar.add(Calendar.DATE, -1);



      return getCurrentWeekOfMonth(calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH) + 1), calendar.get(Calendar.DATE));
    }

    Calendar calendar = Calendar.getInstance(Locale.KOREA);
    calendar.setFirstDayOfWeek(Calendar.MONDAY);
    calendar.setMinimalDaysInFirstWeek(1);
    calendar.set(year, month - (1 - subtractLastWeekNumber), day);

    int dayOfWeekForFirstDayOfMonth = calendar.get(Calendar.WEEK_OF_MONTH) - subtractFirstWeekNumber;

//    return (calendar.get(Calendar.MONTH) + 1) + "," + dayOfWeekForFirstDayOfMonth;
    return (calendar.get(Calendar.MONTH) + 1) + "," + dayOfWeekForFirstDayOfMonth;
  }



  public static List<String> getWeekInMonths(int year, int month) {

    List<String> result = new ArrayList<>();

    Calendar cal = Calendar.getInstance();

    cal.set(Calendar.YEAR, year);
    cal.set(Calendar.MONTH, month - 1);

    for (int week = 1; week < cal.getMaximum(Calendar.WEEK_OF_MONTH); week++) {
      cal.set(Calendar.WEEK_OF_MONTH, week);

      cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
      int startDay = cal.get(Calendar.DAY_OF_MONTH);

      cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
      int endDay = cal.get(Calendar.DAY_OF_MONTH);

      if (week == 1 && startDay >= 7) {
        startDay = 1;
      }

      if (week == cal.getMaximum(Calendar.WEEK_OF_MONTH) - 1 && endDay <= 7) {
        cal.set(Calendar.MONTH, month - 1);
        endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
      }

      String monthR = Integer.toString(month);
      String startDayR = Integer.toString(startDay);
      String endDayR = Integer.toString(endDay);

      if (month<10) monthR = "0"+monthR;
      if (startDay<10) startDayR = "0"+startDayR;
      if (endDay<10) endDayR = "0"+endDayR;

      result.add(year+monthR+startDayR);
      result.add(year+monthR+endDayR);

//      System.out.println(week + "주 : " + startDay + " ~ " + endDay);
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

}
