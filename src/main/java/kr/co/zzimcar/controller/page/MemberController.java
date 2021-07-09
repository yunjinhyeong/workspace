package kr.co.zzimcar.controller.page;

import kr.co.zzimcar.domain.member.*;
import kr.co.zzimcar.domain.test.Memberr;
import kr.co.zzimcar.persistence.MemberRepository;
import kr.co.zzimcar.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Log
@Controller
@RequiredArgsConstructor
@RequestMapping("/member/")
public class MemberController {

  private final MemberService memberService;

  /////////////////////////////////////////////////
  @Autowired
  PasswordEncoder pwEncoder;

  @Autowired
  MemberRepository repo;

  @GetMapping("/joinn")
  public void joinn(){
    System.out.println("joinn 안임");
  }

  @Transactional
  @PostMapping("/joinn")
  public String joinPost(@ModelAttribute("member") Memberr member) {

    log.info("MEMBER: " + member);

    String encryptPw = pwEncoder.encode(member.getPassword());

    log.info("en: " + encryptPw);

    member.setPassword(encryptPw);

    repo.save(member);

    return "redirect:/login";
  }

  @GetMapping(value = "/joinIdDupChk")
  @ResponseBody
  public Map<String, Boolean> ajaxJoinIdDupChk(String id) {
    return memberService.getCountById(id);
  }

  /////////////////////////////////////////////////


//  @PostMapping("/login")
//  @ResponseBody
//  public MemberInfoResDto login(MemberLoginReqDto memberLoginDto) {
//    TokenResultResDto tokenResult = memberService.makeToken(new TokenReqData("apitest", "access_token"));
//    return memberService.login(tokenResult.getToken(), memberLoginDto);
//  }

//  @PostMapping("/isPid")
//  @ResponseBody
//  public Map<String, Boolean> isPid(int pid) {
//    return memberService.countByPid(pid);
//  }
//
//  @PostMapping("/submitRoleDepartmentPid")
//  @ResponseBody
//  public MemberResDto submitRoleDepartmentPid(MemberDto memberDto) {
//    return memberService.insertMember(memberDto);
//  }
//
//  @GetMapping("/loginUseDev")
//  @ResponseBody
//  public MemberInfoDevResDto loginUseDev(String token) {
//    TokenResultResDto tokenResult = memberService.makeToken(new TokenReqData("apitest", "access_token"));
//    return memberService.loginUseDev(tokenResult.getToken(), token);
//  }

//  @GetMapping("/join")
//  public String join(){
//    return "join";
//  }

  @PostMapping("/join")
  @ResponseBody
  public MemberJoinResDto join(MemberReqDto memberReqDto) {
    return memberService.join(memberReqDto);
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping("/login")
  @ResponseBody
  public MemberResDto login(MemberLoginReqDto memberLoginReqDto) {
    System.out.println("///swich01");
    return memberService.loginMember(memberLoginReqDto);
  }

  @GetMapping("/accessDenied")
  public void accessDenied() {

  }

  @GetMapping("/logout")
  public void logout() {

  }

}
