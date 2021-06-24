package kr.co.zzimcar.service.task;

import kr.co.zzimcar.domain.ResponseDto;
import kr.co.zzimcar.domain.task.WeekInfoDto;
import kr.co.zzimcar.domain.task.*;

import org.springframework.http.ResponseEntity;

public interface TaskService {
  WeekInfoDto generateMonthlyTaskMap(int year, int month);

  ResponseEntity<ResponseDto<Void>> create(TaskReqDto taskReqDto);

  ResponseEntity<ResponseDto<TaskResDto>> retrieveOne(int pid);

  ResponseEntity<ResponseDto<Void>> updateOne(TaskUpdateReqDto taskUpdateReqDto);

  ResponseEntity<ResponseDto<Void>> deleteOne(int pid);

  WriteTaskResDto writeTask(TaskDto taskDto);

  ViewTaskResDto viewTask(int pid);

  ViewTaskResDto updateTask(TaskDto taskDto);

  WriteTaskResDto deleteTask(int pid);
}