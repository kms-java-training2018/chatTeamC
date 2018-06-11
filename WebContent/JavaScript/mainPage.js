dispCopyright();
scrool();

function dispCopyright(){
	document.write( window.parent.screen.height);
}

function scrool(){
	document.write("呼び出されてるよー");
	window.scrollBy(0,window.parent.screen.height);
	document.write("ちゃんと呼ばれてるよー");
}
