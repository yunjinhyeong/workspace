package kr.co.zzimcar.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class MemberDataDto {
  private int pid;
  private String name;
  private int departmentPid;
  private String role;
}
