package com.mobile.police;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobile.police.adapter.ImageListAdapter;
import com.mobile.police.common.ActivityUtils;
import com.mobile.police.fugitive.FugitiveSearch;
import com.mobile.police.legalcase.CaseSearch;
import com.mobile.police.map.MapActivity;
import com.mobile.police.person.PersonSearch;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


public class MainActivity extends ListActivity {
	
	//在这个Activity中加一个菜单，设置快捷键
	
	private String[] contents = new String[]{
			"人口查询",
			"案件查询",
			"逃犯查询",
			"位置查询",
			"系统设置"
	};

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.init();
        setContentView(R.layout.main);
        List<Map<String, Object>> data = this.getData();
        ImageListAdapter adapter = new ImageListAdapter(this, data);
        this.getListView().setAdapter(adapter);
        
        
        
    }
    
    private void init(){
    	ProgressDialog progress = ProgressDialog.show(this, "提示", "系统正在初始化...");
    	progress.show();
    	ActivityUtils.initApp(this);
    	progress.dismiss();
    }
    
	
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent();
		switch(position){
		case 0:
			intent.setClass(this, PersonSearch.class);
			startActivity(intent);
			break;
		case 1:
			intent.setClass(this, CaseSearch.class);
			startActivity(intent);
			break;
		case 2:
			intent.setClass(this, FugitiveSearch.class);
			startActivity(intent);
			break;
		case 3:
			intent.setClass(this, MapActivity.class);
			startActivity(intent);
			break;
		case 4:
			intent.setClass(this, SetupList.class);
			startActivity(intent);			
			break;
		default:
			break;
		}
		
		
	}
    
    //构建列表项的数据
    private List<Map<String, Object>> getData(){
    	List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
    	
    	for(int i=0; i<contents.length; i++){
    		Map<String, Object> item = new HashMap<String, Object>();
    		item.put("image", R.drawable.list_item);
    		item.put("content", contents[i]);
    		item.put("right", R.drawable.list_right);
    		result.add(item);
    	}
    	
    	return result;
    }
}