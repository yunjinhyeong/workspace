package kr.co.zzimcar.domain.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class MemberJoinDto {

  private int pid;
  private String name;
  private int departmentPid;
  private String role;

  private List<TaskResDto> taskList;
}
