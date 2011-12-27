package com.mobile.police.provider;

import java.util.HashMap;
import java.util.Map;

import com.mobile.police.provider.Works.Work;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class WorkProvider extends ContentProvider {

	private WorkDBHelper dbHelper;
	private static final UriMatcher uriMatcher;
	private static final int WORK = 1; 
	private static final int WORK_ID = 2;
	
	private static Map<String, String> workProjectionMap;
	
	static{
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(Works.AUTHORITY, "work", WORK); //添加一个匹配的Uri
		uriMatcher.addURI(Works.AUTHORITY, "work/#", WORK_ID);
		
		//查询列
		workProjectionMap = new HashMap<String, String>();
		workProjectionMap.put(Work._ID, Work._ID);
		workProjectionMap.put(Work.NAME, Work.NAME);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count;
		
		switch(uriMatcher.match(uri)){
		case WORK:
			count = db.delete(dbHelper.TABLE_NAME, selection, selectionArgs);
			//根据指定条件删除
			break;
		case WORK_ID:
			//根据指定条件和ID删除
			String id = uri.getPathSegments().get(1); 
			String sel = Work._ID + " = " + id + (TextUtils.isEmpty(selection)?"":" and ("+selection+")");
			count = db.delete(dbHelper.TABLE_NAME, sel, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Uri错误！"+uri.toString());
		}
		this.getContext().getContentResolver().notifyChange(uri, null);
		return count;		

	}

	@Override
	public String getType(Uri arg0) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long rowId = db.insert(WorkDBHelper.TABLE_NAME, Work.NAME, values);
		
		//如果插入成功，返回URI
		if(rowId > 0){
			Uri pUri = ContentUris.withAppendedId(Work.CONTENT_URI, rowId);
			this.getContext().getContentResolver().notifyChange(pUri, null);
			return pUri;
		}
		return null;
	}

	@Override
	public boolean onCreate() {
		
		dbHelper =  new WorkDBHelper(this.getContext());
		
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		//这里使用SQLiteQueryBuilder来辅助实现查询功能
		SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();
		
		switch (uriMatcher.match(uri)) {
		case WORK:
			sqb.setTables(dbHelper.TABLE_NAME);
			sqb.setProjectionMap(workProjectionMap);
			break;
		case WORK_ID:
			sqb.setTables(dbHelper.TABLE_NAME);
			sqb.setProjectionMap(workProjectionMap);
			sqb.appendWhere(Work._ID + " = "+uri.getPathSegments().get(1));
			break;
		default:
			throw new IllegalArgumentException("Uri错误！"+uri.toString());
		}
		
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cur = sqb.query(db, projection, selection, selectionArgs, null, null, Work.DEFAULT_SORT_ORDER);
		cur.setNotificationUri(this.getContext().getContentResolver(), uri);
		return cur;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count;
		
		switch(uriMatcher.match(uri)){
		case WORK:
			count = db.update(dbHelper.TABLE_NAME, values, selection, selectionArgs);
			//根据指定条件更新
			break;
		case WORK_ID:
			//根据指定条件和ID更新
			String id = uri.getPathSegments().get(1); 
			String sel = Work._ID + " = " + id + (TextUtils.isEmpty(selection)?"":" and ("+selection+")");
			count = db.update(dbHelper.TABLE_NAME, values, sel, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Uri错误！"+uri.toString());
		}
		this.getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

}
