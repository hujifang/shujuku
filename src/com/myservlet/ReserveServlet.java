package com.myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javabean.*;
import com.jdbc.dao.*;

/**
 * Servlet implementation class ReserveServlet
 */
public class ReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String isbn = request.getParameter("isbn");
		PrintWriter pw = response.getWriter();
		Reader reader = (Reader) request.getSession().getAttribute("reader");
		ReserveDao rd = new ReserveDao();
		Reserve reserve = new Reserve();
		reserve.setReaderId(reader.getId());
		reserve.setISBN(isbn);
		Calendar calendar = new GregorianCalendar();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		reserve.setReserveDate(year + "-" + month + "-" + day);
		if(rd.insert(reserve)) {
			pw.print("<script language='javaScript'> alert('预约成功');</script>");
			response.setHeader("refresh", "0;url=/shujuku/pages/book.jsp");
		}else {
			pw.print("<script language='javaScript'> alert('预约失败');</script>");
			response.setHeader("refresh", "0;url=/shujuku/pages/book.jsp");
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
