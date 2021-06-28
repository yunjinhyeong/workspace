package kr.co.zzimcar.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public abstract class CheckBowl {

  private String state;
  private String priority;
  private Date startAt;
  private Date dueAt;

}
