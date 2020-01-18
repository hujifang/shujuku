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
 * Servlet implementation class CancelReserve
 */
public class CancelReserve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelReserve() {
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
		if(rd.delete(reader.getId(),isbn)) {
			pw.print("<script language='javaScript'> alert('取消预约成功');</script>");
			response.setHeader("refresh", "0;url=/shujuku/pages/reservetable.jsp");
		}else {
			pw.print("<script language='javaScript'> alert('取消预约失败');</script>");
			response.setHeader("refresh", "0;url=/shujuku/pages/reservetable.jsp");
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
