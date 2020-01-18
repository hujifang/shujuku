<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.javabean.*"%>
<%@ page import="com.jdbc.dao.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/shujuku/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/shujuku/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="/shujuku/css/register.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>
<script type="text/javascript">
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
		if (form.password2.value == '') {
			layer.msg('请输入重复密码！', {
				time : 1000
			});
			form.password2.focus();
			return false;
		}
		if (form.password.value != form.password2.value) {
			layer.msg('两次密码不一致！', {
				time : 1000
			});
			form.password2.focus();
			return false;
		}
		if (form.name.value == '') {
			layer.msg('请输入姓名！', {
				time : 1000
			});
			form.name.focus();
			return false;
		}
		if (form.major.value == '') {
			layer.msg('请输入专业！', {
				time : 1000
			});
			form.major.focus();
			return false;
		}
		if (form.classId.value == '') {
			layer.msg('请输入班级！', {
				time : 1000
			});
			form.classId.focus();
			return false;
		}
		if (form.contact.value == '') {
			layer.msg('请输入联系方式！', {
				time : 1000
			});
			form.contact.focus();
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
	}
</script>
</head>
<body style="background: url(/shujuku/images/bg.png) no-repeat">
	<div class="reg">
		<center>
			<h2 style="font-family: KaiTi; color: #38f">注册</h2>
			<form name="reg" action="/shujuku/RegisterServlet"
				target="reg_iframe" onsubmit="return check(this)" method="post">
				<table border="0">
					<tr>
						<td><b>借书证号：</b></td>
						<td><input type="text" name="id" maxlength="16"
							style="width: 200px"></td>
						<td><font color="#FF0000">*</font> 将作为登录账号</td>
					</tr>
					<tr>
						<td><b>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</b></td>
						<td><input type="password" name="password" maxlength="16"
							style="width: 200px"></td>
						<td><font color="#FF0000">*</font> 最长不超过16字符</td>
					</tr>
					<tr>
						<td><b>重复密码：</b></td>
						<td><input type="password" name="password2" maxlength="16"
							style="width: 200px"></td>
						<td><font color="#FF0000">*</font></td>
					</tr>
					<tr>
						<td><b>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</b></td>
						<td><input type="text" name="name" maxlength="20"
							style="width: 200px"></td>
						<td><font color="#FF0000">*</font></td>
					</tr>
					<tr>
						<td><b>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</b></td>
						<td><input type="radio" checked name="sex" value='男'>男&nbsp;&nbsp;
							<input type="radio" name="sex" value='女'>女&nbsp;
							<font color="#FF0000">*</font></td>
					</tr>
					<tr>
						<td><b>生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日：</b></td>
						<td><input type="date" name="birthday"></td>
					</tr>
					<tr>
						<td><b>专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</b></td>
						<td><input type="text" name="major" maxlength="20"
							style="width: 200px"></td>
						<td><font color="#FF0000">*</font></td>
					</tr>
					<tr>
						<td><b>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</b></td>
						<td><select name="grade">
								<option value="2018">2018级</option>
								<option value="2017">2017级</option>
								<option value="2016">2016级</option>
								<option value="2015">2015级</option>
						</select>&nbsp;<font color="#FF0000">*</font></td>
					</tr>
					<tr>
						<td><b>班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</b></td>
						<td><input type="text" name="classId" maxlength="20"
							style="width: 200px"></td>
						<td><font color="#FF0000">*</font></td>
					</tr>
					<tr>
						<td><b>联系方式：</b></td>
						<td><input type="text" name="contact" maxlength="20"
							style="width: 200px"></td>
						<td><font color="#FF0000">*</font></td>
					</tr>
					<tr>
						<td><b>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</b></td>
						<td><input type="text" name="remarks" maxlength="20"
							style="width: 200px"></td>
					</tr>
					<tr>
						<td><b>验&nbsp;&nbsp;证&nbsp;&nbsp;码：</b></td>
						<td><input type="text" name="check_code" maxlength="4"
							style="width: 60px"> <img id="code_img"
							src="/shujuku/CheckCodeServlet" align="middle"> <b><a
								href="javascript:change();" style="font-size: 10px">看不清，换一张</a></b></td>
					</tr>
				</table>
				<br> <input id="bt" type="submit" value="注册">
			</form>
		</center>
	</div>
	<iframe name="reg_iframe" src="RegisterServlet.java"
		style="width: 0; height: 0; border: none"></iframe>
</body>
</html>