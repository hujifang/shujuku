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
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
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
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		String checkCode = request.getParameter("check_code");
		String savedCode = (String) request.getSession().getAttribute("check_code");
		PrintWriter pw = response.getWriter();
		Reader reader = (Reader) request.getSession().getAttribute("reader");
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (checkCode.equals(savedCode)) {
			if (reader != null) {
				if (oldpassword.equals(reader.getPassword())) {
					ReaderDao rd = new ReaderDao();
					reader.setPassword(newpassword);
					if (rd.update(reader)) {
						request.getSession().removeAttribute("reader");
						pw.print("<script language='javaScript'> alert('�޸ĳɹ��������µ�¼');</script>");
						pw.print("<script language='javaScript'> top.location.href='/shujuku/index.jsp'</script>");
					} else {
						pw.print("<script language='javaScript'> alert('�޸�ʧ��');</script>");
					}
				} else {
					pw.print("<script language='javaScript'> alert('�����������������');</script>");
				}
			}else {
				if (oldpassword.equals(admin.getPassword())) {
					AdminDao ad = new AdminDao();
					admin.setPassword(newpassword);
					if (ad.update(admin)) {
						request.getSession().removeAttribute("admin");
						pw.print("<script language='javaScript'> alert('�޸ĳɹ��������µ�¼');</script>");
						pw.print("<script language='javaScript'> top.location.href='/shujuku/index.jsp'</script>");
					} else {
						pw.print("<script language='javaScript'> alert('�޸�ʧ��');</script>");
					}
				} else {
					pw.print("<script language='javaScript'> alert('�����������������');</script>");
				}
			}
		}else {
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
