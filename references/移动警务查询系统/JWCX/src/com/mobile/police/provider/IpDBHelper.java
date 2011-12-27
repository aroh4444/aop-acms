package com.mobile.police.provider;

import com.mobile.police.provider.Ips.Ip;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class IpDBHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "ip.db";
	
	private static final Integer VERVION = 1;
	
	public static final String TABLE_NAME = "ip";
	
	public IpDBHelper(Context context){
		super(context,DB_NAME,null,VERVION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("create table ").append(TABLE_NAME)
			.append(" (").append(Ip._ID).append(" integer primary key,")
			.append(Ip.IP_ADDR).append(" text,")
			.append(Ip.IP_PORT).append(" integer);");
		db.execSQL(sb.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("drop table if exists ip");
		
		this.onCreate(db);

	}

}
