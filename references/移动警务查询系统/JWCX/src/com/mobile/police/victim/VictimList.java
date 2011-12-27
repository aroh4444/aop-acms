package com.mobile.police.victim;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mobile.police.R;
import com.mobile.police.common.ActivityUtils;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

public class VictimList extends ListActivity {
	
	private List<Victim> victims;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.victim_list);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.getData());
		this.getListView().setAdapter(adapter);
		
		this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				
				Victim vic = victims.get(position);
				Intent intent = new Intent();
				intent.putExtra("victim", vic);
				intent.setClass(VictimList.this, VictimInfo.class);
				startActivity(intent);				
				
			}
		});
	}
	
	public void onClick(View v){
		
//		LinearLayout menu = (LinearLayout)this.findViewById(v.getId());
//		menu.setBackgroundResource(R.drawable.menu_pressed);		
		this.finish();		
	}
	
	private List<String> getData(){
		this.parseJSON();
		List<String> data = new ArrayList<String>();
		for(Victim s : victims){
			StringBuilder sb = new StringBuilder();
			sb.append(s.getName()).append(", ")
				.append((s.getSex().equals("m"))?"男":"女").append(", ")
				.append(s.getAge()+"").append(", ")
				.append(s.getProvince()).append("省").append(s.getCity()).append("市");
			
			data.add(sb.toString());
		}
		
		return data;
	}
	
	private void parseJSON(){
		victims = new ArrayList<Victim>();
		
		try {
			JSONArray array = new JSONArray(this.getIntent().getStringExtra("result"));
			for(int i=0; i<array.length(); i++){
				JSONObject jo = array.getJSONObject(i);
				Victim v = new Victim();
				v.setAge(jo.getInt("age"));
				v.setCity(jo.getString("city"));
				v.setCountry(jo.getString("country"));
				v.setDetailAddr(jo.getString("detailAddr"));
				v.setId(jo.getInt("id"));
				v.setIdCard(jo.getString("idCard"));
				v.setName(jo.getString("name"));
				v.setProvince(jo.getString("province"));
				v.setRace(jo.getString("race"));
				v.setRemark(jo.getString("remark"));
				v.setSex(jo.getString("sex"));
				v.setTelphone(jo.getString("telphone"));
				v.setVictimType(jo.getString("victimType"));
				v.setWork(jo.getString("work"));
				
				victims.add(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ActivityUtils.showMessage(this, "受害人数据解析出错！");
		}
	}

}
