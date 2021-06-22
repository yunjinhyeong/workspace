package kr.co.zzimcar.domain.task;

import kr.co.zzimcar.domain.task.MemberTaskDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class WeeklyTasks {

  private String departmentName;
  private List<MemberTaskDto> memberTasks;

  public WeeklyTasks(String departmentName, List<MemberTaskDto> memberTasks) {
    this.departmentName = departmentName;
    this.memberTasks = memberTasks;
  }
}