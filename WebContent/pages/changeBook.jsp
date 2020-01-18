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
</script>
</head>
<body>
	<%
		String isbn = request.getParameter("isbn");
		BookDao bd = new BookDao();
		Book book = bd.find("all", "isbn", isbn).get(0);
	%>
	<iframe name="change_iframe" src="UpdateBook.java"
		style="width: 0; height: 0; border: none"></iframe>
	<div class="reg">
		<center>
			<br>
			<form name="change" action="/shujuku/UpdateBook?isbn=<%=isbn %>"
				target="change_iframe" method="post">
				<table border="0">
					<tr>
						<td>图书分类号：</td>
						<td>
						<select name='classNum'>
						<%
							int n = book.getClassNum();
							for(int i = 'A';i <= 'Z';i++){
								if(i == n){
									out.print("<option selected value='" + (char)i + "'>"+ (char)i + "</option>");
								}else{
									out.print("<option value='" + (char)i + "'>"+ (char)i + "</option>");
								}
							}
						%>
						</select>
						</td>
					</tr>
					<tr>
						<td>I&nbsp;&nbsp;&nbsp;&nbsp;S&nbsp;&nbsp;&nbsp;B&nbsp;&nbsp;&nbsp;&nbsp;N：</td>
						<td><%=book.getISBN()%></td>
					</tr>
					<tr>
						<td>书&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
						<td><input type="text" name="name" maxlength="30"
							style="width: 200px" value="<%=book.getName()%>"></td>
					</tr>
					<tr>
						<td>作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者：</td>
						<td><input type="text" name="author" maxlength="20"
							style="width: 200px" value="<%=book.getAuthor()%>"></td>
					</tr>
					<tr>
						<td>出&nbsp;&nbsp;&nbsp;&nbsp;版&nbsp;&nbsp;&nbsp;&nbsp;社：</td>
						<td><input type="text" name="press" maxlength="20"
							style="width: 200px" value="<%=book.getPress()%>"></td>
					</tr>
					<tr>
						<td>出&nbsp;&nbsp;版&nbsp;时&nbsp;间：</td>
						<td><input type="text" name="pubDate" maxlength="10"
							style="width: 100px" value="<%=book.getPubDate()%>"></td>
					</tr>
					<tr>
						<td>复&nbsp;&nbsp;&nbsp;&nbsp;本&nbsp;&nbsp;&nbsp;&nbsp;量：</td>
						<td><input type="text" name="copyNum" style="width: 50px" value="<%=book.getCopyNum()%>"></td>
					</tr>
					<tr>
						<td>库&nbsp;&nbsp;&nbsp;&nbsp;存&nbsp;&nbsp;&nbsp;&nbsp;量：</td>
						<td><input type="text" name="stockNum" style="width: 50px" value="<%=book.getStockNum()%>"></td>
					</tr>
				</table>
			</form>
			<br>
			<button onclick="queren(change)">修改</button>
		</center>
	</div>
</body>
</html>