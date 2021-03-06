package kr.co.zzimcar.domain.task;

import kr.co.zzimcar.domain.member.MemberDto;
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
public class TaskDto {

  private int pid;
  private String memberId;
  private String title;
  private String content;
  private String state;
  private String priority;
  private Date startAt;
  private Date dueAt;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

  private MemberDto member;

  public TaskDto(TaskReqDto taskReqDto) {
    this.memberId = taskReqDto.getMemberId();
    this.title = taskReqDto.getTitle();
    this.content = taskReqDto.getContent();
    this.state = taskReqDto.getState();
    this.priority = taskReqDto.getPriority();
    this.startAt = taskReqDto.getStartAt();
    this.dueAt = taskReqDto.getDueAt();
  }

  public TaskDto(TaskUpdateReqDto taskUpdateReqDto) {
    this.pid = taskUpdateReqDto.getPid();
    this.content = taskUpdateReqDto.getContent();
    this.title = taskUpdateReqDto.getTitle();
    this.state = taskUpdateReqDto.getState();
    this.priority = taskUpdateReqDto.getPriority();
    this.startAt = taskUpdateReqDto.getStartAt();
    this.dueAt = taskUpdateReqDto.getDueAt();
  }
}
