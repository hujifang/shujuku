function check(form) {
	if (form.oldpassword.value == '') {
		layer.msg('请输入旧密码！', {
			time : 1000
		});
		form.oldpassword.focus();
		return false;
	}
	if (form.newpassword.value == '') {
		layer.msg('请输入新密码！', {
			time : 1000
		});
		form.newpassword.focus();
		return false;
	}
	if (form.newpassword2.value == '') {
		layer.msg('请再次输入新密码！', {
			time : 1000
		});
		form.newpassword2.focus();
		return false;
	}
	if (form.newpassword.value != form.newpassword2.value) {
		layer.msg('两次密码不一致！', {
			time : 1000
		});
		form.newpassword2.focus();
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
function changeCheckCode() {
	var img = document.getElementById("code_img");
	img.src = img.src + "?";
}
function changepassword() {
	layer
			.open({
				type : 1,
				title : [ '修改密码', 'font-size:18px;font-family: KaiTi;' ],
				closeBtn : 1,
				shade : [ 0.1, '#000' ],
				shadeClose : true,
				resize : false,
				skin : 'layui-layer-dialog',
				content : "<div><center>"
						+ "<form name='changePassword' action='/shujuku/ChangePassword' method='post' target='changePass'><table>"
						+ "<tr><td><p style='font-size:16px'>旧&nbsp;&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;&nbsp;码：</p></td>"
						+ "<td><input type='password' name='oldpassword' maxlength='16'></td></tr>"
						+ "<tr><td><p style='font-size:16px'>新&nbsp;&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;&nbsp;码：</p></td>"
						+ "<td><input type='password' name='newpassword' maxlength='16'></td></tr>"
						+ "<tr><td><p style='font-size:16px'>确认新密码：</p></td>"
						+ "<td><input type='password' name='newpassword2' maxlength='16'></td></tr>"
						+ "<tr><td><p style='font-size:16px'>验&nbsp;&nbsp;&nbsp;&nbsp;证&nbsp;&nbsp;&nbsp;&nbsp;码：</p></td>"
						+ "<td><input type='text' name='check_code' maxlength='4' style='width: 60px;height: 23px;font-size: 17px;border-radius:5px;'>"
						+ "<img id='code_img' src='/shujuku/CheckCodeServlet' align='middle'><a href='javascript:changeCheckCode();' style='font-size: 10px'>看不清，换一张</a></td></tr>"
						+ "</table></form></center></div>",
				btn : [ '提交', '取消' ],
				btn1 : function(index, layero) {
					if (check(changePassword)) {
						layer.confirm('确定要修改吗?', {
							icon : 3,
							shade : [ 0.2, '#000' ],
							title : '确认'
						}, function(index) {
							changePassword.submit();
							layer.close(index);
						});
					}
				},
				btnAlign : 'c'
			});
}
function changeinfo() {
	layer.open({
		type : 2,
		title : [ '修改信息', 'font-size:18px;font-family: KaiTi;' ],
		closeBtn : 1,
		shade : [ 0.1, '#000' ],
		shadeClose : true,
		resize : false,
		content : "changeInfo.jsp",
		area : [ '380px', '400px' ]
	});
}
function logout() {
	layer.confirm('确定要退出吗?', {
		icon : 3,
		shade : [ 0.2, '#000' ],
		title : '确认'
	}, function(index) {
		window.location.href = '/shujuku/LogoutServlet?type=0';
		layer.close(index);
	});
}