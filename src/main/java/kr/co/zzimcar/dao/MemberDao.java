package kr.co.zzimcar.dao;

import kr.co.zzimcar.domain.member.MemberDto;
import kr.co.zzimcar.domain.member.TestLoginMember;
import kr.co.zzimcar.domain.member.TestMember;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberDao {

  void create(MemberDto memberDto);

  int countByPid(int pid);

  void testjoinmember(TestMember member);

  String getapw(String id);

  TestMember testlogin(TestLoginMember testLoginMember);
}
