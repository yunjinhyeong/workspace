package kr.co.zzimcar.controller.page;

import kr.co.zzimcar.domain.member.*;
import kr.co.zzimcar.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
  public MemberInfoDto login(MemberLoginDto memberLoginDto) {
    System.out.println(memberLoginDto);
    TokenResult tokenResult = memberService.makeToken(new TokenBasicData("apitest","access_token"));
    return memberService.login(tokenResult.getToken(), memberLoginDto);
  }

  @PostMapping("/isPid")
  @ResponseBody
  public Map<String, Boolean> test(int pid, String name, Model model) {
    int count = memberService.countByPid(pid);
    Map<String, Boolean> map = new HashMap<>();
    if (count == 0) {
      map.put("isPidDup", false);

    } else { // count == 1
      map.put("isPidDup", true);
      model.addAttribute("member_pid", pid);
      model.addAttribute("name", name);
    }
    return map;
  }

  @PostMapping("/submitRoleDepartmentPid")
  @ResponseBody
  public MemberMsgDto submitRoleDepartmentPid(MemberLoginDto memberLoginDto) {
    return memberService.insertMember(memberLoginDto);
  }
}
