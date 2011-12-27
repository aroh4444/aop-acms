package com.mobile.police.legalcase;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.Toast;

import com.mobile.police.common.RequestListener;

public class CaseListener implements RequestListener {
	
	private CaseSearch context;
	
	private ProgressDialog progress;
	
	public CaseListener(CaseSearch context){
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
				intent.setClass(context, CaseList.class);
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
