package kr.co.zzimcar.domain.task;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class WeeklyTasks {

  private String departmentName;
  private List<MemberTask> memberTasks;

  public WeeklyTasks(String departmentName, List<MemberTask> memberTasks) {
    this.departmentName = departmentName;
    this.memberTasks = memberTasks;
  }
}