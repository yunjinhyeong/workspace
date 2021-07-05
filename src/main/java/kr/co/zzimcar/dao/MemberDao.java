package kr.co.zzimcar.dao;

import kr.co.zzimcar.domain.member.MemberDataResDto;
import kr.co.zzimcar.domain.member.MemberDto;
import kr.co.zzimcar.domain.member.MemberLoginReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberDao {

  void create(MemberDto memberDto);

  int countByPid(int pid);

  String getPassword(String id);

  MemberDataResDto login(MemberLoginReqDto memberLoginReqDto);
}
