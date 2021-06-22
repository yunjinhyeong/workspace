package kr.co.zzimcar.controller.page;

import kr.co.zzimcar.domain.task.WeekInfoDto;
import kr.co.zzimcar.domain.task.*;
import kr.co.zzimcar.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/week")
public class WeeklyTaskController {

  private final TaskService taskService;

  @PostMapping("/weekly")
  @ResponseBody
  public WeekInfoDto drawTask(int year, int month) {
    return taskService.generateMonthlyTaskMap(year, month);
  }

  @ResponseBody
  @GetMapping("viewTask")
  public ViewTaskResDto viewOne(int pid) {
    return taskService.viewTask(pid);
  }

  @ResponseBody
  @PutMapping("updateTask")
  public ViewTaskResDto updateOne(TaskDto taskDto) {
    return taskService.updateTask(taskDto);
  }

  @ResponseBody
  @DeleteMapping("deleteTask")
  public WriteTaskResDto deleteOne(int pid) {
    return taskService.deleteTask(pid);
  }

  @ResponseBody
  @PostMapping("writeTask")
  public WriteTaskResDto writeOne(TaskDto taskDto){
    return taskService.writeTask(taskDto);
  }

}
