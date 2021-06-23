package kr.co.zzimcar.domain.task;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class Task {

  private LocalDate startAt;
  private LocalDate dueAt;
  private String content;
  private int pid;
  private String state;
  private String title;

  public Task(LocalDate startAt, LocalDate dueAt, String content, int pid, String state, String title) {
    this.startAt = startAt;
    this.dueAt = dueAt;
    this.content = content;
    this.pid = pid;
    this.state = state;
    this.title = title;
  }
}
