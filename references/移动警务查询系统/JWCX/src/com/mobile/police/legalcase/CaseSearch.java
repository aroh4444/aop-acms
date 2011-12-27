package com.mobile.police.legalcase;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import com.mobile.police.R;
import com.mobile.police.common.ActivityUtils;
import com.mobile.police.common.DefaultRequestListener;
import com.mobile.police.common.RequestListener;
import com.mobile.police.common.Search;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class CaseSearch extends Activity {
	
	private static final int DATE_DIALOG = 0;
	
	//private static final String HTTP_URL = "http://192.168.0.90:8080/case.json";
	
	private static final String JSON_NAME = "case.json";
	
	private EditText timeFrom, timeTo;
	
	private Spinner category,timing,site,target,method;
	
	private Integer m_year, m_month, m_day;
	
	private int flag = 0;
	
	private String selectedCategory, selectedTiming,selectedSite,selectedTarget,selectedMethod;
	
	private Search search;
	
	@SuppressWarnings("rawtypes")
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.case_search);
		
//		btn_dateFrom = (Button)this.findViewById(R.id.case_timeBegin);
//		btn_dateFrom.setOnClickListener(dateFromListener);
		
		timeFrom = (EditText)this.findViewById(R.id.case_timeBegin);
		timeTo = (EditText)this.findViewById(R.id.case_timeEnd);
		
		final Calendar c = Calendar.getInstance();
		m_year = c.get(Calendar.YEAR);
		m_month = c.get(Calendar.MONTH);
		m_day = c.get(Calendar.DAY_OF_MONTH);
		
		//udpateDisplay();
		category = (Spinner)this.findViewById(R.id.case_category);
		ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.case_category, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		category.setAdapter(adapter1);
		category.setOnItemSelectedListener(categoryListener);
		
		timing = (Spinner)this.findViewById(R.id.case_timing);
		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.case_timing, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		timing.setAdapter(adapter2);	
		timing.setOnItemSelectedListener(timingListener);
		
		
		site = (Spinner)this.findViewById(R.id.case_site);
		ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this, R.array.case_site, android.R.layout.simple_spinner_item);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		site.setAdapter(adapter3);	
		site.setOnItemSelectedListener(siteListener);
		
		target = (Spinner)this.findViewById(R.id.case_target);
		ArrayAdapter adapter4 = ArrayAdapter.createFromResource(this, R.array.case_target, android.R.layout.simple_spinner_item);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		target.setAdapter(adapter4);
		target.setOnItemSelectedListener(targetListener);
		
		method = (Spinner)this.findViewById(R.id.case_method);
		
		ArrayAdapter adapter5 = ArrayAdapter.createFromResource(this, R.array.case_method, android.R.layout.simple_spinner_item);
		adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		method.setAdapter(adapter5);
		method.setOnItemSelectedListener(methodListener);
		
	}
	
	private AdapterView.OnItemSelectedListener categoryListener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int position,
				long id) {
			selectedCategory = (String)parent.getItemAtPosition(position);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			
			
		}
		
	};
	private AdapterView.OnItemSelectedListener timingListener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int position,
				long id) {
			selectedTiming = (String)parent.getItemAtPosition(position);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			
			
		}
		
	};
	private AdapterView.OnItemSelectedListener siteListener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int position,
				long id) {
			
			selectedSite = (String)parent.getItemAtPosition(position);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			
			
		}
		
	};
	private AdapterView.OnItemSelectedListener targetListener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int position,
				long id) {
			selectedTarget = (String)parent.getItemAtPosition(position);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			
			
		}
		
	};
	private AdapterView.OnItemSelectedListener methodListener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int position,
				long id) {
			selectedMethod = (String)parent.getItemAtPosition(position);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			
			
		}
		
	};	

	private Bundle buildParams(){
		Bundle bundle = new Bundle();
		
		try {
			if(selectedCategory != null && !selectedCategory.equals("请选择")){
				selectedCategory = new String(selectedCategory.getBytes(), "ISO-8859-1");
				bundle.putString("legalCase.category", selectedCategory);
			}
			
			if(selectedTiming != null && !selectedTiming.equals("请选择")){
				selectedTiming = new String(selectedTiming.getBytes(),"ISO-8859-1");
				bundle.putString("legalCase.timing", selectedTiming);
			}
			
			if(selectedSite != null && !selectedSite.equals("请选择")){
				selectedSite = new String(selectedSite.getBytes(), "ISO-8859-1");
				bundle.putString("legalCase.site", selectedSite);
			}
			
			if(selectedTarget != null && !selectedTarget.equals("请选择")){
				selectedTarget = new String(selectedTarget.getBytes(), "ISO-8859-1");
				bundle.putString("legalCase.target", selectedTarget);
			}
			
			if(selectedMethod != null && !selectedMethod.equals("请选择")){
				selectedMethod = new String(selectedMethod.getBytes(), "ISO-8859-1");
				bundle.putString("legalCase.method", selectedMethod);
			}
			
			String dateFrom = timeFrom.getText().toString();
			if(dateFrom != null && !dateFrom.equals("") && !dateFrom.equals("请单击")){
				bundle.putString("dateBegin", dateFrom);
			}
			String dateTo = timeTo.getText().toString();
			if(dateTo != null && !dateTo.equals("") && !dateTo.equals("请单击")){
				bundle.putString("dateEnd", dateTo);
			}
			
			return bundle;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
//	private View.OnClickListener dateFromListener = new View.OnClickListener() {
//		
//		@Override
//		public void onClick(View v) {
//			
//			showDialog(DATE_DIALOG);
//			
//		}
//	};
	
	public void onClick(View v){
		int id = v.getId();
		switch(id){
		case R.id.case_timeBegin:
			flag = 0;
			showDialog(DATE_DIALOG);
			break;
		case R.id.case_timeEnd:
			flag = 1;
			showDialog(DATE_DIALOG);
			break;
		case R.id.case_back:
//			LinearLayout menu = (LinearLayout)this.findViewById(id);
//			menu.setBackgroundResource(R.drawable.menu_pressed);			
			this.finish();
			break;
		case R.id.case_search:
//			LinearLayout menu1 = (LinearLayout)this.findViewById(id);
//			menu1.setBackgroundResource(R.drawable.menu_pressed);	
			//采用定义新Listener方式
			//RequestListener listener = new CaseListener(this);
			//采用默认Listener方式
			search = new Search(ActivityUtils.getIp(this, JSON_NAME));
			RequestListener listener = new DefaultRequestListener(this,CaseList.class);
			search.request(this.buildParams(), listener);
			break;
		}
	}
	
	private DatePickerDialog.OnDateSetListener dateFromSetListener = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			
			m_year = year;
			m_month = monthOfYear;
			m_day = dayOfMonth;
			
			udpateDisplay();
		}
	};
	
	public void udpateDisplay(){
		StringBuilder sb = new StringBuilder();
		sb.append(m_year).append("-");
		int month = m_month+1;
		if(month>=10){
			sb.append(month);
		}else{
			sb.append("0").append(month);
		}

		sb.append("-")
			.append(m_day);
		
		if(flag == 0){
			timeFrom.setText(sb.toString());
		}else if(flag == 1){
			timeTo.setText(sb.toString());
		}
	}	
	
	private DatePickerDialog.OnDateSetListener dateToSetListener = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			// TODO Auto-generated method stub
			m_year = year;
			m_month = monthOfYear;
			m_day = dayOfMonth;
			udpateDisplay();
		}
	};
	
	
	public Dialog onCreateDialog(int id){
		switch(id){
		case DATE_DIALOG:
			if(flag == 0){
				return new DatePickerDialog(this, dateFromSetListener, m_year, m_month, m_day);
			}else if(flag == 1){
				return new DatePickerDialog(this, dateToSetListener, m_year, m_month, m_day);
			}
			
		}
		
		return null;
	}
	
	
	//更新时间
	public void onPrepareDialog(int id, Dialog d){
		switch(id){
		case DATE_DIALOG:
			((DatePickerDialog)d).updateDate(m_year, m_month, m_day);
		}
	}

}
