package kr.co.zzimcar.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class WeeklyTasks {  // 리스트???
  private String departmentName;
  private List<MemberTaskDto> memberTasks;

  public WeeklyTasks(String departmentName, List<MemberTaskDto> memberTasks) {
    this.departmentName = departmentName;
    this.memberTasks = memberTasks;
  }
}