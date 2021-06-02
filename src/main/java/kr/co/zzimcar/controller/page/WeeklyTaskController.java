package kr.co.zzimcar.controller.page;

import kr.co.zzimcar.dto.member.MemberLoginDto;
import kr.co.zzimcar.dto.member.MemberMsgDto;
import kr.co.zzimcar.dto.member.MemberResDto;
import kr.co.zzimcar.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class WeeklyTaskController {

  private final MemberService memberService;

  @PostMapping("/login")
  @ResponseBody
  public MemberMsgDto login(MemberLoginDto memberLoginDto) {
    return memberService.login(memberLoginDto);
  }
}
