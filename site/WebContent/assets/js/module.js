function getWidth() {
	try {
		if (self.innerWidth) {
			return self.innerWidth;
		}

		if (document.documentElement && document.documentElement.clientWidth) {
			return document.documentElement.clientWidth;
		}

		if (document.body) {
			return document.body.clientWidth;
		}
	} catch (e) {
		alert('width' + e);
	}
}
