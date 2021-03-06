package kr.co.zzimcar.domain.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TaskResDto {

  private int pid;
  private String memberId;
  private String content;
  private String state;
  private String priority;
  private Date startAt;
  private Date dueAt;
  private LocalDateTime createdAt;

  public TaskResDto(TaskDto taskDto) {
    this.pid = taskDto.getPid();
    this.memberId = taskDto.getMemberId();
    this.content = taskDto.getContent();
    this.state = taskDto.getState();
    this.priority = taskDto.getPriority();
    this.startAt = taskDto.getStartAt();
    this.dueAt = taskDto.getDueAt();
    this.createdAt = taskDto.getCreatedAt();
  }
}
