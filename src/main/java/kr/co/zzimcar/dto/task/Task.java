package kr.co.zzimcar.dto.task;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class Task {
  private LocalDate startAt;
  private LocalDate dueAt;
  private String content;

  public Task(LocalDate startAt, LocalDate dueAt, String content) {
    this.startAt = startAt;
    this.dueAt = dueAt;
    this.content = content;
  }
}
