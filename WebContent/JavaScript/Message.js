var gTimer = setTimeout(inputEnd, 10000);

var one = false;


function firstscript() {
	document.getElementById('inText').focus();
	var len = document.getElementById('inText').value.length;
	setSelectionRange(len,len)
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
	document.getElementById("setMes").value = document.getElementById("inText").value;
	// wObj.className = 'defStyle runStyle';
	// =============================================
	// 一定時間を待って入力完了と判断
	// =============================================
	if (gTimer) {
		clearTimeout(gTimer);
	}
	gTimer = setTimeout(inputEnd, 10000);
}

function inputEnd() {
	// =========================================================
	// タイマー時間経過で入力完了と判断
	// =========================================================
	// サンプル用の処理です。入れ替えて利用ください
	// 入力完了後のメッセージ入れ替え
	// var wObj = document.getElementById("endMsg");
	document.getElementById("setMes").value = document.getElementById("inText").value;
	// wObj.innerHTML = document.getElementById("inText").value;
	// wObj.className = 'defStyle endStyle';

	// サブミットするフォームを取得
	var f = document.forms["myForm"];

	f.method = "POST"; // method(GET or POST)を設定する
	f.action = "/chat/directMessage"; // action(遷移先URL)を設定する
	f.submit(); // submit する
	return true;
}