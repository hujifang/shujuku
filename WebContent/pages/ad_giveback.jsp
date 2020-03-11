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
<link rel="stylesheet" type="text/css" href="/shujuku/css/book.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>还书管理</title>
</head>
<body>
	<center>
		<div class="table_content" style="margin: 0">
			<table>
				<tr height='50px'>
					<th width="15%">借书证号</th>
					<th width="15%">ISBN</th>
					<th width="25%">书名</th>
					<th width="15%">借阅时间</th>
					<th width="15%">归还时间</th>
					<th width="15%">操作</th>
				</tr>
				<%
					request.setCharacterEncoding("utf-8");
					String search_type = request.getParameter("search_type");
					String keyword = request.getParameter("keyword");
					String pageCtr = request.getParameter("pageCtr");
					final int contentNum = 15;
					
					if(keyword != null && pageCtr!= null){
						keyword = new String(keyword.getBytes("ISO-8859-1"),"UTF-8");
					}
					
					GivebackDao gd = new GivebackDao();
					ArrayList<Giveback> list = new ArrayList<Giveback>();
					
					if(search_type == null || search_type.equals("null") || keyword == ""){
						list = gd.findAll();
					}else{
						list = gd.search(search_type, keyword);
					}
					
					if(pageCtr == null){
						if (session.getAttribute("pageIndex") != null) {
							session.removeAttribute("pageIndex");
						}
						session.setAttribute("pageIndex", "1");
					}else if(pageCtr.equals("first")){
						session.setAttribute("pageIndex", "1");
					}else if(pageCtr.equals("last")){
						String pageIndex = String.valueOf((Integer.parseInt((String)session.getAttribute("pageIndex")) - 1));
						if(Integer.parseInt(pageIndex) < 1){
							session.setAttribute("pageIndex", "1");
						}else{
							session.setAttribute("pageIndex", pageIndex);
						}
					}else if(pageCtr.equals("jump")){
						String jumpto = request.getParameter("jumpto");
						session.setAttribute("pageIndex", jumpto);
					}else if(pageCtr.equals("next")){
						String pageIndex = String.valueOf((Integer.parseInt((String)session.getAttribute("pageIndex")) + 1));
						if(Integer.parseInt(pageIndex) > list.size() / contentNum + 1){
							session.setAttribute("pageIndex", String.valueOf(list.size() / contentNum + 1));
						}else{
							session.setAttribute("pageIndex", pageIndex);
						}
					}else if(pageCtr.equals("lastest")){
						session.setAttribute("pageIndex", String.valueOf(list.size() / contentNum + 1));
					}
					
					if(list.isEmpty()){
						out.print("<tr><td colspan='10'>没有查询结果</td></tr>");
					}else{
						BookDao bd = new BookDao();
						Book book = new Book();
						int index = Integer.parseInt((String)session.getAttribute("pageIndex"));
						for(int i = (index - 1) * contentNum;i < index * contentNum && i < list.size() ;i++){
							book = bd.find("all", "isbn", list.get(i).getISBN()).get(0);
							out.print("<tr>" 
									+ "<td>" + list.get(i).getReaderId() + "</td>" 
									+ "<td>" + list.get(i).getISBN() + "</td>" 
									+ "<td>" + book.getName() + "</td>" 
									+ "<td>" + list.get(i).getBorrowDate() + "</td>" 
									+ "<td>" + list.get(i).getReturnDate() + "</td>" 
									+ "<td>" + "<a href='javascript:void(0);' onclick='deleteGiveback(\"" + list.get(i).getReaderId() + "\",\"" + list.get(i).getISBN() + "\")'>删除</a>" + "</td>" 
									+ "</tr>");
						}
					}
				%>
			</table>
			<script type="text/javascript">
				function deleteGiveback(readerId,isbn) {
					layer.confirm('确定要删除吗?', {
						icon : 3,
						shade : 0,
						title : '确认'
					}, function(index) {
						window.location.href = '/shujuku/DeleteGiveback?readerId=' + readerId + '&isbn=' + isbn;
						layer.close(index);
					});
				}
			</script>
			<table>
				<tr style="float:right;text-align: right;margin: 12px;">
				<td style="border: 0;">共找到<%=list.size() %>条记录
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
				<td style="border: 0;">
				<form name="mypage" method="post">
					<input type="submit" style="width:40px" value="首页" onclick="pageFun('first')">
					<input type="submit" value="上一页" onclick="pageFun('last')">
					<input type="number" id="jumpto" value="<%=Integer.parseInt((String)session.getAttribute("pageIndex")) %>" min="1" max="<%=list.size() / 8 + 1 %>">
					<font style="vertical-align: middle;">/<%=list.size() / contentNum + 1 %></font>
					<input type="submit" style="width:40px" value="跳转" onclick="pageFun('jump')">
					<input type="submit" value="下一页" onclick="pageFun('next')">
					<input type="submit" style="width:40px" value="尾页" onclick="pageFun('lastest')">
				</form>
				</td>
				</tr>
			</table>
		</div>
	</center>
	<script type="text/javascript">
		var search_type = "<%=search_type%>";
		var keyword = "<%=keyword%>";
		
		function pageFun(pageCtr){
			if(pageCtr == 'last'){
				if(<%=Integer.parseInt((String)session.getAttribute("pageIndex")) %> == 1){
					alert("已经是首页!");
					return false;
				}else{
					document.mypage.action="ad_giveback.jsp?search_type=" + search_type + "&keyword=" + keyword + "&pageCtr=" + pageCtr;
					document.mypage.submit();
 				}
			}else if(pageCtr == 'next'){
				if(<%=Integer.parseInt((String)session.getAttribute("pageIndex")) %> == <%=list.size() / contentNum + 1 %>){
					alert("已经是尾页!");
					return false;
				}else{
					document.mypage.action="ad_giveback.jsp?search_type=" + search_type + "&keyword=" + keyword + "&pageCtr=" + pageCtr;
					document.mypage.submit();
 				}
			}else if(pageCtr == 'jump'){
				if(parseInt(document.getElementById('jumpto').value) > <%=list.size() / contentNum + 1 %> || parseInt(document.getElementById('jumpto').value) < 1){
					alert("请正确输入页码!");
					return false;
				}else{
					document.mypage.action="ad_giveback.jsp?search_type=" + search_type + "&keyword=" + keyword + "&pageCtr=" + pageCtr + "&jumpto=" + document.getElementById('jumpto').value;
					document.mypage.submit();
 				}
			}else{
				document.mypage.action="ad_giveback.jsp?search_type=" + search_type + "&keyword=" + keyword + "&pageCtr=" + pageCtr;
				document.mypage.submit();
			}
		}
	</script>
</body>
</html>