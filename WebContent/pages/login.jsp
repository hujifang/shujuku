<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/shujuku/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/shujuku/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="/shujuku/css/login.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<script type="text/javascript" src="/shujuku/js/login.js"></script>
</head>
<body style="background: url(/shujuku/images/bg.png) no-repeat"
	onpageshow="showTime()" onselectstart="return false"
	ondragstart="return false">
	<div class="logo">
		<table>
			<tr>
				<td>
					<h2>
						<img src="/shujuku/images/logo_school.png">
					</h2>
				</td>
				<td>
					<h3>
						<img src="/shujuku/images/logo_name.png">
					</h3>
				</td>
			<tr>
		</table>
	</div>
	<div class="left">
		<img src="/shujuku/images/login_pic.png" />
	</div>
	<div class="right">
		<center>
			<div id="notice" class="notice">
				<div id="notice-title" class="notice-title">
					<ul>
						<li
							style="border-top-left-radius: 10px; border-right: 1px solid #fff"
							class="select"><a href="#" onclick='return false'>读者登录</a></li>
						<li
							style="border-top-right-radius: 10px; border-left: 1px solid #fff"><a
							href="#" onclick='return false'>管理员登录</a></li>
					</ul>
				</div>

				<div id="notice-content" class="notice-content">
					<!-- 读者 -->
					<div id="module1" class="module" style="display: block">
						<form name="login" action="/shujuku/LoginCheckServlet?type=0"
							target="login" onsubmit="return check(this)" method="post">
							<table border="0">
								<tr>
									<td><b>借书证号：</b></td>
									<td><input type="text" name="id" maxlength="16"
										style="width: 200px"></td>
								</tr>
								<tr>
									<td><b>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</b></td>
									<td><input type="password" name="password" maxlength="16"
										style="width: 200px"></td>
								</tr>
								<tr>
									<td><b>验&nbsp;&nbsp;证&nbsp;&nbsp;码：</b></td>
									<td><input type="text" name="check_code" maxlength="4"
										style="width: 60px"> <img id="code_img"
										src="/shujuku/CheckCodeServlet" align="middle"> <b>
											<a href="javascript:change();" style="font-size: 10px">看不清，换一张</a>
									</b></td>
								</tr>
							</table>
							<br> <input id="bt" type="submit" value="登录">
						</form>
						<h6>
							<a href="register.jsp">没有账号？点这里注册</a>
						</h6>
					</div>
					<!-- 管理员 -->
					<div id="module2" class="module" style="display: none">
						<form name="login" action="/shujuku/LoginCheckServlet?type=1"
							target="login" onsubmit="return check(this)" method="post">
							<table border="0">
								<tr>
									<td><b>账&nbsp;&nbsp;&nbsp;&nbsp;号：</b></td>
									<td><input type="text" name="id" maxlength="16"
										style="width: 200px"></td>
								</tr>
								<tr>
									<td><b>密&nbsp;&nbsp;&nbsp;&nbsp;码：</b></td>
									<td><input type="password" name="password" maxlength="16"
										style="width: 200px"></td>
								</tr>
								<tr>
									<td><b>验证码：</b></td>
									<td><input type="text" name="check_code" maxlength="4"
										style="width: 60px"> <img id="code_img2"
										src="/shujuku/CheckCodeServlet" align="middle"> <b><a
											href="javascript:change();" style="font-size: 10px">看不清，换一张</a></b></td>
								</tr>
							</table>
							<br> <input id="bt" type="submit" value="登录">
						</form>
					</div>
				</div>
			</div>
		</center>
	</div>
	<div class="time" id="time"></div>
	<iframe name="login" src="LoginCheckServlet.java"
		style="width: 0; height: 0; border: none"></iframe>
	<script type="text/javascript" src="/shujuku/js/logintab.js"></script>
</body>
</html>