package com.mobile.police.provider;

import java.util.HashMap;
import java.util.Map;

import com.mobile.police.provider.Ips.Ip;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class IpProvider extends ContentProvider {
	
	private IpDBHelper dbHelper;
	
	private static final UriMatcher mtcher;
	
	private static final int IP = 1;
	
	private static final int IP_ID = 2;
	
	private static Map<String, String> columnMap;
	
	static{
		mtcher = new UriMatcher(UriMatcher.NO_MATCH);
		mtcher.addURI(Ips.AUTHORITY, "ip", IP);
		mtcher.addURI(Ips.AUTHORITY, "ip/#", IP_ID);
		
		columnMap = new HashMap<String, String>();
		
		columnMap.put(Ip._ID, Ip._ID);
		columnMap.put(Ip.IP_ADDR, Ip.IP_ADDR);
		columnMap.put(Ip.IP_PORT, Ip.IP_PORT);
		
	}
	
	
	

	@Override
	public int delete(Uri uri, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long id = db.insert(IpDBHelper.TABLE_NAME, null, values);
		
		if(id > 0){
			Uri iuri = ContentUris.withAppendedId(Ip.CONTENT_URI, id);
			this.getContext().getContentResolver().notifyChange(iuri, null);
			return iuri;
		}
		return null;
	}

	@Override
	public boolean onCreate() {
		dbHelper = new IpDBHelper(this.getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();
		switch(mtcher.match(uri)){
		case IP:
			sqb.setTables(IpDBHelper.TABLE_NAME);
			sqb.setProjectionMap(columnMap);
			break;
		case IP_ID:
			sqb.setTables(IpDBHelper.TABLE_NAME);
			sqb.setProjectionMap(columnMap);
			sqb.appendWhere(Ip._ID + " = " + uri.getPathSegments().get(1));
			break;
		default:
			throw new IllegalArgumentException("Uri错误！"+uri.toString());			
		}
		
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		Cursor c = sqb.query(db, projection, selection, selectionArgs, null, null, null);
		c.setNotificationUri(this.getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String whereClause, String[] whereArgs) {
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count = 0;
		switch(mtcher.match(uri)){
		case IP:
			count = db.update(IpDBHelper.TABLE_NAME, values, whereClause, whereArgs);
			break;
			
		case IP_ID:
			String id = uri.getPathSegments().get(1);
			String sel = Ip._ID + " = "+id + (TextUtils.isEmpty(whereClause)?"":(" and ("+whereClause+")"));
			count = db.update(IpDBHelper.TABLE_NAME, values, sel, whereArgs);
			break;
			
		default:
			throw new IllegalArgumentException("Uri错误！"+uri.toString());	
		}
		this.getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

}
