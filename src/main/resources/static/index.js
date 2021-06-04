let yyyy;
let mm;
let dd;

let week;

function getToday() {
  let today = new Date();
  today = today.toISOString().slice(0, 10);
  console.log(today);
  let bir = document.getElementById("focus_date");
  bir.value = today;
}

function get_date_str(date)
{
  var sYear = date.getFullYear();
  var sMonth = date.getMonth() + 1;
  var sDate = date.getDate();

  sMonth = sMonth > 9 ? sMonth : "0" + sMonth;
  sDate  = sDate > 9 ? sDate : "0" + sDate;
  return sYear +'-'+ sMonth +'-'+ sDate;
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
  dd = value.substring(8,10);
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
}

function drawweek(count, list) {
  let str = '';
  for (let num = 1; num <= count; num++){
    str += `
      <th class="align-middle swich">${num}주차</th>
			`;
  }
  $('#weekly').append(str);

  // let ttt= 'ttt';
  // for (let i = 0 ; i<2 ; i++) {
  //   $('#num'+i).append(ttt);
  // }
  for ( var i=0; i<6 ; i++) {
    for (var j = 0; j < count; j++) {
      var txt = "(" + i + "," + j + ")";    // 테이블각 셀에 (행,열) 값을 출력하기위해 정의된 String
      $('#num' + i).append(txt);
      // document.write("<td>"+ txt +"</td>");     // <td> : 열추가.
    } //end for j
  }

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