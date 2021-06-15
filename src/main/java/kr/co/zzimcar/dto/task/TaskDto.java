package kr.co.zzimcar.dto.task;

import kr.co.zzimcar.dto.member.MemberDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

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
  private String state;
  private String priority;
  private Date startAt;
  private Date dueAt;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

  private MemberDto member;

  public TaskDto(TaskReqDto taskReqDto) { // memberPid 여기서?
    this.memberPid = taskReqDto.getMemberPid();
    this.type = taskReqDto.getType();
    this.title = taskReqDto.getTitle();
    this.content = taskReqDto.getContent();
    this.state = taskReqDto.getState();
    this.priority = taskReqDto.getPriority();
    this.startAt = taskReqDto.getStartAt();
    this.dueAt = taskReqDto.getDueAt();
  }
}
