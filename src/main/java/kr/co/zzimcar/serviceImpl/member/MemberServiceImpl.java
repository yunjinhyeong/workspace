package kr.co.zzimcar.serviceImpl.member;

import kr.co.zzimcar.dao.MemberDao;
import kr.co.zzimcar.domain.ResponseDto;
import kr.co.zzimcar.domain.member.*;
import kr.co.zzimcar.exception.ApiException;
import kr.co.zzimcar.service.member.MemberService;
import kr.co.zzimcar.util.CheckJoinMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static kr.co.zzimcar.enumeration.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final PasswordEncoder pwEncoder;

  private final MemberDao memberDao;

  private final HttpSession session;

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

//  @Override
//  public MemberInfoResDto login(String token, MemberLoginReqDto memberLoginDto) {
//    return WebClient.create("https://int-api.dev.zzimcar.co.kr")
//      .post()
//      .uri("/member/login")
//      .header("xClientToken", token)
//      .bodyValue(memberLoginDto)
//      .retrieve()
//      .bodyToMono(MemberInfoResDto.class)
//      .block();
//  }

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
    System.out.println("///////////");
    CheckJoinMember checkJoinMember = new CheckJoinMember();
    checkJoinMember.check(memberReqDto);
    String encrypthPw = pwEncoder.encode(memberReqDto.getPassword());
    System.out.println("///////// 33333333");
    memberReqDto.setPassword(encrypthPw);
    System.out.println("///////// 1111111");
    memberDao.create(memberReqDto);
    System.out.println("///////// 2222222");
    return ResponseEntity.ok(new ResponseDto<>(true));
  }

  @Override
  public ResponseEntity<ResponseDto<MemberDataResDto>> login(MemberLoginReqDto memberLoginReqDto) {
    String password = Optional.ofNullable(memberDao.getPassword(memberLoginReqDto.getId())).orElseThrow(() -> new ApiException(MEMBER_NOT_EXIST));
    if(!pwEncoder.matches(memberLoginReqDto.getPassword() ,password)) throw new ApiException(MEMBER_LOGIN_FAIL);
    ResponseDto<MemberDataResDto> responseDto = new ResponseDto<>(true);
    MemberDataResDto memberDataResDto = memberDao.login(memberLoginReqDto);
    responseDto.setData(memberDataResDto);
    return ResponseEntity.ok(responseDto);
  }

  @Override
  public MemberResDto loginMember(MemberLoginReqDto memberLoginReqDto) {
    System.out.println("///swich02");
    System.out.println(memberLoginReqDto);

    return WebClient.create("http://localhost:8888")
      .post()
      .uri("/memberAPI/login")
      .bodyValue(memberLoginReqDto)
      .retrieve()
      .bodyToMono(MemberResDto.class)
      .block();
  }

  @Override
  public MemberJoinResDto join(MemberReqDto memberReqDto) {
    return WebClient.create("http://localhost:8888")
      .post()
      .uri("/memberAPI/create")
      .bodyValue(memberReqDto)
      .retrieve()
      .bodyToMono(MemberJoinResDto.class)
      .block();
  }
  //  @Override
//  public void testloginmember(TestLoginMember testLoginMember, HttpSession session) {
//
//    String apw = memberDao.getapw(testLoginMember.getId());
//    TestMember testMember;
//
//    if(pwEncoder.matches(testLoginMember.getPw() ,apw)) {
//      testMember = memberDao.testlogin(testLoginMember);
//    } else {
//      throw new BadCredentialsException(testLoginMember.getId());
//    }
//    session.setAttribute("userPid", testMember.getPid());
//  }

}