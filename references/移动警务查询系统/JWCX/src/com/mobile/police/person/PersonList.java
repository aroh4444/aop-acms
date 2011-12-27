package com.mobile.police.person;

import java.util.ArrayList;
import java.util.List;

import com.mobile.police.R;
import com.mobile.police.common.ActivityUtils;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class PersonList extends ListActivity {
	
	private ArrayList<Person> plist;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.person_list);
		
		List<String> data = this.getData();
		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,data);
		this.getListView().setAdapter(adapter);
		this.getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				
//				String item = (String)parent.getItemAtPosition(position);
//				ActivityUtils.showMessage(PersonList.this, position+item);
				Person p = plist.get(position);
				Intent intent = new Intent();
				intent.putExtra("person", p);
				intent.setClass(PersonList.this, PersonInfo.class);
				
				PersonList.this.startActivity(intent);
				
			}
		});
	}
	
	public void onClick(View v){
		int id = v.getId();
//		LinearLayout menu = (LinearLayout)this.findViewById(id);
//		menu.setBackgroundResource(R.drawable.menu_pressed);		
		if(id == R.id.person_back){
			this.finish();
		}
	}
	

	private List<String> getData(){
		
		List<String> data = new ArrayList<String>();
		Bundle bundle = this.getIntent().getExtras();
		plist = bundle.getParcelableArrayList("plist");
		
		for(Person p : plist){
			StringBuilder sb = new StringBuilder();
			
			sb.append(p.getName()).append(", ")
				.append(p.getSex().equals("m")?"男":"女").append(", ")
				.append(ActivityUtils.dateToAge("1989-11-04")).append(", ")
				.append(p.getProvince()).append("省").append(p.getCity()).append("市");
			
			data.add(sb.toString());
		}
		//ActivityUtils.showMessage(this, "当前大小："+plist.size());
		return data;
	}

}
