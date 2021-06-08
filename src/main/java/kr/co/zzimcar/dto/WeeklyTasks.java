package kr.co.zzimcar.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Data
@RequiredArgsConstructor
public class WeeklyTasks {  // 리스트???
  private String departmentName;
  private List<MemberTaskDto> memberTasks;

  public WeeklyTasks(String departmentName) {
    this.departmentName = departmentName;
  }
}

@Data
class Task {
  private LocalDate startAt;
  private LocalDate dueAt;
  private String content;
}
