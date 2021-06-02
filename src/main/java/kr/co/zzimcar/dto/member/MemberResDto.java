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
  private String department;
  private String role;

  public MemberResDto(MemberDto memberDto) {
    this.pid = memberDto.getPid();
    this.name = memberDto.getName();
    this.department = memberDto.getDepartment();
    this.role = memberDto.getRole();
  }
}
