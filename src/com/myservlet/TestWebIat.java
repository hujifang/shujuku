package com.myservlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class TestWebIat {
	// ��дwebapi�ӿڵ�ַ
		private static final String WEBIAT_URL = "http://api.xfyun.cn/v1/service/v1/iat";
		// ����Ӧ��ID
		private static final String TEST_APPID = "5c1cdded";
		// ���Խӿ���Կ
		private static final String TEST_API_KEY = "300fa5d9d63e22f04e8d035418edeb65";
		// ������Ƶ�ļ����λ��
		private static final String AUDIO_FILE_PATH = "D:\\test.wav";

		/**
		 * ��װhttp����ͷ
		 * 
		 * @param aue
		 * @param engineType
		 * @return
		 * @throws UnsupportedEncodingException
		 * @throws ParseException 
		 */
		private static Map<String, String> constructHeader(String aue, String engineType) throws UnsupportedEncodingException, ParseException {
			// ϵͳ��ǰʱ���
			String X_CurTime = System.currentTimeMillis() / 1000L + "";
			// ҵ�����
			String param = "{\"aue\":\""+aue+"\""+",\"engine_type\":\"" + engineType + "\"}";
			String X_Param = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
			// �ӿ���Կ
			String apiKey = TEST_API_KEY;
			// Ѷ�ɿ���ƽ̨Ӧ��ID
			String X_Appid = TEST_APPID;
			// ��������
			String X_CheckSum = DigestUtils.md5Hex(apiKey + X_CurTime + X_Param);

			// ��װ����ͷ
			Map<String, String> header = new HashMap<String, String>();
			header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			header.put("X-Param", X_Param);
			header.put("X-CurTime", X_CurTime);
			header.put("X-CheckSum", X_CheckSum);
			header.put("X-Appid", X_Appid);
			return header;
		}
		
		public static String start() throws IOException,ParseException {
			EngineeCore engineeCore = new EngineeCore();
	        engineeCore.startRecognize();
			
			Map<String, String> header = constructHeader("raw", "sms16k");
			// ��ȡ��Ƶ�ļ���ת���������飬Ȼ��Base64����
			byte[] audioByteArray = FileUtil.read2ByteArray(AUDIO_FILE_PATH);
			String audioBase64 = new String(Base64.encodeBase64(audioByteArray), "UTF-8");
			String bodyParam = "audio=" + audioBase64;
			String result = HttpUtil.doPost(WEBIAT_URL, header, bodyParam);
			result = new String(result.getBytes(),"utf-8");
			System.out.println(result);
			return result;
		}
}
