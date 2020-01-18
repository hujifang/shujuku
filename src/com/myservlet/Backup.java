package com.myservlet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Backup
 */
public class Backup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Backup() {
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
		/*String path = request.getParameter("path");
		
		Calendar calendar = new GregorianCalendar();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		path += year + "-" + month + "-" + day + "-" + hours + "-" + minute + "-" + second + ".sql";*/
		
		String pghomeString = "C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\";  
		StringBuilder command = new StringBuilder();  
		command.append(pghomeString).append("mysqldump -uroot -p101079 --opt --set-charset=utf8 shujuku");
				
		PrintWriter printWriter = null;
		PrintWriter pw = response.getWriter();
		FileOutputStream fileOutput = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(command.toString());
			
			fileOutput = new FileOutputStream("E:\\backup\\backup.sql");
			printWriter = new PrintWriter(new OutputStreamWriter(fileOutput,"utf8")); 
			
			inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
			bufferedReader = new BufferedReader(inputStreamReader); 
			
			String line;  
			while((line = bufferedReader.readLine()) != null){  
				printWriter.println(line);  
			}  
			printWriter.flush();
			
			if (process.waitFor() == 0) {  
				pw.print("<script language='javaScript'> alert('备份成功');</script>");
			} else {  
				pw.print("<script language='javaScript'> alert('备份失败');</script>"); 
			}  
		} catch (FileNotFoundException e) {
			pw.print("<script language='javaScript'> alert('"+ e.getMessage().replaceAll("\\\\", "\\\\\\\\") + "');</script>"); 
        } catch (IOException e) {
        	pw.print("<script language='javaScript'> alert('"+ e.getMessage().replaceAll("\\\\", "\\\\\\\\") +"');</script>"); 
		} catch (InterruptedException exception){  
			pw.print("<script language='javaScript'> alert('"+ exception.getMessage().replaceAll("\\\\", "\\\\\\\\") +"');</script>"); 
		} finally{
			try {
				if(bufferedReader != null) {	        			
					bufferedReader.close();
				}
				if(printWriter != null) {
					printWriter.close();	        			
				}
			} catch (IOException e) {
				pw.print("<script language='javaScript'> alert('"+ e.getMessage().replaceAll("\\\\", "\\\\\\\\") +"');</script>"); 
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
