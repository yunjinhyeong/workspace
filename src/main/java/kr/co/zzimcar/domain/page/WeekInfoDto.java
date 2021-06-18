package kr.co.zzimcar.domain.page;

import kr.co.zzimcar.domain.WeeklyTasks;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class WeekInfoDto {
  private int weekcount;
  private List<DrawWeekWorkDto> list;
  private Map<String, List<WeeklyTasks>> items;
}
