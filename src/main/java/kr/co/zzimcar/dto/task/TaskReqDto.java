package kr.co.zzimcar.dto.task;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Profile;

import javax.annotation.MatchesPattern;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class TaskReqDto {
  
  @NotNull(message = "memberPid를 입력해 주세요")
  @ApiModelProperty(value = "회원번호")
  private int memberPid;

  @NotNull(message = "type를 입력해 주세요")
  @ApiModelProperty(value = "월간/주간 상태")

  private String type;

  @NotNull(message = "title를 입력해 주세요")
  @ApiModelProperty(value = "업무제목")
  @Size(min = 1, max = 20)
  private String title;

  @NotNull(message = "content를 입력해 주세요")
  @ApiModelProperty(value = "업무내용")
  private String content;

  @NotNull(message = "state를 입력해 주세요")
  @ApiModelProperty(value = "업무상태")
  private String state;

  @NotNull(message = "priority를 입력해 주세요")
  @ApiModelProperty(value = "업무우선순위")
  private String priority;

  @NotNull(message = "startAt 입력해 주세요")
  @ApiModelProperty(value = "업무시작일")
  private Date startAt;

  @NotNull(message = "dueAt 입력해 주세요")
  @ApiModelProperty(value = "업무종료일")
  private Date dueAt;
}
