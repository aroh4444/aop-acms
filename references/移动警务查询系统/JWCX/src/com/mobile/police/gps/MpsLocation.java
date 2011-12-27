package com.mobile.police.gps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;

import com.mobile.police.common.ActivityUtils;

public class MpsLocation {
	
	private Activity context;
	
	public MpsLocation(Activity context){
		this.context = context;
	}
	
	//检查GPS模块是否开启
	public void openGPSSetting(){
		LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){
			ActivityUtils.showMessage(context, "GPS模块正常！");
		}else{
			ActivityUtils.showMessage(context, "请开启GPS功能！");
			Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
			context.startActivityForResult(intent, 0);
		}
	}
	
	public void getLocation(LocationListener listener){
		LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		
		Criteria c = new Criteria();
		c.setAccuracy(Criteria.ACCURACY_FINE);
		c.setAltitudeRequired(false);
		c.setBearingRequired(false);
		c.setSpeedRequired(false);
		c.setPowerRequirement(Criteria.POWER_LOW);
		
		String provider = lm.getBestProvider(c, true);
		
		Location location = lm.getLastKnownLocation(provider);
		//这里将Location信息保存到本地，同时上传到服务器
		updateNewLocation(location);
		lm.requestLocationUpdates(provider, 600*1000, 500, listener);
		
	}
	
	/**
	 * 1、获取本终端位置
	 * 2、上传到服务器
	 * 3、更新服务器位置信息到本地
	 * 4、将信息存储到本地位置数据库
	 */
	public void updateNewLocation(Location location){
		//location.getL
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
	}

}
