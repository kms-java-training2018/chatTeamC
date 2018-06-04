
function deleteMessage(){
    // 「OK」時の処理開始 ＋ 確認ダイアログの表示
    if(window.confirm('本当に削除していいですか？')){

        location.href = "/chat/deleteDirectMessage";

        return true;
    }
    // 「OK」時の処理終了

    // cancel
    return false;
}


//メインページに飛ぶ
function mainpage()
{
	location.href = "/chat/mainpage";
	return true;
}

