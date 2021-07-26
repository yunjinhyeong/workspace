package kr.co.zzimcar.dao;

import kr.co.zzimcar.atest.Account;
import kr.co.zzimcar.domain.member.MemberDataResDto;
import kr.co.zzimcar.domain.member.MemberLoginReqDto;
import kr.co.zzimcar.domain.member.MemberReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberDao {

  void create(MemberReqDto memberReqDto);

  int getCountById(String id);

  String getPassword(String id);

  MemberDataResDto login(MemberLoginReqDto memberLoginReqDto);

  //////////////////////////////
  Account findById(String username);

}
