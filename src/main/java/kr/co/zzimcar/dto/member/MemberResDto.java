package kr.co.zzimcar.dto.member;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class MemberResDto {
  private int pid;
  private String name;
  private int departmentPid;
  private String role;

  public MemberResDto(MemberDto memberDto) {
    this.pid = memberDto.getPid();
    this.name = memberDto.getName();
    this.departmentPid = memberDto.getDepartmentPid();
    this.role = memberDto.getRole();
  }
}
