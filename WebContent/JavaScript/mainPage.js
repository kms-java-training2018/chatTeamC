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

//var timeoutId = 500;
//
//window.addEventListener( "scroll", function () {
//
//	// スクロールを停止して500ms後に終了とする
//	clearTimeout( timeoutId ) ;
//
//	timeoutId = setTimeout( function () {
//		alert("とまるな");
//	}, 500 ) ;
//} ) ;