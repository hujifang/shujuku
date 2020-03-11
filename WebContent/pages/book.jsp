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
<title>图书数据</title>
</head>
<body>
	<center>
		<div class="table_content" style="margin: 0">
			<table>
				<tr height="50px">
					<th width="5%">图书分类号</th>
					<th width="10%">ISBN</th>
					<th width="25%">书名</th>
					<th width="17%">作者</th>
					<th width="15%">出版社</th>
					<th width="8%">出版时间</th>
					<th width="5%">复本量</th>
					<th width="5%">库存量</th>
					<th width="10%">操作</th>
				</tr>
				<%
					request.setCharacterEncoding("utf-8");
					Reader reader = (Reader)request.getSession().getAttribute("reader");
					Admin admin = (Admin)request.getSession().getAttribute("admin");
					String classNum = request.getParameter("classNum");
					String search_type = request.getParameter("search_type");
					String keyword = request.getParameter("keyword");
					String pageCtr = request.getParameter("pageCtr");
					final int contentNum = 15;
					
					if(keyword != null && pageCtr!= null){
						keyword = new String(keyword.getBytes("ISO-8859-1"),"UTF-8");
					}
					
					BookDao bd = new BookDao();
					ArrayList<Book> list = new ArrayList<Book>();
					
					if(search_type == null || search_type.equals("null") || classNum.equals("all") && keyword == ""){
						list = bd.findAll();
					}else{
						list = bd.find(classNum, search_type, keyword);
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
						int index = Integer.parseInt((String)session.getAttribute("pageIndex"));
						for(int i = (index - 1) * contentNum;i < index * contentNum && i < list.size() ;i++){
							String option = "";
							if(reader != null){
								BorrowDao brd = new BorrowDao();
								Borrow borrow = new Borrow();
								ReserveDao rsd = new ReserveDao();
								Reserve reserve = new Reserve();
								if(brd.findByISBN(reader.getId(), list.get(i).getISBN()) != null){
									option = "已借阅";
								}else if(rsd.findByISBN(reader.getId(), list.get(i).getISBN()) != null){
									option = "已预约";
								}else if(list.get(i).getStockNum() > 0){
									option = "<a href='/shujuku/BorrowServlet?isbn=" + list.get(i).getISBN() + "'>借阅</a>";
								}else{
									option = "<a href='/shujuku/ReserveServlet?isbn=" + list.get(i).getISBN() + "'>预约</a>";
								}
							}else{
								option = "<a href='javascript:void(0);' onclick='deleteBook(\"" + list.get(i).getISBN() + "\")'>刪除</a>" 
										+ "&nbsp;&nbsp;&nbsp;&nbsp;"
										+ "<a href='javascript:void(0);' onclick='updateBook(\"" + list.get(i).getISBN() + "\")'>修改</a>";
							}
							out.print("<tr>" 
									+ "<td>" + list.get(i).getClassNum() + "</td>" 
									+ "<td>" + list.get(i).getISBN() + "</td>" 
									+ "<td>" + list.get(i).getName() + "</td>" 
									+ "<td>" + list.get(i).getAuthor() + "</td>" 
									+ "<td>" + list.get(i).getPress() + "</td>" 
									+ "<td>" + list.get(i).getPubDate() + "</td>" 
									+ "<td>" + list.get(i).getCopyNum() + "</td>" 
									+ "<td>" + list.get(i).getStockNum() + "</td>" 
									+ "<td>" + option + "</td>" 
									+ "</tr>");
						}
					}
				%>
			</table>
			<script type="text/javascript">
				function deleteBook(isbn) {
					layer.confirm('确定要删除吗?', {
						icon : 3,
						shade : 0,
						title : '确认'
					}, function(index) {
						window.location.href = '/shujuku/DeleteBook?isbn=' + isbn;
						layer.close(index);
					});
				}
				function updateBook(isbn) {
					layer.open({
						type : 2,
						title : [ '修改图书信息', 'font-size:18px;font-family: KaiTi;' ],
						closeBtn : 1,
						shade : 0,
						shadeClose : true,
						resize : false,
						content : "changeBook.jsp?isbn=" + isbn,
						area : [ '380px', '370px' ]
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
		var classNum = "<%=classNum%>";
		var search_type = "<%=search_type%>";
		var keyword = "<%=keyword%>";
		
		function pageFun(pageCtr){
			if(pageCtr == 'last'){
				if(<%=Integer.parseInt((String)session.getAttribute("pageIndex")) %> == 1){
					alert("已经是首页!");
					return false;
				}else{
					document.mypage.action="book.jsp?classNum=" + classNum + "&search_type=" + search_type + "&keyword=" + keyword + "&pageCtr=" + pageCtr;
					document.mypage.submit();
 				}
			}else if(pageCtr == 'next'){
				if(<%=Integer.parseInt((String)session.getAttribute("pageIndex")) %> == <%=list.size() / contentNum + 1 %>){
					alert("已经是尾页!");
					return false;
				}else{
					document.mypage.action="book.jsp?classNum=" + classNum + "&search_type=" + search_type + "&keyword=" + keyword + "&pageCtr=" + pageCtr;
					document.mypage.submit();
 				}
			}else if(pageCtr == 'jump'){
				if(parseInt(document.getElementById('jumpto').value) > <%=list.size() / contentNum + 1 %> || parseInt(document.getElementById('jumpto').value) < 1){
					alert("请正确输入页码!");
					return false;
				}else{
					document.mypage.action="book.jsp?classNum=" + classNum + "&search_type=" + search_type + "&keyword=" + keyword + "&pageCtr=" + pageCtr + "&jumpto=" + document.getElementById('jumpto').value;
					document.mypage.submit();
 				}
			}else{
				document.mypage.action="book.jsp?classNum=" + classNum + "&search_type=" + search_type + "&keyword=" + keyword + "&pageCtr=" + pageCtr;
				document.mypage.submit();
			}
		}
	</script>
</body>
</html>