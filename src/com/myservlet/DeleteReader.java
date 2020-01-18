package com.myservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.dao.ReaderDao;

/**
 * Servlet implementation class DeleteReader
 */
public class DeleteReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReader() {
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
		String id = request.getParameter("id");
		PrintWriter pw = response.getWriter();
		ReaderDao rd = new ReaderDao();
		if(rd.delete(id)) {
			pw.print("<script language='javaScript'> alert('É¾³ý³É¹¦');</script>");
			response.setHeader("refresh", "0;url=/shujuku/pages/ad_reader.jsp");
		}else {
			pw.print("<script language='javaScript'> alert('É¾³ýÊ§°Ü');</script>");
			response.setHeader("refresh", "0;url=/shujuku/pages/ad_reader.jsp");
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
