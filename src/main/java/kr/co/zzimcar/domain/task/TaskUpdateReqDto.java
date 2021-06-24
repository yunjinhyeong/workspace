package kr.co.zzimcar.domain.task;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Getter
@Setter
@ToString
public class TaskUpdateReqDto {
  @NotNull(message = "pid(업무글 번호)를 입력해 주세요")
  @ApiModelProperty(value = "업무글 번호")
  private int pid;

  @NotNull(message = "title(업무제목)를 입력해 주세요")
  @ApiModelProperty(value = "업무제목")
  @Size(min = 1, max = 30, message = "title(업무제목)은 1에서 30글자 사이입니다.")
  private String title;

  @NotNull(message = "content(업무내용)를 입력해 주세요")
  @ApiModelProperty(value = "업무내용")
  private String content;

  @NotNull(message = "state(업무상태)를 입력해 주세요")
  @ApiModelProperty(value = "업무상태")
  private String state;

  @NotNull(message = "priority(업무우선순위)를 입력해 주세요")
  @ApiModelProperty(value = "업무우선순위")
  private String priority;

  @NotNull(message = "startAt(업무시작일)을 입력해 주세요")
  @ApiModelProperty(value = "업무시작일")
  private Date startAt;

  @NotNull(message = "dueAt(업무종료일)을 입력해 주세요")
  @ApiModelProperty(value = "업무종료일")
  private Date dueAt;

}
