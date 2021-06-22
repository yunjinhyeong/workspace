package kr.co.zzimcar.controller.API;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.co.zzimcar.domain.ResponseDto;

import kr.co.zzimcar.domain.task.TaskReqDto;
import kr.co.zzimcar.domain.task.TaskResDto;
import kr.co.zzimcar.domain.task.TaskUpdateReqDto;
import kr.co.zzimcar.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Api(tags = "업무현황 API")
public class TaskAPIController {

  private final TaskService taskService;

  @PostMapping("")
  @ApiOperation("업무 현황 등록 API")
  public ResponseEntity<ResponseDto<Void>> create(@RequestBody @Valid @ApiParam(value = "등록할 업무 현황", required = true) TaskReqDto taskReqDto) {
    return taskService.create(taskReqDto);
  }

  @GetMapping("/{pid}")
  @ApiOperation("업무내역 하나 조회 API")
  public ResponseEntity<ResponseDto<TaskResDto>> retrieveOne(@PathVariable @Valid @ApiParam(value = "업무내역 번호", required = true, example = "1") int pid) {
    return taskService.retrieveOne(pid);
  }

  @PutMapping("")
  @ApiOperation("업무 하나 수정 API")
  public ResponseEntity<ResponseDto<Void>> updateOne(@RequestBody @Valid @ApiParam(value = "수정할 업무 정보", required = true) TaskUpdateReqDto taskUpdateReqDto) {
    return taskService.updateOne(taskUpdateReqDto);
  }

  @DeleteMapping("/{pid}")
  @ApiOperation("업무 삭제 API")
  public ResponseEntity<ResponseDto<Void>> deleteOne(@PathVariable @ApiParam(value = "삭제할 업무 번호", required = true, example = "1") int pid) {
    return taskService.deleteOne(pid);
  }

}
