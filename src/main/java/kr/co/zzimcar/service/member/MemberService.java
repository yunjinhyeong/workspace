package kr.co.zzimcar.service.member;

import kr.co.zzimcar.domain.ResponseDto;
import kr.co.zzimcar.domain.member.*;
import org.springframework.http.ResponseEntity;

public interface MemberService {
//  MemberMsgDto login(MemberLoginDto memberLoginDto);
  MemberMsgDto insertMember(MemberLoginDto memberLoginDto);

  TokenResult makeToken(TokenBasicData tokenBasicData);
  MemberInfoDto login(String token, MemberLoginDto memberLoginDto);
  int countByPid(int pid);
  ResponseEntity<ResponseDto<Void>> create(MemberReqDto memberReqDto);

  ResponseEntity<ResponseDto<MemberResDto>> loginApi(MemberReqDto memberReqDto);
}
