package com.mobile.police.adapter;

import java.util.List;
import java.util.Map;

import com.mobile.police.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 自定义适配器
 * @author chenjie
 *
 */
public class ImageListAdapter extends BaseAdapter {
	
	private Context context;
	
	private List<Map<String, Object>> datas;
	
	public ImageListAdapter(Context context, List<Map<String, Object>> datas){
		this.context = context;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		return this.datas.size();
	}

	@Override
	public Object getItem(int position) {
		return this.datas.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			
			holder.image = (ImageView)convertView.findViewById(R.id.item_image);
			holder.text = (TextView)convertView.findViewById(R.id.item_content);
			holder.rightImage = (ImageView)convertView.findViewById(R.id.item_right);
			
			convertView.setTag(holder);
			
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		Map<String, Object> item = this.datas.get(position);
		int imgId = Integer.parseInt(item.get("image").toString());
		holder.image.setImageResource(imgId);
		holder.text.setText(item.get("content").toString());
		int rightId = Integer.parseInt(item.get("right").toString());
		holder.rightImage.setImageResource(rightId);
		return convertView;
	}
	
	private static class ViewHolder{
		ImageView image;
		TextView text;
		ImageView rightImage;
	}

}
