package kr.co.zzimcar.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class TaskTestForm {
  private int pid;
  private String department;
  private String name;
  private LocalDate startAt;
  private LocalDate dueAt;
  private String content;

}