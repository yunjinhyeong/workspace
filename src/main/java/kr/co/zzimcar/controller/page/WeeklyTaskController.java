package kr.co.zzimcar.controller.page;

import kr.co.zzimcar.dao.TaskDao;
import kr.co.zzimcar.domain.MemberTaskDto;
import kr.co.zzimcar.domain.TaskTestForm;
import kr.co.zzimcar.domain.WeeklyTasks;
import kr.co.zzimcar.domain.page.WeekInfoDto;
import kr.co.zzimcar.domain.task.*;
import kr.co.zzimcar.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/week")
public class WeeklyTaskController {

  private final TaskService taskService;
  private final TaskDao taskDao;

  @PostMapping("/weekly")
  @ResponseBody
  public WeekInfoDto week(int yyyy, int mm) {
    return taskService.generateMonthlyTaskMap(yyyy, mm);


//    Calendar cal = Calendar.getInstance();
//    WeekInfoDto weekInfoDto = new WeekInfoDto();
//    int result = getCurrentWeekOfMonth(yyyy,mm,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
//
//    if(result == 1 || result == 4) {
//      weekInfoDto.setWeekcount(4);
//    } else {
//      weekInfoDto.setWeekcount(5);
//    }
////
////    List<DrawWeekWorkDto> list = taskService.weekDate();
////    System.out.println("list>>>> 컨트롤러 111 >>>>>>>"+list);
////    for(int i=0; i<list.size() ; i++) {
////      int forY = Integer.parseInt(list.get(i).getYyyy());
////      int forM = Integer.parseInt(list.get(i).getMm());
////      int forD = Integer.parseInt(list.get(i).getDd());
////      int forR1 = getCurrentWeekOfMonth(forY,forM,forD);
////      int forR2 = getCurrentWeekOfMonth2(forY,forM,forD);
////      System.out.println("forR1 = "+forR1+"    forR2 = "+forR2+"       forY = "+forY+"     forM = "+forM+"      forD = "+forD);
////      list.get(i).setRealweek(forR1);
////      list.get(i).setRealmm(forR2);
////      weekInfoDto.setList(list);
////    }
////    System.out.println("list >>>>>>>>>>>2222222222222"+list);
//
//    // 기본 세팅들
//    Map<String, List<WeeklyTasks>> map = new HashMap<>();
//    List<Task> w1 = new ArrayList<>();
//    List<Task> w2 = new ArrayList<>();
//    List<Task> w3 = new ArrayList<>();
//    List<Task> w4 = new ArrayList<>();
//    List<Task> w5 = new ArrayList<>();
//    List<MemberTaskDto> tasksList = new ArrayList<>();
//    List<WeeklyTasks> departmentList = new ArrayList<>();
//
//    // 해당 월에 해당되는 데이터 뽑아오기
////    cal.set(yyyy,mm-1,1);
////    int dd = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
////    String month = mm<10 ? "0"+Integer.toString(mm) : Integer.toString(mm);
////    String day = dd<10 ? "0"+Integer.toString(dd) : Integer.toString(dd);
////    String startday = yyyy+"-"+month+"-01";
////    String dueday = yyyy+"-"+month+"-"+day;
//    List<TaskTestForm> taskTestForm = taskDao.taskTestRetrieve(yyyy,mm);
//
////    System.out.println(taskTestForm.get(1).getDueAt().getClass().getName());
//
//    // 각 주차별 시작일 끝일 구해 전체데이터 각 startAt dueAt이 그 범위에 부합하는지 비교후 세팅한다.
//    List<String> weekstartduepoint = getWeekInMonths(yyyy,mm);    // 일단 1픽
//    List<LocalDate> dateconvert = new ArrayList<>();
//    SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
//    SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//    try {
//      for (int i = 0 ; i<weekstartduepoint.size() ; i++) {
//        // String 타입을 Date 타입으로 변환
//        Date formatDate = dtFormat.parse(weekstartduepoint.get(i));
//        // Date타입의 변수를 새롭게 지정한 포맷으로 변환
//        String strNewDtFormat = newDtFormat.format(formatDate);
//        LocalDate date = LocalDate.parse(strNewDtFormat, DateTimeFormatter.ISO_DATE);
//        dateconvert.add(date);
//      }
//    }catch (ParseException e) {
//      e.printStackTrace();
//    }
////    System.out.println(dateconvert);
//
////    (startAt >= '2021-06-01' && startAt <= '2021-06-05') || (dueAt >= '2021-06-01' && dueAt <= '2021-06-05') || (startAt <= '2021-06-01' && dueAt >= '2021-06-05')
//
//
//    for (int t=0 ; t<taskTestForm.size()-1 ; t++) {
//      int lastIndex = t+2;
//      int size = taskTestForm.size();
//      for (int i = 0; i < dateconvert.size(); i += 2) {
//        if (((taskTestForm.get(t).getStartAt().isAfter(dateconvert.get(i)) || taskTestForm.get(t).getStartAt().isEqual(dateconvert.get(i))) && (taskTestForm.get(t).getStartAt().isBefore(dateconvert.get(i + 1)) || taskTestForm.get(t).getStartAt().isEqual(dateconvert.get(i + 1)))) ||
//          ((taskTestForm.get(t).getDueAt().isAfter(dateconvert.get(i)) || taskTestForm.get(t).getDueAt().isEqual(dateconvert.get(i))) && (taskTestForm.get(t).getDueAt().isBefore(dateconvert.get(i + 1)) || taskTestForm.get(t).getDueAt().isEqual(dateconvert.get(i + 1)))) ||
//          ((taskTestForm.get(t).getStartAt().isBefore(dateconvert.get(i)) || taskTestForm.get(t).getStartAt().isEqual(dateconvert.get(i))) && (taskTestForm.get(t).getDueAt().isAfter(dateconvert.get(i + 1)) || taskTestForm.get(t).getDueAt().isEqual(dateconvert.get(i + 1))))) {
//          if (i == 0) w1.add(new Task(taskTestForm.get(t).getStartAt(), taskTestForm.get(t).getDueAt(), taskTestForm.get(t).getContent(), taskTestForm.get(t).getPid()));
//          if (i == 2) w2.add(new Task(taskTestForm.get(t).getStartAt(), taskTestForm.get(t).getDueAt(), taskTestForm.get(t).getContent(), taskTestForm.get(t).getPid()));
//          if (i == 4) w3.add(new Task(taskTestForm.get(t).getStartAt(), taskTestForm.get(t).getDueAt(), taskTestForm.get(t).getContent(), taskTestForm.get(t).getPid()));
//          if (i == 6) w4.add(new Task(taskTestForm.get(t).getStartAt(), taskTestForm.get(t).getDueAt(), taskTestForm.get(t).getContent(), taskTestForm.get(t).getPid()));
//          if (i == 8) w5.add(new Task(taskTestForm.get(t).getStartAt(), taskTestForm.get(t).getDueAt(), taskTestForm.get(t).getContent(), taskTestForm.get(t).getPid()));
//        }
//      }
//      if (!taskTestForm.get(t).getName().equals(taskTestForm.get(t+1).getName())) { //  || (t+1) == taskTestForm.size()
//        tasksList.add(new MemberTaskDto(taskTestForm.get(t).getName(), w1, w2, w3, w4, w5));
//        w1=new ArrayList<>();w2=new ArrayList<>();w3=new ArrayList<>();w4=new ArrayList<>();w5=new ArrayList<>();
//        if (!taskTestForm.get(t).getDepartment().equals(taskTestForm.get(t+1).getDepartment())) {
//          departmentList.add(new WeeklyTasks(taskTestForm.get(t).getDepartment(), tasksList));
//          tasksList=new ArrayList<>();
//        }
//      }
//      if (lastIndex==size) {
//        for (int i = 0; i < dateconvert.size(); i += 2) {
//          if (((taskTestForm.get(size-1).getStartAt().isAfter(dateconvert.get(i)) || taskTestForm.get(size-1).getStartAt().isEqual(dateconvert.get(i))) && (taskTestForm.get(size-1).getStartAt().isBefore(dateconvert.get(i + 1)) || taskTestForm.get(size-1).getStartAt().isEqual(dateconvert.get(i + 1)))) ||
//            ((taskTestForm.get(size-1).getDueAt().isAfter(dateconvert.get(i)) || taskTestForm.get(size-1).getDueAt().isEqual(dateconvert.get(i))) && (taskTestForm.get(size-1).getDueAt().isBefore(dateconvert.get(i + 1)) || taskTestForm.get(size-1).getDueAt().isEqual(dateconvert.get(i + 1)))) ||
//            ((taskTestForm.get(size-1).getStartAt().isBefore(dateconvert.get(i)) || taskTestForm.get(size-1).getStartAt().isEqual(dateconvert.get(i))) && (taskTestForm.get(size-1).getDueAt().isAfter(dateconvert.get(i + 1)) || taskTestForm.get(size-1).getDueAt().isEqual(dateconvert.get(i + 1))))) {
//            if (i == 0) w1.add(new Task(taskTestForm.get(size-1).getStartAt(), taskTestForm.get(size-1).getDueAt(), taskTestForm.get(size-1).getContent(), taskTestForm.get(size-1).getPid()));
//            if (i == 2) w2.add(new Task(taskTestForm.get(size-1).getStartAt(), taskTestForm.get(size-1).getDueAt(), taskTestForm.get(size-1).getContent(), taskTestForm.get(size-1).getPid()));
//            if (i == 4) w3.add(new Task(taskTestForm.get(size-1).getStartAt(), taskTestForm.get(size-1).getDueAt(), taskTestForm.get(size-1).getContent(), taskTestForm.get(size-1).getPid()));
//            if (i == 6) w4.add(new Task(taskTestForm.get(size-1).getStartAt(), taskTestForm.get(size-1).getDueAt(), taskTestForm.get(size-1).getContent(), taskTestForm.get(size-1).getPid()));
//            if (i == 8) w5.add(new Task(taskTestForm.get(size-1).getStartAt(), taskTestForm.get(size-1).getDueAt(), taskTestForm.get(size-1).getContent(), taskTestForm.get(size-1).getPid()));
//          }
//        }
//        tasksList.add(new MemberTaskDto(taskTestForm.get(size-1).getName(), w1, w2, w3, w4, w5));
//        w1=new ArrayList<>();w2=new ArrayList<>();w3=new ArrayList<>();w4=new ArrayList<>();w5=new ArrayList<>();
//        departmentList.add(new WeeklyTasks(taskTestForm.get(size-1).getDepartment(), tasksList));
//        tasksList=new ArrayList<>();
//      }
//    }
//    map.put("departmentList", departmentList);
//    weekInfoDto.setItems(map);
//
//    return weekInfoDto;
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


//  public static int getCurrentWeekOfMonth2(int year, int month, int day)  {
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
//    return calendar.get(Calendar.MONTH) + 1;
//  }

//  @GetMapping("viewContent")
//  public String viewContent(Model model, int pid) {
//    ViewContentDto item = taskDao.viewContent(pid);
//    model.addAttribute("item",item);
//    return "viewContent";
//  }

  @ResponseBody
  @GetMapping("viewTask")
  public ViewTaskResDto viewContent(int pid) {
    return taskService.viewTask(pid);
  }

  @ResponseBody
  @PutMapping("updateTask")
  public ViewTaskResDto updateTask(TaskDto taskDto) {
    return taskService.updateTask(taskDto);
  }

  @ResponseBody
  @DeleteMapping("deleteTask")
  public WriteTaskResDto deleteTask(int pid) {
    return taskService.deleteTask(pid);
  }

//  @PostMapping("updateTask")
//  public ModelAndView updateTask(ViewContentDto viewContentDto) {
//    taskDao.updateTask(viewContentDto);
//    int pid = viewContentDto.getPid();
//    ModelAndView mav = new ModelAndView();
//    mav.setViewName("redirect:/week/viewContent?pid="+pid);
//    return mav;
//  }

//  @GetMapping("writeTask")
//  public String writeTask(Model model, String name) {
//    int pid = taskDao.retrieveMemberPid(name);
//    model.addAttribute("pid",pid);
//    return "writeTask";
//  }

  @ResponseBody
  @PostMapping("writeTask")
  public WriteTaskResDto writeTask(TaskDto taskDto){  // 완료
    return taskService.writeTask(taskDto);
  }

}
