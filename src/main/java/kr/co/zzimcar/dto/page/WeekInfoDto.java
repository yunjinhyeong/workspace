package kr.co.zzimcar.dto.page;

import kr.co.zzimcar.dto.WeeklyTasks;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDateTime;
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
