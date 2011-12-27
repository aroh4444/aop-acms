package com.mobile.police.person;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.mobile.police.R;
import com.mobile.police.common.ActivityUtils;
import com.mobile.police.common.HttpUtils;
import com.mobile.police.common.RequestListener;
import com.mobile.police.common.Search;
import com.mobile.police.provider.Cities.City;
import com.mobile.police.provider.Provinces.Province;
import com.mobile.police.provider.Works.Work;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.style.LeadingMarginSpan;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class PersonSearch extends Activity {
	//这里不能使用localhost或者127.0.0.1来访问本机ip，因为在模拟器上内置的ip是10.0.2.2
	private static final String JSON_NAME = "person.json";
	
	private EditText txt_Name,txt_Idcard,txt_AgeBegin,txt_AgeEnd;
	private Spinner txt_Sex,txt_Province,txt_City,txt_Work;
	//private Button btn_back,btn_search;
	
	private List<String> provinces, cities, works;
	
	private String[] sexes = new String[]{
			"请选择",
			"男",
			"女"
	};
	
	private String selectedProvince,selectedSex,selectedCity,selectedWork; //选择的省份
	
	private Search search;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.person_search);
		getProvinces();
		getWorks();
		
		txt_Name = (EditText)this.findViewById(R.id.person_name);
		txt_Sex = (Spinner)this.findViewById(R.id.person_sex);
		txt_Idcard = (EditText)this.findViewById(R.id.person_idcard);
		txt_AgeBegin =(EditText)this.findViewById(R.id.person_age_begin);
		txt_AgeEnd = (EditText)this.findViewById(R.id.person_age_end);
		txt_Province = (Spinner)this.findViewById(R.id.person_province);
		
		txt_City = (Spinner)this.findViewById(R.id.person_city);
		txt_Work = (Spinner)this.findViewById(R.id.person_work);
		
//		btn_back = (Button)this.findViewById(R.id.person_back);
//		btn_search = (Button)this.findViewById(R.id.person_search);
		
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, provinces);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		txt_Province.setAdapter(adapter1);
		
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sexes);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		txt_Sex.setAdapter(adapter2);
		
		ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, works);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		txt_Work.setAdapter(adapter3);		
		
		
		txt_Province.setOnItemSelectedListener(provinceSelectedListener);
		txt_City.setOnItemSelectedListener(citySelectedListener);
		txt_Sex.setOnItemSelectedListener(sexSelectedListener);
		txt_Work.setOnItemSelectedListener(workSelectedListener);
		
		
		
		
	}
	
	private AdapterView.OnItemSelectedListener provinceSelectedListener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View  v, int position,
				long id) {
			selectedProvince = (String) parent.getItemAtPosition(position);
			
			//再根据用户选择的省份，得到对应的城市
			getCities();
			ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(PersonSearch.this, android.R.layout.simple_spinner_item, cities);
			adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			txt_City.setAdapter(adapter2);			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	private AdapterView.OnItemSelectedListener citySelectedListener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int position,
				long id) {
			
			selectedCity = (String)parent.getItemAtPosition(position);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	private AdapterView.OnItemSelectedListener sexSelectedListener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int position,
				long id) {
			// TODO Auto-generated method stub
			selectedSex = (String)parent.getItemAtPosition(position);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	};	
	private AdapterView.OnItemSelectedListener workSelectedListener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int position,
				long id) {
			// TODO Auto-generated method stub
			selectedWork = (String)parent.getItemAtPosition(position);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	};	
	
	//获取省份列表
	private void getProvinces(){
		provinces = new ArrayList<String>();
		provinces.add("请选择");
		String[] projection = new String[]{
				Province._ID,
				Province.NAME
		};
		Cursor c = this.managedQuery(Province.CONTENT_URI, projection, null, null, null);
		
		if(c.moveToFirst()){
			for(int i=0; i<c.getCount(); i++){
				c.moveToPosition(i);
				String name = c.getString(1);
				provinces.add(name);
			}
		}		
	}
	
	//根据用户选择的省份动态获取城市列表
	private void getCities(){
		cities = new ArrayList<String>();
		cities.add("请选择");
		
		if(this.selectedProvince != null && !this.selectedProvince.equals("请选择")){
			
			String[] projection = new String[]{
					City._ID,
					City.PROVINCE_NAME,
					City.NAME
					
			};
			
			Cursor c = this.managedQuery(City.CONTENT_URI, projection, City.PROVINCE_NAME + "=?", new String[]{this.selectedProvince}, null);
			if(c.moveToFirst()){
				for(int i=0; i<c.getCount(); i++){
					c.moveToPosition(i);
					String name = c.getString(2);
					cities.add(name);
				}
			}
			
		}		
	}
	
	//查询系统工作信息列表
	private void getWorks(){
		works = new ArrayList<String>();
		works.add("请选择");
		
		String[] projection = new String[]{
				Work._ID,
				Work.NAME
		};
		
		Cursor c = this.managedQuery(Work.CONTENT_URI, projection, null, null, null);
		
		if(c.moveToFirst()){
			for(int i=0; i<c.getCount(); i++){
				c.moveToPosition(i);
				String name = c.getString(1);
				works.add(name);
			}			
		}
	}
	
	//将表单中的值，封装进Bundle
	private Bundle buildParams(){
		Bundle params = new Bundle();
		
		try {
			String name = txt_Name.getText().toString().trim();
			if(!TextUtils.isEmpty(name)){
				
				name = new String(name.getBytes(),"ISO-8859-1");
				params.putString("person.name",name);
			}
			
			if(!selectedSex.equals("请选择")){
				if(selectedSex.equals("男")){
					params.putString("person.sex", "m");
				}else{
					params.putString("person.sex", "w");
				}
			}
			
			String idcard = txt_Idcard.getText().toString().trim();
			if(!TextUtils.isEmpty(idcard)){
				params.putString("person.idCard", idcard);
			}
			
			String ageBegin = txt_AgeBegin.getText().toString().trim();
			if(!TextUtils.isEmpty(ageBegin)){
				params.putString("ageBegin", ageBegin);
			}
			
			String ageEnd = txt_AgeEnd.getText().toString().trim();
			if(!TextUtils.isEmpty(ageEnd)){
				params.putString("ageEnd", ageEnd);
			}
			
			if(!selectedProvince.equals("请选择")){
				selectedProvince = new String(selectedProvince.getBytes(),"ISO-8859-1");
				params.putString("person.province", selectedProvince);
			}
			
			if(!selectedCity.equals("请选择")){
				selectedCity = new String(selectedCity.getBytes(),"ISO-8859-1");
				params.putString("person.city", selectedCity);
			}
			
			if(!selectedWork.equals("请选择")){
				selectedWork = new String(selectedWork.getBytes(),"ISO-8859-1");
				params.putString("person.work", selectedWork);
			}
			
			return params;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			ActivityUtils.showMessage(this, "字符编码出错");
		}
		
		return null;
		
	}
	
	
	public void onClick(View v){
		
		int id = v.getId();
//		LinearLayout menu = (LinearLayout)this.findViewById(id);
//		menu.setBackgroundResource(R.drawable.menu_pressed);
		
		if(id == R.id.person_back){
			//返回
			
			//Toast.makeText(this, "返回", Toast.LENGTH_LONG).show();
			this.finish();
			
		}else if(id == R.id.person_search){
			//执行查询
			if(HttpUtils.isNetworkAvailable(this)){
				//获取Http地址；
				search = new Search(ActivityUtils.getIp(this, JSON_NAME));
				RequestListener listener = new PersonListener(this);
				search.request(this.buildParams(), listener);
				
			}else{
				ActivityUtils.showMessage(this, "当前网络不可用，请先进行配置");
			}

		}
	}
	
//	public void testDelete(){
//		Uri uri = City.CONTENT_URI;
//		getContentResolver().delete(uri, null, null);
//	}
//	
//	private void testInsert(){
//		Uri uri = Work.CONTENT_URI;
//		ContentValues values = new ContentValues();
//		//values.put(City.PROVINCE_NAME, "安徽");
//		values.put(Work.NAME, "暂无职业");
//		getContentResolver().insert(uri, values);
//	}
//	
//	public void testQuery(){
//		String[] projection = new String[]{
//				Province._ID,
//				Province.NAME
//		};
//		Cursor c = this.managedQuery(Province.CONTENT_URI, projection, null, null, null);
//		
//		if(c.moveToFirst()){
//			for(int i=0; i<c.getCount(); i++){
//				c.moveToPosition(i);
//				String name = c.getString(1);
//				Log.i("Province:", name);
//			}
//		}
//	}

}
