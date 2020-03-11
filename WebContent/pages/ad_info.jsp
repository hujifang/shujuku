<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.javabean.*"%>
<%@ page import="com.jdbc.dao.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/shujuku/css/index.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人信息</title>
</head>
<body>
	<center>
		<%
			Admin admin0 = (Admin)session.getAttribute("admin");
			AdminDao ad = new AdminDao();
			Admin admin = ad.findById(admin0.getId());
		%>
		<h3>个人信息</h3>
		<div class="table_content" style="width: 50%">
			<table>
				<tr>
					<th width="50%">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</th>
					<td width="50%"><%=admin.getId()%></td>
				</tr>
				<tr>
					<th>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</th>
					<td><%=admin.getName()%></td>
				</tr>
				<tr>
					<th>联系方式：</th>
					<td><%=admin.getContact()%></td>
				</tr>
				<tr>
					<th>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</th>
					<td><%=admin.getRemarks()%></td>
				</tr>
			</table>
		</div>
	</center>
</body>
</html>