package kr.co.zzimcar.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoDataResDto {

  private String accessToken;
  private String refreshToken;
  private MemberInfoDatasResDto member;

}
