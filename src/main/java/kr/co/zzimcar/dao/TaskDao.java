package kr.co.zzimcar.dao;

import kr.co.zzimcar.domain.TaskTestForm;
import kr.co.zzimcar.domain.ViewContentDto;
import kr.co.zzimcar.domain.department.DepartmentDto;
import kr.co.zzimcar.domain.member.MemberDto;
import kr.co.zzimcar.domain.page.DrawWeekWorkDto;
import kr.co.zzimcar.domain.task.MemberJoinDto;
import kr.co.zzimcar.domain.task.TaskDto;
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
  void updateOne(TaskDto taskDto);
  void deleteOne(int pid);
  MemberJoinDto retrieveJoinAll(int pid);
  List<DrawWeekWorkDto> allDateInfo();
  List<String> departmentList();
  List<String> nameList();

  List<TaskDto> retrieveTasks();

  List<TaskTestForm> taskTestRetrieve(int year, int month);
  List<MemberDto> memberInfo();
  List<DepartmentDto> departmentInfo();

  ViewContentDto viewContent(int pid);
  void updateTask(ViewContentDto viewContentDto);
  int retrieveMemberPid(String name);
}