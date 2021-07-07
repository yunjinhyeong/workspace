package kr.co.zzimcar.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
  SQL_INTERGRITY_CONSTRAINT_VIOLATION_EXCEPTION("SQL_10000", "회원 ID가 중복됩니다."),

  RETRIEVEONE_FAIL("COMM_10000", "하나 가져오기 데이터가 존재하지 않습니다."),
  NOT_EXIST("COMM_10001", "데이터가 존재하지 않습니다."),

  MEMBER_NOT_EXIST("MEMBER_10000", "등록된 회원이 아닙니다."),
  MEMBER_LOGIN_FAIL("MEMBER_10001", "입력정보가 틀렸습니다."),
  MEMBER_JOIN_NAME_FAIL("MEMBER_10002", "죄송합니다. 가입하실수없습니다."),
  MEMBER_JOIN_ROLE_FAIL("MEMBER_10003", "존재하지 않는 직책입니다."),
  MEMBER_JOIN_DEPARTMENTPID_FAIL("MEMBER_10003", "존재하지 않는 부서입니다."),

  TASK_PRIORITY_SAVE_FAILED("TASK_10000", "업무 우선순위는 HIGHEST, HIGH, LOW, LOWEST 로만 저장됩니다."),
  TASK_STATE_SAVE_FAILED("TASK_10001", "업무 상태는 대기중, 진행중, 지연, 완료 로만 저장됩니다."),
  TASK_DATE_SAVE_FAILED("TASK_10002", "startAt은 dueAt보다 클수없습니다."),

  COMM_PAGING_REQ_PARAM_INVALID_SP("Comm_Error_10001", "sp는 0이상 값이어야 합니다."),
  COMM_PAGING_REQ_PARAM_INVALID_CNT("Comm_Error_10002", "cnt는 0보다 큰 값이어야 합니다."),
  COMM_PAGING_REQ_PARAM_INVALID_SORT("Comm_Error_10003", "sort는 D 또는 A 여야함"),
  COMM_RETRIVE_NOT_EXIST("Comm_Error_10004", "데이터가 존재하지 않습니다."),

  SQL_EXCEPTION("SQL_10000", "데이터 저장/불러오기 실패");

  private final String code;
  private final String message;
}
