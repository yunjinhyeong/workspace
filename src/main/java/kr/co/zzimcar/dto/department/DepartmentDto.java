package kr.co.zzimcar.dto.department;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class DepartmentDto {
  private int pid;
  private String name;
}
