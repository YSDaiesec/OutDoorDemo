package com.example.zouchuqu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import cn.buaa.myweixin.R;

public class CopyOfMainActivity extends Activity {
	private Button data_setting=null;
    private RelativeLayout aboutzouchuqu=null;
    private RelativeLayout feedbackzouchuqu=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		data_setting=(Button)findViewById(R.id.data_setting);
		data_setting.setOnClickListener(new data_settingListener());
		aboutzouchuqu=(RelativeLayout)findViewById(R.id.aboutzouchuqu);
		aboutzouchuqu.setOnClickListener(new aboutzouchuquListener());
		feedbackzouchuqu=(RelativeLayout)findViewById(R.id.feedbackzouchuqu);
		feedbackzouchuqu.setOnClickListener(new feedbackzouchuquListener());
	}
	class data_settingListener implements OnClickListener{
	@Override
	public void onClick(View v){
		Intent intent=new Intent();
		intent.setClass(CopyOfMainActivity.this, info_setting.class );
		CopyOfMainActivity.this.startActivity(intent);
		
	}
	
		
	}
	class aboutzouchuquListener implements OnClickListener{
		@Override
		public void onClick(View v){
			Intent intent=new Intent();
			intent.setClass(CopyOfMainActivity.this, about.class );
			CopyOfMainActivity.this.startActivity(intent);
			
		}
		
			
		}
	class feedbackzouchuquListener implements OnClickListener{
		@Override
		public void onClick(View v){
			Intent intent=new Intent();
			intent.setClass(CopyOfMainActivity.this, feedback.class );
			CopyOfMainActivity.this.startActivity(intent);
			
		}
		
			
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
