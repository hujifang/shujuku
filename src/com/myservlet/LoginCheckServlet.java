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
 * Servlet implementation class LoginCheckServlet
 */
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCheckServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		String checkCode = request.getParameter("check_code");
		String savedCode = (String) request.getSession().getAttribute("check_code");
		PrintWriter pw = response.getWriter();
		if (checkCode.equals(savedCode)) {
			if (type.equals("0")) {
				ReaderDao rd = new ReaderDao();
				Reader reader = rd.findById(id);
				if (reader != null) {
					if (reader.getPassword().equals(password)) {
						if (request.getSession().getAttribute("reader") != null) {
							request.getSession().removeAttribute("reader");
						}
						request.getSession().setAttribute("reader", reader);
						pw.print("<script language='javaScript'> top.location.href='/shujuku/index.jsp'</script>");
					} else {
						pw.print("<script language='javaScript'> alert('密码错误，请重新输入');</script>");
					}
				} else {
					pw.print("<script language='javaScript'> alert('用户不存在，请重新输入');</script>");
				}
			} else {
				AdminDao ad = new AdminDao();
				Admin admin = ad.findById(id);
				if (admin != null) {
					if (admin.getPassword().equals(password)) {
						if (request.getSession().getAttribute("admin") != null) {
							request.getSession().removeAttribute("admin");
						}
						request.getSession().setAttribute("admin", admin);
						pw.print("<script language='javaScript'> top.location.href='/shujuku/index.jsp'</script>");
					} else {
						pw.print("<script language='javaScript'> alert('密码错误，请重新输入');</script>");
					}
				} else {
					pw.print("<script language='javaScript'> alert('用户不存在，请重新输入');</script>");
				}
			}
		} else {
			pw.print("<script language='javaScript'> alert('验证码错误，请重新输入');</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
