// 更新時間
var gTimer = setTimeout(inputEnd, 10000);

/**
 * フォーカスをそろえた後、スクロールを整える。
 */
function firstscript(setHeight) {
	// スクロールをあわせる
	if (setHeight == null) {
		// 一番下にあわせる
		var maxY = document.documentElement.scrollHeight;
	} else {
		// 前のスクロールにあわせる
		var maxY = setHeight;
	}
	window.scroll(0, maxY);

	// フォーカスをあわせる
	var textFocus = document.getElementById('inText');
	textFocus.focus();
	textFocus.value = textFocus.value;
	// }

	return true;
}


/**
 * テキスト欄に入力が行なわれた際、更新時間を元に戻す。
 */
function inputText() {
	// 更新時のテキストを設定しておく
	document.getElementById("setMes").value = document.getElementById("inText").value;

	// 一定時間後更新を行なう
	if (gTimer) {
		clearTimeout(gTimer);
	}
	// 入力されたため時間を更新する
	gTimer = setTimeout(inputEnd, 10000);
}

/**
 * チャットの更新を行なう
 */
function inputEnd() {
	// 入力完了と判断してメッセージを設定しておく
	document.getElementById("setMes").value = document.getElementById("inText").value;
	document.getElementById("scroll").value = document.documentElement.scrollTop;

	// サブミットするフォームを取得
	var f = document.forms["myForm"];

	// 更新をおこなう
	// method(GET or POST)を設定する
	f.method = "POST";
	// action(遷移先URL)を設定する
	f.action = document.getElementById("URL").value;
	// submit する
	f.submit();
	return true;
}