package com.mobile.police.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

/**
 * 该类是访问Internet的通用类
 * @author Administrator
 *
 */
public final class HttpUtils {
	
	
	/**
	 * 访问给定的url
	 * @param url
	 * @param method：指定访问的方式，GET或者POST
	 * @param params：url中需要封装的参数
	 * @return
	 */
	public static String openUrl(String url, String method, Bundle params) throws Exception{
		
		if(method.equalsIgnoreCase("GET")){
			url = url +"?"+ encodeUrl(params);
		}
		
		HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
		conn.setRequestProperty("User-Agent", System.getProperties().getProperty("http.agent"));
		conn.setReadTimeout(10000);
		
		//如果是POST请求方式，则需要特殊的设置
		if(method.equalsIgnoreCase("POST")){
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.getOutputStream().write(encodeUrl(params).getBytes());
		}
		
		//读取流，获取返回字符串
		InputStream is = conn.getInputStream(); 
		
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(is), 1000);
		for(String line = br.readLine(); line != null; line = br.readLine()){
			sb.append(line);
		}
		
		is.close();
		//zreturn url;
		return sb.toString();
	}
	
	/**
	 * 将Bundle封装的参数解析成url格式的参数
	 * @param parameters
	 * @return
	 */
	public static String encodeUrl(Bundle parameters) {
		if (parameters == null || parameters.size()==0)
			return "";
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String key : parameters.keySet()) {
			if (first)
				first = false;
			else
				sb.append("&");
			sb.append(key + "=" + parameters.getString(key));
		}
		return sb.toString();
	}


	/**
	 * 判断网络功能是否可用
	 * 需要权限< uses-permission android:name="android.permission.ACCESS_NETWORK_STATE">
	 * @param ctx
	 * @return
	 */
	public static boolean isNetworkAvailable(Context ctx){
		ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();

		return (info != null && info.isConnected());
	}

}
