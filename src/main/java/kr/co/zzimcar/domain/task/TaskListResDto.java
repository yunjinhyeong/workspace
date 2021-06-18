package kr.co.zzimcar.domain.task;

import kr.co.zzimcar.domain.task.TaskResDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TaskListResDto {
  private int totalCnt;
  private List<TaskResDto> list;
}
