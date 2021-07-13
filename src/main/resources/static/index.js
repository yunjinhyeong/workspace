let year;
let month;

function getToday() {
  console.log('getToday1');
  let today = new Date();
  today = today.toISOString().slice(0, 7);
  let bir = document.getElementById('focus_date');
  bir.value = today;
  console.log('getToday2');
}

$(document).ready(function (e) {

  // console.log('window1 다');
  // const tab_switchers = document.querySelectorAll('[data-switcher]');
  // console.log('window2 다');
  // for (let i = 0; i < tab_switchers.length; i++) {
  //   console.log('window3 다');
  //   const tab_switcher = tab_switchers[i];
  //   const page_id = tab_switcher.dataset.tab;
  //   console.log('window4 다');
  //   tab_switcher.addEventListener('click', () => {
  //     console.log('window5 다');
  //     document.querySelector('.tabs .tab.is-active').classList.remove('is-active');
  //     console.log('window6 다');
  //     tab_switcher.parentNode.classList.add('is-active');
  //     console.log('window7 다');
  //     SwitchPage(page_id);
  //     console.log('window8 다');
  //   });
  // }

  console.log('window9 다');
  isWho();
  console.log('window10 다');
  getToday();
  console.log('window11 다');
  getWeek();
  console.log('window12 다');
})

var removeTableSwitch = function () {
  $('th').remove('.swich');
  $('td').remove('.swich');
  $('tr').remove('.swich');
}

var moveMonth = function () {
  console.log('moveMonth 다');
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
  console.log('getWeek1');
  let value = $('#focus_date').val();
  console.log('getWeek2');
  year = value.substring(0, 4);
  console.log('getWeek3');
  month = value.substring(5, 7);
  console.log('getWeek4');
  console.log(year);
  console.log(month);
  console.log(csrf);
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
      console.log(rs);
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

// $('tbody#task_body').on('click', '.task-data', function () {
//
//   if ($.cookie('name') == undefined) {
//     alert('로그인을 하십시오');
//   }
// });

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

// if ($.cookie('name') == undefined) {
//   $('#loginBox').show();
//   $('#logoutBox').hide();
//   $('[name=writeTask]').hide();
// }

function isWho() {
  // if ($.cookie('name') != undefined) {
  //   $('#loginBox').hide();
  //   $('#logoutBox').show();
  //   $('[name=writeTask]').show();
  //   $('span[name=\'name_area\']').text($.cookie('name'));
  //   $('input[name=\'memberPid\']').val(Number($.cookie('member_pid')));
  // }
}

$('[name=logout]').click(function () {
  sessionStorage.removeItem("member");
  // document.cookie = 'name=; Max-Age=-1;';
  // document.cookie = 'member_pid=; Max-Age=-1;';
  // location.reload();
});

// $('[name=submitRoleDepartmentPid]').click(function () {
//   $.ajax({
//     url: '/member/submitRoleDepartmentPid',
//     type: 'POST',
//     dataType: 'json',
//     data: {
//       pid: Number($('[name=insertInputMemberPid]').val()),
//       name: $('[name=insertInputName]').val(),
//       role: $('[name=insertInputRole]').val(),
//       departmentPid: $('[name=insertInputDepartmentPid]').val()
//     },
//     success: function (rs) {
//       if (rs.success) {
//         alert('성공! 다시 로그인 하십시오');
//         $('#insertInput').modal('hide');
//       }
//       if (!rs.success) {
//         alert('실패!');
//       }
//     }
//   });
// });

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
        // loginSuccess(rs.data.member.pid, rs.data.accessToken, rs.data.member);
        alert('성공하였습니다.');
        console.log(rs);
        // $.cookie('member_pid', rs.data.pid, {expires: 1});
        // $.cookie('name', rs.data.name, {expires: 1});
        // isWho(rs.data.pid);
      }
      if (!rs.success) {
        alert('아이디 또는 페스워드가 틀렸습니다.');
        console.log(rs);
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
        // loginSuccess(rs.data.member.pid, rs.data.accessToken, rs.data.member);
        alert('성공');
        console.log(rs);
      }
      if (!rs.success) {
        alert('실패');
        console.log(rs);
      }
    }
  });
});

// function loginSuccess(pid, token, member) {
//
//   $.ajax({
//     url: '/member/isPid',
//     type: 'POST',
//     dataType: 'json',
//     data: {
//       pid: pid
//     },
//     success: function (rs) {
//       if (!rs.isPidDup) {
//         $('#insertInput').modal('show');
//         $('[name=insertInputMemberPid]').val(member.pid);
//         $('[name=insertInputName]').val(member.name);
//       }
//       if (rs.isPidDup) {
//         loginUseDev(token);
//       }
//     }
//   });
// }

function loginUseDev(token) {
  $.ajax({
    url: '/member/loginUseDev',
    type: 'GET',
    dataType: 'json',
    data: {
      token: token
    },
    success: function (rs) {
      if (!rs.success) {
        alert('로그인 실패!');
      }
      if (rs.success) {
        $.cookie('member_pid', rs.data.pid, {expires: 1});
        $.cookie('name', rs.data.name, {expires: 1});
        isWho(rs.data.pid);
      }
    }
  });
}

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
  console.log('버튼클릭 일단 들어옴');

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