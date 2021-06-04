package kr.co.zzimcar.dto.page;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class DrawWeekWorkDto {

  private int realweek;
  private int realmm;
  private int pid;
  private String name;
  private String department;
  private String role;
  private String yyyy;
  private String mm;
  private String dd;
  private int tPid;
  private int memberPid;
  private String type;
  private String title;
  private String content;
  private String state;
  private String priority;
  private Date startAt;
  private Date dueAt;
  private LocalDateTime createdAt;
}
