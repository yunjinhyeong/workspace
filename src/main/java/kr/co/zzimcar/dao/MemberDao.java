package kr.co.zzimcar.dao;

import kr.co.zzimcar.domain.member.MemberDataResDto;
import kr.co.zzimcar.domain.member.MemberDto;
import kr.co.zzimcar.domain.member.MemberLoginReqDto;
import kr.co.zzimcar.domain.member.MemberReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface MemberDao {

  void create(MemberReqDto memberReqDto);

  Optional<MemberDto> findById(String id);

  int countByPid(int pid);

  String getPassword(String id);

  MemberDataResDto login(MemberLoginReqDto memberLoginReqDto);
}
