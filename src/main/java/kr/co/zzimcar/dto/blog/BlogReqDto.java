package kr.co.zzimcar.dto.blog;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.YearMonth;

@Getter
@Setter
@ToString
public class BlogReqDto {
  private String title;
  private String post;
  private String writer;
}
