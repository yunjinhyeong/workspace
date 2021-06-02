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
        $('#username').val($.cookie('name')+' 님 환영합니다.');
      }
    }
  });
});