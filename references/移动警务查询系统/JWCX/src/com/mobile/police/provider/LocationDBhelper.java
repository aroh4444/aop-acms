package com.mobile.police.provider;

import com.mobile.police.provider.Locations.Location;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocationDBhelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "mpslocation.db";
	
	private static final Integer VERVION = 1;
	
	public static final String TABLE_NAME = "mpslocation";	
	
	public LocationDBhelper(Context context){
		super(context,DB_NAME,null,VERVION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		StringBuilder sb = new StringBuilder("create table ");
		sb.append(TABLE_NAME).append(" (")
			.append(Location._ID).append(" integer primary key,")
			.append(Location.POLICE_CODE).append(" text,")
			.append(Location.POLICE_NAME).append(" text,")
			.append(Location.LATITUDE).append(" real,")
			.append(Location.LONGITUDE).append(" real,")
			.append(Location.DISTANCE).append(" real);");
		
		db.execSQL(sb.toString());

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		
		db.execSQL("drop table if exists mpslocation");
		
		this.onCreate(db);

	}

}
