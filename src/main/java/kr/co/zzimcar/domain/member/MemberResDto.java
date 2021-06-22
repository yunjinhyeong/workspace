package kr.co.zzimcar.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class MemberResDto {

  private boolean success;
  private MemberDataResDto data;
  private String message;
  private String code;

}
