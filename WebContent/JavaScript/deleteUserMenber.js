
function deleteUserMenber(groupNo){

    // 「OK」時の処理開始 ＋ 確認ダイアログの表示
    if(window.confirm('本当に脱退していいですか？')){

        location.href = "/chat/secessionGroupMessgeModel?toGroupNo=" + groupNo;

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

