package com.mobile.police;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobile.police.adapter.ImageListAdapter;
import com.mobile.police.common.ActivityUtils;
import com.mobile.police.common.RequestListener;
import com.mobile.police.common.Search;
import com.mobile.police.setup.CityListener;
import com.mobile.police.setup.IpSetup;
import com.mobile.police.setup.ProvinceListener;
import com.mobile.police.setup.WorkListener;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;

public class SetupList extends ListActivity {
	
	private static final String JSON_WORK = "work.json";
	
	private static final String JSON_PROVINCE = "province.json";
	
	private static final String JSON_CITY = "city.json";
	
	private Search search;
	
	private String[] contents = new String[]{
			"更新省份信息  ",
			"更新城市信息",
			"更新职业信息",
			"设置IP信息  "
	};
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.list);
		
        List<Map<String, Object>> data = this.getData();
        ImageListAdapter adapter = new ImageListAdapter(this, data);
        this.getListView().setAdapter(adapter);
        
        this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				doSelect(position);
			}
		});
	
	}
	
	private void updateWork(){
		search = new Search(ActivityUtils.getIp(this, JSON_WORK));
		RequestListener listener = new WorkListener(this);
		search.request(null, listener);
	}
	
	private void updateProvince(){
		search = new Search(ActivityUtils.getIp(this, JSON_PROVINCE));
		RequestListener listener = new ProvinceListener(this);
		search.request(null, listener);
	}
	
	private void updateCity(){
		search = new Search(ActivityUtils.getIp(this, JSON_CITY));
		RequestListener listener = new CityListener(this);
		search.request(null, listener);		
	}
	
	private void updateIp(){
		Intent intent = new Intent();
		intent.setClass(this, IpSetup.class);
		startActivity(intent);
		this.finish();
	}
	
	private void doSelect(int position){
		switch(position){
		case 0:
			updateProvince();
			break;
		case 1:
			updateCity();
			break;
		case 2:
			updateWork();
			break;
		case 3:
			updateIp();
		}
	}
	
    //构建列表项的数据
    private List<Map<String, Object>> getData(){
    	List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
    	
    	for(int i=0; i<4; i++){
    		Map<String, Object> item = new HashMap<String, Object>();
    		item.put("image", R.drawable.list_item);
    		item.put("content", contents[i]);
    		item.put("right", R.drawable.list_right);
    		result.add(item);
    	}
    	
    	return result;
    }		

}
