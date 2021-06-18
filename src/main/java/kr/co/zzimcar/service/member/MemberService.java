package kr.co.zzimcar.service.member;

import kr.co.zzimcar.domain.ResponseDto;
import kr.co.zzimcar.domain.member.*;
import org.springframework.http.ResponseEntity;

public interface MemberService {
  MemberMsgDto login(MemberLoginDto memberLoginDto);
  ResponseEntity<ResponseDto<MemberResDto>> loginApi(MemberReqDto memberReqDto);
}
