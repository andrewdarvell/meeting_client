package ru.darvell.android.meetingclient.api;

import android.util.Log;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Основной класс для работы с сервером
 */
public class MeetingApi {

	public static final String SECUR_METHOD = "secur";

	public static Map<String, String> prepareLogin(String login, String pass){
		Map<String, String> params = new HashMap<String, String>();
		params.put("login","dwdw");
		params.put("pass","fsdfsf");
		return params;
	}

	static Map<String, String> parseParams(String str){
		Map<String, String> result = new HashMap<String, String>();
		try {
			String rawPars[] = str.split(";");
			for (int i = 0; i < rawPars.length; i++) {
				String keyValue[] = rawPars[i].split(":");
				result.put(keyValue[0], keyValue[1]);
			}
			return result;
		}catch (Exception e){
			result.put("code", "-1");
			e.printStackTrace();
			return result;
		}
	}

	public static String sendPost(Map<String, String> params){
		try{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(Conf.apiUrl+params.get("method"));

			Set keys = params.keySet();
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(keys.size());
			Iterator<String> iterator = keys.iterator();
			while (iterator.hasNext()){
				String tmpStr = iterator.next();
				if (!tmpStr.equals("method")) {
					nameValuePairs.add(new BasicNameValuePair(tmpStr, params.get(tmpStr)));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			String responseRaw = httpclient.execute(httpPost, new BasicResponseHandler());

			return responseRaw;

		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
