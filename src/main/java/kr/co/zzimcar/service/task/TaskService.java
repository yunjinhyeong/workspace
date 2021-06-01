package kr.co.zzimcar.service.task;

import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.dto.task.MemberDto;
import kr.co.zzimcar.dto.task.TaskListResDto;
import kr.co.zzimcar.dto.task.TaskReqDto;
import kr.co.zzimcar.dto.task.TaskResDto;
import org.springframework.http.ResponseEntity;

public interface TaskService {
  ResponseEntity<ResponseDto<Void>> create(TaskReqDto taskReqDto);
  ResponseEntity<ResponseDto<TaskResDto>> retrieveOne(int pid);
  ResponseEntity<ResponseDto<TaskListResDto>> retrieveAll();
  ResponseEntity<ResponseDto<Void>> updateOne(int pid, TaskReqDto taskReqDto);
  ResponseEntity<ResponseDto<Void>> deleteOne(int pid);
  ResponseEntity<ResponseDto<MemberDto>> retrieveJoinAll(int pid);
}