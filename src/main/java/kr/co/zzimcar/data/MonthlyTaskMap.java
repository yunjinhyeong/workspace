package kr.co.zzimcar.data;

import kr.co.zzimcar.domain.task.*;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Data
public class MonthlyTaskMap {

  private int year;
  private int month;
  private int weeksCnt;

  private List<WeeklyTasks> departmentList;
  private List<Task> weekly1;
  private List<Task> weekly2;
  private List<Task> weekly3;
  private List<Task> weekly4;
  private List<Task> weekly5;

  private List<TaskFormDto> tasks;
//  private List<MemberInfoDto> memberInfo;
  private List<LocalDate> weekstartduepoint;
  List<MemberTask> tasksList;
  private Map<String, List<WeeklyTasks>> taskMap;

  public MonthlyTaskMap(int year, int month) {

    this.year = year;
    this.month = month;
  }

  public void calcWeeks() {
    Calendar cal = Calendar.getInstance();

    int result = getCurrentWeekOfMonth(year, month, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

    if (result == 1 || result == 4) {
      this.weeksCnt = 4;
    } else {
      this.weeksCnt = 5;
    }
  }

  public void generateWeekTaskList() {
    this.weekly1 = new ArrayList<>();
    this.weekly2 = new ArrayList<>();
    this.weekly3 = new ArrayList<>();
    this.weekly4 = new ArrayList<>();
    this.weekly5 = new ArrayList<>();
    this.tasksList = new ArrayList<>();
    this.departmentList = new ArrayList<>();
    this.taskMap = new HashMap<>();

    weekstartduepoint = getWeekInMonths(year, month);
    this.weekstartduepoint = weekstartduepoint;

//    List<LocalDate> dateconvert = new ArrayList<>();
//    SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
//    SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");

//    try {
//      for (int i = 0; i < weekstartduepoint.size(); i++) {
//        Date formatDate = dtFormat.parse(weekstartduepoint.get(i));
//        String strNewDtFormat = newDtFormat.format(formatDate);
//        LocalDate date = LocalDate.parse(strNewDtFormat, DateTimeFormatter.ISO_DATE);
//        dateconvert.add(date);
//      }
//    } catch (ParseException e) {
//      e.printStackTrace();
//    }

    for (int t = 0; t < tasks.size() - 1; t++) {
      int lastIndex = t + 2;
      int size = tasks.size();
      for (int i = 0; i < weekstartduepoint.size(); i += 2) {
        if (((tasks.get(t).getStartAt().isAfter(weekstartduepoint.get(i)) || tasks.get(t).getStartAt().isEqual(weekstartduepoint.get(i))) && (tasks.get(t).getStartAt().isBefore(weekstartduepoint.get(i + 1)) || tasks.get(t).getStartAt().isEqual(weekstartduepoint.get(i + 1)))) ||
          ((tasks.get(t).getDueAt().isAfter(weekstartduepoint.get(i)) || tasks.get(t).getDueAt().isEqual(weekstartduepoint.get(i))) && (tasks.get(t).getDueAt().isBefore(weekstartduepoint.get(i + 1)) || tasks.get(t).getDueAt().isEqual(weekstartduepoint.get(i + 1)))) ||
          ((tasks.get(t).getStartAt().isBefore(weekstartduepoint.get(i)) || tasks.get(t).getStartAt().isEqual(weekstartduepoint.get(i))) && (tasks.get(t).getDueAt().isAfter(weekstartduepoint.get(i + 1)) || tasks.get(t).getDueAt().isEqual(weekstartduepoint.get(i + 1))))) {
          if (i == 0)
            weekly1.add(new Task(tasks.get(t).getStartAt(), tasks.get(t).getDueAt(), tasks.get(t).getContent(), tasks.get(t).getPid(), tasks.get(t).getState()));
          if (i == 2)
            weekly2.add(new Task(tasks.get(t).getStartAt(), tasks.get(t).getDueAt(), tasks.get(t).getContent(), tasks.get(t).getPid(), tasks.get(t).getState()));
          if (i == 4)
            weekly3.add(new Task(tasks.get(t).getStartAt(), tasks.get(t).getDueAt(), tasks.get(t).getContent(), tasks.get(t).getPid(), tasks.get(t).getState()));
          if (i == 6)
            weekly4.add(new Task(tasks.get(t).getStartAt(), tasks.get(t).getDueAt(), tasks.get(t).getContent(), tasks.get(t).getPid(), tasks.get(t).getState()));
          if (i == 8)
            weekly5.add(new Task(tasks.get(t).getStartAt(), tasks.get(t).getDueAt(), tasks.get(t).getContent(), tasks.get(t).getPid(), tasks.get(t).getState()));
        }
      }
      if (!tasks.get(t).getMemberId().equals(tasks.get(t + 1).getMemberId())) {
//        for (int m=0 ; m<memberInfo.size() ; m++) {
//          if (!memberInfo.get(m).getId().equals(tasks.get(t).getMemberId())) {
//            tasksList.add(new MemberTask(memberInfo.get(m).getName(), memberInfo.get(m).getId(), null, null, null, null, null));
//          }
//        }
        tasksList.add(new MemberTask(tasks.get(t).getName(), tasks.get(t).getMemberId(), weekly1, weekly2, weekly3, weekly4, weekly5));
        weekly1 = new ArrayList<>();
        weekly2 = new ArrayList<>();
        weekly3 = new ArrayList<>();
        weekly4 = new ArrayList<>();
        weekly5 = new ArrayList<>();
        if (!tasks.get(t).getDepartment().equals(tasks.get(t + 1).getDepartment())) {
          departmentList.add(new WeeklyTasks(tasks.get(t).getDepartment(), tasksList));
          tasksList = new ArrayList<>();
        }
      }
      if (lastIndex == size) {
        for (int i = 0; i < weekstartduepoint.size(); i += 2) {
          if (((tasks.get(size - 1).getStartAt().isAfter(weekstartduepoint.get(i)) || tasks.get(size - 1).getStartAt().isEqual(weekstartduepoint.get(i))) && (tasks.get(size - 1).getStartAt().isBefore(weekstartduepoint.get(i + 1)) || tasks.get(size - 1).getStartAt().isEqual(weekstartduepoint.get(i + 1)))) ||
            ((tasks.get(size - 1).getDueAt().isAfter(weekstartduepoint.get(i)) || tasks.get(size - 1).getDueAt().isEqual(weekstartduepoint.get(i))) && (tasks.get(size - 1).getDueAt().isBefore(weekstartduepoint.get(i + 1)) || tasks.get(size - 1).getDueAt().isEqual(weekstartduepoint.get(i + 1)))) ||
            ((tasks.get(size - 1).getStartAt().isBefore(weekstartduepoint.get(i)) || tasks.get(size - 1).getStartAt().isEqual(weekstartduepoint.get(i))) && (tasks.get(size - 1).getDueAt().isAfter(weekstartduepoint.get(i + 1)) || tasks.get(size - 1).getDueAt().isEqual(weekstartduepoint.get(i + 1))))) {
            if (i == 0)
              weekly1.add(new Task(tasks.get(size - 1).getStartAt(), tasks.get(size - 1).getDueAt(), tasks.get(size - 1).getContent(), tasks.get(size - 1).getPid(), tasks.get(size - 1).getState()));
            if (i == 2)
              weekly2.add(new Task(tasks.get(size - 1).getStartAt(), tasks.get(size - 1).getDueAt(), tasks.get(size - 1).getContent(), tasks.get(size - 1).getPid(), tasks.get(size - 1).getState()));
            if (i == 4)
              weekly3.add(new Task(tasks.get(size - 1).getStartAt(), tasks.get(size - 1).getDueAt(), tasks.get(size - 1).getContent(), tasks.get(size - 1).getPid(), tasks.get(size - 1).getState()));
            if (i == 6)
              weekly4.add(new Task(tasks.get(size - 1).getStartAt(), tasks.get(size - 1).getDueAt(), tasks.get(size - 1).getContent(), tasks.get(size - 1).getPid(), tasks.get(size - 1).getState()));
            if (i == 8)
              weekly5.add(new Task(tasks.get(size - 1).getStartAt(), tasks.get(size - 1).getDueAt(), tasks.get(size - 1).getContent(), tasks.get(size - 1).getPid(), tasks.get(size - 1).getState()));
          }
        }
        tasksList.add(new MemberTask(tasks.get(size - 1).getName(), tasks.get(size - 1).getMemberId(), weekly1, weekly2, weekly3, weekly4, weekly5));
        weekly1 = new ArrayList<>();
        weekly2 = new ArrayList<>();
        weekly3 = new ArrayList<>();
        weekly4 = new ArrayList<>();
        weekly5 = new ArrayList<>();
        departmentList.add(new WeeklyTasks(tasks.get(size - 1).getDepartment(), tasksList));
        tasksList = new ArrayList<>();
      }
    }
    taskMap.put("departmentList", departmentList);
    System.out.println(taskMap);
  }

  public static int getCurrentWeekOfMonth(int year, int month, int day) {

    int subtractFirstWeekNumber = subWeekNumberIsFirstDayAfterThursday(year, month, day);
    int subtractLastWeekNumber = addMonthIsLastDayBeforeThursday(year, month, day);

    if (subtractLastWeekNumber > 0) {
      day = 1;
      subtractFirstWeekNumber = 0;
    }

    if (subtractFirstWeekNumber < 0) {
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

    return dayOfWeekForFirstDayOfMonth;
  }

  public List<LocalDate> getWeekInMonths(int year, int month) {

    List<LocalDate> result = new ArrayList<>();
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

//      String monthR = Integer.toString(month);
//      String startDayR = Integer.toString(startDay);
//      String endDayR = Integer.toString(endDay);
//
//      if (month < 10) monthR = "0" + monthR;
//      if (startDay < 10) startDayR = "0" + startDayR;
//      if (endDay < 10) endDayR = "0" + endDayR;

      result.add(LocalDate.of(year,month,startDay));
      result.add(LocalDate.of(year,month,endDay));

    }
    return result;
  }

  public static int subWeekNumberIsFirstDayAfterThursday(int year, int month, int day) {

    Calendar calendar = Calendar.getInstance(Locale.KOREA);

    calendar.set(year, month - 1, day);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    calendar.setFirstDayOfWeek(Calendar.MONDAY);

    int weekOfDay = calendar.get(Calendar.DAY_OF_WEEK);

    if ((weekOfDay >= Calendar.MONDAY) && (weekOfDay <= Calendar.THURSDAY)) {
      return 0;
    } else if (day == 1 && (weekOfDay < Calendar.MONDAY || weekOfDay > Calendar.TUESDAY)) {
      return -1;
    } else {
      return 1;
    }
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

      if (maximumDayOfWeek < Calendar.THURSDAY && maximumDayOfWeek > Calendar.SUNDAY) {
        return 1;
      } else {
        return 0;
      }
    } else {
      return 0;
    }
  }
}
