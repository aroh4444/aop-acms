package com.mobile.police.fugitive;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mobile.police.R;
import com.mobile.police.common.ActivityUtils;
import com.mobile.police.suspect.Suspect;
import com.mobile.police.suspect.SuspectInfo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

public class FugitiveList extends ListActivity {
	
	private List<Fugitive> fugs;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.fugitive_list);
		
		this.parseJson();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.getData());
		this.getListView().setAdapter(adapter);	
		
		this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				
				Fugitive f = fugs.get(position);
				Intent intent = new Intent();
				intent.putExtra("suspect", f.getSuspect());
				intent.setClass(FugitiveList.this, SuspectInfo.class);
				startActivity(intent);
			}
		});
		
	}
	
	private List<String> getData(){
		List<String> data = new ArrayList<String>();
		if(fugs != null){
			for(Fugitive lc : fugs){
				StringBuilder sb = new StringBuilder();
				sb.append(lc.getSuspect().getName()).append(", ")
				  .append(lc.getSuspect().getSex().equals("m")?"男":"女").append(", ")
				  .append(lc.getSuspect().getAge()).append(", ")
				  .append(lc.getCategory());
				
				data.add(sb.toString());
			}
		}
		
		return data;
	}
	
	public void onClick(View v){
//		LinearLayout menu = (LinearLayout)this.findViewById(v.getId());
//		menu.setBackgroundResource(R.drawable.menu_pressed);		
		this.finish();	
	}
	
	private void parseJson(){
		fugs = new ArrayList<Fugitive>();
		try {
			JSONArray array = new JSONArray(this.getIntent().getStringExtra("result"));
			for(int i=0; i<array.length(); i++){
				JSONObject o = array.getJSONObject(i);
				Fugitive c = new Fugitive();
				c.setCategory(o.getString("category"));
				c.setId(o.getInt("id"));
				
				Suspect s = new Suspect();
				JSONObject jo = o.getJSONObject("suspect");
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
				c.setSuspect(s);
				
				fugs.add(c);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			ActivityUtils.showMessage(this, "在逃人员数据解析出错！");
		}
	}	

}
