package com.mobile.police;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobile.police.adapter.ImageListAdapter;
import com.mobile.police.legalcase.CaseSearch;
import com.mobile.police.person.PersonSearch;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

/**
 * 查询列表
 * @author chenjie
 *
 */
public class SearchList extends ListActivity {
	
	private String[] contents = new String[]{
			"人口查询",
			"逃犯查询",
			"案件查询",
			"车辆查询",
			"其他查询"
	};
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.list);
		
        List<Map<String, Object>> data = this.getData();
        ImageListAdapter adapter = new ImageListAdapter(this, data);
        this.getListView().setAdapter(adapter);

	}
	
	@Override
	protected void onListItemClick(ListView l,View v,int position,long id){
		super.onListItemClick(l, v, position, id);
		Intent intent = this.getIntent();
		
		switch(position){
		case 0:
			intent.setClass(this, PersonSearch.class);
			break;
		case 1:
			break;
		case 2:
			intent.setClass(this, CaseSearch.class);
			break;
		case 3:
			break;
		case 4:
			intent.setClass(this, OtherSearchList.class);
			break;
			
		}
		
		startActivity(intent);
		this.finish();
	}
	
    //构建列表项的数据
    private List<Map<String, Object>> getData(){
    	List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
    	
    	for(int i=0; i<5; i++){
    		Map<String, Object> item = new HashMap<String, Object>();
    		item.put("image", R.drawable.list_item);
    		item.put("content", contents[i]);
    		item.put("right", R.drawable.list_right);
    		result.add(item);
    	}
    	
    	return result;
    }	

}
