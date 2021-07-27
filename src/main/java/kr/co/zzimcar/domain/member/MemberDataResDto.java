package kr.co.zzimcar.domain.member;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class MemberDataResDto {

  private String id;
  private int departmentPid;
  private String name;

  public MemberDataResDto(MemberDto memberDto) {
    this.id = memberDto.getId();
    this.departmentPid = memberDto.getDepartmentPid();
    this.name = memberDto.getName();
  }

}
