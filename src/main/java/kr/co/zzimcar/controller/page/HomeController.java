package kr.co.zzimcar.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class HomeController {

  @GetMapping("")
  public String home(Model model) {
    new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
    model.addAttribute("thisit",new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
//    model.addAttribute("thisit","윤진형");
    return "/index";
  }
}
