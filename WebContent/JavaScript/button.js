/**
 *  buttonのdisable化
 */

$(function(){
	$('.click').click(function(){

		$(this).prop('disabled',true);//ボタンを無効化する
		$(this).closest('form').submit();//フォームを送信する

	});
});