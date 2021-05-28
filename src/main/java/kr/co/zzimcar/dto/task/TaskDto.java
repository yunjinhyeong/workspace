package kr.co.zzimcar.dto.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TaskDto {

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
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;
  
  public TaskDto(TaskReqDto taskReqDto) { // 맴버 pid는 세션인가 아닌가 고민해야된다 어디서 가져올지
    this.type = taskReqDto.getType();
    this.title = taskReqDto.getTitle();
    this.content = taskReqDto.getContent();
    this.status = taskReqDto.getStatus();
    this.priority = taskReqDto.getPriority();
    this.startAt = taskReqDto.getStartAt();
    this.dueAt = taskReqDto.getDueAt();
  }
}
