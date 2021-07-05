package kr.co.zzimcar.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@RequiredArgsConstructor
@ToString
public class MemberLoginReqDto {

  private String id;
  private String password;

}
