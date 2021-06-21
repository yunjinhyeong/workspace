package kr.co.zzimcar.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenBasicData {
  private String apiKey;
  private String grantType;
}
