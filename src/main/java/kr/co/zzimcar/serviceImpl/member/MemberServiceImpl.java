package kr.co.zzimcar.serviceImpl.member;

import kr.co.zzimcar.dao.MemberDao;
import kr.co.zzimcar.domain.ResponseDto;
import kr.co.zzimcar.domain.member.*;
import kr.co.zzimcar.exception.ApiException;
import kr.co.zzimcar.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

import static kr.co.zzimcar.enumeration.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberDao memberDao;

  @Override
  public MemberMsgDto login(MemberLoginDto memberLoginDto) {
    return WebClient.create("http://localhost:8888")
      .post()
      .uri("/member/retrieve")
      .bodyValue(memberLoginDto)
      .retrieve()
      .bodyToMono(MemberMsgDto.class)
      .block();
  }

  @Override
  public ResponseEntity<ResponseDto<MemberResDto>> loginApi(MemberReqDto memberReqDto) {
    MemberDto memberDto = Optional.ofNullable(memberDao.login(memberReqDto)).orElseThrow(() -> new ApiException(MEMBER_LOGIN_FAIL));
    ResponseDto<MemberResDto> responseDto = new ResponseDto<>(true);
    responseDto.setData(new MemberResDto(memberDto));
    return ResponseEntity.ok(responseDto);
  }
}