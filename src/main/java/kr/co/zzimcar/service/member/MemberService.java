package kr.co.zzimcar.service.member;

import kr.co.zzimcar.domain.ResponseDto;
import kr.co.zzimcar.domain.member.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface MemberService {

  Map<String, Boolean> getCountById(String id);


  ResponseEntity<ResponseDto<Void>> create(MemberReqDto memberReqDto);
  ResponseEntity<ResponseDto<MemberDataResDto>> login(MemberLoginReqDto memberLoginReqDto);

  MemberResDto loginMember(MemberLoginReqDto memberLoginReqDto);

  MemberJoinResDto join(MemberReqDto memberReqDto);

}