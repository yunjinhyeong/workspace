package kr.co.zzimcar.service.task;

import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.dto.task.TaskReqDto;
import org.springframework.http.ResponseEntity;

public interface TaskService {
  ResponseEntity<ResponseDto<Void>> create(TaskReqDto taskReqDto);
}
