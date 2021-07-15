package kr.co.zzimcar.controller.page;

import lombok.extern.java.Log;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log
@Controller
public class HomeController {

  @GetMapping("/index")
  public void index() {

  }

  @GetMapping("/login")
  public void login() {

  }

  @GetMapping("/accessDenied")
  public void accessDenied() {

  }

  @GetMapping("/logout")
  public void logout() {

  }

  @RequestMapping("/guest")
  public void forGuest() {

    log.info("guest");

  }

  @RequestMapping("/manager")
  public void forManager() {

    log.info("manager");

  }

  @RequestMapping("/sampletest")
  public void forSampleTest() {
    log.info("manager");

  }

  @RequestMapping("/admin")
  public void forAdmin() {
    log.info("admin");

  }

  @Secured({ "ROLE_ADMIN" })
  @RequestMapping("/adminSecret")
  public void forAdminSecret() {

    log.info("admin secret ");

  }

  @Secured("ROLE_MANAGER")
  @RequestMapping("/managerSecret")
  public void forManagerSecret() {

    log.info("manager secret");

  }
}
