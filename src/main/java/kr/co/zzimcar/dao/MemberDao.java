package kr.co.zzimcar.dao;

import kr.co.zzimcar.dto.member.MemberDataDto;
import kr.co.zzimcar.dto.member.MemberDto;
import kr.co.zzimcar.dto.member.MemberLoginDto;
import kr.co.zzimcar.dto.member.MemberReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberDao {
  MemberDto login(MemberReqDto memberReqDto);
}
