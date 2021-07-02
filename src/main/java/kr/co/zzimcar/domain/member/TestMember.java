package kr.co.zzimcar.domain.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

@Getter
@ToString
@Setter
public class TestMember {

  private int pid;
  private int departmentPid;
  private String id;
  private String name;
  private String role;
  private String pw;
}
