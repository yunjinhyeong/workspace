package kr.co.zzimcar.serviceImpl.task;

import kr.co.zzimcar.dao.TaskDao;
import kr.co.zzimcar.data.MonthlyTaskMap;
import kr.co.zzimcar.domain.ResponseDto;
import kr.co.zzimcar.domain.task.TaskFormDto;
import kr.co.zzimcar.domain.task.WeekInfoDto;
import kr.co.zzimcar.domain.task.*;
import kr.co.zzimcar.exception.ApiException;
import kr.co.zzimcar.service.task.TaskService;
import kr.co.zzimcar.util.CheckStatePriority;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

import static kr.co.zzimcar.enumeration.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  private final Logger log = LogManager.getLogger(TaskServiceImpl.class);
  private final TaskDao taskDao;

  @Override
  public WeekInfoDto generateMonthlyTaskMap(int year, int month) {
    System.out.println("/11111111111"+year+"////////"+month);
    MonthlyTaskMap monthlyTaskMap = new MonthlyTaskMap(year, month);
    monthlyTaskMap.calcWeeks();
    monthlyTaskMap.setTasks(retrieveMonthlyTasks(year, month));
    monthlyTaskMap.generateWeekTaskList();
    WeekInfoDto weekInfoDto = new WeekInfoDto();
    weekInfoDto.setWeekcount(monthlyTaskMap.getWeeksCnt());
    weekInfoDto.setItems(monthlyTaskMap.getTaskMap());

    return weekInfoDto;
  }

  private List<TaskFormDto> retrieveMonthlyTasks(int year, int month) {
    return taskDao.taskTestRetrieve(year, month);
  }

  @Override
  public ResponseEntity<ResponseDto<Void>> create(TaskReqDto taskReqDto) {
    CheckStatePriority<TaskReqDto> checkClass = new CheckStatePriority<>();
    TaskReqDto checkBowl = new TaskReqDto();
    checkBowl.setPriority(taskReqDto.getPriority());
    checkBowl.setState(taskReqDto.getState());
    checkBowl.setStartAt(taskReqDto.getStartAt());
    checkBowl.setDueAt(taskReqDto.getDueAt());

    checkClass.check(checkBowl);
    taskDao.create(new TaskDto(taskReqDto));
    return ResponseEntity.ok(new ResponseDto<>(true));
  }

  @Override
  public ResponseEntity<ResponseDto<TaskResDto>> retrieveOne(int pid) {
    TaskDto taskDto = Optional.ofNullable(taskDao.retrieveOne(pid)).orElseThrow(() -> new ApiException(RETRIEVEONE_FAIL));
    ResponseDto<TaskResDto> responseDto = new ResponseDto<>(true);
    responseDto.setData(new TaskResDto(taskDto));
    return ResponseEntity.ok(responseDto);
  }

  @Override
  public ResponseEntity<ResponseDto<Void>> updateOne(TaskUpdateReqDto taskUpdateReqDto) {
    CheckStatePriority<TaskUpdateReqDto> checkClass = new CheckStatePriority<>();
    TaskUpdateReqDto checkBowl = new TaskUpdateReqDto();
    checkBowl.setPriority(taskUpdateReqDto.getPriority());
    checkBowl.setState(taskUpdateReqDto.getState());
    checkBowl.setStartAt(taskUpdateReqDto.getStartAt());
    checkBowl.setDueAt(taskUpdateReqDto.getDueAt());
    checkClass.check(checkBowl);
    taskDao.updateOne(new TaskDto(taskUpdateReqDto));
    return ResponseEntity.ok(new ResponseDto<>(true));
  }

  @Override
  public ResponseEntity<ResponseDto<Void>> deleteOne(int pid) {
    Optional.ofNullable(taskDao.retrieveOne(pid)).orElseThrow(() -> new ApiException(NOT_EXIST));
    taskDao.deleteOne(pid);
    return ResponseEntity.ok(new ResponseDto<>(true));
  }

  @Override
  public WriteTaskResDto writeTask(TaskDto taskDto) {
    return WebClient.create("http://localhost:8888")
      .post()
      .uri("/task")
      .bodyValue(taskDto)
      .retrieve()
      .bodyToMono(WriteTaskResDto.class)
      .block();
  }

  @Override
  public ViewTaskResDto viewTask(int pid) {
    return WebClient.create("http://localhost:8888")
      .get()
      .uri("/task/" + pid)
      .retrieve()
      .bodyToMono(ViewTaskResDto.class)
      .block();
  }

  @Override
  public ViewTaskResDto updateTask(TaskDto taskDto) {
    return WebClient.create("http://localhost:8888")
      .put()
      .uri("/task")
      .bodyValue(taskDto)
      .retrieve()
      .bodyToMono(ViewTaskResDto.class)
      .block();
  }

  @Override
  public WriteTaskResDto deleteTask(int pid) {
    return WebClient.create("http://localhost:8888")
      .delete()
      .uri("/task/" + pid)
      .retrieve()
      .bodyToMono(WriteTaskResDto.class)
      .block();
  }

}
