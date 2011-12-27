package com.mobile.police.provider;

import java.util.HashMap;
import java.util.Map;

import com.mobile.police.provider.Locations.Location;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.location.LocationManager;
import android.net.Uri;

public class LocationProvider extends ContentProvider {
	private LocationDBhelper dbHelper;
	
	private static final UriMatcher mtcher;
	
	private static final int LOCATIONS = 1;
	
	private static final int LOCATION_ID = 2;
	
	private static Map<String, String> columnMap;
	
	static{
		mtcher = new UriMatcher(UriMatcher.NO_MATCH);
		mtcher.addURI(Locations.AUTHORITY, "location", LOCATIONS);
		mtcher.addURI(Locations.AUTHORITY, "location/#", LOCATION_ID);
		
		columnMap = new HashMap<String, String>();
		columnMap.put(Location._ID, Location._ID);
		columnMap.put(Location.POLICE_CODE, Location.POLICE_CODE);
		columnMap.put(Location.POLICE_NAME, Location.POLICE_NAME);
		columnMap.put(Location.LATITUDE, Location.LATITUDE);
		columnMap.put(Location.LONGITUDE, Location.LONGITUDE);
		columnMap.put(Location.DISTANCE, Location.DISTANCE);
	}

	@Override
	public int delete(Uri uri, String whereClause, String[] whereArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count = 0;
		switch(mtcher.match(uri)){
		case LOCATIONS:
			count = db.delete(LocationDBhelper.TABLE_NAME, whereClause, whereArgs);
			break;
		case LOCATION_ID:
			String id = uri.getPathSegments().get(1);
			StringBuilder sel = new StringBuilder();
			sel.append(Location._ID).append(" = ").append(id);
/*			if(whereClause != null){
				sel.append(" and (").append(whereClause).append(")");
			}*/
			count = db.delete(LocationDBhelper.TABLE_NAME, sel.toString(), null);
			break;
			
		default:
			throw new IllegalArgumentException("Uri错误！"+uri.toString());		
		}
		return count;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long id = db.insert(LocationDBhelper.TABLE_NAME, null, values);
		
		if(id > 0){
			Uri luri = ContentUris.withAppendedId(Location.CONTENT_URI, id);
			this.getContext().getContentResolver().notifyChange(luri, null);
			return luri;
		}
		return null;
	}

	@Override
	public boolean onCreate() {
		this.dbHelper = new LocationDBhelper(this.getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();
		switch(mtcher.match(uri)){
		case LOCATIONS:
			sqb.setTables(LocationDBhelper.TABLE_NAME);
			sqb.setProjectionMap(columnMap);
			break;
			
		case LOCATION_ID:
			sqb.setTables(LocationDBhelper.TABLE_NAME);
			sqb.setProjectionMap(columnMap);
			sqb.appendWhere(Location._ID + " = " + uri.getPathSegments().get(1));
			break;
			
		default:
			throw new IllegalArgumentException("Uri错误！"+uri.toString());				
		}
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor c = sqb.query(db, projection, selection, selectionArgs, null, null, Location.DEFAULT_SORT_ORDER);
		c.setNotificationUri(this.getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String whereClause, String[] whereArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count = 0;
		switch(mtcher.match(uri)){
		case LOCATIONS:
			count = db.update(LocationDBhelper.TABLE_NAME, values, whereClause, whereArgs);
			break;
		case LOCATION_ID:
			String id = uri.getPathSegments().get(1);
			StringBuilder sel = new StringBuilder();
			sel.append(Location._ID).append(" = ").append(id);
			if(whereClause != null){
				sel.append(" and (").append(whereClause).append(")");
			}
			count = db.update(LocationDBhelper.TABLE_NAME, values, sel.toString(), whereArgs);
			break;
			
		default:
			throw new IllegalArgumentException("Uri错误！"+uri.toString());		
		}
		return count;
	}

}
