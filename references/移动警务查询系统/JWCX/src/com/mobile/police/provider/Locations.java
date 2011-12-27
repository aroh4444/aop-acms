package com.mobile.police.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class Locations {
	
	public static final String AUTHORITY = "com.mobile.police.provider.Locations";
	
	public static final class Location implements BaseColumns{
		
		public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"location");
		
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.mobile.police.locations";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.mobile.police.location";
		
		public static final String DEFAULT_SORT_ORDER = "distance asc";
		
		public static final String POLICE_CODE = "code";
		
		public static final String POLICE_NAME = "name";
		
		public static final String LATITUDE = "latitude";
		
		public static final String LONGITUDE = "longitude";
		
		public static final String DISTANCE = "distance";
	}

}
