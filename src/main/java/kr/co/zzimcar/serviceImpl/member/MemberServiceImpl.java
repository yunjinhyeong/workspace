package kr.co.zzimcar.serviceImpl.member;

import kr.co.zzimcar.atest.Account;
import kr.co.zzimcar.dao.MemberDao;
import kr.co.zzimcar.domain.ResponseDto;
import kr.co.zzimcar.domain.member.*;
import kr.co.zzimcar.exception.ApiException;
import kr.co.zzimcar.service.member.MemberService;
import kr.co.zzimcar.util.CheckJoinMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static kr.co.zzimcar.enumeration.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final PasswordEncoder pwEncoder;

  private final MemberDao memberDao;

  @Override
  public Map<String, Boolean> getCountById(String id) {
    int count = memberDao.getCountById(id);
    Map<String, Boolean> map = new HashMap<>();
    if (count == 0) {map.put("isIdDup", false);}
    else {map.put("isIdDup", true);}
    return map;
  }


  @Override
  public ResponseEntity<ResponseDto<Void>> create(MemberReqDto memberReqDto) {
    CheckJoinMember checkJoinMember = new CheckJoinMember();
    checkJoinMember.check(memberReqDto);
    String encrypthPw = pwEncoder.encode(memberReqDto.getPassword());
    memberReqDto.setPassword(encrypthPw);
    memberDao.create(memberReqDto);
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

}