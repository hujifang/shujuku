package com.myservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javabean.*;
import com.jdbc.dao.*;

/**
 * Servlet implementation class UpateBook
 */
public class UpdateBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");// 解码方式
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		String classNum = request.getParameter("classNum");
		String isbn = request.getParameter("isbn");
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String press = request.getParameter("press");
		String pubDate = request.getParameter("pubDate");
		String copyNum = request.getParameter("copyNum");
		String stockNum = request.getParameter("stockNum");
		PrintWriter pw = response.getWriter();

		if(admin != null){
			BookDao bd = new BookDao();
			Book book = new Book();
			book.setClassNum(classNum.charAt(0));
			book.setISBN(isbn);
			book.setName(name);
			book.setAuthor(author);
			book.setPress(press);
			book.setPubDate(pubDate);
			book.setCopyNum(Integer.parseInt(copyNum));
			book.setStockNum(Integer.parseInt(stockNum));
			if(bd.update(book)) {
				pw.print("<script language='javaScript'> alert('修改成功');</script>");
				pw.print("<script language='javaScript'> top.location.href='/shujuku/index.jsp'</script>");
			}else {
				pw.print("<script language='javaScript'> alert('修改失败');</script>");
				pw.print("<script language='javaScript'> top.location.href='/shujuku/index.jsp'</script>");
			}
		}else {
			pw.print("<script language='javaScript'> alert('错误');</script>");
			pw.print("<script language='javaScript'> top.location.href='/shujuku/index.jsp'</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
