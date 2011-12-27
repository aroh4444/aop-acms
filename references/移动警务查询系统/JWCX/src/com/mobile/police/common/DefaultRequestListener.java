package com.mobile.police.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;

/**
 * 该类是RequestListener接口的一个默认实现
 * @author Administrator
 *
 */
public class DefaultRequestListener implements RequestListener {
	
	private Activity context;
	
	private ProgressDialog progress;
	
	@SuppressWarnings("rawtypes")
	private Class clazz;
	
	@SuppressWarnings("rawtypes")
	public DefaultRequestListener(Activity context, Class clazz){
		this.context = context;
		this.clazz = clazz;
		progress = ProgressDialog.show(context, "提示", "正在查询,请稍后...");
		progress.show();
	}
	
	@SuppressWarnings("rawtypes")
	public DefaultRequestListener(Activity context, Class clazz, String msg){
		this.context = context;
		this.clazz = clazz;
		progress = ProgressDialog.show(context, "提示", msg);
		progress.show();		
	}
	
	private void doWhenComplete(String result){
		Intent intent = new Intent();
		intent.putExtra("result", result);
		intent.setClass(context, clazz);
		context.startActivity(intent);
		context.finish();		
	}

	@Override
	public void onComplete(final String result) {
		
		context.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				if(progress != null){
					progress.dismiss();
				}
				
				doWhenComplete(result);
			}
		});		

	}

	@Override
	public void onException(final Exception e) {
		context.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				if(progress != null){
					progress.dismiss();
				}
				e.printStackTrace();
				new AlertDialog.Builder(context)
					.setMessage(e.getMessage())
					.setPositiveButton("确定", null)
					.create().show();				
				
			}
		});

	}

}
