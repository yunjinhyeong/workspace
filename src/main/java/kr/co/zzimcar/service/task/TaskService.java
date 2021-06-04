package kr.co.zzimcar.service.task;

import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.dto.page.DrawWeekWorkDto;
import kr.co.zzimcar.dto.page.WeekInfoDto;
import kr.co.zzimcar.dto.task.MemberJoinDto;
import kr.co.zzimcar.dto.task.TaskListResDto;
import kr.co.zzimcar.dto.task.TaskReqDto;
import kr.co.zzimcar.dto.task.TaskResDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
  ResponseEntity<ResponseDto<Void>> create(TaskReqDto taskReqDto);
  ResponseEntity<ResponseDto<TaskResDto>> retrieveOne(int pid);
  ResponseEntity<ResponseDto<TaskListResDto>> retrieveAll();
  ResponseEntity<ResponseDto<Void>> updateOne(int pid, TaskReqDto taskReqDto);
  ResponseEntity<ResponseDto<Void>> deleteOne(int pid);
  ResponseEntity<ResponseDto<MemberJoinDto>> retrieveJoinAll(int pid);
  List<DrawWeekWorkDto> weekDate();
}