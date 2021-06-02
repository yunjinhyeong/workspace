package kr.co.zzimcar.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

  @NotNull(message = "비밀번호를 입력해 주세요")
  @ApiModelProperty(value = "회원비밀번호")
  private String pw;

}
