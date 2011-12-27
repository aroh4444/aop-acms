package com.mobile.police.legalcase;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mobile.police.R;
import com.mobile.police.common.ActivityUtils;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class CaseList extends ListActivity {
	
	private ArrayList<LegalCase> cases;

	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.case_list);
		
		this.parseJson();
		
		List<String> data = this.getData();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
		this.getListView().setAdapter(adapter);
		this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				
				LegalCase c = cases.get(position);
				Intent intent = new Intent();
				intent.putExtra("case", c);
				intent.setClass(CaseList.this, CaseInfo.class);
				CaseList.this.startActivity(intent);
				
			}
		});
		
		//用户常按某一项时，弹出一个选择列表，让用户选择“嫌疑人信息，受害人信息，或者受害单位信息”
		this.getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View v,
					int position, long id) {
				//将该项的ID传递过去
				int caseId = cases.get(position).getId();
				Intent intent = new Intent();
				intent.putExtra("caseId", caseId);
				intent.setClass(CaseList.this, SelectList.class);
				CaseList.this.startActivity(intent);
				return false;
			}
		});
	}
	
	private List<String> getData(){
		List<String> data = new ArrayList<String>();
		if(cases != null){
			for(LegalCase lc : cases){
				StringBuilder sb = new StringBuilder();
				sb.append(lc.getCode()).append(", ")
				  .append(lc.getCategory()).append(", ")
				  .append(lc.getEventTime());
				
				data.add(sb.toString());
			}
		}
		
		return data;
	}
	
	public void onClick(View v){
		int id = v.getId();
		switch(id){
		case R.id.case_back:
//			LinearLayout menu = (LinearLayout)this.findViewById(id);
//			menu.setBackgroundResource(R.drawable.menu_pressed);		
			this.finish();
			break;
		}

	}
	
	private void parseJson(){
		cases = new ArrayList<LegalCase>();
		try {
			JSONArray array = new JSONArray(this.getIntent().getStringExtra("result"));
			for(int i=0; i<array.length(); i++){
				JSONObject o = array.getJSONObject(i);
				LegalCase c = new LegalCase();
				c.setBrief(o.getString("brief"));
				c.setCategory(o.getString("category"));
				c.setCharacteristic(o.getString("characteristic"));
				c.setCode(o.getString("code"));
				c.setCrimeNumber(o.getInt("crimeNumber"));
				c.setDeathNumber(o.getInt("deathNumber"));
				c.setEventAddr(o.getString("eventAddr"));
				c.setEventTime(o.getString("eventTime"));
				c.setEvidence(o.getString("evidence"));
				c.setFindTime(o.getString("findTime"));
				c.setId(o.getInt("id"));
				c.setInjuredNumber(o.getInt("injuredNumber"));
				c.setLoss(o.getDouble("loss"));
				c.setMethod(o.getString("method"));
				c.setReportTime(o.getString("reportTime"));
				c.setSite(o.getString("site"));
				c.setSiteFeature(o.getString("siteFeature"));
				c.setSolve(o.getString("solve"));
				c.setSource(o.getString("source"));
				c.setTarget(o.getString("target"));
				c.setTiming(o.getString("timing"));
				c.setTool(o.getString("tool"));
				c.setTrace(o.getString("trace"));
				
				cases.add(c);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			ActivityUtils.showMessage(this, "案件数据解析出错！");
		}
	}

}
