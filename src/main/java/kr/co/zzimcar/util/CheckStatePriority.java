package kr.co.zzimcar.util;

import kr.co.zzimcar.domain.CheckBowl;
import kr.co.zzimcar.domain.task.TaskReqDto;
import kr.co.zzimcar.enumeration.Priority;
import kr.co.zzimcar.enumeration.State;
import kr.co.zzimcar.exception.ApiException;

import java.util.Date;

import static kr.co.zzimcar.enumeration.ResponseCode.*;

public class CheckStatePriority<T extends CheckBowl> {

  boolean check = false;

  public void check(T obj) {

    Date startAt = obj.getStartAt();
    Date dueAt = obj.getDueAt();

    if (startAt.after(dueAt)) throw new ApiException(TASK_DATE_SAVE_FAILED);

    for (Priority priority : Priority.values()) {
      if (obj.getPriority().equals(priority.toString())) {
        check = true;
        break;
      }
    }
    if (!check) throw new ApiException(TASK_PRIORITY_SAVE_FAILED);

    check = false;
    for (State state : State.values()) {
      if (obj.getState().equals(state.toString())) {
        check = true;
        break;
      }
    }
    if (!check) throw new ApiException(TASK_STATE_SAVE_FAILED);
  }

}
