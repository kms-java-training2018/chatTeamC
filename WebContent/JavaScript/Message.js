// 更新時間
var gTimer = setTimeout(inputEnd, 10000);

// 最初にスクロールの場所を設定する
function firstscript(setHeight) {

	// フォーカスをあわせる
	// if (setHeight >= document.documentElement.scrollHeight - 606) {
	var textFocus = document.getElementById('inText');
	textFocus.focus();
	textFocus.value = textFocus.value;
	// }
	// スクロールをあわせる
	if (setHeight == null) {
		// 一番下にあわせる
		var maxY = document.documentElement.scrollHeight;
	} else {
		// 前のスクロールにあわせる
		var maxY = setHeight;
	}
	window.scroll(0, maxY);
	//
	// var textFocus = document.getElementById('inText');
	// textFocus.focus();
	// textFocus.value = textFocus.value;
	// var maxY
	// if (setHeight == null) {
	// maxY = document.documentElement.scrollHeight;
	// } else {
	// maxY = setHeight;
	// }
	// window.scroll(0, maxY);

	return true;
}

function inputText() {
	// =========================================================
	// 入力の度に実行される
	// 入力完了までタイマーで実行待ちする
	// タイマーまでに次の入力があると、再度タイマー設定
	// =========================================================
	// --- サンプル用 メッセージ出力 -------------------
	// var wObj = document.getElementById("endMsg");
	// wObj.innerHTML = '入力中です' + document.getElementById("inText").value;

	// 更新時のテキストを設定しておく
	document.getElementById("setMes").value = document.getElementById("inText").value;

	// wObj.className = 'defStyle runStyle';
	// =============================================
	// 一定時間を待って入力完了と判断
	// =============================================
	// 一定時間後更新を行なう
	if (gTimer) {
		clearTimeout(gTimer);
	}
	// 入力されたため時間を更新する
	gTimer = setTimeout(inputEnd, 10000);
}

function inputEnd() {
	// =========================================================
	// タイマー時間経過で入力完了と判断
	// =========================================================
	// サンプル用の処理です。入れ替えて利用ください
	// 入力完了後のメッセージ入れ替え
	// var wObj = document.getElementById("endMsg");

	// 入力完了と判断してメッセージを設定しておく
	document.getElementById("setMes").value = document.getElementById("inText").value;
	document.getElementById("scroll").value = document.documentElement.scrollTop;
	// wObj.innerHTML = document.getElementById("inText").value;
	// wObj.className = 'defStyle endStyle';

	// サブミットするフォームを取得
	var f = document.forms["myForm"];

	// 更新をおこなう
	f.method = "POST"; // method(GET or POST)を設定する
	f.action = document.getElementById("URL").value; // action(遷移先URL)を設定する
	f.submit(); // submit する
	return true;
}