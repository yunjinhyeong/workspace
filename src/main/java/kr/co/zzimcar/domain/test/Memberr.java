package kr.co.zzimcar.domain.test;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "members")
@EqualsAndHashCode(of = "id")
@ToString
public class Memberr {

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
