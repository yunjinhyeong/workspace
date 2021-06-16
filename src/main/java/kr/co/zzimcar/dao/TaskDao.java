package kr.co.zzimcar.dao;

import kr.co.zzimcar.dto.TaskTestForm;
import kr.co.zzimcar.dto.ViewContentDto;
import kr.co.zzimcar.dto.department.DepartmentDto;
import kr.co.zzimcar.dto.member.MemberDto;
import kr.co.zzimcar.dto.page.DrawWeekWorkDto;
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
  List<DrawWeekWorkDto> allDateInfo();
  List<String> departmentList();
  List<String> nameList();

  List<TaskDto> retrieveTasks();

  List<TaskTestForm> tasktestretrieve(String startday, String dueday);
  List<MemberDto> memberInfo();
  List<DepartmentDto> departmentInfo();

  ViewContentDto viewContent(int pid);
  void updateTask(ViewContentDto viewContentDto);
  int retrieveMemberPid(String name);
}