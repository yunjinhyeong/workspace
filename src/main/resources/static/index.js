let year;
let month;

function getToday() {

  let today = new Date();
  today = today.toISOString().slice(0, 7);
  let bir = document.getElementById("focus_date");
  bir.value = today;
}

function get_date_str(date) {

  let sYear = date.getFullYear();
  let sMonth = date.getMonth() + 1;

  sMonth = sMonth > 9 ? sMonth : "0" + sMonth;
  return sYear +'-'+ sMonth;
}

window.onload = () => {

  const tab_switchers = document.querySelectorAll('[data-switcher]');

  console.log(
    '%c%s',
    'color: #22A8A5; font-size: 5px;',
    `
███╗░░██╗░█████╗░████████╗██╗░░░██╗██████╗░███████╗  ███╗░░░███╗░█████╗░██████╗░██╗██╗░░░░░██╗████████╗██╗░░░██╗
████╗░██║██╔══██╗╚══██╔══╝██║░░░██║██╔══██╗██╔════╝  ████╗░████║██╔══██╗██╔══██╗██║██║░░░░░██║╚══██╔══╝╚██╗░██╔╝
██╔██╗██║███████║░░░██║░░░██║░░░██║██████╔╝█████╗░░  ██╔████╔██║██║░░██║██████╦╝██║██║░░░░░██║░░░██║░░░░╚████╔╝░
██║╚████║██╔══██║░░░██║░░░██║░░░██║██╔══██╗██╔══╝░░  ██║╚██╔╝██║██║░░██║██╔══██╗██║██║░░░░░██║░░░██║░░░░░╚██╔╝░░
██║░╚███║██║░░██║░░░██║░░░╚██████╔╝██║░░██║███████╗  ██║░╚═╝░██║╚█████╔╝██████╦╝██║███████╗██║░░░██║░░░░░░██║░░░
╚═╝░░╚══╝╚═╝░░╚═╝░░░╚═╝░░░░╚═════╝░╚═╝░░╚═╝╚══════╝  ╚═╝░░░░░╚═╝░╚════╝░╚═════╝░╚═╝╚══════╝╚═╝░░░╚═╝░░░░░░╚═╝░░░

░██╗░░░░░░░██╗░█████╗░██████╗░██╗░░██╗░██████╗██████╗░░█████╗░░█████╗░███████╗
░██║░░██╗░░██║██╔══██╗██╔══██╗██║░██╔╝██╔════╝██╔══██╗██╔══██╗██╔══██╗██╔════╝
░╚██╗████╗██╔╝██║░░██║██████╔╝█████═╝░╚█████╗░██████╔╝███████║██║░░╚═╝█████╗░░
░░████╔═████║░██║░░██║██╔══██╗██╔═██╗░░╚═══██╗██╔═══╝░██╔══██║██║░░██╗██╔══╝░░
░░╚██╔╝░╚██╔╝░╚█████╔╝██║░░██║██║░╚██╗██████╔╝██║░░░░░██║░░██║╚█████╔╝███████╗
░░░╚═╝░░░╚═╝░░░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝╚═════╝░╚═╝░░░░░╚═╝░░╚═╝░╚════╝░╚══════╝`
  );

  for (let i = 0; i < tab_switchers.length; i++) {

    const tab_switcher = tab_switchers[i];
    const page_id = tab_switcher.dataset.tab;

    tab_switcher.addEventListener('click', () => {
      document.querySelector('.tabs .tab.is-active').classList.remove('is-active');
      tab_switcher.parentNode.classList.add('is-active');
      SwitchPage(page_id);
    });
  }

  isWho();
  getToday();
  getWeek();
}

$('.jump_month_plus').click(function(){

  $('th').remove('.swich');
  $('td').remove('.swich');
  $('tr').remove('.swich');

  let value = $('#focus_date').val();
  let selectedDate = new Date(value);
  selectedDate.setMonth(selectedDate.getMonth() + 1);
  let test =  get_date_str(selectedDate);

  year = test.substring(0,4);
  month = test.substring(5,7);
  document.getElementById("focus_date").value = test;

  $.ajax({
    url: '/week/weekly',
    type: 'POST',
    dataType: 'json',
    data: {
      year: year,
      month: month
    },
    success: function (rs) {
      drawWeekly(rs.weekcount, rs.items);
    }
  });
});

$('.jump_month_minus').click(function(){

  $('th').remove('.swich');
  $('td').remove('.swich');
  $('tr').remove('.swich');

  let value = $('#focus_date').val();
  let selectedDate = new Date(value);
  selectedDate.setMonth(selectedDate.getMonth() - 1);
  let test =  get_date_str(selectedDate);

  year = test.substring(0,4);
  month = test.substring(5,7);
  document.getElementById("focus_date").value = test;

  $.ajax({
    url: '/week/weekly',
    type: 'POST',
    dataType: 'json',
    data: {
      year: year,
      month: month
    },
    success: function (rs) {
      drawWeekly(rs.weekcount, rs.items);
    }
  });
});

function getWeek() {

  let value = $('#focus_date').val();
  year = value.substring(0,4);
  month = value.substring(5,7);

  $.ajax({
    url: '/week/weekly',
    type: 'POST',
    dataType: 'json',
    data: {
      year: year,
      month: month
    },
    success: function (rs) {
      drawWeekly(rs.weekcount, rs.items);
    }
  });
}

function drawWeekly(count, list) {

  let str = '';
  for (let num = 1; num <= count; num++){
    str += `
      <th class="align-middle swich">${num}주차</th>
			`;
  }
  $('#weekly').append(str);

  let row = '';

  list.departmentList.forEach(weeklyTasks => {

    let weekly = '';

    for (let i=1 ; i<=count ; i++) {
      weekly += `<td> <ul class="main">`
      weeklyTasks.memberTasks[0]['weekly'+i].forEach(task => {
        var bgColor = selectBgColor(task.state);
        weekly += `
                  <li><div class="task-data" style="background-color: ${bgColor}" data-pid="${task.pid}" data-content="${task.content}" data-start_at="${task.startAt}" data-name="${task.name}" data-member_pid="${weeklyTasks.memberTasks[0].memberPid}">${task.title}</div>
                    <ul class="sub">
                      <input name="viewTask" type="button" class="btn" data-toggle="modal" data-target="#viewTaskModal" data-whatever="@mdo" data-pid="${task.pid}" data-member_pid="${weeklyTasks.memberTasks[0].memberPid}" value="상세보기">  
                      <input name="deleteTask" type="button" class="btn" value="삭제하기" data-pid="${task.pid}" data-member_pid="${weeklyTasks.memberTasks[0].memberPid}">                    
                    </ul>    
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
              <li style="background-color:#fff;" class="task-data"><div class="member_name">${weeklyTasks.memberTasks[0].name}</div>
                <ul class="sub">
                  <input name="writeTask" type="button" class="btn" data-toggle="modal" data-target="#writeTaskModal" data-whatever="@mdo" value="업무등록하기">                      
                </ul>
              </li>
            </ul>
          </td>
          ${weekly}
      </tr>
    `;

    for(let idx=1; idx < weeklyTasks.memberTasks.length; idx++) {
      let weekly = '';

      for (let i=1 ; i<=count ; i++) {
        weekly += `<td><ul class="main">`
        weeklyTasks.memberTasks[idx]['weekly'+i].forEach(task => {
          var bgColor = selectBgColor(task.state);
          weekly += `
                  <li><div class="task-data" style="background-color: ${bgColor}" data-pid="${task.pid}" data-content="${task.content}" data-start_at="${task.startAt}" data-name="${task.name}" data-member_pid="${weeklyTasks.memberTasks[idx].memberPid}">${task.title}</div>
                    <ul class="sub">                   
                      <input name="viewTask" type="button" class="btn" data-toggle="modal" data-target="#viewTaskModal" data-whatever="@mdo" data-pid="${task.pid}" data-member_pid="${weeklyTasks.memberTasks[idx].memberPid}" value="상세보기">
                      <input name="deleteTask" type="button" class="btn" value="삭제하기" data-pid="${task.pid}" data-member_pid="${weeklyTasks.memberTasks[idx].memberPid}">                                            
                    </ul>    
                  </li>
                `;
        });
        weekly += `</ul></td>`
      }

      row += `
        <tr class="swich">
          <td class="align-middle text-center">
            <ul class="main" style="margin:auto;">
              <li style="background-color:#fff;" class="task-data"><div class="member_name">${weeklyTasks.memberTasks[idx].name}</div>
                <ul class="sub">
                  <input name="writeTask" type="button" class="btn" data-toggle="modal" data-target="#writeTaskModal" data-whatever="@mdo" value="업무등록하기">
                </ul>
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

$('tbody#task_body').on('click', "input[name=deleteTask]", function (e) {
  e.preventDefault();

  let pid = $(e.target).data('pid');
  let member_pid = $(e.target).data('member_pid');
  if (member_pid != $.cookie('member_pid')) {
    alert('권한이 없습니다.');
    return;
  }

  let confirmAlert = confirm("정말 삭제하시겠습니까?");
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
      if(rs.success) {
        alert('삭제되었습니다.');
        newdraw();
      }
    }
  });
});



$("[name=deleteTask]").click(function () {

  let pid = $("[name=viewPid]").val();
  if ($.cookie('member_pid_temporary') != $.cookie('member_pid')) {
    alert('권한이 없습니다.');
    return;
  }

  let confirmAlert = confirm("정말 삭제하시겠습니까?");
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
      if(rs.success) {
        alert('삭제되었습니다.');
        $('#viewTaskModal').modal("hide");
        newdraw();
      }
    }
  });
});

$('tbody#task_body').on('click', "input[name=viewTask]", function (e) {
  e.preventDefault();

  let pid = $(e.target).data('pid');
  let memberPid = $(e.target).data('member_pid');
  $("[name=viewPid]").val(pid);

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
      if(rs.success) {
        $("[name=viewTitle]").val(rs.data.title);
        $("[name=viewContent]").val(rs.data.content);
        $("[name=viewStartAt]").val(rs.data.startAt);
        $("[name=viewDueAt]").val(rs.data.dueAt);
        $("[name=viewState]").val(rs.data.state);
        $("[name=viewPriority]").val(rs.data.priority);

        $.cookie('member_pid_temporary', memberPid, {expires: 1});
      }
    }
  });
});

$('tbody#task_body').on('click', '.task-data', function () {

  if($.cookie('name') == undefined) {
    alert('로그인을 안했잖아');
  }
});

$("button[name='updateTaskPage']").on("click", function(e){

  if ($.cookie('member_pid_temporary') != $.cookie('member_pid')) {
    alert('권한이 없습니다.');
    return;
  }

  let pid = $("[name=viewPid]").val();
  let title = $("[name=viewTitle]").val();
  let content = $("[name=viewContent]").val();
  let startAt = $("[name=viewStartAt]").val();
  let dueAt = $("[name=viewDueAt]").val();
  let state = $("[name=viewState]").val();
  let priority = $("[name=viewPriority]").val();

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
      if(rs.success) {
        alert('수정성공했습니다.');
        newdraw();
      }
    }
  });

});
$('tbody#task_body').on('click', '.main>li', function () {
  $(this).children(".sub").stop().slideDown();
});
$('tbody#task_body').on('mouseleave', '.main>li', function () {
  $(this).children(".sub").stop().slideUp();
});

function changeDate()  {
  $('th').remove('.swich');
  $('td').remove('.swich');
  $('tr').remove('.swich');
  getWeek();
}

function selectBgColor(state) {

  if (state == '대기중') {
    return '#ffffcc';
  }
  if (state == '진행중') {
    return '#ccccff';
  }
  if (state == '지연') {
    return '#ffc6b3';
  }
  if (state == '완료') {
    return '#ccffdc';
  }
}

if($.cookie('name') == undefined) {
  $("#loginBox").show();
  $("#logoutBox").hide();
  $("[name=writeTask]").hide();
}

function isWho() {
  if($.cookie('name') != undefined) {
    $("#loginBox").hide();
    $("#logoutBox").show();
    $("[name=writeTask]").show();
    $("label[name='name_area']").text($.cookie('name'));
    $("input[name='memberPid']").val(Number($.cookie('member_pid')));
  }
}

$("[name=logout]").click(function () {
  document.cookie = 'name=; Max-Age=-1;';
  document.cookie = 'member_pid=; Max-Age=-1;';
  location.reload();
});

$("[name=submitRoleDepartmentPid]").click(function () {
  $.ajax({
    url: '/member/submitRoleDepartmentPid',
    type: 'POST',
    dataType: 'json',
    data: {
      pid: Number($("[name=insertInputMemberPid]").val()),
      name: $("[name=insertInputName]").val(),
      role: $("[name=insertInputRole]").val(),
      departmentPid: $("[name=insertInputDepartMentPid]").val()
    },
    success: function (rs) {
      if (rs.success) {
        alert('성공! 다시 로그인 하십시오');
        $('#insertInput').modal("hide");
      }
      if (!rs.success) {
        alert('실패!');
      }
    }
  });
});

$("[name=login]").click(function () {
  $.ajax({
    url: '/member/login',
    type: 'POST',
    dataType: 'json',
    data: {
      username: $("[name=name]").val(),
      password: $("[name=pw]").val()
    },
    success: function (rs) {
      if (rs.success) {
        loginSuccess(rs.data.pid, rs.data.name);
      }
      if (!rs.success) {
        alert('아이디와 페스워드가 틀렸습니다.');
      }
    }
  });
});

function loginSuccess(pid, name) {

  $("[name=insertInputMemberPid]").val(pid);
  $("[name=insertInputName]").val(name);

  $.ajax({
    url: '/member/isPid',
    type: 'POST',
    dataType: 'json',
    data: {
      pid: pid,
      name: name
    },
    success: function (rs) {
      if (!rs.isPidDup) {
        $('#insertInput').modal("show");
      }
      if (rs.isPidDup) {
        $.cookie('member_pid', $("[name=insertInputMemberPid]").val(), {expires: 1});
        $.cookie('name', $("[name=insertInputName]").val(), {expires: 1});
        isWho($("[name=insertInputMemberPid]").val());
      }
    }
  });
}

document.getElementById('startAt').value = new Date().toISOString().substring(0, 10);
document.getElementById('dueAt').value = getDueAt();

function getDueAt() {
  let writeDueAt = new Date();
  writeDueAt.setDate(writeDueAt.getDate()+1);
  writeDueAt = writeDueAt.toISOString().slice(0, 10);
  return writeDueAt;
}

$("[name=writeTaskSubmit]").click(function () {
  let memberPid = Number($.cookie('member_pid'));
  let type = '주간';
  let title = $("[name=title]").val();
  let content = $("[name=content]").val();
  let startAt = $("[name=startAt]").val();
  let dueAt = $("[name=dueAt]").val();
  let state = $("[name=state]").val();
  let priority = $("[name=priority]").val();

  if (startAt > dueAt) {
    alert('시작일이 종료일보다 큽니다.');
    return;
  }

  $.ajax({
    url: '/week/writeTask',
    type: 'POST',
    dataType: 'json',
    data: {
      memberPid: memberPid,
      type: type,
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
      if(rs.success) {
        alert('업무등록성공!!');
        $('#writeTaskModal').modal("hide");
        newdraw();
      }
    }
  });
});

function newdraw() {
  $('th').remove('.swich');
  $('td').remove('.swich');
  $('tr').remove('.swich');
  getToday();
  getWeek();
}