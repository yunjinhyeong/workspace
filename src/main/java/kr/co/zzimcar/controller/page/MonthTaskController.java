package kr.co.zzimcar.controller.page;

import kr.co.zzimcar.dto.blog.BlogDto;
import kr.co.zzimcar.service.blog.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MonthTaskController {

  private final BlogService blogService;

  @PostMapping("/write")
  public String writeReview(BlogDto wr) {
    System.out.println("1");
    blogService.review(wr);
    System.out.println("3");

    return "redirect:/";
  }

}
