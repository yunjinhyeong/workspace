package kr.co.zzimcar.domain.member;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor
public class MemberDto {

  private String id;
  private int departmentPid;
  private String name;
  private String role;
  private String password;

  public MemberDto(MemberReqDto memberReqDto) {
    this.id = memberReqDto.getId();
    this.departmentPid = memberReqDto.getDepartmentPid();
    this.name = memberReqDto.getName();
    this.role = memberReqDto.getRole();
    this.password = memberReqDto.getPassword();
  }

}