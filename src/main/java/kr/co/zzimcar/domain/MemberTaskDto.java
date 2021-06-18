package kr.co.zzimcar.domain;

import kr.co.zzimcar.domain.task.Task;
import lombok.Data;
import lombok.RequiredArgsConstructor;


import java.util.List;

@Data
@RequiredArgsConstructor
public class MemberTaskDto {

  private String name;
  private List<Task> w1;
  private List<Task> w2;
  private List<Task> w3;
  private List<Task> w4;
  private List<Task> w5;

  public MemberTaskDto(String name, List<Task> w1, List<Task> w2, List<Task> w3, List<Task> w4, List<Task> w5) {
    this.name = name;
    this.w1 = w1;
    this.w2 = w2;
    this.w3 = w3;
    this.w4 = w4;
    this.w5 = w5;
  }

  public MemberTaskDto(String name, List<Task> w1, List<Task> w2, List<Task> w3, List<Task> w4) {
    this.name = name;
    this.w1 = w1;
    this.w2 = w2;
    this.w3 = w3;
    this.w4 = w4;
  }
}
