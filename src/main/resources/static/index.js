let yyyy;
let mm;
let dd;

let week;

function getToday() {
  let today = new Date();
  today = today.toISOString().slice(0, 7);
  console.log(today);
  let bir = document.getElementById("focus_date");
  bir.value = today;
}

function get_date_str(date)
{
  var sYear = date.getFullYear();
  var sMonth = date.getMonth() + 1;
  // var sDate = date.getDate();

  sMonth = sMonth > 9 ? sMonth : "0" + sMonth;
  // sDate  = sDate > 9 ? sDate : "0" + sDate;
  return sYear +'-'+ sMonth;
}


window.onload = () => {
  const tab_switchers = document.querySelectorAll('[data-switcher]');

  console.log(
    '%c%s',
    'color: #22A8A5; font-size: 5px;',
    `
███╗░░██╗░█████╗░████████╗██╗░░░██╗██████╗░███████╗
████╗░██║██╔══██╗╚══██╔══╝██║░░░██║██╔══██╗██╔════╝
██╔██╗██║███████║░░░██║░░░██║░░░██║██████╔╝█████╗░░
██║╚████║██╔══██║░░░██║░░░██║░░░██║██╔══██╗██╔══╝░░
██║░╚███║██║░░██║░░░██║░░░╚██████╔╝██║░░██║███████╗
╚═╝░░╚══╝╚═╝░░╚═╝░░░╚═╝░░░░╚═════╝░╚═╝░░╚═╝╚══════╝

███╗░░░███╗░█████╗░██████╗░██╗██╗░░░░░██╗████████╗██╗░░░██╗
████╗░████║██╔══██╗██╔══██╗██║██║░░░░░██║╚══██╔══╝╚██╗░██╔╝
██╔████╔██║██║░░██║██████╦╝██║██║░░░░░██║░░░██║░░░░╚████╔╝░
██║╚██╔╝██║██║░░██║██╔══██╗██║██║░░░░░██║░░░██║░░░░░╚██╔╝░░
██║░╚═╝░██║╚█████╔╝██████╦╝██║███████╗██║░░░██║░░░░░░██║░░░
╚═╝░░░░░╚═╝░╚════╝░╚═════╝░╚═╝╚══════╝╚═╝░░░╚═╝░░░░░░╚═╝░░░

░██╗░░░░░░░██╗░█████╗░██████╗░██╗░░██╗██████╗░░█████╗░░█████╗░███████╗
░██║░░██╗░░██║██╔══██╗██╔══██╗██║░██╔╝██╔══██╗██╔══██╗██╔══██╗██╔════╝
░╚██╗████╗██╔╝██║░░██║██████╔╝█████═╝░██████╔╝███████║██║░░╚═╝█████╗░░
░░████╔═████║░██║░░██║██╔══██╗██╔═██╗░██╔═══╝░██╔══██║██║░░██╗██╔══╝░░
░░╚██╔╝░╚██╔╝░╚█████╔╝██║░░██║██║░╚██╗██║░░░░░██║░░██║╚█████╔╝███████╗
░░░╚═╝░░░╚═╝░░░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░░░░╚═╝░░╚═╝░╚════╝░╚══════╝
    `
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
  iswho();
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
  yyyy = test.substring(0,4);
  mm = test.substring(5,7);
  document.getElementById("focus_date").value = test;
  $.ajax({
    url: '/week/weekly',
    type: 'POST',
    dataType: 'json',
    data: {
      yyyy: yyyy,
      mm: mm
    },
    success: function (rs) {
      console.log(rs.weekcount);
      // drawweek(rs.weekcount, rs.list);
      drawSample(rs.weekcount, rs.items);
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
  yyyy = test.substring(0,4);
  mm = test.substring(5,7);
  document.getElementById("focus_date").value = test;
  $.ajax({
    url: '/week/weekly',
    type: 'POST',
    dataType: 'json',
    data: {
      yyyy: yyyy,
      mm: mm
    },
    success: function (rs) {
      console.log(rs.weekcount);
      // drawweek(rs.weekcount, rs.list);
      drawSample(rs.weekcount, rs.items);
    }
  });
});

function getWeek() {
  let value = $('#focus_date').val();
  yyyy = value.substring(0,4);
  mm = value.substring(5,7);
  $.ajax({
    url: '/week/weekly',
    type: 'POST',
    dataType: 'json',
    data: {
      yyyy: yyyy,
      mm: mm
    },
    success: function (rs) {
      console.log(rs.items);
      console.log('rs.weekcount = '+rs.weekcount);

      var sampleList = [{
        departmentName: '경영지원',
        memberTasks: [{
          name: '임진숙',
          w1: [{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '1주차 t1'
          },{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '1주차 t2'
          }],
          w2: [{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '2주차 t1'
          },{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '2주차 t2'
          }],
          w3: [{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '3주차 t1'
          },{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '3주차 t2'
          }],
          w4: [{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '4주차 t1'
          },{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '4주차 t2'
          }]
        },{
          name: '김영범',
          w1: [{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '김영범 1주차 t1'
          },{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '김영범 1주차 t2'
          }],
          w2: [{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '김영범 2주차 t1'
          },{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '김영범2주차 t2'
          }],
          w3: [{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '김영범 3주차 t1'
          },{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '김영범 3주차 t2'
          }],
          w4: [{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '김영범 4주차 t1'
          },{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '김영범 4주차 t2'
          }]
        }]
      },{
        departmentName: '개발팀',
        memberTasks: [{
          name: '이욱세',
          w1: [{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '1주차 t1'
          },{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '1주차 t2'
          }],
          w2: [{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '1주차 t1'
          },{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '1주차 t2'
          }],
          w3: [{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '1주차 t1'
          },{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '1주차 t2'
          }],
          w4: [{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '1주차 t1'
          },{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '1주차 t2'
          }]
        },{
          name: '박혜미',
          w1: [{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '1주차 t1'
          },{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '1주차 t2'
          }],
          w2: [{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '1주차 t1'
          },{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '1주차 t2'
          }],
          w3: [{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '1주차 t1'
          },{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '1주차 t2'
          }],
          w4: [{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '1주차 t1'
          },{
            startAt: '2021-06-01',
            dueAt: '2021-06-03',
            content: '1주차 t2'
          }]
        }]
      }]

      // drawweek(rs.weekcount, rs.list);

      drawSample(rs.weekcount, rs.items);
    }
  });
}

let weekDataList = []

function drawSample(count, list) {
  let str = '';
  for (let num = 1; num <= count; num++){
    str += `
      <th class="align-middle swich">${num}주차</th>
			`;
  }
  $('#weekly').append(str);

  let row = '';


  console.log('weekDataList',weekDataList)

  list.departmentList.forEach(weeklyTasks => {

    let ww = '';
    // var w1 ='';
    // var w2 ='';
    // var w3 ='';
    // var w4 ='';
    // var w5 ='';

    // let iterator = Object.keys(weeklyTasks.memberTasks[0]);
    // iterator.forEach(key => {
    //   let temp = weeklyTasks.memberTasks[0][key];
    //   key += `
    //       <p>
    //         startAt: ${temp.startAt}<br />
    //         dueAt: ${temp.dueAt}<br />
    //         content: ${temp.content}
    //       </p>
    //     `;
    //   });

    for (let i=1 ; i<=count ; i++) {
      ww += `<td>`
      weeklyTasks.memberTasks[0]['w'+i].forEach(task => {
        ww += `<ul class="main">
                  <li><div class="task-data" data-pid="${task.pid}" data-start_at="${task.startAt}">content: ${task.content}</div>
                    <ul class="sub">
                      <input name="viewTask" type="button" class="btn" data-toggle="modal" data-target="#viewTaskModal" data-whatever="@mdo" value="상세보기" data-pid="${task.pid}">  
                      <input name="deleteTask" type="button" class="btn" value="삭제하기" data-pid="${task.pid}">                    
                    </ul>    
                  </li>                
                </ul>`;
      });
      ww += `</td>`
    }

    // weeklyTasks.memberTasks[0].w1.forEach(task => {
    //   w1 += `
    //       <p>
    //         startAt: ${task.startAt}<br />
    //         dueAt: ${task.dueAt}<br />
    //         content: ${task.content}
    //       </p>
    //     `;
    // });
    // weeklyTasks.memberTasks[0].w2.forEach(task => {
    //   w2 += `
    //       <p>
    //         startAt: ${task.startAt}<br />
    //         dueAt: ${task.dueAt}<br />
    //         content: ${task.content}
    //       </p>
    //     `;
    // })
    // weeklyTasks.memberTasks[0].w3.forEach(task => {
    //   w3 += `
    //       <p>
    //         startAt: ${task.startAt}<br />
    //         dueAt: ${task.dueAt}<br />
    //         content: ${task.content}
    //       </p>
    //     `;
    // })
    // weeklyTasks.memberTasks[0].w4.forEach(task => {
    //   w4 += `
    //       <p>
    //         startAt: ${task.startAt}<br />
    //         dueAt: ${task.dueAt}<br />
    //         content: ${task.content}
    //       </p>
    //     `;
    // })
    //
    // row += `
    //   <tr>
    //     <td scope="row" rowspan="${weeklyTasks.memberTasks.length}" class="align-middle text-center">${weeklyTasks.departmentName}</td>
    //     <td class="align-middle text-center">${weeklyTasks.memberTasks[0].name}</td>
    //     <td class="align-middle text-center">${w1}</td>
    //     <td class="align-middle text-center">${w2}</td>
    //     <td class="align-middle text-center">${w3}</td>
    //     <td class="align-middle text-center">${w4}</td>
    //
    //   </tr>
    // `;
console.log(weeklyTasks);
    row += `
      <tr class="swich">
        <td scope="row" rowspan="${weeklyTasks.memberTasks.length}" class="align-middle text-center" style="width: 100px">${weeklyTasks.departmentName}</td>
        <td class="align-middle" style="width: 100px">
            <ul class="main" style="margin:auto;">
              <li style="background-color:#fff;" class="task-data"><div>${weeklyTasks.memberTasks[0].name}</div>
                <ul class="sub">
                  <input name="writeTask" type="button" class="btn" data-toggle="modal" data-target="#writeTaskModal" data-whatever="@mdo" value="업무등록하기">                      
                </ul>
              </li>
            </ul>
          </td>
          ${ww}
      </tr>
    `;

    for(let idx=1; idx < weeklyTasks.memberTasks.length; idx++) {
      let ww = '';


      for (let i=1 ; i<=count ; i++) {
        ww += `<td>`
        weeklyTasks.memberTasks[idx]['w'+i].forEach(task => {
          ww += `<ul class="main">
                  <li><div class="task-data" data-pid="${task.pid}" data-start_at="${task.startAt}">content: ${task.content}</div>
                    <ul class="sub">                   
                      <input name="viewTask" type="button" class="btn" data-toggle="modal" data-target="#viewTaskModal" data-whatever="@mdo" value="상세보기" data-pid="${task.pid}">
                      <input name="deleteTask" type="button" class="btn" value="삭제하기" data-pid="${task.pid}">                                            
                    </ul>    
                  </li>
                </ul>`;
        });
        ww += `</td>`
      }


      // weeklyTasks.memberTasks[idx].w1.forEach(task => {
      //   w1 += `
      //     <p>
      //       startAt: ${task.startAt}<br />
      //       dueAt: ${task.dueAt}<br />
      //       content: ${task.content}
      //     </p>
      //   `;
      // });
      // weeklyTasks.memberTasks[idx].w2.forEach(task => {
      //   w2 += `
      //     <p>
      //       startAt: ${task.startAt}<br />
      //       dueAt: ${task.dueAt}<br />
      //       content: ${task.content}
      //     </p>
      //   `;
      // })
      // weeklyTasks.memberTasks[idx].w3.forEach(task => {
      //   w3 += `
      //     <p>
      //       startAt: ${task.startAt}<br />
      //       dueAt: ${task.dueAt}<br />
      //       content: ${task.content}
      //     </p>
      //   `;
      // })
      // weeklyTasks.memberTasks[idx].w4.forEach(task => {
      //   w4 += `
      //     <p>
      //       startAt: ${task.startAt}<br />
      //       dueAt: ${task.dueAt}<br />
      //       content: ${task.content}
      //     </p>
      //   `;
      // })



      row += `
        <tr class="swich">
          <td class="align-middle text-center">
            <ul class="main" style="margin:auto;">
              <li style="background-color:#fff;" class="task-data"><div>${weeklyTasks.memberTasks[idx].name}</div>
                <ul class="sub">
                  <input name="writeTask" type="button" class="btn" data-toggle="modal" data-target="#writeTaskModal" data-whatever="@mdo" value="업무등록하기">
                </ul>
              </li>
            </ul>
          </td>
          ${ww}
        </tr>
      `;
    }
  });
  $('#task_body').append(row);
}

$('tbody#task_body').on('click', "input[name=deleteTask]", function (e) {
  e.preventDefault();
  // console.log('e.target',e.target, $(e.target).data('pid'), $(e.target).data('start_at'));
  let pid = $(e.target).data('pid');

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
});////////////////////////////////////////////////////////////////////////////////////////////////////

$('tbody#task_body').on('click', "input[name=viewTask]", function (e) {
  e.preventDefault();
  // console.log('e.target',e.target, $(e.target).data('pid'), $(e.target).data('start_at'));
  let pid = $(e.target).data('pid');
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

      }
    }
  });

});

$('tbody#task_body').on('click', '.task-data', function () {///////////////
  if($.cookie('name') == undefined) {
    alert('로그인을 안했잖아');
  }
});

$("button[name='updateTaskPage']").on("click", function(e){
  console.log($("[name=viewPid]").val());
  let pid = $("[name=viewPid]").val();
  console.log(pid);
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
// $('tbody#task_body').on('click', "input[name=updateTaskPage]", function (e) {
//   e.preventDefault();
//   console.log('머냐?');
//   alert('일단나와');
// });

$('tbody#task_body').on('click', '.main>li', function () {
  $(this).children(".sub").stop().slideDown();
});
$('tbody#task_body').on('mouseleave', '.main>li', function () {
  $(this).children(".sub").stop().slideUp();
});

function drawweek(count, list) {
  let str = '';
  for (let num = 1; num <= count; num++){
    str += `
      <th class="align-middle swich">${num}주차</th>
			`;
  }
  $('#weekly').append(str);

  let row = '';

  list.forEach(weeklyTasks => {

  let ww ='';

  for (let i=1 ; i<=count ; i++) {
    ww += `<td class="align-middle text-center">`
    weeklyTasks.memberTasks[0]['w'+i].forEach(task => {
      ww += `
            <p>
              startAt: ${task.startAt}<br />
              dueAt: ${task.dueAt}<br />
              content: ${task.content}
            </p>
        `;
    });
    ww += `</td>`
  }

  row += `
      <tr>
        <td scope="row" rowspan="${weeklyTasks.memberTasks.length}" class="align-middle text-center">${weeklyTasks.departmentName}</td>
        <td class="align-middle text-center">${weeklyTasks.memberTasks[0].name}</td>
        ${ww}
      </tr>
    `;

  for(let idx=1; idx < weeklyTasks.memberTasks.length; idx++) {
    let ww = '';
    var w1 ='';
    var w2 ='';
    var w3 ='';
    var w4 ='';



    for (let i=1 ; i<=count ; i++) {
      ww += `<td class="align-middle text-center">`
      weeklyTasks.memberTasks[idx]['w'+i].forEach(task => {
        ww += `
            <p>
              startAt: ${task.startAt}<br />
              dueAt: ${task.dueAt}<br />
              content: ${task.content}
            </p>
        `;
      });
      ww += `</td>`
    }

  row += `
        <tr>
          <td class="align-middle text-center">${weeklyTasks.memberTasks[idx].name}</td>
          ${ww}
        </tr>
      `;
}
});

$('#task_body').append(row);

  // let ttt= 'ttt';
  // for (let i = 0 ; i<2 ; i++) {
  //   $('#num'+i).append(ttt);
  // }
  // for ( var i=0; i < 16 ; i++) {
  //   for (var j = 1; j <= count; j++) {
  //     for (let l = 0 ; l<list.length ; l++) {
  //       console.log(list.length);
  //       if (parseInt(list[l].mm)==mm && parseInt(list[l].yyyy)==yyyy && list[l].type == '주간' && j == list[l].realweek && $('#name' + i).text() == list[l].name) {
  //         let txt = `<td class="swich">들어간다</td>`;    // 테이블각 셀에 (행,열) 값을 출력하기위해 정의된 String
  //         $('#num' + i).append(txt);
  //       }
  //       if (parseInt(list[l].mm)!=mm || parseInt(list[l].yyyy)!=yyyy || list[l].type != '주간' || j != list[l].realweek || $('#name' + i).text() != list[l].name) {
  //         let txt = `<td class="swich"></td>`;    // 테이블각 셀에 (행,열) 값을 출력하기위해 정의된 String
  //         $('#num' + i).append(txt);
  //       }
  //     }

      // if (parseInt(list[i].mm)==mm && parseInt(list[i].yyyy)==yyyy && list[i].type == '주간' && j == list[i].realweek) {
      //   var txt = `<td class="swich">들어간다</td>`;    // 테이블각 셀에 (행,열) 값을 출력하기위해 정의된 String
      //   $('#num' + i).append(txt);
      // }
      // if (parseInt(list[i].mm)!=mm || parseInt(list[i].yyyy)!=yyyy || list[i].type != '주간' || j != list[i].realweek) {
      //   var txt = `<td class="swich"></td>`;    // 테이블각 셀에 (행,열) 값을 출력하기위해 정의된 String
      //   $('#num' + i).append(txt);
      // }
      // document.write("<td>"+ txt +"</td>");     // <td> : 열추가.
  //   } //end for j
  // }

  // console.log(list);
  // let empty = '';
  // let taskstr = '';
  // for (let i = 0 ; i<list.length ; i++) {
  //   console.log('>');
  //   if (parseInt(list[i].mm)!=mm || parseInt(list[i].yyyy)!=yyyy || list[i].type != '주간') {
  //     console.log('>>');
  //     $('#num'+i).append(empty);
  //     console.log('>>>');
  //     continue;
  //   }
  //   console.log('>>>>');
  //   taskstr += `
  //     <td>멍미</td>
	// 		`;
  //   console.log('>>>>>');
  //   $('#num'+i).append(taskstr);
  //   console.log('>>>>>>');
  // }
}

function changeDate()  {
  $('th').remove('.swich');
  $('td').remove('.swich');
  $('tr').remove('.swich');
  getWeek();
}

function SwitchPage (page_id) {
  const current_page = document.querySelector('.pages .page.is-active');
  current_page.classList.remove('is-active');

  const next_page = document.querySelector(`.pages .page[data-page="${page_id}"]`);
  next_page.classList.add('is-active');
}

if($.cookie('name') == undefined) {
  $("#loginBox").show();
  $("#logoutBox").hide();
  $("[name=writeTask]").hide();

}

function iswho() {
  if($.cookie('name') != undefined) {
    $("#loginBox").hide();
    $("#logoutBox").show();
    $("[name=writeTask]").show();
    $("label[name='name_area']").text($.cookie('name')+' 님 환영합니다 ~ ');
    $("input[name='memberPid']").val(Number($.cookie('member_pid')));
  }
}


// $("[name=writeTask]").click(function () {
//   window.open('/week/writeTask?name='+$.cookie('name'), '업무등록하기', 'width=1000,height=1000');
// });

$("[name=logout]").click(function () {
  document.cookie = 'name=; Max-Age=-1;';
  document.cookie = 'member_pid=; Max-Age=-1;';
  location.reload();
});

$("[name=login]").click(function () {
  $.ajax({
    url: '/member/login',
    type: 'POST',
    dataType: 'json',
    data: {
      name: $("[name=name]").val(),
      pw: $("[name=pw]").val()
    },
    success: function (rs) {
      if (rs.success) {
        $.cookie('member_pid', rs.data.pid, {expires: 1});
        $.cookie('name', rs.data.name, {expires: 1});
        iswho(rs.data.pid);
      }
    }
  });
});
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