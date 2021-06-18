package kr.co.zzimcar.domain.task;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WriteTaskResDto {
  private String msg;
  private boolean success;
}
