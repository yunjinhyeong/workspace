package kr.co.zzimcar.data;

import kr.co.zzimcar.domain.MemberTaskDto;
import kr.co.zzimcar.domain.TaskTestForm;
import kr.co.zzimcar.domain.WeeklyTasks;
import kr.co.zzimcar.domain.page.WeekInfoDto;
import kr.co.zzimcar.domain.task.Task;
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

  private List<TaskTestForm> tasks;
  List<MemberTaskDto> tasksList;
  private Map<String, List<WeeklyTasks>> taskMap;

  public MonthlyTaskMap(int year, int month) {
    this.year = year;
    this.month = month;
  }


  public void calcWeeks() {
    Calendar cal = Calendar.getInstance();
//    WeekInfoDto weekInfoDto = new WeekInfoDto();
    int result = getCurrentWeekOfMonth(year,month,cal.getActualMaximum(Calendar.DAY_OF_MONTH));

    if(result == 1 || result == 4) {
//      weekInfoDto.setWeekcount(4);
      this.weeksCnt = 4;
    } else {
//      weekInfoDto.setWeekcount(5);
      this.weeksCnt = 5;
    }
  }

  public void generateDepartmentList() {
    this.departmentList = new ArrayList<>();
  }

  public void generateWorkerList() {

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

    List<String> weekstartduepoint = getWeekInMonths(year,month);    // 일단 1픽
    List<LocalDate> dateconvert = new ArrayList<>();
    SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");

    try {
      for (int i = 0 ; i<weekstartduepoint.size() ; i++) {
        // String 타입을 Date 타입으로 변환
        Date formatDate = dtFormat.parse(weekstartduepoint.get(i));
        // Date타입의 변수를 새롭게 지정한 포맷으로 변환
        String strNewDtFormat = newDtFormat.format(formatDate);
        LocalDate date = LocalDate.parse(strNewDtFormat, DateTimeFormatter.ISO_DATE);
        dateconvert.add(date);
      }
    }catch (ParseException e) {
      e.printStackTrace();
    }
    //    System.out.println(dateconvert);

    //    (startAt >= '2021-06-01' && startAt <= '2021-06-05') || (dueAt >= '2021-06-01' && dueAt <= '2021-06-05') || (startAt <= '2021-06-01' && dueAt >= '2021-06-05')


    for (int t=0 ; t<tasks.size()-1 ; t++) {
      int lastIndex = t+2;
      int size = tasks.size();
      for (int i = 0; i < dateconvert.size(); i += 2) {
        if (((tasks.get(t).getStartAt().isAfter(dateconvert.get(i)) || tasks.get(t).getStartAt().isEqual(dateconvert.get(i))) && (tasks.get(t).getStartAt().isBefore(dateconvert.get(i + 1)) || tasks.get(t).getStartAt().isEqual(dateconvert.get(i + 1)))) ||
          ((tasks.get(t).getDueAt().isAfter(dateconvert.get(i)) || tasks.get(t).getDueAt().isEqual(dateconvert.get(i))) && (tasks.get(t).getDueAt().isBefore(dateconvert.get(i + 1)) || tasks.get(t).getDueAt().isEqual(dateconvert.get(i + 1)))) ||
          ((tasks.get(t).getStartAt().isBefore(dateconvert.get(i)) || tasks.get(t).getStartAt().isEqual(dateconvert.get(i))) && (tasks.get(t).getDueAt().isAfter(dateconvert.get(i + 1)) || tasks.get(t).getDueAt().isEqual(dateconvert.get(i + 1))))) {
          if (i == 0) weekly1.add(new Task(tasks.get(t).getStartAt(), tasks.get(t).getDueAt(), tasks.get(t).getContent(), tasks.get(t).getPid()));
          if (i == 2) weekly2.add(new Task(tasks.get(t).getStartAt(), tasks.get(t).getDueAt(), tasks.get(t).getContent(), tasks.get(t).getPid()));
          if (i == 4) weekly3.add(new Task(tasks.get(t).getStartAt(), tasks.get(t).getDueAt(), tasks.get(t).getContent(), tasks.get(t).getPid()));
          if (i == 6) weekly4.add(new Task(tasks.get(t).getStartAt(), tasks.get(t).getDueAt(), tasks.get(t).getContent(), tasks.get(t).getPid()));
          if (i == 8) weekly5.add(new Task(tasks.get(t).getStartAt(), tasks.get(t).getDueAt(), tasks.get(t).getContent(), tasks.get(t).getPid()));
        }
      }
      if (!tasks.get(t).getName().equals(tasks.get(t+1).getName())) { //  || (t+1) == taskTestForm.size()
        tasksList.add(new MemberTaskDto(tasks.get(t).getName(), weekly1, weekly2, weekly3, weekly4, weekly5));
        weekly1=new ArrayList<>();weekly2=new ArrayList<>();weekly3=new ArrayList<>();weekly4=new ArrayList<>();weekly5=new ArrayList<>();
        if (!tasks.get(t).getDepartment().equals(tasks.get(t+1).getDepartment())) {
          departmentList.add(new WeeklyTasks(tasks.get(t).getDepartment(), tasksList));
          tasksList=new ArrayList<>();
        }
      }
      if (lastIndex==size) {
        for (int i = 0; i < dateconvert.size(); i += 2) {
          if (((tasks.get(size-1).getStartAt().isAfter(dateconvert.get(i)) || tasks.get(size-1).getStartAt().isEqual(dateconvert.get(i))) && (tasks.get(size-1).getStartAt().isBefore(dateconvert.get(i + 1)) || tasks.get(size-1).getStartAt().isEqual(dateconvert.get(i + 1)))) ||
            ((tasks.get(size-1).getDueAt().isAfter(dateconvert.get(i)) || tasks.get(size-1).getDueAt().isEqual(dateconvert.get(i))) && (tasks.get(size-1).getDueAt().isBefore(dateconvert.get(i + 1)) || tasks.get(size-1).getDueAt().isEqual(dateconvert.get(i + 1)))) ||
            ((tasks.get(size-1).getStartAt().isBefore(dateconvert.get(i)) || tasks.get(size-1).getStartAt().isEqual(dateconvert.get(i))) && (tasks.get(size-1).getDueAt().isAfter(dateconvert.get(i + 1)) || tasks.get(size-1).getDueAt().isEqual(dateconvert.get(i + 1))))) {
            if (i == 0) weekly1.add(new Task(tasks.get(size-1).getStartAt(), tasks.get(size-1).getDueAt(), tasks.get(size-1).getContent(), tasks.get(size-1).getPid()));
            if (i == 2) weekly2.add(new Task(tasks.get(size-1).getStartAt(), tasks.get(size-1).getDueAt(), tasks.get(size-1).getContent(), tasks.get(size-1).getPid()));
            if (i == 4) weekly3.add(new Task(tasks.get(size-1).getStartAt(), tasks.get(size-1).getDueAt(), tasks.get(size-1).getContent(), tasks.get(size-1).getPid()));
            if (i == 6) weekly4.add(new Task(tasks.get(size-1).getStartAt(), tasks.get(size-1).getDueAt(), tasks.get(size-1).getContent(), tasks.get(size-1).getPid()));
            if (i == 8) weekly5.add(new Task(tasks.get(size-1).getStartAt(), tasks.get(size-1).getDueAt(), tasks.get(size-1).getContent(), tasks.get(size-1).getPid()));
          }
        }
        tasksList.add(new MemberTaskDto(tasks.get(size-1).getName(), weekly1, weekly2, weekly3, weekly4, weekly5));
        weekly1=new ArrayList<>();weekly2=new ArrayList<>();weekly3=new ArrayList<>();weekly4=new ArrayList<>();weekly5=new ArrayList<>();
        departmentList.add(new WeeklyTasks(tasks.get(size-1).getDepartment(), tasksList));
        tasksList=new ArrayList<>();
      }
    }
    taskMap.put("departmentList", departmentList);
  }

  public void generateTaskMap() {

  }

  public static int getCurrentWeekOfMonth(int year, int month, int day)  {
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

    return dayOfWeekForFirstDayOfMonth;
  }
  public List<String> getWeekInMonths(int year, int month) {

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
