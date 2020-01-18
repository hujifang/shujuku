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
function checkAdd(form) {
	if (form.isbn.value == '') {
		layer.msg('请输入ISBN！', {
			time : 1000
		});
		form.isbn.focus();
		return false;
	}
	if (form.name.value == '') {
		layer.msg('请输入书名！', {
			time : 1000
		});
		form.name.focus();
		return false;
	}
	if (form.copyNum.value == '') {
		layer.msg('请输入复本量！', {
			time : 1000
		});
		form.copyNum.focus();
		return false;
	}
	if (form.copyNum.value < 0) {
		layer.msg('复本量不能为负数！', {
			time : 1000
		});
		form.copyNum.focus();
		return false;
	}
	if (form.stockNum.value == '') {
		layer.msg('请输入库存量！', {
			time : 1000
		});
		form.stockNum.focus();
		return false;
	}
	if (form.stockNum.value < 0) {
		layer.msg('库存量不能为负数！', {
			time : 1000
		});
		form.stockNum.focus();
		return false;
	}
	if (form.stockNum.value > form.copyNum.value) {
		layer.msg('库存量不得大于复本量！', {
			time : 1000
		});
		form.stockNum.focus();
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
function addbook() {
	var str = "";
	for(var i = 65;i < 91;i++){
		var s = String.fromCharCode(i);
		str += "<option style='font-family: FangSong;' value='" + s + "'>"+ s + "</option>";
	}
	layer
			.open({
				type : 1,
				title : [ '添加图书', 'font-size:18px;font-family: KaiTi;' ],
				closeBtn : 1,
				shade : [ 0.1, '#000' ],
				shadeClose : true,
				resize : false,
				skin : 'layui-layer-dialog',
				content : "<div><center>"
						+ "<form name='addbook_form' action='/shujuku/AddBook' method='post' target='addBook'><table>"
						+ "<tr><td>图书分类号：</td>"
						+ "<td><select name='classNum' style='height: 24px;border-width: 1px;border-color: #38f;border-radius:5px;font-family: FangSong;'>"
						+ str
						+ "</select></td></tr>"
						+ "<tr><td>ISBN：</td>"
						+ "<td><input type='text' name='isbn' maxlength='13' style='width: 200px'></td></tr>"
						+ "<tr><td>书名：</td>"
						+ "<td><input type='text' name='name' maxlength='30' style='width: 200px'></td></tr>"
						+ "<tr><td>作者：</td>"
						+ "<td><input type='text' name='author' maxlength='20' style='width: 200px'></td></tr>"
						+ "<tr><td>出版社：</td>"
						+ "<td><input type='text' name='press' maxlength='20' style='width: 200px'></td></tr>"
						+ "<tr><td>出版时间：</td>"
						+ "<td><input type='text' name='pubDate' maxlength='10' style='width: 100px'></td></tr>"
						+ "<tr><td>复本量：</td>"
						+ "<td><input type='text' name='copyNum' style='width: 50px'></td></tr>"
						+ "<tr><td>库存量：</td>"
						+ "<td><input type='text' name='stockNum' style='width: 50px'></td></tr>"
						+ "</table></form></center></div>",
				btn : [ '添加', '取消' ],
				btn1 : function(index, layero) {
					if (checkAdd(addbook_form)) {
						addbook_form.submit();
						layer.close(index);
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
		content : "changeAdminInfo.jsp",
		area : [ '380px', '380px' ]
	});
}
function logout() {
	layer.confirm('确定要退出吗?', {
		icon : 3,
		shade : [ 0.2, '#000' ],
		title : '确认'
	}, function(index) {
		window.location.href = '/shujuku/LogoutServlet?type=1';
		layer.close(index);
	});
}