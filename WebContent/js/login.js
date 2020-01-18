function check(form) {
	if (form.id.value == '') {
		layer.msg('请输入借书证号！', {
			time : 1000
		});
		form.id.focus();
		return false;
	}
	if (form.password.value == '') {
		layer.msg('请输入密码！', {
			time : 1000
		});
		form.password.focus();
		return false;
	}
	if (form.check_code.value == '') {
		layer.msg('请输入验证码！', {
			time : 1000
		});
		form.check_code.focus();
		return false;
	}
	return true;
}
function change() {
	var img = document.getElementById("code_img");
	img.src = img.src + "?";
	var img2 = document.getElementById("code_img2");
	img2.src = img2.src + "?";
}

function showTime() {
	var s = new Date();
	document.getElementById("time").innerHTML = "当前时间：" + s;
	setTimeout(showTime, 1000);
}