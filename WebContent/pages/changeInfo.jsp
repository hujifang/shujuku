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
<title>修改信息</title>
<script type="text/javascript">
	function check(form) {
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
		Reader reader0 = (Reader)session.getAttribute("reader");
		ReaderDao rd = new ReaderDao();
		Reader reader = rd.findById(reader0.getId());
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
						<td>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
						<td><input type="text" name="name" maxlength="20"
							style="width: 200px" value="<%=reader.getName()%>"></td>
					</tr>
					<tr>
						<td>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
						<td>
							<%
								if(reader.getSex() == '男'){
									out.print("<input type='radio' checked name='sex' value='男'>男&nbsp;&nbsp;"
											+ "<input type='radio' name='sex' value='女'>女");
								}else{
									out.print("<input type='radio' name='sex' value='男'>男&nbsp;&nbsp;"
											+ "<input type='radio' checked name='sex' value='女'>女");
								}
							%>
						</td>
					</tr>
					<tr>
						<td>生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日：</td>
						<td><input type="date" name="birthday" value="<%=reader.getBirthday()%>"></td>
					</tr>
					<tr>
						<td>专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</td>
						<td><input type="text" name="major" maxlength="20"
							style="width: 200px" value="<%=reader.getMajor()%>"></td>
					</tr>
					<tr>
						<td>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</td>
						<td><select name="grade">
							<%
								for(int i = 2015;i < 2019;i++){
									if(i == reader.getGrade()){
										out.print("<option selected value='" + i + "'>" + i + "级</option>");
									}else{
										out.print("<option value='" + i + "'>" + i + "级</option>");
									}
								}
							%>
						</select></td>
					</tr>
					<tr>
						<td>班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</td>
						<td><input type="text" name="classId" maxlength="20"
							style="width: 200px" value="<%=reader.getClassId()%>"></td>
					</tr>
					<tr>
						<td>联系方式：</td>
						<td><input type="text" name="contact" maxlength="20"
							style="width: 200px" value="<%=reader.getContact()%>"></td>
					</tr>
					<tr>
						<td>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
						<td><input type="text" name="remarks" maxlength="20"
							style="width: 200px" value="<%=reader.getRemarks()%>"></td>
					</tr>
					<tr>
						<td>验&nbsp;&nbsp;证&nbsp;&nbsp;码：</td>
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