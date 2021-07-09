package kr.co.zzimcar.domain.task;

import io.swagger.annotations.ApiModelProperty;
import kr.co.zzimcar.domain.CheckBowl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Getter
@Setter
@ToString
public class TaskReqDto extends CheckBowl {

  @NotNull(message = "memberId(회원 아이디)를 입력해 주세요")
  @ApiModelProperty(value = "회원 아이디")
  private String memberId;

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
