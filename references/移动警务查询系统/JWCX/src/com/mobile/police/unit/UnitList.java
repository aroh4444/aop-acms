package com.mobile.police.unit;

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

public class UnitList extends ListActivity {
	
	private List<Unit> units;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.unit_list);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.getData());
		this.getListView().setAdapter(adapter);
		
		this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				
				Unit u = units.get(position);
				Intent intent = new Intent();
				intent.putExtra("unit", u);
				intent.setClass(UnitList.this, UnitInfo.class);
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
		for(Unit s : units){
			StringBuilder sb = new StringBuilder();
			sb.append(s.getName()).append(", ")
				.append(s.getOwner()).append(", ")
				.append(s.getCategory());
			
			data.add(sb.toString());
		}
		
		return data;
	}	
	
	
	private void parseJSON(){
		units = new ArrayList<Unit>();
		try {
			JSONArray array = new JSONArray(this.getIntent().getStringExtra("result"));
			for(int i=0; i<array.length(); i++){
				JSONObject jo = array.getJSONObject(i);
				Unit unit = new Unit();
				unit.setCategory(jo.getString("category"));
				unit.setEmail(jo.getString("email"));
				unit.setId(jo.getInt("id"));
				unit.setName(jo.getString("name"));
				unit.setOwner(jo.getString("owner"));
				unit.setTelphone(jo.getString("telphone"));
				units.add(unit);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ActivityUtils.showMessage(this, "受害单位数据解析出错！");
		}
	}

}
