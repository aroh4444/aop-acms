package com.mobile.police.fugitive;

import com.mobile.police.R;
import com.mobile.police.common.ActivityUtils;
import com.mobile.police.common.DefaultRequestListener;
import com.mobile.police.common.RequestListener;
import com.mobile.police.common.Search;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class FugitiveSearch extends Activity {
	
	//private static final String HTTP_URL = "http://192.168.0.90:8080/fugitive.json";
	
	private static final String JSON_NAME = "fugitive.json";
	
	private EditText name, idCard, txt_AgeBegin, txt_AgeEnd, txt_StatureBegin, txt_StatureEnd;
	
	private Spinner sex, voice, face, shape;
	
	private String selectedSex, selectedVoice, selectedFace, selectedShape;
	
	private String[] sexes = new String[]{
			"请选择",
			"男",
			"女"
	};
	
	private Search search;

	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.fugitive_search);
		
		name = (EditText)this.findViewById(R.id.fug_name);
		idCard = (EditText)this.findViewById(R.id.fug_idcard);
		txt_AgeBegin = (EditText)this.findViewById(R.id.fug_age_begin);
		txt_AgeEnd = (EditText)this.findViewById(R.id.fug_age_end);
		txt_StatureBegin = (EditText)this.findViewById(R.id.fug_stature_begin);
		txt_StatureEnd = (EditText)this.findViewById(R.id.fug_stature_end);
		
		sex = (Spinner)this.findViewById(R.id.fug_sex);
		voice = (Spinner)this.findViewById(R.id.fug_voice);
		face = (Spinner)this.findViewById(R.id.fug_face);
		shape = (Spinner)this.findViewById(R.id.fug_shape);
		
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sexes);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sex.setAdapter(adapter1);
		
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.fug_voice, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		voice.setAdapter(adapter2);
		
		ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.fug_face, android.R.layout.simple_spinner_item);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		face.setAdapter(adapter3);
		
		ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.fug_shape, android.R.layout.simple_spinner_item);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		shape.setAdapter(adapter4);	
		
		sex.setOnItemSelectedListener(sexListener);
		voice.setOnItemSelectedListener(voiceListener);
		face.setOnItemSelectedListener(faceListener);
		shape.setOnItemSelectedListener(shapeListener);
		
		
	}
	
	public void onClick(View v){
		int id = v.getId();
//		LinearLayout menu1 = (LinearLayout)this.findViewById(id);
//		menu1.setBackgroundResource(R.drawable.menu_pressed);		
		switch(id){
		case R.id.fug_back:
			this.finish();
			break;
		case R.id.fug_search:	
			//RequestListener listener = new FugitiveListener(this);
			search = new Search(ActivityUtils.getIp(this, JSON_NAME));
			RequestListener listener = new DefaultRequestListener(this, FugitiveList.class);
			search.request(this.buildParams(), listener);
			break;
		}
	}
	
	private Bundle buildParams(){
		Bundle params = new Bundle();
		try {
			String n = name.getText().toString().trim();
			if(!TextUtils.isEmpty(n)){
				
				n = new String(n.getBytes(),"ISO-8859-1");
				params.putString("fugitive.suspect.name",n);
			}
			
			if(!selectedSex.equals("请选择")){
				if(selectedSex.equals("男")){
					params.putString("fugitive.suspect.sex", "m");
				}else{
					params.putString("fugitive.suspect.sex", "w");
				}
			}
			
			String idcard = idCard.getText().toString().trim();
			if(!TextUtils.isEmpty(idcard)){
				params.putString("fugitive.suspect.idCard", idcard);
			}
			
			String ageBegin = txt_AgeBegin.getText().toString().trim();
			if(!TextUtils.isEmpty(ageBegin)){
				params.putString("ageBegin", ageBegin);
			}
			
			String ageEnd = txt_AgeEnd.getText().toString().trim();
			if(!TextUtils.isEmpty(ageEnd)){
				params.putString("ageEnd", ageEnd);
			}
			
			String statureBegin = txt_StatureBegin.getText().toString().trim();
			if(!TextUtils.isEmpty(statureBegin)){
				params.putString("statureBegin", statureBegin);
			}
			
			String statureEnd = txt_StatureEnd.getText().toString().trim();
			if(!TextUtils.isEmpty(statureEnd)){
				params.putString("statureEnd", statureEnd);
			}
			
			if(selectedVoice != null && !selectedVoice.equals("请选择")){
				selectedVoice = new String(selectedVoice.getBytes(),"ISO-8859-1");
				params.putString("fugitive.suspect.voice", selectedVoice);
			}
			
			if(selectedFace != null && !selectedFace.equals("请选择")){
				selectedFace = new String(selectedFace.getBytes(),"ISO-8859-1");
				params.putString("fugitive.suspect.face", selectedFace);
			}
			
			if(selectedShape != null && !selectedShape.equals("请选择")){
				selectedShape = new String(selectedShape.getBytes(),"ISO-8859-1");
				params.putString("fugitive.suspect.shape", selectedShape);				
			}
			return params;
			
		} catch (Exception e) {
			ActivityUtils.showMessage(this, "字符编码出错");
		}
		return null;
	}
	
	private AdapterView.OnItemSelectedListener sexListener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int position,
				long id) {
			
			selectedSex = (String)parent.getItemAtPosition(position);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	private AdapterView.OnItemSelectedListener voiceListener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int position,
				long id) {
			
			selectedVoice = (String)parent.getItemAtPosition(position);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	private AdapterView.OnItemSelectedListener faceListener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int position,
				long id) {
			
			selectedFace = (String)parent.getItemAtPosition(position);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	private AdapterView.OnItemSelectedListener shapeListener = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int position,
				long id) {
			
			selectedShape = (String)parent.getItemAtPosition(position);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	};	

}
