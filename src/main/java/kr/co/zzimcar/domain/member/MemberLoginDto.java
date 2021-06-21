package kr.co.zzimcar.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@RequiredArgsConstructor
@ToString
public class MemberLoginDto {

//  private String name;
//  private String pw;

  private String password;
  private String username;
}
