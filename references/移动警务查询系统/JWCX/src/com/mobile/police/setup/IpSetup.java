package com.mobile.police.setup;

import com.mobile.police.R;
import com.mobile.police.common.ActivityUtils;
import com.mobile.police.provider.Ips.Ip;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class IpSetup extends Activity {
	
	private EditText ipAddr, ipPort;
	
	private long id;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.ip_setup);
		
		this.ipAddr = (EditText)this.findViewById(R.id.ip_addr);
		ipPort = (EditText)this.findViewById(R.id.ip_port);
		
		init();
		
	}
	
	public void init(){
		Uri uri = Ip.CONTENT_URI;
		String[] projection = new String[]{
				Ip._ID,
				Ip.IP_ADDR,
				Ip.IP_PORT
		};
		
		Cursor c = this.getContentResolver().query(uri, projection, null, null, null);
		if(c.getCount() > 0){
			c.moveToFirst();
			id = c.getLong(0);
			ipAddr.setText(c.getString(1));
			ipPort.setText(c.getInt(2)+"");
		}
	}
	
	public void save(){
		
		String addr = ipAddr.getText().toString().trim();
		String port = ipPort.getText().toString().trim();
		
		if(TextUtils.isEmpty(addr) || TextUtils.isEmpty(port)){
			ActivityUtils.showMessage(this, "ip地址和ip端口号不能为空！");
		}else{
			Uri uri = Ip.CONTENT_URI;
			uri = ContentUris.withAppendedId(uri, id);
			ContentValues values = new ContentValues();
			values.put(Ip.IP_ADDR, addr);
			values.put(Ip.IP_PORT, Integer.parseInt(port));
			this.getContentResolver().update(uri, values, null, null);
			ActivityUtils.showMessage(this, "设置成功");
		}
		
	}
	
	
	public void onClick(View v){
		int id = v.getId();
		switch(id){
		case R.id.ok:
			save();
			this.finish();
			break;
		case R.id.cancel:
			this.finish();
			break;
		}
	}

}
