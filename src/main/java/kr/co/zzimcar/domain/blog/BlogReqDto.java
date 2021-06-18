package kr.co.zzimcar.domain.blog;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BlogReqDto {
  private String title;
  private String post;
  private String writer;
}
