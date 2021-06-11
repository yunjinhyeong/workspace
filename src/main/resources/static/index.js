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

  for (let i = 0; i < tab_switchers.length; i++) {
    const tab_switcher = tab_switchers[i];
    const page_id = tab_switcher.dataset.tab;

    tab_switcher.addEventListener('click', () => {
      document.querySelector('.tabs .tab.is-active').classList.remove('is-active');
      tab_switcher.parentNode.classList.add('is-active');
      SwitchPage(page_id);

    });
  }
  getToday();
  getWeek();
}

$('.jump_month_plus').click(function(){
  $('th').remove('.swich');
  $('td').remove('.swich');
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
      drawweek(rs.weekcount, rs.list);
    }
  });
});

$('.jump_month_minus').click(function(){
  $('th').remove('.swich');
  $('td').remove('.swich');
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
      drawweek(rs.weekcount, rs.list);
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
      console.log('1');
      console.log(JSON.stringify(rs.items));
      console.log('2');
      console.log(rs.items);
      drawSample(rs.weekcount, JSON.stringify(rs.items));
    }
  });
}

function drawSample(count, list) {
  let str = '';
  for (let num = 1; num <= count; num++){
    str += `
      <th class="align-middle swich">${num}주차</th>
			`;
  }
  $('#weekly').append(str);

  let row = '';

  var list2 = JSON.parse(list);
  console.log(list2.departmentList);

  list2.departmentList.forEach(d => {
    console.log(d.departmentName);
    d.memberTasks.forEach(m => {
      console.log(m.name);
    });
  });

  // list.forEach(weeklyTasks => {
  //
  //   let ww = '';
  //   // var w1 ='';
  //   // var w2 ='';
  //   // var w3 ='';
  //   // var w4 ='';
  //   // var w5 ='';
  //
  //   // let iterator = Object.keys(weeklyTasks.memberTasks[0]);
  //   // iterator.forEach(key => {
  //   //   let temp = weeklyTasks.memberTasks[0][key];
  //   //   key += `
  //   //       <p>
  //   //         startAt: ${temp.startAt}<br />
  //   //         dueAt: ${temp.dueAt}<br />
  //   //         content: ${temp.content}
  //   //       </p>
  //   //     `;
  //   //   });
  //
  //   for (let i=1 ; i<=count ; i++) {
  //     ww += `<td class="align-middle text-center">`
  //     weeklyTasks.memberTasks[0]['w'+i].forEach(task => {
  //       ww += `
  //           <p>
  //             startAt: ${task.startAt}<br />
  //             dueAt: ${task.dueAt}<br />
  //             content: ${task.content}
  //           </p>
  //       `;
  //     });
  //     ww += `</td>`
  //   }
  //
  //
  //   // weeklyTasks.memberTasks[0].w1.forEach(task => {
  //   //   w1 += `
  //   //       <p>
  //   //         startAt: ${task.startAt}<br />
  //   //         dueAt: ${task.dueAt}<br />
  //   //         content: ${task.content}
  //   //       </p>
  //   //     `;
  //   // });
  //   // weeklyTasks.memberTasks[0].w2.forEach(task => {
  //   //   w2 += `
  //   //       <p>
  //   //         startAt: ${task.startAt}<br />
  //   //         dueAt: ${task.dueAt}<br />
  //   //         content: ${task.content}
  //   //       </p>
  //   //     `;
  //   // })
  //   // weeklyTasks.memberTasks[0].w3.forEach(task => {
  //   //   w3 += `
  //   //       <p>
  //   //         startAt: ${task.startAt}<br />
  //   //         dueAt: ${task.dueAt}<br />
  //   //         content: ${task.content}
  //   //       </p>
  //   //     `;
  //   // })
  //   // weeklyTasks.memberTasks[0].w4.forEach(task => {
  //   //   w4 += `
  //   //       <p>
  //   //         startAt: ${task.startAt}<br />
  //   //         dueAt: ${task.dueAt}<br />
  //   //         content: ${task.content}
  //   //       </p>
  //   //     `;
  //   // })
  //   //
  //   // row += `
  //   //   <tr>
  //   //     <td scope="row" rowspan="${weeklyTasks.memberTasks.length}" class="align-middle text-center">${weeklyTasks.departmentName}</td>
  //   //     <td class="align-middle text-center">${weeklyTasks.memberTasks[0].name}</td>
  //   //     <td class="align-middle text-center">${w1}</td>
  //   //     <td class="align-middle text-center">${w2}</td>
  //   //     <td class="align-middle text-center">${w3}</td>
  //   //     <td class="align-middle text-center">${w4}</td>
  //   //
  //   //   </tr>
  //   // `;
  //
  //   row += `
  //     <tr>
  //       <td scope="row" rowspan="${weeklyTasks.memberTasks.length}" class="align-middle text-center">${weeklyTasks.departmentName}</td>
  //       <td class="align-middle text-center">${weeklyTasks.memberTasks[0].name}</td>
  //       ${ww}
  //     </tr>
  //   `;
  //
  //   for(let idx=1; idx < weeklyTasks.memberTasks.length; idx++) {
  //     let ww = '';
  //     var w1 ='';
  //     var w2 ='';
  //     var w3 ='';
  //     var w4 ='';
  //
  //
  //
  //     for (let i=1 ; i<=count ; i++) {
  //       ww += `<td class="align-middle text-center">`
  //       weeklyTasks.memberTasks[idx]['w'+i].forEach(task => {
  //         ww += `
  //           <p>
  //             startAt: ${task.startAt}<br />
  //             dueAt: ${task.dueAt}<br />
  //             content: ${task.content}
  //           </p>
  //       `;
  //       });
  //       ww += `</td>`
  //     }
  //
  //
  //     // weeklyTasks.memberTasks[idx].w1.forEach(task => {
  //     //   w1 += `
  //     //     <p>
  //     //       startAt: ${task.startAt}<br />
  //     //       dueAt: ${task.dueAt}<br />
  //     //       content: ${task.content}
  //     //     </p>
  //     //   `;
  //     // });
  //     // weeklyTasks.memberTasks[idx].w2.forEach(task => {
  //     //   w2 += `
  //     //     <p>
  //     //       startAt: ${task.startAt}<br />
  //     //       dueAt: ${task.dueAt}<br />
  //     //       content: ${task.content}
  //     //     </p>
  //     //   `;
  //     // })
  //     // weeklyTasks.memberTasks[idx].w3.forEach(task => {
  //     //   w3 += `
  //     //     <p>
  //     //       startAt: ${task.startAt}<br />
  //     //       dueAt: ${task.dueAt}<br />
  //     //       content: ${task.content}
  //     //     </p>
  //     //   `;
  //     // })
  //     // weeklyTasks.memberTasks[idx].w4.forEach(task => {
  //     //   w4 += `
  //     //     <p>
  //     //       startAt: ${task.startAt}<br />
  //     //       dueAt: ${task.dueAt}<br />
  //     //       content: ${task.content}
  //     //     </p>
  //     //   `;
  //     // })
  //
  //     row += `
  //       <tr>
  //         <td class="align-middle text-center">${weeklyTasks.memberTasks[idx].name}</td>
  //         ${ww}
  //       </tr>
  //     `;
  //   }
  // });

  $('#task_body').append(row);

}

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
}
$("[name=logout]").click(function () {
  document.cookie = 'name=; Max-Age=-1;';
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
        $.cookie('name', rs.data.name, {expires: 1});
        $("#loginBox").hide();
        $("#logoutBox").show();
        $("label[name='name_area']").text($.cookie('name')+' 님 환영합니다 ~ ');
      }
    }
  });
});