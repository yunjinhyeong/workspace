package kr.co.zzimcar.domain.member;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoResDto {

  private boolean success;
  private MemberInfoDataResDto data;
  private String message;
  private String code;
//  private String list[];
//  private HttpStatus status;

}