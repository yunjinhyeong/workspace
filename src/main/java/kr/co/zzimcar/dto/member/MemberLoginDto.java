package kr.co.zzimcar.dto.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Setter
@Getter
@RequiredArgsConstructor
@ToString
public class MemberLoginDto {

  private String name;
  private String pw;
}
