package kr.co.zzimcar.service.task;

import kr.co.zzimcar.domain.ResponseDto;
import kr.co.zzimcar.domain.page.DrawWeekWorkDto;
import kr.co.zzimcar.domain.page.WeekInfoDto;
import kr.co.zzimcar.domain.task.*;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
  WeekInfoDto generateMonthlyTaskMap(int year, int month);

  ResponseEntity<ResponseDto<Void>> create(TaskReqDto taskReqDto);
  ResponseEntity<ResponseDto<TaskResDto>> retrieveOne(int pid);
//  ResponseEntity<ResponseDto<TaskListResDto>> retrieveAll();
  ResponseEntity<ResponseDto<Void>> updateOne(TaskUpdateReqDto taskUpdateReqDto);
  ResponseEntity<ResponseDto<Void>> deleteOne(int pid);
  ResponseEntity<ResponseDto<MemberJoinDto>> retrieveJoinAll(int pid);
  List<DrawWeekWorkDto> weekDate();
  List<String> department();
  List<String> name();

  ///////// 만들어진 API 이용 /////////
  WriteTaskResDto writeTask(TaskDto taskDto);
  ViewTaskResDto viewTask(int pid);
  ViewTaskResDto updateTask(TaskDto taskDto);
  WriteTaskResDto deleteTask(int pid);
}