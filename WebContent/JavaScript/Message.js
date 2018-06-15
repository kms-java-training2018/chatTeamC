var gTimer;
function inputText(){
 // =========================================================
 //   入力の度に実行される
 //     入力完了までタイマーで実行待ちする
 //     タイマーまでに次の入力があると、再度タイマー設定
 // =========================================================
    // --- サンプル用 メッセージ出力 -------------------
    var wObj	= document.getElementById("endMsg");
    wObj.innerHTML = '入力中です';
    wObj.className = 'defStyle runStyle';
    // =============================================
    //   一定時間を待って入力完了と判断
    // =============================================
    if(gTimer){clearTimeout(gTimer);}
    gTimer = setTimeout(inputEnd, 2000);
}
function inputEnd(){
 // =========================================================
 //   タイマー時間経過で入力完了と判断
 // =========================================================
    // サンプル用の処理です。入れ替えて利用ください
    // 入力完了後のメッセージ入れ替え
    var wObj	= document.getElementById("endMsg");
    wObj.innerHTML = '入力完了と判定しました<br>入力：'+document.getElementById("inText").value;
    wObj.className = 'defStyle endStyle';

    location.href = "/chat/directMessage?toUserNo=42&Text="+document.getElementById("inText").value;
}