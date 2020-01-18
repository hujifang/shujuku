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
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		request.setCharacterEncoding("utf-8");// 解码方式
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String major = request.getParameter("major");
		String grade = request.getParameter("grade");
		String classId = request.getParameter("classId");
		String contact = request.getParameter("contact");
		String remarks = request.getParameter("remarks");
		String checkCode = request.getParameter("check_code");
		String savedCode = (String) request.getSession().getAttribute("check_code");
		PrintWriter pw = response.getWriter();

		if (checkCode.equals(savedCode)) {
			ReaderDao rd = new ReaderDao();
			Reader reader = rd.findById(id);
			if (reader == null) {
				reader = new Reader();
				reader.setId(id);
				reader.setPassword(password);
				reader.setName(name);
				reader.setSex(sex.charAt(0));
				reader.setBirthday(birthday);
				reader.setMajor(major);
				reader.setGrade(Integer.parseInt(grade));
				reader.setClassId(classId);
				reader.setBorrowedNum(0);
				reader.setContact(contact);
				reader.setRemarks(remarks);
				rd.insert(reader);
				if (request.getSession().getAttribute("reader") != null) {
					request.getSession().removeAttribute("reader");
				}
				request.getSession().setAttribute("reader", reader);
				pw.print("<script language='javaScript'> top.location.href='/shujuku/pages/registersuccess.jsp'</script>");
			} else {
				pw.print("<script language='javaScript'> alert('该用户已存在，请重新输入');</script>");
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
