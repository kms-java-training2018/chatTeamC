
$(function() {
  $('#member_all').on('click', function() {
    $('.member').prop('checked', this.checked);
  });

  $('.member').on('click', function() {
    if ($(status) == $('.member :input').length){
      $('#member_all').prop('checked', 'checked');
    }else{
      $('#member_all').prop('checked', false);
    }
  });
});

