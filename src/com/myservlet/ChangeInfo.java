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
 * Servlet implementation class ChangeInfo
 */
public class ChangeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");// ���뷽ʽ
		Reader reader0 = (Reader)request.getSession().getAttribute("reader");
		Admin admin0 = (Admin)request.getSession().getAttribute("admin");
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
			if(reader0 != null) {
				ReaderDao rd = new ReaderDao();
				Reader reader = rd.findById(reader0.getId());
				reader.setName(name);
				reader.setSex(sex.charAt(0));
				reader.setBirthday(birthday);
				reader.setMajor(major);
				reader.setGrade(Integer.parseInt(grade));
				reader.setClassId(classId);
				reader.setContact(contact);
				reader.setRemarks(remarks);
				rd.update(reader);
				pw.print("<script language='javaScript'> alert('�޸ĳɹ�');</script>");
				pw.print("<script language='javaScript'> top.location.href='/shujuku/index.jsp'</script>");
			}else if(admin0 != null){
				AdminDao ad = new AdminDao();
				Admin admin = ad.findById(admin0.getId());
				admin.setName(name);
				admin.setContact(contact);
				admin.setRemarks(remarks);
				ad.update(admin);
				pw.print("<script language='javaScript'> alert('�޸ĳɹ�');</script>");
				pw.print("<script language='javaScript'> top.location.href='/shujuku/index.jsp'</script>");
			}else {
				pw.print("<script language='javaScript'> alert('����');</script>");
				pw.print("<script language='javaScript'> top.location.href='/shujuku/index.jsp'</script>");
			}
		} else {
			pw.print("<script language='javaScript'> alert('��֤���������������');</script>");
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
