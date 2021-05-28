package kr.co.zzimcar.serviceImpl.task;

import kr.co.zzimcar.dto.ResponseDto;
import kr.co.zzimcar.dto.task.TaskReqDto;
import kr.co.zzimcar.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  @Override
  public ResponseEntity<ResponseDto<Void>> create(TaskReqDto taskReqDto) {
    return null;
  }
}
