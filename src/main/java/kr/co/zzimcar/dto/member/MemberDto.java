package kr.co.zzimcar.dto.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class MemberDto {

  private int pid;
  private String name;
  private String department;
  private String role;
  private String pw;


}
