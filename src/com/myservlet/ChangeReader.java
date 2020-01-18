package com.myservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javabean.Admin;
import com.javabean.Reader;
import com.jdbc.dao.ReaderDao;

/**
 * Servlet implementation class ChangeReader
 */
public class ChangeReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeReader() {
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
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String major = request.getParameter("major");
		String grade = request.getParameter("grade");
		String classId = request.getParameter("classId");
		String borrowedNum = request.getParameter("borrowedNum");
		String contact = request.getParameter("contact");
		String remarks = request.getParameter("remarks");
		PrintWriter pw = response.getWriter();

		if(admin != null) {
			ReaderDao rd = new ReaderDao();
			Reader reader = rd.findById(id);
			reader.setName(name);
			reader.setSex(sex.charAt(0));
			reader.setBirthday(birthday);
			reader.setMajor(major);
			reader.setGrade(Integer.parseInt(grade));
			reader.setClassId(classId);
			if(borrowedNum != null) {
				int n = Integer.parseInt(request.getParameter("borrowedNum"));
				reader.setBorrowedNum(n);
			}
			reader.setContact(contact);
			reader.setRemarks(remarks);
			rd.update(reader);
			pw.print("<script language='javaScript'> alert('修改成功');</script>");
			pw.print("<script language='javaScript'> top.location.href='/shujuku/index.jsp'</script>");
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
