package com.mobile.police.legalcase;

import com.mobile.police.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class CaseInfo extends Activity {
	
	private TextView info_code,info_category,info_source,info_addr,info_brief,info_report,info_find,
		info_event,info_crime,info_injured,info_death,info_loss,info_trace,info_evidence,info_feature,
		info_timing,info_site,info_target,info_method,info_characters,info_tool;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.case_info);
		
		info_code = (TextView)this.findViewById(R.id.info_case_code);
		info_category = (TextView)this.findViewById(R.id.info_case_category);
		info_source = (TextView)this.findViewById(R.id.info_case_source);
		info_addr = (TextView)this.findViewById(R.id.info_case_addr);
		info_brief = (TextView)this.findViewById(R.id.info_case_brief);
		info_report = (TextView)this.findViewById(R.id.info_case_report);
		info_find = (TextView)this.findViewById(R.id.info_case_find);
		info_event = (TextView)this.findViewById(R.id.info_case_event);
		info_crime = (TextView)this.findViewById(R.id.info_case_crime);
		info_injured = (TextView)this.findViewById(R.id.info_case_injured);
		info_death = (TextView)this.findViewById(R.id.info_case_death);
		info_loss = (TextView)this.findViewById(R.id.info_case_loss);
		info_trace = (TextView)this.findViewById(R.id.info_case_trace);
		info_evidence = (TextView)this.findViewById(R.id.info_case_evidence);
		info_feature = (TextView)this.findViewById(R.id.info_case_feature);
		info_timing = (TextView)this.findViewById(R.id.info_case_timing);
		info_site = (TextView)this.findViewById(R.id.info_case_site);
		info_target = (TextView)this.findViewById(R.id.info_case_target);
		info_method = (TextView)this.findViewById(R.id.info_case_method);
		info_characters = (TextView)this.findViewById(R.id.info_case_characters);
		info_tool = (TextView)this.findViewById(R.id.info_case_tool);
		
		this.getData();
	}
	
	
	private void getData(){
		LegalCase c = (LegalCase)this.getIntent().getSerializableExtra("case");
		info_code.setText(c.getCode());
		info_category.setText(c.getCategory());
		info_source.setText(c.getSource());
		info_addr.setText(c.getEventAddr());
		info_brief.setText(c.getBrief());
		info_report.setText(c.getReportTime());
		info_find.setText(c.getFindTime());
		info_event.setText(c.getEventTime());
		info_crime.setText(c.getCrimeNumber()+"");
		info_injured.setText(c.getInjuredNumber()+"");
		info_death.setText(c.getDeathNumber()+"");
		info_loss.setText(c.getLoss()+"");
		info_trace.setText(c.getTrace());
		info_evidence.setText(c.getEvidence());
		info_feature.setText(c.getSiteFeature());
		info_timing.setText(c.getTiming());
		info_site.setText(c.getSite());
		info_target.setText(c.getTarget());
		info_method.setText(c.getMethod());
		info_characters.setText(c.getCharacteristic());
		info_tool.setText(c.getTool());
	}

}
