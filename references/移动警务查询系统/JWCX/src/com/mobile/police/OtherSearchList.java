package com.mobile.police;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobile.police.adapter.ImageListAdapter;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.AdapterView.OnItemClickListener;

public class OtherSearchList extends ListActivity {
	
	private String[] contents = new String[]{
			"社区查询",
			"单位查询",
			"法律查询",
			"公文查询",
			"位置查询"
	};

	private OnItemClickListener itemClickListener;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.list);
		
        List<Map<String, Object>> data = this.getData();
        ImageListAdapter adapter = new ImageListAdapter(this, data);
        this.getListView().setAdapter(adapter);
        
        this.getListView().setOnItemClickListener(itemClickListener);		
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
