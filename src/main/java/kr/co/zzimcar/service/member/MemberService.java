package kr.co.zzimcar.service.member;

import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.dto.member.*;
import org.springframework.http.ResponseEntity;

public interface MemberService {
  MemberMsgDto login(MemberLoginDto memberLoginDto);
  ResponseEntity<ResponseDto<MemberResDto>> loginApi(MemberReqDto memberReqDto);
}
