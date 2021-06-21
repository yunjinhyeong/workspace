package kr.co.zzimcar.domain.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class MemberReqDto {

  @NotNull(message = "이름을 입력해 주세요")
  @ApiModelProperty(value = "회원이름")
  private String name;

  @NotNull(message = "pid를 입력해 주세요")
  @ApiModelProperty(value = "회원번호")
  private int pid;

  @NotNull(message = "직책을 입력해 주세요")
  @ApiModelProperty(value = "직책")
  private String role;

  @NotNull(message = "부서번호를 입력해 주세요")
  @ApiModelProperty(value = "부서번호")
  private int departmentPid;

}
