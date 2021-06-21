package kr.co.zzimcar.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResult {
  private boolean success;
  private TokenDataDto data;
  private String message;
  private String code;
  private HttpStatus status;

  public String getToken() {
    return this.data.getAccessToken();
  }
}

@Data
class TokenDataDto {
  private String accessToken;
  private String refreshToken;
}