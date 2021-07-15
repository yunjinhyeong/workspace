package kr.co.zzimcar.domain.member;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "roles")
@EqualsAndHashCode(of = "pid")
@ToString
public class MemberRole {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long pid;
  
  private String role;
    
}
