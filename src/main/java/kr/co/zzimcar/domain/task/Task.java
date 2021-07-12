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
  private String title;
  private int pid;
  private String state;

  public Task(LocalDate startAt, LocalDate dueAt, String content, int pid, String state, String title) {
    this.startAt = startAt;
    this.dueAt = dueAt;
    this.content = content;
    this.title = title;
    this.pid = pid;
    this.state = state;
  }
}
