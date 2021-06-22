package kr.co.zzimcar.serviceImpl.member;

import kr.co.zzimcar.dao.MemberDao;
import kr.co.zzimcar.domain.ResponseDto;
import kr.co.zzimcar.domain.member.*;
import kr.co.zzimcar.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberDao memberDao;

  @Override
  public MemberResDto insertMember(MemberDto memberDto) { // WebClient 회원가입
    return WebClient.create("http://localhost:8888")
          .post()
          .uri("/member/create")
          .bodyValue(memberDto)
          .retrieve()
          .bodyToMono(MemberResDto.class)
          .block();
  }

  @Override
  public TokenResultResDto makeToken(TokenData tokenData) { // 토큰발급
    return WebClient.create("https://int-api.dev.zzimcar.co.kr")
      .post()
      .uri("/client/token")
      .bodyValue(tokenData)
      .retrieve()
      .bodyToMono(TokenResultResDto.class)
      .block();
  }

  @Override
  public MemberInfoResDto login(String token, MemberLoginDto memberLoginDto) { // 로그인하기
    return WebClient.create("https://int-api.dev.zzimcar.co.kr")
      .post()
      .uri("/member/login")
      .header("xClientToken",token)
      .bodyValue(memberLoginDto)
      .retrieve()
      .bodyToMono(MemberInfoResDto.class)
      .block();
  }

  @Override
  public int countByPid(int pid) {
    return memberDao.countByPid(pid); // 회원으로 존재하냐 안하냐
  }

  @Override
  public ResponseEntity<ResponseDto<Void>> create(MemberReqDto memberReqDto) {  // 회원가입 API
    memberDao.create(new MemberDto(memberReqDto));
    return ResponseEntity.ok(new ResponseDto<>(true));
  }

}