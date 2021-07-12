package kr.co.zzimcar.dao;

import kr.co.zzimcar.domain.task.MemberInfoDto;
import kr.co.zzimcar.domain.task.TaskFormDto;
import kr.co.zzimcar.domain.task.TaskDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TaskDao {

  void create(TaskDto taskDto);

  TaskDto retrieveOne(int pid);

  void updateOne(TaskDto taskDto);

  void deleteOne(int pid);

  List<TaskFormDto> taskTestRetrieve(int year, int month);

  List<MemberInfoDto> retrieveMember();

}