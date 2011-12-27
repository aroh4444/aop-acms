package com.mobile.police.fugitive;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;

import com.mobile.police.common.RequestListener;

public class FugitiveListener implements RequestListener {
	
	private Activity context;
	
	private ProgressDialog progress;
	
	public FugitiveListener(Activity context){
		this.context = context;
		
		progress = ProgressDialog.show(context, "提示", "正在查询,请稍后...");
		progress.show();
	}

	@Override
	public void onComplete(final String result) {
		
		context.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				if(progress != null){
					progress.dismiss();
				}
				//Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.putExtra("result", result);
				intent.setClass(context, FugitiveList.class);
				context.startActivity(intent);
				context.finish();
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
