package kr.co.zzimcar.domain.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class TestMember {

  private int pid;
  private int departmentPid;
  private String name;
  private String role;
  private String pw;
}
