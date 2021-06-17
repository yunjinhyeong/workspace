package kr.co.zzimcar.dto.task;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ViewTaskResDto {
  private String msg;
  private TaskResDto data;
  private boolean success;
}
