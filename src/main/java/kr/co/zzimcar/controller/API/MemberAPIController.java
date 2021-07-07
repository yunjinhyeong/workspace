package kr.co.zzimcar.controller.API;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.co.zzimcar.domain.ResponseDto;
import kr.co.zzimcar.domain.member.MemberDataResDto;
import kr.co.zzimcar.domain.member.MemberLoginReqDto;
import kr.co.zzimcar.domain.member.MemberReqDto;
import kr.co.zzimcar.domain.member.MemberResDto;
import kr.co.zzimcar.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/memberAPI")
@RequiredArgsConstructor
@Api(tags = "회원 API")
public class MemberAPIController {

  private final MemberService memberService;

  @PostMapping("/create")
  @ApiOperation("맴버 회원가입 API")
  public ResponseEntity<ResponseDto<Void>> create(@RequestBody @ApiParam(value = "회원가입할 정보", required = true) MemberReqDto memberReqDto) {
    return memberService.create(memberReqDto);
  }

  @PostMapping("/login")
  @ApiOperation("맴버 로그인 API")
  public ResponseEntity<ResponseDto<MemberDataResDto>> login(@RequestBody @ApiParam(value = "로그인할 정보", required = true) MemberLoginReqDto memberLoginReqDto) {
    return memberService.login(memberLoginReqDto);
  }
}
