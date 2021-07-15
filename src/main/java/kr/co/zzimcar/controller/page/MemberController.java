package kr.co.zzimcar.controller.page;

import kr.co.zzimcar.domain.member.*;
import kr.co.zzimcar.persistence.MemberRepository;
import kr.co.zzimcar.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Map;

@Log
@Controller
@RequiredArgsConstructor
@RequestMapping("/member/")
public class MemberController {

  private final MemberService memberService;

  @Autowired
  PasswordEncoder pwEncoder;

  @Autowired
  MemberRepository repo;

  @GetMapping("/join")
  public void join(){
  }

  @Transactional
  @PostMapping("/join")
  public String joinPost(@ModelAttribute("member") MemberDto member) {
    String encryptPw = pwEncoder.encode(member.getPassword());
    member.setPassword(encryptPw);
    repo.save(member);
    return "redirect:/login";
  }

  @GetMapping(value = "/joinIdDupChk")
  @ResponseBody
  public Map<String, Boolean> ajaxJoinIdDupChk(String id) {
    return memberService.getCountById(id);
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
