package com.mobile.police.provider;

import com.mobile.police.provider.Cities.City;
import com.mobile.police.provider.Provinces.Province;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CityDBHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "cities.db";
	private static final int VERSION = 1;
	
	public static final String CITIES_TABLE_NAME = "city";
	
	public CityDBHelper(Context context){
		super(context,DATABASE_NAME,null,VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("create table ").append(CITIES_TABLE_NAME)
		  .append(" (").append(City._ID).append(" integer primary key,")
		  .append(City.PROVINCE_NAME).append(" text,")
		  .append(Province.NAME).append(" text);");
		
		db.execSQL(sb.toString());

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("drop table if exists city");
		
		this.onCreate(db);

	}

}
