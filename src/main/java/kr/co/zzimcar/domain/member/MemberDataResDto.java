package kr.co.zzimcar.domain.member;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class MemberDataResDto {

  private int pid;
  private int departmentPid;
  private String id;
  private String name;
  private String role;

  public MemberDataResDto(MemberDto memberDto) {
    this.pid = memberDto.getPid();
    this.departmentPid = memberDto.getDepartmentPid();
    this.id = memberDto.getId();
    this.name = memberDto.getName();
    this.role = memberDto.getRole();
  }

}
