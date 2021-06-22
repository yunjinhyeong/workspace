package kr.co.zzimcar.service.member;

import kr.co.zzimcar.domain.ResponseDto;
import kr.co.zzimcar.domain.member.*;
import org.springframework.http.ResponseEntity;

public interface MemberService {

  MemberResDto insertMember(MemberDto memberDto);
  TokenResultResDto makeToken(TokenData tokenData);
  MemberInfoResDto login(String token, MemberLoginDto memberLoginDto);
  int countByPid(int pid);

  ResponseEntity<ResponseDto<Void>> create(MemberReqDto memberReqDto);

}