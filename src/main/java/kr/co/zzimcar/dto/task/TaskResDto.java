package kr.co.zzimcar.dto.task;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
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
  private String state;
  private String priority;
  private Date startAt;
  private Date dueAt;
  private LocalDateTime createdAt;

  public TaskResDto(TaskDto taskDto) {
    this.pid = taskDto.getPid();
    this.memberPid = taskDto.getMemberPid();
    this.type = taskDto.getType();
    this.title = taskDto.getTitle();
    this.content = taskDto.getContent();
    this.state = taskDto.getState();
    this.priority = taskDto.getPriority();
    this.startAt = taskDto.getStartAt();
    this.dueAt = taskDto.getDueAt();
    this.createdAt = taskDto.getCreatedAt();
  }
}
