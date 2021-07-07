package kr.co.zzimcar.domain.member;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor
public class MemberDto {

  private int pid;
  private int departmentPid;
  private String id;
  private String name;
  private String role;
  private String password;

  public MemberDto(MemberReqDto memberReqDto) {
    this.departmentPid = memberReqDto.getDepartmentPid();
    this.id = memberReqDto.getId();
    this.name = memberReqDto.getName();
    this.role = memberReqDto.getRole();
    this.password = memberReqDto.getPassword();
  }

}