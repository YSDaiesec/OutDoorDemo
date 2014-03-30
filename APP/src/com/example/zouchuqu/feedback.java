package com.example.zouchuqu;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import cn.buaa.myweixin.R;
public class feedback extends Activity {
	private Button feedback_reback_btn=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);
		feedback_reback_btn=(Button)findViewById(R.id.feedback_reback_btn);
		feedback_reback_btn.setOnClickListener(new feedback_reback_btnListener());
	}
	class feedback_reback_btnListener implements OnClickListener{
		@Override
		public void onClick(View v){
			finish();
			
		}
		
			
		}
	
}
