package kr.co.zzimcar.atest;

import javax.servlet.http.HttpServletRequest;

import kr.co.zzimcar.dao.MemberDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AccountController {

  @Autowired
  AccountService accountService;

  @Autowired
  MemberDao accountMapper;

  Logger log = LoggerFactory.getLogger(this.getClass());

  // LOGIN
  @RequestMapping(value = "/login" )
  public String login(Model model, HttpServletRequest req) {

    log.info("### /login 입니다 ");
    return "loginPage";
  }

  // LOGIN SUCCESS
  @RequestMapping("/loginSuccess")
  public String loginSuccess() {
    return "index";
  }

  // LOGIN Fail
  @GetMapping("/loginFail")
  @ResponseBody
  public String loginFail() {
    return "Fail !";
  }

}
