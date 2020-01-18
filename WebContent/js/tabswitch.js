function $(id) {
	return typeof id === 'string' ? document.getElementById(id) : id;
}

window.onload = function() {
	// 获取鼠标点击的标签和要切换内容的元素
	var titles = $('notice-title').getElementsByTagName('li');
	var divs = new Array(titles.length);
	for (var i = 0; i < titles.length; i++) {
		titles[i].id = i;
		divs[i] = document.getElementById('module' + (i + 1));
	}
	// 遍历titles下所有的li
	for (var i = 0; i < titles.length; i++) {
		titles[i].onclick = function() {
			// 清除所有li上的class
			for (var j = 0; j < titles.length; j++) {
				titles[j].className = '';
				divs[j].style.display = 'none';
				setCookie('tab', this.id, 0);
			}
			// 设置当前为高亮显示
			this.className = 'select';
			divs[this.id].style.display = 'block';
		}
	}

	// 判断是有cookie值
	if (getCookie('tab')) {
		for (var i = 0; i < titles.length; i++) {
			titles[i].className = '';
			divs[i].style.display = 'none'
		}
		titles[getCookie('tab')].className = 'select';
		divs[getCookie('tab')].style.display = 'block';
	}
	// /设置cookie
	function setCookie(name, value, iDay) {
		if (iDay) {
			var oDate = new Date();
			oDate.setDate(oDate.getDate() + iDay);
			document.cookie = name + '=' + value + ';path=/;expires=' + oDate;
		} else {
			document.cookie = name + '=' + value + ';path=/';
		}
	}
	;
	// 获取cookie
	function getCookie(name) {
		var arr = document.cookie.split('; ');//空格必不可少
		for (var i = 0; i < arr.length; i++) {
			var arr1 = arr[i].split('=');
			if (arr1[0] == name) {
				return arr1[1];
			}

		}

		return '';
	}
	
}