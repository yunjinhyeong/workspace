package kr.co.zzimcar.controller.page;

import kr.co.zzimcar.domain.member.*;
import kr.co.zzimcar.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

  private final MemberService memberService;

//  @PostMapping("/login")
//  @ResponseBody
//  public MemberInfoResDto login(MemberLoginReqDto memberLoginDto) {
//    TokenResultResDto tokenResult = memberService.makeToken(new TokenReqData("apitest", "access_token"));
//    return memberService.login(tokenResult.getToken(), memberLoginDto);
//  }

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

  @GetMapping("/loginUseDev")
  @ResponseBody
  public MemberInfoDevResDto loginUseDev(String token) {
    TokenResultResDto tokenResult = memberService.makeToken(new TokenReqData("apitest", "access_token"));
    return memberService.loginUseDev(tokenResult.getToken(), token);
  }

  @GetMapping("/join")
  public String join(){
    return "join";
  }

  @Transactional
  @PostMapping("/join")
  public String joinPost(@ModelAttribute("member") TestMember member) {
//    String encryptPw = pwEncoder.encode(member.getUpw());
//
//    member.setUpw(encryptPw);
//
//    repo.save(member);
    memberService.testjoinmember(member);
    return "joinResult";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping("/login")
  public String login(TestLoginMember testLoginMember, HttpSession session) {
    memberService.testloginmember(testLoginMember, session);
    return "login";
  }

  @GetMapping("/accessDenied")
  public void accessDenied() {

  }

  @GetMapping("/logout")
  public void logout() {

  }

}
