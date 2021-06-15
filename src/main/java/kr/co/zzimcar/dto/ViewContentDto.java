package kr.co.zzimcar.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class ViewContentDto {

  private int pid;
  private String department; //
  private String name;  //
  private String type;  //
  private String state; //
  private String priority;  //
  private String title; //
  private String content; //
  private String startAt;  //
  private String dueAt;

}