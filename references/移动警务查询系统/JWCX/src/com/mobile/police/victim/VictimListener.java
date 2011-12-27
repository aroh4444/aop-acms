package com.mobile.police.victim;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;

import com.mobile.police.common.RequestListener;
import com.mobile.police.legalcase.SelectList;

public class VictimListener implements RequestListener {
	
	private SelectList context;
	
	private ProgressDialog progress;
	
	public VictimListener(SelectList context){
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
				
				Intent intent = new Intent();
				intent.putExtra("result", result);
				intent.setClass(context, VictimList.class);
				
				context.startActivity(intent);
				context.finish();
			}
		});

	}

	@Override
	public void onException(final Exception e) {
		// TODO Auto-generated method stub
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
