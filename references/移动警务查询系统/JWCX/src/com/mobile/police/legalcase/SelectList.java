package com.mobile.police.legalcase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobile.police.R;
import com.mobile.police.adapter.ImageListAdapter;
import com.mobile.police.common.ActivityUtils;
import com.mobile.police.common.DefaultRequestListener;
import com.mobile.police.common.RequestListener;
import com.mobile.police.common.Search;
import com.mobile.police.fugitive.FugitiveList;
import com.mobile.police.fugitive.FugitiveListener;
import com.mobile.police.suspect.SuspectList;
import com.mobile.police.suspect.SuspectListener;
import com.mobile.police.unit.UnitList;
import com.mobile.police.unit.UnitListener;
import com.mobile.police.victim.VictimList;
import com.mobile.police.victim.VictimListener;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;

public class SelectList extends ListActivity {
	
	private static final String JSON_SUSPECT = "suspect.json";
	
	private static final String JSON_VICTIM = "victim.json";
	
	private static final String JSON_UNIT = "unit.json";
	
	private static final String JSON_FUG= "fugitive.json";
	
	private Search search;
	
	private String[] menus = new String[]{
			"嫌疑人  ",
			"受害人  ",
			"受害单位",
			"在逃人员"
	};
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.list);
		
		List<Map<String, Object>> data = this.getData();
		ImageListAdapter adapter = new ImageListAdapter(this, data);
		this.getListView().setAdapter(adapter);
		
		this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				
				//ActivityUtils.showMessage(SelectList.this, position+"");
				longClickEvent(position);
			}
		});
		
		
		
	}
	
	private void getSuspectList(Bundle params){
		search = new Search(ActivityUtils.getIp(this, JSON_SUSPECT));
		//RequestListener listener = new SuspectListener(this);
		RequestListener listener = new DefaultRequestListener(this, SuspectList.class);
		search.request(params, listener);
	}
	
	private void getVictimList(Bundle params){
		search = new Search(ActivityUtils.getIp(this, JSON_VICTIM));
		//RequestListener listener = new VictimListener(this);
		RequestListener listener = new DefaultRequestListener(this, VictimList.class);
		search.request(params, listener);
	}
	
	private void getUnitList(Bundle params){
		search = new Search(ActivityUtils.getIp(this, JSON_UNIT));
		//RequestListener listener = new UnitListener(this);
		RequestListener listener = new DefaultRequestListener(this, UnitList.class);
		search.request(params, listener);		
	}
	
	private void getFugitives(Bundle params){
		search = new Search(ActivityUtils.getIp(this, JSON_FUG));
		//RequestListener listener = new FugitiveListener(this);
		RequestListener listener = new DefaultRequestListener(this, FugitiveList.class);
		search.request(params, listener);
	}
	
	
	private void longClickEvent(int position){
		int caseId = this.getIntent().getIntExtra("caseId", 0);
		Bundle params = new Bundle();
		params.putString("caseId", caseId+"");		
		switch(position){
		case 0:
			//嫌疑人
			getSuspectList(params);
			break;
		case 1:
			//受害人
			getVictimList(params);
			break;
		case 2:
			//受害单位
			getUnitList(params);
			break;
		case 3:
			getFugitives(params);
			break;
		}
	}
	
    //构建列表项的数据
    private List<Map<String, Object>> getData(){
    	List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
    	
    	for(int i=0; i<4; i++){
    		Map<String, Object> item = new HashMap<String, Object>();
    		item.put("image", R.drawable.list_item);
    		item.put("content", menus[i]);
    		item.put("right", R.drawable.list_right);
    		result.add(item);
    	}
    	
    	return result;
    }	

}
