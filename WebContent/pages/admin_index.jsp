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
<link rel="stylesheet" type="text/css" href="/shujuku/css/index.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
</head>
<script type="text/javascript" src="/shujuku/js/admin_index.js"></script>
<body onselectstart="return false" ondragstart="return false">
	<div>
		<table>
			<tr height='88px'>
				<td>
						<img src="/shujuku/images/logo_school.png">
				</td>
				<td>
						<img src="/shujuku/images/logo_name.png">
				</td>
				<td width=100%>
					<div align=right style="margin-right: 20px;">
						<h5>
							<%
								Admin admin = (Admin) session.getAttribute("admin");
								if (admin == null) {
									response.sendRedirect("login.jsp");
								} else {
									out.print("欢迎您，" + admin.getName()
									+ "【<a href='javascript:void(0);' onclick='changepassword()'>修改密码</a>】"
									+ "【<a href='javascript:void(0);' onclick='logout()'>退出</a>】");
								}
							%>
						</h5>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<iframe name='changePass' src='ChangePassword.java' style='width: 0; height: 0; border: none'></iframe>
	<div id="notice" class="notice">
		<!-- 上面的标题栏 -->
		<div id="notice-title" class="notice-title">
			<ul>
				<li class="select"><a href="#" onclick='return false'>图书管理</a></li>
				<li><a href="#" onclick='return false'>借书管理</a></li>
				<li><a href="#" onclick='return false'>还书管理</a></li>
				<li><a href="#" onclick='return false'>读者管理</a></li>
				<li><a href="#" onclick='return false'>信息管理</a></li>
				<li><a href="#" onclick='return false'>备份与还原</a></li>
			</ul>
		</div>

		<!-- 下面的内容部分 -->
		<div id="notice-content" class="notice-content">
			<!-- 图书管理 -->
			<div id="module1" class="module" style="display: block">
				<center>
					<form action="book.jsp" target="book" method="post">
						<font style="font-size:16px;font-family: FangSong;">图书分类号：</font>
						<select name="classNum" style="height:28px;font-size:16px;font-family: FangSong;">
							<option selected value="all">全部</option>
							<%
								BookDao bd = new BookDao();
								char[] list = bd.findClassNum();
								for(int i = 0;i < list.length;i++){
									out.print("<option value='" + list[i] + "'>" + list[i] + "</option>");
								}
							%>
						</select>
						&nbsp;&nbsp;&nbsp;
						<select name="search_type" style="height:28px;font-size:16px;font-family: FangSong;">
							<option selected value="isbn">按ISBN检索</option>
							<option value="name">按书名检索</option>
							<option value="author">按作者检索</option>
							<option value="press">按出版社检索</option>
						</select>
						<input type="text" name="keyword"
							style="width: 450px; height: 24px; font-size: 16px;">
						<input type="submit" value="查询"
							style="height: 28px; font-size: 16px; font-family: KaiTi;">
						<button type="button" onclick='addbook()' 
						style="float: right;height: 28px; font-size: 16px; font-family: KaiTi;margin:0 30px 0 0">添加</button>
					</form>
					<iframe name="book" src="book.jsp"
						style="width: 100%; height: 540px; border: none"></iframe>
					<iframe name='addBook' src='AddBook.java'
						style='width: 0; height: 0; border: none'></iframe>
				</center>
			</div>
			<!-- 借书管理 -->
			<div id="module2" class="module">
				<center>
					<form action="ad_borrow.jsp" target="borrow" method="post">
						<select name="search_type" style="height:28px;font-size:16px;font-family: FangSong;">
							<option selected value="readerId">按借书证号检索</option>
							<option value="isbn">按ISBN检索</option>
							<option value="borrowDate">按借书时间检索</option>
						</select>
						<input type="text" name="keyword"
							style="width: 450px; height: 24px; font-size: 16px;">
						<input type="submit" value="查询"
							style="height: 28px; font-size: 16px; font-family: KaiTi;">
					</form>
					<iframe name="borrow" src="ad_borrow.jsp"
						style="width: 100%; height: 540px; border: none"></iframe>
				</center>
			</div>
			<!-- 还书管理 -->
			<div id="module3" class="module">
				<center>
					<form action="ad_giveback.jsp" target="giveback" method="post">
						<select name="search_type" style="height:28px;font-size:16px;font-family: FangSong;">
							<option selected value="readerId">按借书证号检索</option>
							<option value="isbn">按ISBN检索</option>
							<option value="returnDate">按还书时间检索</option>
						</select>
						<input type="text" name="keyword"
							style="width: 450px; height: 24px; font-size: 16px;">
						<input type="submit" value="查询"
							style="height: 28px; font-size: 16px; font-family: KaiTi;">
					</form>
					<iframe name="giveback" src="ad_giveback.jsp"
						style="width: 100%; height: 540px; border: none"></iframe>
				</center>
			</div>
			<!-- 读者管理 -->
			<div id="module4" class="module">
				<center>
					<form action="ad_reader.jsp" target="reader" method="post">
						<select name="search_type" style="height:28px;font-size:16px;font-family: FangSong;">
							<option selected value="readerId">按借书证号检索</option>
							<option value="name">按姓名检索</option>
						</select>
						<input type="text" name="keyword"
							style="width: 450px; height: 24px; font-size: 16px;">
						<input type="submit" value="查询"
							style="height: 28px; font-size: 16px; font-family: KaiTi;">
					</form>
					<iframe name="reader" src="ad_reader.jsp"
						style="width: 100%; height: 540px; border: none"></iframe>
				</center>
			</div>
			<!-- 信息管理 -->
			<div id="module5" class="module">
				<center>
					<iframe name="ad_info" src="ad_info.jsp"
						style="width: 100%; height: 170px; border: none"></iframe>
					<button onclick="changeinfo()">修改信息</button>
					
				</center>
			</div>
			<!-- 备份与还原 -->
			<div id="module6" class="module">
				<center>
				<br>
					<table width="30%">
						<tr>
							<td width="50%" align="center">
								<form action="/shujuku/Backup" target="backup">
									<input type="submit" value="一键备份"
										style="height: 28px; font-size: 16px; font-family: KaiTi;">
								</form>
							</td>
							<td width="50%" align="center">
								<form action="/shujuku/Restore" target="restore">
									<input type="submit" value="一键还原"
										style="height: 28px; font-size: 16px; font-family: KaiTi;">
								</form>
							</td>
						</tr>
					</table>
					<iframe name="backup" src="Backup.java"
						style="width: 0; height: 0; border: none"></iframe>
					<iframe name="restore" src="Restore.java"
						style="width: 0; height: 0; border: none"></iframe>
				</center>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/shujuku/js/tabswitch.js"></script>
</body>
</html>