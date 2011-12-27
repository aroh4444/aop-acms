package com.mobile.police.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class Ips {
	
	public static final String AUTHORITY = "com.mobile.police.provider.Ips";
	
	public static final class Ip implements BaseColumns{
		
		private Ip(){}
		
		public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/ip");
		
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.mobile.police.ips";
		
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.mobile.police.ip";
		
		public static final String IP_ADDR = "ip_addr";
		
		public static final String IP_PORT = "ip_port";
		
	}

}
