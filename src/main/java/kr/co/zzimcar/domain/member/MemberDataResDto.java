package kr.co.zzimcar.domain.member;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class MemberDataResDto {

  private int pid;
  private String name;
  private int departmentPid;
  private String role;

}
