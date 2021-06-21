package kr.co.zzimcar.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoDto {
  private boolean success;
  private MemberInfoDataDto data;
  private String message;
  private String code;
  private String list[];
  private HttpStatus status;
}
