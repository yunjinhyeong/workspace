package kr.co.zzimcar.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
  SQL_INTERGRITY_CONSTRAINT_VIOLATION_EXCEPTION("SQL_10000", "없는 회원이라서 등록할 수 없습니다."),

  RETRIEVEONE_FAIL("COMM_10000", "하나 가져오기 데이터가 존재하지 않습니다."),
  NOT_EXIST("COMM_10001", "데이터가 존재하지 않습니다."),

  MEMBER_NOT_EXIST("MEMBER_10000", "등록된 회원이 아닙니다."),
  MEMBER_LOGIN_FAIL("MEMBER_10001", "입력정보가 틀렸습니다."),

  TASK_PRIORITY_SAVE_FAILED("TASK_10000", "업무 우선순위는 HIGHEST, HIGH, LOW, LOWEST 로만 저장됩니다."),
  TASK_STATE_SAVE_FAILED("TASK_10001", "업무 상태는 대기중, 진행중, 지연, 완료 로만 저장됩니다."),

  BLOG_SAVE_FAILED("BLOG_10000", "블로그 저장 실패"),
  BLOG_NOT_FOUND("BLOG_10001", "블로그 정보를 찾을 수 없습니다."),
  BLOG_LIST_FAILED("BLOG_10002", "블로그 목록 불러오기 실패"),
  BLOG_DELETE_FAILED("BLOG_10003", "블로그 삭제 실패"),
  BLOG_NOT_EXIST("BLOG_10004", "블로그가 이미 삭제되었거나, 잘못된 pid번호 입니다."),
  BLOG_UPDATE_FAILED("BLOG_10005", "블로그 수정 실패"),

  BOOK_SAVE_FAILED("BOOK_10000", "책 저장 실패"),
  BOOK_NOT_FOUND("BOOK_10001", "책 정보를 찾을 수 없습니다."),
  BOOK_LIST_FAILED("BOOK_10002", "책 목록 불러오기 실패"),
  BOOK_RETRIVE_NOT_EXIST("BOOK_10003", "데이터가 존재하지 않습니다."),
  BOOK_UPDATE_FAILED("BOOK_10004", "책 수정 실패"),
  BOOK_DELETE_FAILED("BOOK_10005", "책 삭제 실패"),
  BOOK_NOT_EXIST("BOOK_10006", "책이 이미 삭제되었거나, 잘못된 pid번호 입니다."),

  COMM_PAGING_REQ_PARAM_INVALID_SP("Comm_Error_10001", "sp는 0이상 값이어야 합니다."),
  COMM_PAGING_REQ_PARAM_INVALID_CNT("Comm_Error_10002", "cnt는 0보다 큰 값이어야 합니다."),
  COMM_PAGING_REQ_PARAM_INVALID_SORT("Comm_Error_10003", "sort는 D 또는 A 여야함"),
  COMM_RETRIVE_NOT_EXIST("Comm_Error_10004", "데이터가 존재하지 않습니다."),

  SQL_EXCEPTION("SQL_10000", "데이터 저장/불러오기 실패");

  private final String code;
  private final String message;
}
