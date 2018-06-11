dispCopyright();
scrool();

function dispCopyright(){
	document.write( window.parent.screen.height);
}

function scrool(){
	window.scrollBy(0,window.parent.screen.height);
}
