package com.mobile.police.gps;

import com.mobile.police.common.ActivityUtils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;
import android.provider.Settings;

/**
 * 该Service用于获取位置信息，上传位置信息，并更新位置信息
 * @author Administrator
 *
 */
public class LocationService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}
	
	public void onCreate(){
		super.onCreate();
	}
	
	public void onStart(Intent intent, int startId){
		super.onStart(intent, startId);
	}
	
	public void onDestroy(){
		super.onDestroy();
	}
	


}
