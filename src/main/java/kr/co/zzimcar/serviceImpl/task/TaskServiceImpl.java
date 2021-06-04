package kr.co.zzimcar.serviceImpl.task;

import kr.co.zzimcar.dao.TaskDao;
import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.dto.page.DrawWeekWorkDto;
import kr.co.zzimcar.dto.page.WeekInfoDto;
import kr.co.zzimcar.dto.task.MemberJoinDto;
import kr.co.zzimcar.dto.task.*;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kr.co.zzimcar.enumeration.ResponseCode.*;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  private final Logger log = LogManager.getLogger(TaskServiceImpl.class);
  private final TaskDao taskDao;

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
  public ResponseEntity<ResponseDto<TaskListResDto>> retrieveAll() {
    TaskListResDto taskListResDto = new TaskListResDto();
    taskListResDto.setTotalCnt(taskDao.totalCnt());
    taskListResDto.setList(taskDao.retrieveAll().stream().map(TaskResDto::new).collect(Collectors.toList()));
    ResponseDto<TaskListResDto> responseDto = new ResponseDto<>(true);
    responseDto.setData(taskListResDto);
    return ResponseEntity.ok(responseDto);
  }

  @Override
  public ResponseEntity<ResponseDto<Void>> updateOne(int pid, TaskReqDto taskReqDto) {
    Optional.ofNullable(taskDao.retrieveOne(pid)).orElseThrow(() -> new ApiException(NOT_EXIST));
    checkCreate(taskReqDto);
    taskDao.updateOne(pid, new TaskDto(taskReqDto));
    return ResponseEntity.ok(new ResponseDto<>(true));
  }

  @Override
  public ResponseEntity<ResponseDto<Void>> deleteOne(int pid) {
    Optional.ofNullable(taskDao.retrieveOne(pid)).orElseThrow(() -> new ApiException(NOT_EXIST));
    taskDao.deleteOne(pid);
    return ResponseEntity.ok(new ResponseDto<>(true));
  }

  @Override
  public ResponseEntity<ResponseDto<MemberJoinDto>> retrieveJoinAll(int pid) {
    System.out.println("서비스1");
    MemberJoinDto memberJoinDto = Optional.ofNullable(taskDao.retrieveJoinAll(pid)).orElseThrow(() -> new ApiException(MEMBER_NOT_EXIST));
    System.out.println("서비스2"+memberJoinDto);
    ResponseDto<MemberJoinDto> responseDto = new ResponseDto<>(true);
    System.out.println("서비스3"+responseDto);
    responseDto.setData(memberJoinDto);
    System.out.println("서비스4"+responseDto);
    return ResponseEntity.ok(responseDto);
  }

  @Override
  public List<DrawWeekWorkDto> weekDate() {
    List<DrawWeekWorkDto> list = taskDao.allDateInfo();
    System.out.println("weekInfoDto>>>>> 서비스인플 >>>>>>"+list);
    return list;
  }


  private void checkCreate(TaskReqDto taskReqDto) {
    boolean check=false;
    for(Priority priority : Priority.values()) {
      if (taskReqDto.getPriority().equals(priority.toString())) {
        check=true;
        break;
      }
    }
    if (!check) throw new ApiException(TASK_PRIORITY_SAVE_FAILED);
    check=false;
    for(State state : State.values()) {
      if (taskReqDto.getState().equals(state.toString())) {
        check=true;
        break;
      }
    }
    if (!check) throw new ApiException(TASK_STATE_SAVE_FAILED);
    check=false;
    for(Type type : Type.values()) {
      if (taskReqDto.getType().equals(type.toString())) {
        check=true;
        break;
      }
    }
    if (!check) throw new ApiException(TASK_TYPE_SAVE_FAILED);

  }

}
