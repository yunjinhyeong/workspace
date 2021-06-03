package kr.co.zzimcar.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("")
  public String home(Model model) {
    model.addAttribute("thisit","윤진형");
    return "/index";
  }
}
