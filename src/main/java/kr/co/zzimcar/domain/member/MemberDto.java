package kr.co.zzimcar.domain.member;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "member")
@EqualsAndHashCode(of = "id")
@ToString
public class MemberDto {

  @Id
  private String id;

  @Column(length = 10)
  private int departmentPid;

  private String password;

  private String name;

  @CreationTimestamp
  private Timestamp regdate;

  @UpdateTimestamp
  private Timestamp updatedate;

  @OneToMany(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
  @JoinColumn(name = "member")
  private List<MemberRole> roles;

}