function footerStart(setHeight) {

	var maxY
	if (setHeight == null) {
		maxY = document.documentElement.scrollHeight;
	} else {
		maxY = setHeight;
	}
	window.scroll(0, maxY);
	return true;
}