function footerStart(setHeight) {
	if (setHeight != null) {
		var maxY = document.documentElement.scrollHeight;
	} else {
		var maxY = setHeight;
	}
	window.scroll(0, maxY);
}