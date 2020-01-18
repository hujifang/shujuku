package com.myservlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Restore
 */
public class Restore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Restore() {
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
		//String path = request.getParameter("path");
		PrintWriter pw = response.getWriter();
		
		String pghomeString = "C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\";  
		StringBuilder command = new StringBuilder();  
		command.append(pghomeString).append("mysql -uroot -p101079 --default-character-set=utf8 shujuku");
		
		FileInputStream fileInput = null;
		BufferedReader br = null;
		OutputStreamWriter writer = null;
		try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(command.toString());
            
            fileInput = new FileInputStream("E:\\backup\\backup.sql");
            br = new BufferedReader(new InputStreamReader(fileInput, "utf-8"));
            
            String str;
            StringBuffer sb = new StringBuffer();
            while ((str = br.readLine()) != null) {
                sb.append(str + "\r\n");
            }

            writer = new OutputStreamWriter(process.getOutputStream(),"utf-8");
            writer.write(sb.toString());
            writer.flush();
            writer.close();
            
            if (process.waitFor() == 0) {  
				pw.print("<script language='javaScript'> alert('还原成功');</script>");
			} else {  
				pw.print("<script language='javaScript'> alert('还原失败');</script>"); 
			}
            
        } catch (UnsupportedEncodingException e) {
        	pw.print("<script language='javaScript'> alert('"+ e.getMessage().replaceAll("\\\\", "\\\\\\\\") + "');</script>"); 
        } catch (FileNotFoundException e) {
        	pw.print("<script language='javaScript'> alert('"+ e.getMessage().replaceAll("\\\\", "\\\\\\\\") + "');</script>"); 
        } catch (IOException e) {
        	pw.print("<script language='javaScript'> alert('"+ e.getMessage().replaceAll("\\\\", "\\\\\\\\") + "');</script>"); 
        }catch (InterruptedException exception){  
        	pw.print("<script language='javaScript'> alert('"+ exception.getMessage().replaceAll("\\\\", "\\\\\\\\") + "');</script>"); 
        }finally{
			try {
				if(br != null) {
					br.close();	
				}
			} catch (IOException e) {
				pw.print("<script language='javaScript'> alert('"+ e.getMessage().replaceAll("\\\\", "\\\\\\\\") + "');</script>"); 
			}
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
