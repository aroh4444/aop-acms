package com.mobile.police.person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.mobile.police.common.ActivityUtils;
import com.mobile.police.common.RequestListener;

//处理查询到的结果
public class PersonListener implements RequestListener{
	
	private PersonSearch context;
	private ProgressDialog progress;
	
	public PersonListener(PersonSearch context){
		this.context = context;
		this.progress = ProgressDialog.show(context, "提示", "正在查询,请稍后...");
		this.progress.show();
	}

	@Override
	public void onComplete(final String result) {
		
		context.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				ArrayList<Person> plist = parseJson(result);
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putParcelableArrayList("plist", plist);
				intent.putExtras(bundle);
				intent.setClass(context, PersonList.class);
				
				
				if(progress != null){
					progress.dismiss();
				}
				context.startActivity(intent);
				context.finish();
			}
		});
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
	
	
	private ArrayList<Person> parseJson(String jsonStr){
		
		ArrayList<Person> plist = new ArrayList<Person>();
		
		try {
			JSONArray array = new JSONArray(jsonStr);
			for(int i=0; i<array.length(); i++){
				JSONObject jo = array.getJSONObject(i);
				Person p = new Person();
				p.setId(jo.getInt("id"));
				p.setName(jo.getString("name"));
				p.setSex(jo.getString("sex"));
				p.setRace(jo.getString("race"));
				p.setIdCard(jo.getString("idCard"));
				p.setBirthTime(jo.getString("birthTime"));
				p.setProvince(jo.getString("province"));
				p.setCity(jo.getString("city"));
				p.setDetailAddr(jo.getString("detailAddr"));
				p.setWork(jo.getString("work"));
				p.setRemark(jo.getString("remark"));
				p.setAge(jo.getInt("age"));
				
				plist.add(p);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
			ActivityUtils.showMessage(context, "人口数据解析出错！");
			
		}
		
		return plist;
	}
	
}
