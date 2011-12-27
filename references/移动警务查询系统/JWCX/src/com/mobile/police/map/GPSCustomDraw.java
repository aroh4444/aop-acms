package com.mobile.police.map;

import java.util.Timer;
import java.util.TimerTask;

import com.mobile.police.R;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import cn.creable.gridgis.controls.App;
import cn.creable.gridgis.controls.ICustomDraw;
import cn.creable.gridgis.controls.MapControl;
import cn.creable.gridgis.display.IDisplayTransformation;
import cn.creable.gridgis.geometry.Point;
import cn.creable.gridgis.util.Image;
import cn.creable.ucmap.LBS;
import cn.creable.ucmap.OpenSourceMapLayer;

public class GPSCustomDraw implements ICustomDraw,LocationListener {
	
	private MapControl mapControl;
	public float lon,lat;
	public float x,y;
	private Image gps;
	private Image gps1;
	private boolean flag;
	private Paint paint;
	private IDisplayTransformation dt;
	private MyTimerTask timer;
	
	private LBS lbs;
	
	private class MyTimerTask extends TimerTask
	{

		@Override
		public void run() {
			if (lon!=0 && lat!=0 && mapControl.noCustomDraw==false)
				mapControl.repaint();
		}
		
	}
	
	public GPSCustomDraw(MapControl mapControl)
	{
		this.mapControl=mapControl;
		dt=mapControl.getDisplay().getDisplayTransformation();
		paint=new Paint();
		paint.setAntiAlias(true);
		BitmapDrawable bmpDraw=(BitmapDrawable)App.getInstance().getResources().getDrawable(R.drawable.gps);
		gps=new Image(bmpDraw.getBitmap());
		bmpDraw=(BitmapDrawable)App.getInstance().getResources().getDrawable(R.drawable.gps1);
		gps1=new Image(bmpDraw.getBitmap());
		Timer myTimer = new Timer();
		timer=new MyTimerTask();
		myTimer.schedule(timer, 500, 500);
		
		lbs=new LBS(App.getInstance());
		lbs.openGPS(1000, 0.01f, this);
		lbs.getPositionByNetwork(this);
		
	}
	
	public void close()
	{
		lbs.closeGPS(this);
	}

	@Override
	public void draw(Canvas g) {
		if (x!=0 && y!=0)
		{
			Point pt=new Point(x,y);
			Point result=new Point();
			dt.fromMapPoint(pt, result);//��ͼ�����ת��Ϊ��Ļ���
			//�������ͼƬ���γ�gps����˸��Ч��
			if ((flag=!flag))
				gps.draw(g, (int)result.getX()-gps.getWidth()/2, (int)result.getY()-gps.getWidth()/2, null);
			else
				gps1.draw(g, (int)result.getX()-gps1.getWidth()/2, (int)result.getY()-gps1.getWidth()/2, null);
			pt=null;
			result=null;
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		lon=(float) location.getLongitude();
		lat=(float) location.getLatitude();
		//����γ��ת��Ϊͼ�����
		OpenSourceMapLayer oslayer=(OpenSourceMapLayer)mapControl.getMap().getLayer(0);
		if (oslayer!=null)
		{
			Point offset=oslayer.getOffset(lon, lat);
			Point pt=oslayer.fromLonLat(lon, lat);
			x=pt.getX()+offset.getX();
			y=pt.getY()+offset.getY();
		}
	}

	@Override
	public void onProviderDisabled(String arg0) {

	}

	@Override
	public void onProviderEnabled(String arg0) {

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {

	}

}
