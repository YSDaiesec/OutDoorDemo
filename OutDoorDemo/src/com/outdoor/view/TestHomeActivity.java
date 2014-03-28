package com.outdoor.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.outdoordemo2.R;

public class TestHomeActivity extends Activity implements OnClickListener{
	
	private final static String TAG = "TestHomeActivity";
	
	private Button partyBtn = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		partyBtn = (Button) findViewById(R.id.btn_Party);
		partyBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent toPartyActivity = null;
		switch (v.getId()) {
		case R.id.btn_Party:
			toPartyActivity = new Intent(TestHomeActivity.this, PartyActivity.class);
			startActivity(toPartyActivity);
			break;

		default:
			break;
		}
	}
	

}
