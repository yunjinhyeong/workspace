package kr.co.zzimcar.controller;

import kr.co.zzimcar.dto.BlogDto;
import kr.co.zzimcar.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DepartmentController {

  private final BlogService blogService;

  @PostMapping("/write")
  public String writeReview(BlogDto wr) {
    blogService.review(wr);

    return "index";
  }

}
