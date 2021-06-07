package kr.co.zzimcar.dto;

import java.time.LocalDate;
import java.util.List;

public class WeeklyTasks {
  private String departmentName;
  private List<MemberTask> memberTasks;
}

class MemberTask {
  private String name;
  private List<Task> w1;
  private List<Task> w2;
  private List<Task> w3;
  private List<Task> w4;
  private List<Task> w5;
}

class Task {
  private LocalDate startAt;
  private LocalDate dueAt;
  private String content;
}
