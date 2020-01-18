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
 * Servlet implementation class GivebackServlet
 */
public class GivebackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GivebackServlet() {
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
		String borrowDate = request.getParameter("borrowDate");
		PrintWriter pw = response.getWriter();
		Reader reader = (Reader) request.getSession().getAttribute("reader");
		GivebackDao gd = new GivebackDao();
		Giveback gb = new Giveback();
		gb.setReaderId(reader.getId());
		gb.setISBN(isbn);
		Calendar calendar = new GregorianCalendar();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		gb.setBorrowDate(borrowDate);
		gb.setReturnDate(year + "-" + month + "-" + day);
		if(gd.insert(gb)) {
			pw.print("<script language='javaScript'> alert('归还成功');</script>");
			response.setHeader("refresh", "0;url=/shujuku/pages/brw&gb.jsp");
		}else {
			pw.print("<script language='javaScript'> alert('归还失败');</script>");
			response.setHeader("refresh", "0;url=/shujuku/pages/brw&gb.jsp");
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
