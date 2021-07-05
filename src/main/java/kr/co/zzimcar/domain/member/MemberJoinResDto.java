package kr.co.zzimcar.domain.member;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinResDto {

  private boolean success;
  private String message;
  private String code;

}