package com.mobile.police.provider;

import com.mobile.police.provider.Provinces.Province;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 创建省份数据库
 * @author Administrator
 *
 */
public class ProvinceDBHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "provinces.db";
	private static final int VERSION = 1;
	
	public static final String PROVINCES_TABLE_NAME = "province";
	
	
	public ProvinceDBHelper(Context context){
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("create table ").append(PROVINCES_TABLE_NAME)
		  .append(" (").append(Province._ID).append(" integer primary key,")
		  .append(Province.NAME).append(" text);");
		
		db.execSQL(sb.toString());

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		
		db.execSQL("drop table if exists province");
		
		this.onCreate(db);

	}

}
