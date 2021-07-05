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

  @NotNull(message = "부서번호(부서번호)를 입력해 주세요")
  @ApiModelProperty(value = "부서번호")
  private int departmentPid;

  @NotNull(message = "id(회원ID)를 입력해 주세요")
  @ApiModelProperty(value = "회원ID")
  private String id;

  @NotNull(message = "이름(회원이름)을 입력해 주세요")
  @ApiModelProperty(value = "회원이름")
  private String name;

  @NotNull(message = "직책(직책)을 입력해 주세요")
  @ApiModelProperty(value = "직책")
  private String role;

  @NotNull(message = "비밀번호를 입력해 주세요")
  @ApiModelProperty(value = "비밀번호")
  private String password;


}
