package com.mobile.police.suspect;

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

public class SuspectList extends ListActivity {
	
	private List<Suspect> suspects;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.suspect_list);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.getData());
		this.getListView().setAdapter(adapter);
		this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				
				Suspect s = suspects.get(position);
				Intent intent = new Intent();
				intent.putExtra("suspect", s);
				intent.setClass(SuspectList.this, SuspectInfo.class);
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
		for(Suspect s : suspects){
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
		suspects = new ArrayList<Suspect>();
		try {
			JSONArray array = new JSONArray(this.getIntent().getStringExtra("result"));
			for(int i=0; i<array.length(); i++){
				JSONObject jo = array.getJSONObject(i);
				Suspect s = new Suspect();
				s.setAge(jo.getInt("age"));
				s.setBlood(jo.getString("blood"));
				s.setCity(jo.getString("city"));
				s.setCountry(jo.getString("country"));
				s.setDetailAddr(jo.getString("detailAddr"));
				s.setFace(jo.getString("face"));
				s.setFingerprint(jo.getString("fingerprint"));
				s.setId(jo.getInt("id"));
				s.setIdCard(jo.getString("idCard"));
				s.setName(jo.getString("name"));
				s.setOthers(jo.getString("others"));
				s.setProvince(jo.getString("province"));
				s.setRace(jo.getString("race"));
				s.setRemark(jo.getString("remark"));
				s.setSex(jo.getString("sex"));
				s.setShape(jo.getString("shape"));
				s.setSpeciality(jo.getString("speciality"));
				s.setStature(jo.getInt("stature"));
				s.setSuspectNumber(jo.getString("suspectNumber"));
				s.setVoice(jo.getString("voice"));
				s.setWork(jo.getString("work"));
				
				suspects.add(s);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ActivityUtils.showMessage(this, "嫌疑人数据解析出错！");			
		}
	}

}
