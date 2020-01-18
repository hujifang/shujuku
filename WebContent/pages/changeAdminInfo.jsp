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
<link rel="stylesheet" type="text/css" href="/shujuku/css/changeInfo.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>
<script type="text/javascript">
	function check(form) {
		if (form.name.value == '') {
			layer.msg('请输入姓名！', {
				time : 1000
			});
			form.name.focus();
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
	function queren(form){
		if(check(form)){
			layer.confirm('确定要修改吗?', {
				icon : 3,
				shade : 0,
				title : '确认'
			}, function(index) {
				form.submit();
			    layer.close(index);
			});
		}
	}
	function change() {
		var img = document.getElementById("code_img");
		img.src = img.src + "?";
	}
</script>
</head>
<body>
	<%
		Admin admin0 = (Admin)session.getAttribute("admin");
		AdminDao ad = new AdminDao();
		Admin admin = ad.findById(admin0.getId());
	%>
	<iframe name="change_iframe" src="ChangeInfo.java"
		style="width: 0; height: 0; border: none"></iframe>
	<div class="reg">
		<center>
			<br>
			<form name="change" action="/shujuku/ChangeInfo"
				target="change_iframe" method="post">
				<table border="0">
					<tr>
						<td><p style='font-size:16px'>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</p></td>
						<td><input type="text" name="name" maxlength="20"
							style="width: 200px" value="<%=admin.getName()%>"></td>
					</tr>
					<tr>
						<td><p style='font-size:16px'>联系方式：</p></td>
						<td><input type="text" name="contact" maxlength="20"
							style="width: 200px" value="<%=admin.getContact()%>"></td>
					</tr>
					<tr>
						<td><p style='font-size:16px'>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</p></td>
						<td><input type="text" name="remarks" maxlength="20"
							style="width: 200px" value="<%=admin.getRemarks()%>"></td>
					</tr>
					<tr>
						<td><p style='font-size:16px'>验&nbsp;&nbsp;证&nbsp;&nbsp;码：</p></td>
						<td><input type="text" name="check_code" maxlength="4"
							style="width: 60px"> <img id="code_img"
							src="/shujuku/CheckCodeServlet" align="middle"> <b><a
								href="javascript:change();" style="font-size: 10px">看不清，换一张</a></b></td>
					</tr>
				</table>
			</form>
			<br>
			<button onclick="queren(change)">提交</button>
		</center>
	</div>
</body>
</html>