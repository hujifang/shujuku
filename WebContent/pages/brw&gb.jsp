<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.javabean.*"%>
<%@ page import="com.jdbc.dao.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/shujuku/css/book.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>数据</title>
</head>
<body>
	<center>
		<h3>在借记录</h3>
		<div class="table_content">
			<table>
				<tr height='50px'>
					<th width="20%">ISBN</th>
					<th width="20%">书名</th>
					<th width="20%">借阅时间</th>
					<th width="20%">应还时间</th>
					<th width="20%">操作</th>
				</tr>
				<%
					BorrowDao brd = new BorrowDao();
					BookDao bd = new BookDao();
					ArrayList<Borrow> borrowList = new ArrayList<Borrow>();
					Reader reader = (Reader)session.getAttribute("reader");
					borrowList = brd.findById(reader.getId());
					if(borrowList.isEmpty()){
						out.print("<tr height='50px'>" 
								+ "<td colspan='5'>" + "无在借记录" + "</td>"
								+ "</tr>");
					}else{
						for(int i = 0;i < borrowList.size();i++){
							out.print("<tr height='50px'>" 
									+ "<td>" + borrowList.get(i).getISBN() + "</td>"
									+ "<td>" + bd.find("all", "isbn", borrowList.get(i).getISBN()).get(0).getName() + "</td>"
									+ "<td>" + borrowList.get(i).getBorrowDate() + "</td>"
									+ "<td>" + borrowList.get(i).getReturnDate() + "</td>"
									+ "<td>" + "<a href='/shujuku/GivebackServlet?isbn=" + borrowList.get(i).getISBN() +"&borrowDate=" + borrowList.get(i).getBorrowDate() + "'>归还</a></td>"
									+ "</tr>");
						}
					}
								
				%>
			</table>
		</div>
		<h3>已还记录</h3>
		<div class="table_content">
			<table>
				<tr height='50px'>
					<th width="20%">ISBN</th>
					<th width="20%">书名</th>
					<th width="20%">借阅时间</th>
					<th width="20%">还书时间</th>
				</tr>
				<%
					GivebackDao gd = new GivebackDao();
					ArrayList<Giveback> gbList = new ArrayList<Giveback>();
					gbList = gd.findById(reader.getId());
					if(gbList.isEmpty()){
						out.print("<tr height='50px'>" 
								+ "<td colspan='4'>" + "无还书记录" + "</td>"
								+ "</tr>");
					}else{
						for(int i = 0;i < gbList.size();i++){
							out.print("<tr height='50px'>" 
									+ "<td>" + gbList.get(i).getISBN() + "</td>"
									+ "<td>" + bd.find("all", "isbn", gbList.get(i).getISBN()).get(0).getName() + "</td>"
									+ "<td>" + gbList.get(i).getBorrowDate() + "</td>"
									+ "<td>" + gbList.get(i).getReturnDate() + "</td>"
									+ "</tr>");
						}
					}
								
				%>
			</table>
		</div>
	</center>
</body>
</html>