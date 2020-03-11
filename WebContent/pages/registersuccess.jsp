<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册成功</title>
</head>
<body>
	<div style="width: 500px; height: 100px; position: absolute; left: 0; right: 0; bottom: 0; top: 0; margin: auto;">
		<h1 style="font-family: KaiTi; color: green">
			<img src="/shujuku/images/success.jpg" height=25px width=25px>
			注册成功，将在3秒后跳转首页
		</h1>
		<%
			response.setHeader("refresh", "3;url=/shujuku/index.jsp");
		%>
	</div>
</body>
</html>