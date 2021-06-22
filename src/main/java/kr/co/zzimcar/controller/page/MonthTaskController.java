package kr.co.zzimcar.controller.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MonthTaskController {

  @PostMapping("/write")
  public String writeReview() {
    return "redirect:/";
  }

}
