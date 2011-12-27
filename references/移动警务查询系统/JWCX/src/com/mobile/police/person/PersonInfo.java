package com.mobile.police.person;

import com.mobile.police.R;
import com.mobile.police.common.ActivityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class PersonInfo extends Activity {
	
	
	
	private TextView info_name,info_sex,info_idcard,info_age,info_race,info_area,info_work,info_addr,info_remark;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.person_info);
		
		info_name = (TextView)this.findViewById(R.id.info_person_name);
		info_sex = (TextView)this.findViewById(R.id.info_person_sex);
		info_idcard = (TextView)this.findViewById(R.id.info_person_idcard);
		info_age = (TextView)this.findViewById(R.id.info_person_age);
		info_race = (TextView)this.findViewById(R.id.info_person_race);
		info_area = (TextView)this.findViewById(R.id.info_person_area);
		info_work = (TextView)this.findViewById(R.id.info_person_work);
		info_addr = (TextView)this.findViewById(R.id.info_person_addr);
		info_remark = (TextView)this.findViewById(R.id.info_person_remark);
		

		getData();
	}
	
	
	private void getData(){
		
		Person p = this.getIntent().getParcelableExtra("person");
		//ActivityUtils.showMessage(this, p.getAge()+"");
		info_name.setText(p.getName());
		info_sex.setText((p.getSex().equals("m"))?"男":"女");
		info_idcard.setText(p.getIdCard());
		info_age.setText(p.getAge()+"");
		info_race.setText(p.getRace());
		info_area.setText(p.getProvince()+"省"+p.getCity()+"市");
		info_work.setText(p.getWork());
		info_addr.setText(p.getDetailAddr());
		info_remark.setText(p.getRemark());
	}

}
