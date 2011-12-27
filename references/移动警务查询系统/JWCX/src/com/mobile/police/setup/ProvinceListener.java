package com.mobile.police.setup;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.net.Uri;

import com.mobile.police.common.ActivityUtils;
import com.mobile.police.common.RequestListener;
import com.mobile.police.provider.Provinces.Province;

public class ProvinceListener implements RequestListener {
	
	private Activity context;
	
	private ProgressDialog progress;
	
	public ProvinceListener(Activity context){
		
		this.context = context;
		progress = ProgressDialog.show(context, "提示", "正在更新省份信息,请稍后...");
		progress.show();
	}

	@Override
	public void onComplete(final String result) {
		
		
		context.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				

				//在这里将解析后的职业信息存入本地职业数据库
				parseJSON(result);
				if(progress != null){
					progress.dismiss();
				}
				ActivityUtils.showMessage(context, "省份数据更新成功！");					
			}
		});
	}
	
	private void updateDatabase(String name){
		Uri uri = Province.CONTENT_URI;
		ContentValues values = new ContentValues();
		values.put(Province.NAME, name);
		context.getContentResolver().insert(uri, values);
	}
	
	private void clearDatabase(){
		Uri uri = Province.CONTENT_URI;
		context.getContentResolver().delete(uri, null, null);
	}
	
	private void parseJSON(String jsonStr){
		//List<Work> works = new ArrayList<Work>();
		try {
			clearDatabase();
			JSONArray array = new JSONArray(jsonStr);
			for(int i=0; i<array.length(); i++){
				JSONObject jo = array.getJSONObject(i);
				updateDatabase(jo.getString("name"));
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			ActivityUtils.showMessage(context, "省份数据解析出错！");			
		}
		
	}	

	@Override
	public void onException(final Exception e) {
		
		
		context.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				if(progress != null){
					progress.dismiss();
				}
				
				new AlertDialog.Builder(context)
					.setMessage(e.getMessage())
					.setPositiveButton("确定", null)
					.create().show();
				
			}
		});	
	}

}
