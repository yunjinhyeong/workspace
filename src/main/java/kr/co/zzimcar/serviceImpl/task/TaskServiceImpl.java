package kr.co.zzimcar.serviceImpl.task;

import kr.co.zzimcar.dao.TaskDao;
import kr.co.zzimcar.data.MonthlyTaskMap;
import kr.co.zzimcar.domain.ResponseDto;
import kr.co.zzimcar.domain.task.TaskFormDto;
import kr.co.zzimcar.domain.task.WeekInfoDto;
import kr.co.zzimcar.domain.task.*;
import kr.co.zzimcar.enumeration.Priority;
import kr.co.zzimcar.enumeration.State;
import kr.co.zzimcar.enumeration.Type;
import kr.co.zzimcar.exception.ApiException;
import kr.co.zzimcar.service.task.TaskService;
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
    MonthlyTaskMap monthlyTaskMap = new MonthlyTaskMap(year, month);
    // 1. 받아온 연월로 총 몇주가 있는지 계산
    monthlyTaskMap.calcWeeks();
    // 2. 연월에 맞는 task 데이터 가져오기
    monthlyTaskMap.setTasks(retrieveMonthlyTasks(year, month));
    // 5. 주차 기준 리스트 생성
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
    checkCreate(taskReqDto);
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
    checkUpdate(taskUpdateReqDto);
    taskDao.updateOne(new TaskDto(taskUpdateReqDto));
    return ResponseEntity.ok(new ResponseDto<>(true));
  }

  @Override
  public ResponseEntity<ResponseDto<Void>> deleteOne(int pid) {
    Optional.ofNullable(taskDao.retrieveOne(pid)).orElseThrow(() -> new ApiException(NOT_EXIST));
    taskDao.deleteOne(pid);
    return ResponseEntity.ok(new ResponseDto<>(true));
  }

  // 만들어진 API 이용
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

  // 만들어진 API 이용
  @Override
  public ViewTaskResDto viewTask(int pid) {
    return WebClient.create("http://localhost:8888")
      .get()
      .uri("/task/" + pid)
      .retrieve()
      .bodyToMono(ViewTaskResDto.class)
      .block();
  }

  // API도 가져와야되고 음 그려
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

  private void checkCreate(TaskReqDto taskReqDto) {
    boolean check = false;
    for (Priority priority : Priority.values()) {
      if (taskReqDto.getPriority().equals(priority.toString())) {
        check = true;
        break;
      }
    }
    if (!check) throw new ApiException(TASK_PRIORITY_SAVE_FAILED);
    check = false;
    for (State state : State.values()) {
      if (taskReqDto.getState().equals(state.toString())) {
        check = true;
        break;
      }
    }
    if (!check) throw new ApiException(TASK_STATE_SAVE_FAILED);
    check = false;
    for (Type type : Type.values()) {
      if (taskReqDto.getType().equals(type.toString())) {
        check = true;
        break;
      }
    }

    if (!check) throw new ApiException(TASK_TYPE_SAVE_FAILED);
  }

  private void checkUpdate(TaskUpdateReqDto taskUpdateReqDto) {
    boolean check = false;
    for (Priority priority : Priority.values()) {
      if (taskUpdateReqDto.getPriority().equals(priority.toString())) {
        check = true;
        break;
      }
    }
    if (!check) throw new ApiException(TASK_PRIORITY_SAVE_FAILED);
    check = false;
    for (State state : State.values()) {
      if (taskUpdateReqDto.getState().equals(state.toString())) {
        check = true;
        break;
      }
    }
    if (!check) throw new ApiException(TASK_STATE_SAVE_FAILED);

  }

}
