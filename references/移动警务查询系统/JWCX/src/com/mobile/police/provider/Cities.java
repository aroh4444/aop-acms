package com.mobile.police.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class Cities {
	
	public static final String AUTHORITY = "com.mobile.police.provider.Cities";
	
	public static final class City implements BaseColumns{
		
		private City(){}
		
		public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/city");
		//两种MIME类型
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.mobile.police.cities";
		
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.mobile.police.city";
		
		public static final String DEFAULT_SORT_ORDER = "name DESC";//按照名称排序
		
		public static final String PROVINCE_NAME = "province_name";
		public static final String NAME = "name";
		
	}

}
