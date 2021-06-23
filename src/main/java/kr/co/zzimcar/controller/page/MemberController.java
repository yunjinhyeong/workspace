package kr.co.zzimcar.controller.page;

import kr.co.zzimcar.domain.member.*;
import kr.co.zzimcar.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/login")
  @ResponseBody
  public MemberInfoResDto login(MemberLoginDto memberLoginDto) {
    TokenResultResDto tokenResult = memberService.makeToken(new TokenData("apitest","access_token"));
    return memberService.login(tokenResult.getToken(), memberLoginDto);
  }

  @PostMapping("/isPid")
  @ResponseBody
  public Map<String, Boolean> isPid(int pid) {
    return memberService.countByPid(pid);
  }

  @PostMapping("/submitRoleDepartmentPid")
  @ResponseBody
  public MemberResDto submitRoleDepartmentPid(MemberDto memberDto) {
    return memberService.insertMember(memberDto);
  }

}
