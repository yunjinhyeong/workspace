package kr.co.zzimcar.dto.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class MemberDataDto {
  private int pid;
  private String name;
  private String department;
  private String role;
}
