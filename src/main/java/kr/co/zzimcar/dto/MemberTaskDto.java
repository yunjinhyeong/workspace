package kr.co.zzimcar.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class MemberTaskDto {

  private String name;
  private String w1;
  private String w2;
  private String w3;
  private String w4;
  private String w5;

  public MemberTaskDto(String name) {
    this.name = name;
  }

}
