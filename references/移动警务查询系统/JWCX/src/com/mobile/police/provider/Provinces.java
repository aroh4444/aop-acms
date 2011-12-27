package com.mobile.police.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * 
 * @author Administrator
 * 该类是省份信息的provider
 *
 */
public final class Provinces {
	
	//唯一授权标识
	public static final String AUTHORITY = "com.mobile.police.provider.Provinces";
	
	/**
	 * 该类是静态内部类，其中定义列名，排序列等常量信息
	 * 其中BaseColumns是常量接口
	 * @author Administrator
	 *
	 */
	public static final class Province implements BaseColumns{
		
		private Province(){}
		
		public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/province");
		
		//两种MIME类型
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.mobile.police.provinces";
		
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.mobile.police.provinces";
		
		public static final String DEFAULT_SORT_ORDER = "name DESC";//按照名称排序
		
		public static final String NAME = "name"; //字段名称
	}

}
