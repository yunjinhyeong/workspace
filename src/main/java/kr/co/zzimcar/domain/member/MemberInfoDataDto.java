package kr.co.zzimcar.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoDataDto {
  private int pid;
  private String userId;
  private String name;
  private String firstName;
  private String lastName;
  private String birth;
  private String email;
  private String mobile;
  private String externalLoginType;
  private int point;
  private LocalDateTime lastLoginDtime;
  private LocalDateTime regDtime;
  private LocalDateTime modDtime;
  private String niceIdDi;
  private String accessToken;
  private String refreshToken;
  private boolean activated;
}
