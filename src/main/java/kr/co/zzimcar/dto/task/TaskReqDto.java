package kr.co.zzimcar.dto.task;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class TaskReqDto {

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

  @NotNull(message = "status를 입력해 주세요")
  @ApiModelProperty(value = "업무상태")
  private String status;

  @NotNull(message = "priority를 입력해 주세요")
  @ApiModelProperty(value = "업무우선순위")
  private String priority;

  @NotNull(message = "startAt 입력해 주세요")
  @ApiModelProperty(value = "업무시작일")
  private LocalDateTime startAt;

  @NotNull(message = "dueAt 입력해 주세요")
  @ApiModelProperty(value = "업무종료일")
  private LocalDateTime dueAt;
}
