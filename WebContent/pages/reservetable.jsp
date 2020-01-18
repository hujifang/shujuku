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
		<h3>预约信息</h3>
		<div class="table_content">
			<table>
				<tr height='50px'>
					<th width="20%">ISBN</th>
					<th width="20%">书名</th>
					<th width="20%">预约时间</th>
					<th width="20%">当前排名</th>
					<th width="20%">操作</th>
				</tr>
				<%
					ReserveDao rsd = new ReserveDao();
					BookDao bd = new BookDao();
					ArrayList<Reserve> reserveList = new ArrayList<Reserve>();
					Reader reader = (Reader)session.getAttribute("reader");
					reserveList = rsd.findAll(reader.getId());
					if(reserveList.isEmpty()){
						out.print("<tr height='50px'>" 
								+ "<td colspan='5'>" + "无在约记录" + "</td>"
								+ "</tr>");
					}else{
						for(int i = 0;i < reserveList.size();i++){
							int rank = 0;
							ArrayList<Reserve> rankList = new ArrayList<Reserve>();
							rankList = rsd.findAllISBN(reserveList.get(i).getISBN());
							for(int j = 0;j < rankList.size();j++){
								if(rankList.get(j).getReaderId().equals(reader.getId())){
									rank = j + 1;
									break;
								}
							}
							out.print("<tr height='50px'>" 
									+ "<td>" + reserveList.get(i).getISBN() + "</td>"
									+ "<td>" + bd.find("all", "isbn", reserveList.get(i).getISBN()).get(0).getName() + "</td>"
									+ "<td>" + reserveList.get(i).getReserveDate() + "</td>"
									+ "<td>" + rank + "</td>"
									+ "<td>" + "<a href='/shujuku/CancelReserve?isbn=" + reserveList.get(i).getISBN() + "'>取消预约</a></td>"
									+ "</tr>");
						}
					}
					
				%>
			</table>
		</div>
	</center>
</body>
</html>