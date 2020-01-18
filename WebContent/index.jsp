<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.javabean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
</head>
<body>
	<%
		Reader reader = (Reader) session.getAttribute("reader");
		Admin admin = (Admin) session.getAttribute("admin");
		if (reader == null) {
			if (admin == null) {
				response.sendRedirect("pages/login.jsp");
			} else {
				//创建Cookie存放Session的标识号
				Cookie cookie = new Cookie("JSESSIONID", session.getId());
				cookie.setMaxAge(60 * 30);
				cookie.setPath("/shujuku");
				response.addCookie(cookie);
				//跳转相应首页
				response.sendRedirect("pages/admin_index.jsp");
			}
		} else {
			//创建Cookie存放Session的标识号
			Cookie cookie = new Cookie("JSESSIONID", session.getId());
			cookie.setMaxAge(60 * 30);
			cookie.setPath("/shujuku");
			response.addCookie(cookie);
			//跳转相应首页
			response.sendRedirect("pages/reader_index.jsp");
		}
	%>
</body>
</html>