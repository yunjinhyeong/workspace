package kr.co.zzimcar.domain.member;

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
  private int departmentPid;
  private String role;

  public MemberDto(MemberReqDto memberReqDto){
    this.pid = memberReqDto.getPid();
    this.name = memberReqDto.getName();
    this.departmentPid = memberReqDto.getDepartmentPid();
    this.role = memberReqDto.getRole();
  }

}
