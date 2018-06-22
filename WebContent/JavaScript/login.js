/**
 * LoginPageのパスワード確認ボタン
 */

function changeInputType(inputId, type) {
	var input = $("#" + inputId);
	$(input).replaceWith(
			$("<input />").val(input.val()).attr("place", input.attr("place"))
					.attr("id", inputId).attr("type", type).attr("name","password").attr("class","fontSize"));
}
function connecttext(ischecked) {
	if (ischecked == true) {
		// チェックが入っていたら有効化
		changeInputType("password", "text");
	} else {
		// チェックが入っていなかったら無効化
		changeInputType("password", "password");
	}
}