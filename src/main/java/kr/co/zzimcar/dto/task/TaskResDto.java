package kr.co.zzimcar.dto.task;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class TaskResDto {
  private int pid;
  private int memberPid;
  private String type;
  private String title;
  private String content;
  private String status;
  private String priority;
  private LocalDateTime startAt;
  private LocalDateTime dueAt;
  private LocalDateTime createdAt;

  public TaskResDto(TaskDto taskDto) {
    this.pid = taskDto.getPid();
    this.memberPid = taskDto.getMemberPid();
    this.type = taskDto.getType();
    this.title = taskDto.getTitle();
    this.content = taskDto.getContent();
    this.status = taskDto.getStatus();
    this.priority = taskDto.getPriority();
    this.startAt = taskDto.getStartAt();
    this.dueAt = taskDto.getDueAt();
    this.createdAt = taskDto.getCreatedAt();
  }
}
