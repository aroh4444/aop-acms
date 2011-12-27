package com.mobile.police.provider;

import com.mobile.police.provider.Works.Work;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WorkDBHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "works.db";
	private static final int VERSION = 1;
	
	public static final String TABLE_NAME = "work";	
	
	public WorkDBHelper(Context context){
		super(context,DATABASE_NAME,null,VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("create table ").append(TABLE_NAME)
		  .append(" (").append(Work._ID).append(" integer primary key,")
		  .append(Work.NAME).append(" text);");
		
		db.execSQL(sb.toString());		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("drop table if exists work");
		
		this.onCreate(db);

	}

}
