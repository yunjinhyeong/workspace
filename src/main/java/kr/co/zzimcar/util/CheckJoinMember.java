package kr.co.zzimcar.util;

import kr.co.zzimcar.domain.member.MemberReqDto;
import kr.co.zzimcar.enumeration.Name;
import kr.co.zzimcar.enumeration.Priority;
import kr.co.zzimcar.enumeration.Role;
import kr.co.zzimcar.exception.ApiException;

import static kr.co.zzimcar.enumeration.ResponseCode.*;
import static kr.co.zzimcar.enumeration.ResponseCode.TASK_PRIORITY_SAVE_FAILED;

public class CheckJoinMember {

  boolean check = false;

  public void check(MemberReqDto memberReqDto) {

    for (Name name : Name.values()) {
      if (memberReqDto.getName().equals(name.toString())) {
        check = true;
        break;
      }
    }
    if (!check) throw new ApiException(MEMBER_JOIN_NAME_FAIL);

    check = false;
    for (Role role : Role.values()) {
      if (memberReqDto.getRole().equals(role.toString())) {
        check = true;
        break;
      }
    }
    if (!check) throw new ApiException(MEMBER_JOIN_ROLE_FAIL);

    check = false;
    int[] departmentPid = {1, 2};
    for (int i = 0 ; i<departmentPid.length ; i++) {
      if (memberReqDto.getDepartmentPid() == departmentPid[i]) {
        check = true;
        break;
      }
    }
    if (!check) throw new ApiException(MEMBER_JOIN_DEPARTMENTPID_FAIL);
  }
}
