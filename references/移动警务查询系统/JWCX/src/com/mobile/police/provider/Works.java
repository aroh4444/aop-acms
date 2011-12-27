package com.mobile.police.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * 工作类
 * @author Administrator
 *
 */
public class Works {
	
	public static final String AUTHORITY = "com.mobile.police.provider.Works";
	
	public static final class Work implements BaseColumns{
		
		private Work(){}
		
		public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/work");
		
		//两种MIME类型
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.mobile.police.works";
		
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.mobile.police.work";
		
		public static final String DEFAULT_SORT_ORDER = "name DESC";//按照名称排序
		
		public static final String NAME = "name";
		
	}

}
