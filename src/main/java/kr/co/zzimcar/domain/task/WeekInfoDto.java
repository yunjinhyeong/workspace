package kr.co.zzimcar.domain.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class WeekInfoDto {
  private int weekcount;
//  private List<DrawWeekWorkDto> list;
  private List<LocalDate> weekstartduepoint;
  private Map<String, List<WeeklyTasks>> items;
}
