/**
 *  buttonのdisable化
 */

$(function(){
	$('button').click(function(){

		$('#click').prop('disabled',true);//ボタンを無効化する
		$('#click').closest('form').submit();//フォームを送信する

	});
});
