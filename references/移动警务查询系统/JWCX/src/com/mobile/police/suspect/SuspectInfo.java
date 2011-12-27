package com.mobile.police.suspect;

import com.mobile.police.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class SuspectInfo extends Activity {
	
	private TextView info_name,info_sex,info_idcard,info_age,info_race,info_area,info_work,info_addr,info_remark,
		info_stature,info_voice,info_face,info_shape,info_blood,info_others,info_speciality,info_finger;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.suspect_info);
		
		info_name = (TextView)this.findViewById(R.id.suspect_name);
		info_sex = (TextView)this.findViewById(R.id.suspect_sex);
		info_idcard = (TextView)this.findViewById(R.id.suspect_idcard);
		info_age = (TextView)this.findViewById(R.id.suspect_age);
		info_race = (TextView)this.findViewById(R.id.suspect_race);
		info_area = (TextView)this.findViewById(R.id.suspect_area);
		info_work = (TextView)this.findViewById(R.id.suspect_work);
		info_addr = (TextView)this.findViewById(R.id.suspect_addr);
		info_remark = (TextView)this.findViewById(R.id.suspect_remark);
		info_stature = (TextView)this.findViewById(R.id.suspect_stature);
		info_voice = (TextView)this.findViewById(R.id.suspect_voice);
		info_face = (TextView)this.findViewById(R.id.suspect_face);
		info_shape = (TextView)this.findViewById(R.id.suspect_shape);
		info_blood = (TextView)this.findViewById(R.id.suspect_blood);
		info_others = (TextView)this.findViewById(R.id.suspect_others);
		info_speciality = (TextView)this.findViewById(R.id.suspect_speciality);
		info_finger = (TextView)this.findViewById(R.id.suspect_finger);
		
		this.getData();
	}
	
	public void getData(){
		Suspect s = (Suspect)this.getIntent().getSerializableExtra("suspect");
		
		if(s != null){
			info_name.setText(s.getName());
			info_sex.setText(s.getSex().equals("m")? "男":"女");
			info_idcard.setText(s.getIdCard());
			info_age.setText(s.getAge()+"");
			info_race.setText(s.getRace());
			info_area.setText(s.getProvince()+"省"+s.getCity()+"市");
			info_work.setText(s.getWork());
			info_addr.setText(s.getDetailAddr());
			info_remark.setText(s.getRemark());
			info_stature.setText(s.getStature()+"");
			info_voice.setText(s.getVoice());
			info_face.setText(s.getFace());
			info_shape.setText(s.getShape());
			info_blood.setText(s.getBlood()+"型");
			info_others.setText(s.getOthers());
			info_speciality.setText(s.getSpeciality());
			info_finger.setText(s.getFingerprint());
		}
	}

}
