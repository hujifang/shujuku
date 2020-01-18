package com.myservlet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;


/**
 * Http���󹤾���
 */
public class HttpUtil {
	private HttpUtil() {

	}

	/**
	 * ����post����
	 * 
	 * @param url
	 * @param header
	 * @param body
	 * @return
	 */
	public static String doPost(String url, Map<String, String> header, String body) {
		String result = "";
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			// ���� url
			URL realUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
			// ���� header
			for (String key : header.keySet()) {
				connection.setRequestProperty(key, header.get(key));
			}
			// �������� body
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			//�������ӳ�ʱ�Ͷ�ȡ��ʱʱ��
			connection.setConnectTimeout(20000);
			connection.setReadTimeout(20000);
			try {
				out = new PrintWriter(connection.getOutputStream());
				// ����body
				out.print(body);
				// ����body
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				// ��ȡ��Ӧbody
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
//			return null;
		}
		return result;
	}

	/**
	 * ����get����
	 * 
	 * @param url
	 * @param header
	 * @return
	 */
	public static String doGet(String url, Map<String, String> header) {
		String result = "";
		BufferedReader in = null;
		try {
			// ���� url
			URL realUrl = new URL(url);
			URLConnection connection = realUrl.openConnection();
			// ���� header
			for (String key : header.keySet()) {
				connection.setRequestProperty(key, header.get(key));
			}
			// �������� body
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			return null;
		}
		return result;
	}
}
