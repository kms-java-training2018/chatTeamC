
function logout(){

    // 「OK」時の処理開始 ＋ 確認ダイアログの表示
    if(window.confirm('本当にログアウトしてよろしいですか？')){

        location.href = "/chat/logOut";

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

