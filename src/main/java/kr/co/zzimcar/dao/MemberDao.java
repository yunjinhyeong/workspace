package kr.co.zzimcar.dao;

import kr.co.zzimcar.domain.member.MemberDto;
import kr.co.zzimcar.domain.member.MemberReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberDao {
  MemberDto login(MemberReqDto memberReqDto);
}
