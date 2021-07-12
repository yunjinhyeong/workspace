package kr.co.zzimcar.domain.task;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class TaskFormDto {

  private int pid;
  private String memberId;
  private String department;
  private String name;
  private LocalDate startAt;
  private LocalDate dueAt;
  private String content;
  private String state;
  private String title;

}