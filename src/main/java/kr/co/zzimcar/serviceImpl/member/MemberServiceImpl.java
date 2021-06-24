package kr.co.zzimcar.serviceImpl.member;

import kr.co.zzimcar.dao.MemberDao;
import kr.co.zzimcar.domain.ResponseDto;
import kr.co.zzimcar.domain.member.*;
import kr.co.zzimcar.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberDao memberDao;

  @Override
  public MemberResDto insertMember(MemberDto memberDto) {
    return WebClient.create("http://localhost:8888")
      .post()
      .uri("/member/create")
      .bodyValue(memberDto)
      .retrieve()
      .bodyToMono(MemberResDto.class)
      .block();
  }

  @Override
  public TokenResultResDto makeToken(TokenReqData tokenData) {
    return WebClient.create("https://int-api.dev.zzimcar.co.kr")
      .post()
      .uri("/client/token")
      .bodyValue(tokenData)
      .retrieve()
      .bodyToMono(TokenResultResDto.class)
      .block();
  }

  @Override
  public MemberInfoResDto login(String token, MemberLoginReqDto memberLoginDto) {
    return WebClient.create("https://int-api.dev.zzimcar.co.kr")
      .post()
      .uri("/member/login")
      .header("xClientToken", token)
      .bodyValue(memberLoginDto)
      .retrieve()
      .bodyToMono(MemberInfoResDto.class)
      .block();
  }

  @Override
  public Map<String, Boolean> countByPid(int pid) {
    int count = memberDao.countByPid(pid);
    Map<String, Boolean> map = new HashMap<>();
    if (count == 0) map.put("isPidDup", false);
    if (count > 0) map.put("isPidDup", true);
    return map;
  }

  @Override
  public MemberInfoDevResDto loginUseDev(String xClientToken, String xMemberToken) {
    return WebClient.create("https://int-api.dev.zzimcar.co.kr")
      .get()
      .uri("/member")
      .header("xMemberToken", xMemberToken)
      .header("xClientToken", xClientToken)
      .retrieve()
      .bodyToMono(MemberInfoDevResDto.class)
      .block();
  }

  @Override
  public ResponseEntity<ResponseDto<Void>> create(MemberReqDto memberReqDto) {
    memberDao.create(new MemberDto(memberReqDto));
    return ResponseEntity.ok(new ResponseDto<>(true));
  }

}