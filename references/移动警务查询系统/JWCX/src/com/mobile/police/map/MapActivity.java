package com.mobile.police.map;

import java.util.Vector;

import com.mobile.police.R;

import cn.creable.gridgis.controls.App;
import cn.creable.gridgis.controls.MapView;
import cn.creable.ucmap.ILocalSearchListener;
import cn.creable.ucmap.IPathSearchListener;
import cn.creable.ucmap.MapLoader;
import cn.creable.ucmap.OpenSourceMapLayer;
import cn.creable.ucmap.OpenSourceMapLayer$Path;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MapActivity extends Activity {
	MapView mapView;
	ProgressDialog dlg;
	Activity act;
	PopupWindow pop;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        act=this;
        mapView=(MapView)findViewById(R.id.mapView);
    }
    
    
    public void onClick(View v){
    	int id = v.getId();
//    	LinearLayout menu = (LinearLayout)this.findViewById(id);
//    	menu.setBackgroundResource(R.drawable.menu_pressed);
    	switch (id) {
		case R.id.back:
			
			this.finish();
			break;
		case R.id.push:
			//装入地图
			MapLoader.loadMapXML(mapView.getMapControl(), "/sdcard/OpenSourceMap.xml");
			mapView.getMapControl().setCustomDraw(new GPSCustomDraw(mapView.getMapControl()));
			break;
		case R.id.bigger:
			//放大地图
			mapView.getMapControl().setZoomInTool();
			break;
		case R.id.smaller:
			//缩小图片
			mapView.getMapControl().setZoomOutTool();
			break;
		case R.id.move:
			//平移图片
			mapView.getMapControl().setPanTool();
			break;
		case R.id.other:
			//其他功能
			//这里采用PopupWindow来实现
			LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(R.layout.map_popup, null);
			//获取PopupWindow上菜单的点击事件
			LinearLayout keyMenu = (LinearLayout)view.findViewById(R.id.key);
			keyMenu.setOnClickListener(keyListener);
			LinearLayout routeMenu = (LinearLayout)view.findViewById(R.id.route);
			routeMenu.setOnClickListener(routeListener);
			LinearLayout sourceMenu = (LinearLayout)view.findViewById(R.id.source);
			sourceMenu.setOnClickListener(sourceListener);
			LinearLayout modelMenu = (LinearLayout)view.findViewById(R.id.model);
			modelMenu.setOnClickListener(modelListener);
			pop = new PopupWindow(view, 200,140,true);
			pop.setAnimationStyle(R.style.PopupAnim);//PopupWindow动画
			pop.setBackgroundDrawable(new BitmapDrawable());//加上这句可以点击Pop的父窗体任意位置，Pop自动消失
			pop.showAsDropDown(this.findViewById(id), -140, -190);
			pop.update();
			break;
		default:
			break;
		}
    	//menu.setBackgroundColor(Color.BLACK);
    	
    }
    
    private View.OnClickListener keyListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//关键字查询
			if(pop != null){
				pop.dismiss();
			}			
			keySearch();
			
		}
	};
    private View.OnClickListener routeListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(pop != null){
				pop.dismiss();
			}
			routeSearch();
		}
	};
    private View.OnClickListener sourceListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(pop != null){
				pop.dismiss();
			}			
			switchSource();
			
		}
	};
    private View.OnClickListener modelListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(pop != null){
				pop.dismiss();
			}			
			switchModel();
			
		}
	};	
	
	
	private void keySearch(){
		if (mapView.getMapControl().getMap().getLayerCount()>0)
		{
			OpenSourceMapLayer oslayer=(OpenSourceMapLayer)mapView.getMapControl().getMap().getLayer(0);
			oslayer.setLocalSearchListener(new ILocalSearchListener(){

				@Override
				public void localSearchFinished(Vector pois) {
					if (pois==null) return;
					int size=pois.size();
					StringBuilder sb=new StringBuilder();
					for (int i=0;i<size;++i)
					{
						cn.creable.ucmap.OpenSourceMapLayer$POI poi=(cn.creable.ucmap.OpenSourceMapLayer$POI)pois.get(i);
						sb.append(poi.title);
						sb.append("\n");
					}
					sb.deleteCharAt(sb.length()-1);
					//����Toast��ʾ��ѯ������Ϣ�������
					Bundle b=new Bundle();
					b.putString("string",sb.toString());
					Message msg=new Message();
					msg.what=1;
					msg.setData(b);
					handler.sendMessage(msg);
					sb=null;
				}
				
			});
			oslayer.localSearch("芜湖,镜湖", 0);
			//��ʾ�ȴ����
			dlg = new ProgressDialog(act);   
			dlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);   
			dlg.setTitle("提示");   
			dlg.setMessage("正在进行关键字查询，请稍后");   
			dlg.setIcon(R.drawable.icon);   
			dlg.setIndeterminate(false);   
			dlg.setCancelable(true);
			dlg.setButton("取消", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					OpenSourceMapLayer oslayer=(OpenSourceMapLayer)mapView.getMapControl().getMap().getLayer(0);
					oslayer.cancel();
				}
				
			});
			dlg.show();   
		}		
	}
	
	private void routeSearch(){
		if (mapView.getMapControl().getMap().getLayerCount()>0)
		{
			OpenSourceMapLayer oslayer=(OpenSourceMapLayer)mapView.getMapControl().getMap().getLayer(0);
			oslayer.setPathSearchListener(new IPathSearchListener(){

				@Override
				public void pathSearchFinished(OpenSourceMapLayer$Path path) {
					if (path==null) return;
					int size=path.markArray.length;
					StringBuilder sb=new StringBuilder();
					sb.append("路径名称:");
					sb.append(path.name);
					sb.append("\n");
					for (int i=0;i<size;++i)
					{
						sb.append("拐点名称:");
						sb.append(path.markArray[i].name);
						sb.append("\n");
					}
					sb.deleteCharAt(sb.length()-1);
					//����Toast��ʾ��ѯ����·�����Լ��յ���
					Bundle b=new Bundle();
					b.putString("string",sb.toString());
					Message msg=new Message();
					msg.what=1;
					msg.setData(b);
					handler.sendMessage(msg);
					sb=null;
				}
				
			});
			oslayer.getPath(118.855484f,32.055214f,118.733574f,32.087738f,false);
			//��ʾ�ȴ����
			dlg = new ProgressDialog(act);   
			dlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);   
			dlg.setTitle("提示");   
			dlg.setMessage("正在进行路径查询，请稍后");   
			dlg.setIcon(R.drawable.icon);   
			dlg.setIndeterminate(false);   
			dlg.setCancelable(true);
			dlg.setButton("取消 ", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					OpenSourceMapLayer oslayer=(OpenSourceMapLayer)mapView.getMapControl().getMap().getLayer(0);
					oslayer.cancel();
				}
				
			});
			dlg.show();   
		}		
	}
	
	private void switchSource(){
		if (mapView.getMapControl().getMap().getLayerCount()>0)
		{
			OpenSourceMapLayer oslayer=(OpenSourceMapLayer)mapView.getMapControl().getMap().getLayer(0);
			oslayer.getMapMode();
			switch (oslayer.getMapMode())
			{
			case GOOGLE:
				oslayer.setMapMode(1);break;
			case BING:
				oslayer.setMapMode(0);break;
			}
			mapView.getMapControl().refresh();
		}		
	}
	
	private void switchModel(){
		
		if (mapView.getMapControl().getMap().getLayerCount()>0)
		{
			OpenSourceMapLayer oslayer=(OpenSourceMapLayer)mapView.getMapControl().getMap().getLayer(0);
			switch (oslayer.getMode())
			{
			case 0:
				oslayer.setMode(1);
				break;
			case 1:
				oslayer.setMode(0);
				break;
			}
			mapView.getMapControl().refresh();
		}		
	}
    
	
	private Handler handler=new Handler(){
		public void handleMessage(Message msg) 
		{    
            switch (msg.what) 
            {    
            case 1:
            	//�رյȴ����
            	dlg.cancel();
            	dlg=null;
            	//����Toast��ʾ��ѯ�Ľ��
            	Toast.makeText(App.getInstance(), msg.getData().getString("string"), Toast.LENGTH_SHORT).show();
            	break;
            }
		}
	};
}