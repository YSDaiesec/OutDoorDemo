package com.example.zouchuqu;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import cn.buaa.myweixin.R;

public class about extends Activity {
	private Button about_reback_btn=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		about_reback_btn=(Button)findViewById(R.id.about_reback_btn);
		about_reback_btn.setOnClickListener(new about_reback_btnListener());
	}
	class about_reback_btnListener implements OnClickListener{
		@Override
		public void onClick(View v){
			finish();
		}
		
			
		}
	
}
