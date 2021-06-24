package kr.co.zzimcar.domain.task;

import kr.co.zzimcar.domain.task.Task;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class MemberTask {

  private String name;
  private int memberPid;
  private List<Task> weekly1;
  private List<Task> weekly2;
  private List<Task> weekly3;
  private List<Task> weekly4;
  private List<Task> weekly5;

  public MemberTask(String name, int memberPid,
                    List<Task> weekly1, List<Task> weekly2, List<Task> weekly3, List<Task> weekly4, List<Task> weekly5) {
    this.name = name;
    this.memberPid = memberPid;
    this.weekly1 = weekly1;
    this.weekly2 = weekly2;
    this.weekly3 = weekly3;
    this.weekly4 = weekly4;
    this.weekly5 = weekly5;
  }
}
