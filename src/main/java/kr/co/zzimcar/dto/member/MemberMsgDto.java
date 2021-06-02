package kr.co.zzimcar.dto.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class MemberMsgDto {
  private boolean success;
  private MemberResDto data;
  private String message;
  private String code;
}
