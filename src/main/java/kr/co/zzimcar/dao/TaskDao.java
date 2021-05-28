package kr.co.zzimcar.dao;

import kr.co.zzimcar.dto.task.TaskDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TaskDao {
  void create(TaskDto taskDto);
}
