package kr.co.zzimcar.service.member;

import kr.co.zzimcar.domain.ResponseDto;
import kr.co.zzimcar.domain.member.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface MemberService {

  MemberResDto insertMember(MemberDto memberDto);
  TokenResultResDto makeToken(TokenReqData tokenData);
  MemberInfoResDto login(String token, MemberLoginReqDto memberLoginDto);
  Map<String, Boolean> countByPid(int pid);
  MemberInfoDevResDto loginUseDev(String xClientToken, String xMemberToken);

  ResponseEntity<ResponseDto<Void>> create(MemberReqDto memberReqDto);

}