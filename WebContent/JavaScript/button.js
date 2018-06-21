/**
 *  buttonのdisable化
 */

var set = false;
function nidoosi() {
	if (!set) {
		set = true;
	} else {
		alert("二度押しはいけません。\nそのままお待ちください。");
		return false;
	}
}