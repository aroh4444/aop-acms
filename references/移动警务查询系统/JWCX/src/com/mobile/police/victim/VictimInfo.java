package com.mobile.police.victim;

import com.mobile.police.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class VictimInfo extends Activity {
	
	
	private TextView info_name,info_sex,info_idcard,info_age,info_race,info_area,info_work,info_addr,info_remark,info_telphone,info_type;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.victim_info);
		
		info_name = (TextView)this.findViewById(R.id.victim_name);
		info_sex = (TextView)this.findViewById(R.id.victim_sex);
		info_idcard = (TextView)this.findViewById(R.id.victim_idcard);
		info_age = (TextView)this.findViewById(R.id.victim_age);
		info_race = (TextView)this.findViewById(R.id.victim_race);
		info_area = (TextView)this.findViewById(R.id.victim_area);
		info_work = (TextView)this.findViewById(R.id.victim_work);
		info_addr = (TextView)this.findViewById(R.id.victim_addr);
		info_remark = (TextView)this.findViewById(R.id.victim_remark);
		info_telphone = (TextView)this.findViewById(R.id.victim_telphone);
		info_type = (TextView)this.findViewById(R.id.victim_type);

		getData();
	}
	
	
	private void getData(){
		
		Victim p = (Victim)this.getIntent().getSerializableExtra("victim");
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
		info_telphone.setText(p.getTelphone());
		info_type.setText(p.getVictimType());
	}	

}
