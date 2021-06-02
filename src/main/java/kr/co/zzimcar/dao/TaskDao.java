package kr.co.zzimcar.dao;

import kr.co.zzimcar.dto.task.MemberJoinDto;
import kr.co.zzimcar.dto.task.TaskDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TaskDao {
  void create(TaskDto taskDto);
  TaskDto retrieveOne(int pid);
  List<TaskDto> retrieveAll();
  int totalCnt();
  void updateOne(int pid, TaskDto taskDto);
  void deleteOne(int pid);
  MemberJoinDto retrieveJoinAll(int pid);
}
