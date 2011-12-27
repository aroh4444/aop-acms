package com.mobile.police.unit;

import com.mobile.police.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class UnitInfo extends Activity {
	
	private TextView info_name,info_category,info_owner,info_telphone,info_email;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.unit_info);
		
		info_name = (TextView)this.findViewById(R.id.unit_name);
		info_category = (TextView)this.findViewById(R.id.unit_category);
		info_owner = (TextView)this.findViewById(R.id.unit_owner);
		info_telphone = (TextView)this.findViewById(R.id.unit_phone);
		info_email = (TextView)this.findViewById(R.id.unit_email);
		
		this.getData();
	}
	
	private void getData(){
		
		Unit u = (Unit)this.getIntent().getSerializableExtra("unit");
		
		if(u != null){
			info_name.setText(u.getName());
			info_category.setText(u.getCategory());
			info_owner.setText(u.getOwner());
			info_telphone.setText(u.getTelphone());
			info_email.setText(u.getEmail());
		}
	}

}
