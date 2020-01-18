package com.myservlet;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * �ļ�����������
 */
public class FileUtil {
	/**
	 * ��ȡ�ļ�����Ϊ����������
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] read2ByteArray(String filePath) throws IOException {

		InputStream in = new FileInputStream(filePath);
		byte[] data = inputStream2ByteArray(in);
		in.close();

		return data;
	}

	/**
	 * ��ת����������
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private static byte[] inputStream2ByteArray(InputStream in) throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024 * 4];
		int n = 0;
		while ((n = in.read(buffer)) != -1) {
			out.write(buffer, 0, n);
		}
		return out.toByteArray();
	}
}
