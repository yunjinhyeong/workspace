let year;
let month;

function getToday() {
  let today = new Date();
  today = today.toISOString().slice(0, 7);
  let bir = document.getElementById('focus_date');
  bir.value = today;
}

$(document).ready(function () {
  getToday();
  getWeek();
})

var removeTableSwitch = function () {
  $('th').remove('.swich');
  $('td').remove('.swich');
  $('tr').remove('.swich');
}

var moveMonth = function () {
  removeTableSwitch();

  let selectedDate = new Date($('#focus_date').val());
  selectedDate.setMonth(selectedDate.getMonth() + $(this).data('value'));

  year = selectedDate.getFullYear();
  month = ((selectedDate.getMonth() + 1) > 9) ? selectedDate.getMonth() + 1 : '0' + (selectedDate.getMonth() + 1);
  $('#focus_date').val(year + '-' + month);

  $.ajax({
    url: '/week/weekly',
    type: 'POST',
    dataType: 'json',
    data: {
      year: year,
      month: month
    },
    beforeSend:function (xhr) {
      xhr.setRequestHeader(csrf.headerName, csrf.token);
    },
    success: function (rs) {
      drawWeekly(rs.weekcount, rs.items, rs.weekstartduepoint);
    }
  });
}

$('.jump_month_minus').click(moveMonth);
$('.jump_month_plus').click(moveMonth);

function getWeek() {
  let value = $('#focus_date').val();
  year = value.substring(0, 4);
  month = value.substring(5, 7);
  $.ajax({
    url: '/week/weekly',
    type: 'POST',
    dataType: 'json',
    data: {
      year: year,
      month: month
    },
    beforeSend:function (xhr) {
      xhr.setRequestHeader(csrf.headerName, csrf.token);
    },
    success: function (rs) {
      drawWeekly(rs.weekcount, rs.items, rs.weekstartduepoint);
    }
  });
}

function drawWeekly(count, list, weekstartduepoint) {

  let str = '';
  for (let num = 0, weekDateCnt = 0; num < count; num++, weekDateCnt=weekDateCnt+2) {
    str += `
      <th class="align-middle swich">
        ${num+1}주차<br/>
        ${weekstartduepoint[weekDateCnt]} ~ ${weekstartduepoint[weekDateCnt+1]}                
      </th>
			`;
  }
  $('#weekly').append(str);

  let row = '';

  list.departmentList.forEach(weeklyTasks => {

    let weekly = '';

    for (let i = 1; i <= count; i++) {
      weekly += `<td> <ul class="main">`
      weeklyTasks.memberTasks[0]['weekly' + i].forEach(task => {
        var bgColor = selectBgColor(task.state);
        weekly += `
                  <li>
                    <input class="task-data" name="viewTask" style="background-color: ${bgColor};" type="button" class="btn" data-toggle="modal" data-target="#viewTaskModal" data-whatever="@mdo" data-pid="${task.pid}" data-member_id="${weeklyTasks.memberTasks[0].memberId}" value="${task.title}">                      
                  </li>                
                `;
      });
      weekly += `</ul></td>`
    }

    row += `
      <tr class="swich">
        <td scope="row" rowspan="${weeklyTasks.memberTasks.length}" class="align-middle text-center" style="width: 100px">${weeklyTasks.departmentName}</td>
          <td class="align-middle" style="width: 100px">
            <ul class="main" style="margin:auto;">
              <li style="background-color:#fff;">
                <div>${weeklyTasks.memberTasks[0].name}</div>                
              </li>
            </ul>
            ${weekly}
          </td>
      </tr>
    `;

    for (let idx = 1; idx < weeklyTasks.memberTasks.length; idx++) {
      let weekly = '';

      for (let i = 1; i <= count; i++) {
        weekly += `<td><ul class="main">`
        weeklyTasks.memberTasks[idx]['weekly' + i].forEach(task => {
          var bgColor = selectBgColor(task.state);
          weekly += `
                  <li>                    
                    <input class="task-data" name="viewTask" style="background-color: ${bgColor};" type="button" class="btn" data-toggle="modal" data-target="#viewTaskModal" data-whatever="@mdo" data-pid="${task.pid}" data-member_id="${weeklyTasks.memberTasks[idx].memberId}" value="${task.title}">                       
                  </li>
                `;
        });
        weekly += `</ul></td>`
      }

      row += `
        <tr class="swich">
          <td class="align-middle text-center">
            <ul class="main" style="margin:auto;">
              <li style="background-color:#fff;" class="task-data">
                <div class="member_name">${weeklyTasks.memberTasks[idx].name}</div>            
              </li>
            </ul>
          </td>
          ${weekly}
        </tr>
      `;
    }
  });
  if (row=='') {
    row += `<tr class="swich">
              <td rowspan="${count+2}">등록된 업무가 없습니다.</td>
            </tr>`
  }
  console.log(row);
  $('#task_body').append(row);
}

$('tbody#task_body').on('click', 'input[name=deleteTask]', function (e) {
  e.preventDefault();

  let pid = $(e.target).data('pid');
  let member_pid = $(e.target).data('member_pid');
  if (member_pid != $.cookie('member_pid')) {
    alert('권한이 없습니다.');
    return;
  }

  let confirmAlert = confirm('정말 삭제하시겠습니까?');
  if (!confirmAlert) {
    return;
  }

  $.ajax({
    url: '/week/deleteTask',
    type: 'DELETE',
    dataType: 'json',
    data: {
      pid: pid
    },
    success: function (rs) {
      if (!rs.success) {
        alert(rs.msg);
        return;
      }
      if (rs.success) {
        alert('삭제되었습니다.');
        newdraw();
      }
    }
  });
});


$('[name=deleteTask]').click(function () {

  let pid = $('[name=viewPid]').val();
  if ($.cookie('member_pid_temporary') != $.cookie('member_pid')) {
    alert('권한이 없습니다.');
    return;
  }

  let confirmAlert = confirm('정말 삭제하시겠습니까?');
  if (!confirmAlert) {
    return;
  }

  $.ajax({
    url: '/week/deleteTask',
    type: 'DELETE',
    dataType: 'json',
    data: {
      pid: pid
    },
    success: function (rs) {
      if (!rs.success) {
        alert(rs.msg);
        return;
      }
      if (rs.success) {
        alert('삭제되었습니다.');
        $('#viewTaskModal').modal('hide');
        newdraw();
      }
    }
  });
});

$('tbody#task_body').on('click', 'input[name=viewTask]', function (e) {
  e.preventDefault();

  let pid = $(e.target).data('pid');
  let memberPid = $(e.target).data('member_pid');
  $('[name=viewPid]').val(pid);

  $.ajax({
    url: '/week/viewTask',
    type: 'GET',
    dataType: 'json',
    data: {
      pid: pid
    },
    success: function (rs) {
      if (!rs.success) {
        alert(rs.msg);
        return;
      }
      if (rs.success) {
        $('[name=viewTitle]').val(rs.data.title);
        $('[name=viewContent]').val(rs.data.content);
        $('[name=viewStartAt]').val(rs.data.startAt);
        $('[name=viewDueAt]').val(rs.data.dueAt);
        $('[name=viewState]').val(rs.data.state);
        $('[name=viewPriority]').val(rs.data.priority);

        $.cookie('member_pid_temporary', memberPid, {expires: 1});
      }
    }
  });
});

$('button[name=\'updateTaskPage\']').on('click', function (e) {

  if ($.cookie('member_pid_temporary') != $.cookie('member_pid')) {
    alert('권한이 없습니다.');
    return;
  }

  let pid = $('[name=viewPid]').val();
  let title = $('[name=viewTitle]').val();
  let content = $('[name=viewContent]').val();
  let startAt = $('[name=viewStartAt]').val();
  let dueAt = $('[name=viewDueAt]').val();
  let state = $('[name=viewState]').val();
  let priority = $('[name=viewPriority]').val();

  if (startAt > dueAt) {
    alert('시작일이 종료일보다 큽니다.');
    return;
  }

  if (title.length >= 30) {
    alert('제목은 30글자 이하로 입력하여 주십시오.');
    return;
  }

  $.ajax({
    url: '/week/updateTask',
    type: 'PUT',
    dataType: 'json',
    data: {
      pid: pid,
      title: title,
      content: content,
      startAt: startAt,
      dueAt: dueAt,
      state: state,
      priority: priority
    },
    success: function (rs) {
      if (!rs.success) {
        alert(rs.msg);
        return;
      }
      if (rs.success) {
        alert('수정성공했습니다.');
        newdraw();
      }
    }
  });

});
$('tbody#task_body').on('click', '.main>li', function () {
  $(this).children('.sub').stop().slideDown();
});
$('tbody#task_body').on('mouseleave', '.main>li', function () {
  $(this).children('.sub').stop().slideUp();
});

function changeDate() {
  $('th').remove('.swich');
  $('td').remove('.swich');
  $('tr').remove('.swich');
  getWeek();
}

function selectBgColor(state) {

  if (state == '대기중') return '#ffffcc';
  if (state == '진행중') return '#ccccff';
  if (state == '지연') return '#ffc6b3';
  if (state == '완료') return '#ccffdc';
}

$('[name=login]').click(function () {
  let id = $('[name=id]').val();
  let password = $('[name=pw]').val();

  $.ajax({
    url: '/member/login',
    type: 'POST',
    dataType: 'json',
    data: {
      id: id,
      password: password
    },
    success: function (rs) {
      if (rs.success) {
        alert('성공하였습니다.');
      }
      if (!rs.success) {
        alert('아이디 또는 페스워드가 틀렸습니다.');
      }
    }
  });
});

$('[name=joinMemberSubmit]').click(function () {
  let id = $('[name=joinId]').val();
  let name = $('[name=joinName]').val();
  let password = $('[name=joinPw]').val();
  let departmentPid = $('[name=joinDepartmentPid]').val();
  let role = $('[name=joinRole]').val();

  $.ajax({
    url: '/member/join',
    type: 'POST',
    dataType: 'json',
    data: {
      id: id,
      name: name,
      password: password,
      departmentPid: departmentPid,
      role: role
    },
    success: function (rs) {
      if (rs.success) {
        alert('성공');
      }
      if (!rs.success) {
        alert('실패');
      }
    }
  });
});

document.getElementById('startAt').value = getStartAt();
document.getElementById('dueAt').value = getDueAt();

function getStartAt() {
  return new Date().toISOString().substring(0, 10);
}

function getDueAt() {
  let writeDueAt = new Date();
  writeDueAt.setDate(writeDueAt.getDate() + 1);
  writeDueAt = writeDueAt.toISOString().slice(0, 10);
  return writeDueAt;
}

$('[name=writeTaskSubmit]').click(function () {
  let title = $('[name=title]').val();
  let content = $('[name=content]').val();
  let startAt = $('[name=startAt]').val();
  let dueAt = $('[name=dueAt]').val();
  let state = $('[name=state]').val();
  let priority = $('[name=priority]').val();

  if (startAt > dueAt) {
    alert('시작일이 종료일보다 큽니다.');
    return;
  }

  $.ajax({
    url: '/week/writeTask',
    type: 'POST',
    dataType: 'json',
    data: {
      memberId: id,
      title: title,
      content: content,
      startAt: startAt,
      dueAt: dueAt,
      state: state,
      priority: priority
    },
    beforeSend:function (xhr) {
      xhr.setRequestHeader(csrf.headerName, csrf.token);
    },
    success: function (rs) {
      if (!rs.success) {
        alert(rs.msg);
        return;
      }
      if (rs.success) {
        alert('업무등록성공!!');
        $('#writeTaskModal').modal("hide");
        resetData();
        newdraw();
      }
    }
  });
});

function resetData() {
  $(".modal-body input[type=text]").val("");
  document.getElementById('startAt').value = getStartAt();
  document.getElementById('dueAt').value = getDueAt();
  $(".modal-body textarea").val("");
  $('.modal-body select[name=state]').val("대기중");
  $('.modal-body select[name=priority]').val("HIGHEST");

}

function newdraw() {
  $('th').remove('.swich');
  $('td').remove('.swich');
  $('tr').remove('.swich');
  getWeek();
}