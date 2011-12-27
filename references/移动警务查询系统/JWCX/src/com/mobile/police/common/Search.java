package com.mobile.police.common;


import android.os.Bundle;

public class Search {
	
	//private static final String HTTP_URL = "http://localhost:8080/person.xml";
	private String baseUrl;
	
	public Search(String baseUrl){
		this.baseUrl = baseUrl;
	}
	
	public void request(final Bundle params, final RequestListener listener){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					String response = HttpUtils.openUrl(baseUrl, "GET", params);
					listener.onComplete(response);
				} catch (Exception e) {
					
					listener.onException(e);
				}
				
			}
		}).start();
	}

}
