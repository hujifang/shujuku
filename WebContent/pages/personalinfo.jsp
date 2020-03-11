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
<title>数据</title>
</head>
<body>
	<center>
		<%
			Reader reader0 = (Reader)session.getAttribute("reader");
			ReaderDao rd = new ReaderDao();
			Reader reader = rd.findById(reader0.getId());       //显示数据库中的reader信息而非session中的
		%>
		<h3>个人信息</h3>
		<div class="table_content" style="width: 50%">
			<table>
				<tr>
					<th width="50%">借书证号：</th>
					<td width="50%"><%=reader.getId()%></td>
				</tr>
				<tr>
					<th>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</th>
					<td><%=reader.getName()%></td>
				</tr>
				<tr>
					<th>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</th>
					<td><%=reader.getSex()%></td>
				</tr>
				<tr>
					<th>生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日：</th>
					<td><%=reader.getBirthday()%></td>
				</tr>
				<tr>
					<th>专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</th>
					<td><%=reader.getMajor()%></td>
				</tr>
				<tr>
					<th>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</th>
					<td><%=reader.getGrade()%></td>
				</tr>
				<tr>
					<th>班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</th>
					<td><%=reader.getClassId()%></td>
				</tr>
				<tr>
					<th>借&nbsp;&nbsp;书&nbsp;&nbsp;量：</th>
					<td><%=reader.getBorrowedNum()%></td>
				</tr>
				<tr>
					<th>联系方式：</th>
					<td><%=reader.getContact()%></td>
				</tr>
				<tr>
					<th>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</th>
					<td><%=reader.getRemarks()%></td>
				</tr>
			</table>
		</div>
	</center>
</body>
</html>