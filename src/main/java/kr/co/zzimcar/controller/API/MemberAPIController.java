package kr.co.zzimcar.controller.API;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.co.zzimcar.domain.ResponseDto;
import kr.co.zzimcar.domain.member.MemberReqDto;
import kr.co.zzimcar.domain.member.MemberResDto;
import kr.co.zzimcar.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Api(tags = "회원 API")
public class MemberAPIController {

  private final MemberService memberService;

  @PostMapping("/retrieve")
  @ApiOperation("맴버 로그인 API")
  public ResponseEntity<ResponseDto<MemberResDto>> login(@RequestBody @ApiParam(value = "로그인할 정보", required = true) MemberReqDto memberReqDto){
    return memberService.loginApi(memberReqDto);
  }
}
