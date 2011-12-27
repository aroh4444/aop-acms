package com.mobile.police.common;

import java.util.Date;

import com.mobile.police.provider.Ips.Ip;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public final class ActivityUtils {
	
	
	public static void showMessage(Context context, String msg){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	
	//根据日期获取年龄
	public static String dateToAge(String date){
		Date curr_date = new Date();
		Date birth_date = new Date(date.replace("-", "/"));
		Log.i("curr",curr_date.toString());
		Log.i("time", birth_date.toString()+"");
		
		return (curr_date.getYear() - birth_date.getYear())+"";
	}
	
	//程序一启动检查，Ip是否存在
	//如果不存在，就要初始化一个IP
	public static void initApp(Context context){
		Uri uri = Ip.CONTENT_URI;
		ContentResolver r = context.getContentResolver();
		String[] projection = new String[]{
				Ip._ID,
				Ip.IP_ADDR,
				Ip.IP_PORT
		};
		
		ContentValues values = new ContentValues();
		values.put(Ip.IP_ADDR, "10.0.2.2");
		values.put(Ip.IP_PORT, 8080);
		
		Cursor c = r.query(uri, projection, null, null, null);
		
		if(c != null && c.getCount() <= 0){
			r.insert(uri, values);
		}
	}
	
	public static String getIp(Context context, String jsonName){
		
		StringBuilder addr = new StringBuilder("http://");
		
		Uri uri = Ip.CONTENT_URI;
		ContentResolver r = context.getContentResolver();
		String[] projection = new String[]{
				Ip._ID,
				Ip.IP_ADDR,
				Ip.IP_PORT
		};
		
		Cursor c = r.query(uri, projection, null, null, null);
		
		if(c.getCount() > 0){
			c.moveToFirst();
			addr.append(c.getString(1)).append(":").append(c.getInt(2)).append("/").append(jsonName);
		}
		
		return addr.toString();
	}

}
