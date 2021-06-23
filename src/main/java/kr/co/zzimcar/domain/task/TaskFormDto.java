package kr.co.zzimcar.domain.task;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class TaskFormDto {

  private int pid;
  private int memberPid;
  private String department;
  private String name;
  private LocalDate startAt;
  private LocalDate dueAt;
  private String content;
  private String title;
  private String state;

}