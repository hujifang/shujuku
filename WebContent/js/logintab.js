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
			}
			// 设置当前为高亮显示
			this.className = 'select';
			divs[this.id].style.display = 'block';
		}
	}
}