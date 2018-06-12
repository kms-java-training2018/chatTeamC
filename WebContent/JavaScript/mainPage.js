//トップ画面のスクリプト
//shu = 趣味の内容
//stitle1 = 趣味の種類

$(function() {
	$('#personalMessage').hide();
	$('#groupMessage').hide();

	$('#Direct').on('click', function() {
		if ($('#personalMessage').is(':hidden')) {
			$('#personalMessage').show();
		} else {
			$('#personalMessage').hide();
		}
	});

	$('#Group').on('click', function() {
		if ($('#groupMessage').is(':hidden')) {
			$('#groupMessage').show();
		} else {
			$('#groupMessage').hide();
		}
	});
});