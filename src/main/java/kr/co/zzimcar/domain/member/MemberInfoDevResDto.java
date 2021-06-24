package kr.co.zzimcar.domain.member;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoDevResDto {

  private boolean success;
  private MemberInfoDatasResDto data;
  private String message;
  private String code;
}