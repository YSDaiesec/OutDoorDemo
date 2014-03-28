package com.outdoor.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.outdoordemo2.R;
import com.outdoor.adapter.PartyListAdapter;
import com.outdoor.control.Control;
import com.outdoor.model.Party;

public class PartyActivity extends Activity implements OnClickListener{

	private final static String TAG = "PartyActivity";

	private View joinLayout = null;
	private View favoriteLayout = null;
	private Button joinBtn = null;
	private Button favoriteBtn = null;
	private ListView partyJoinLv = null;
	private ListView partyFavoLv = null;

	
	private PartyListAdapter partyJoinListAdapter = null;
	private PartyListAdapter partyFavoListAdapter = null;
	
	private ArrayList<Party> partyJoinList = null;
	private ArrayList<Party> partyFavoList = null;
	
	
	private boolean joinLayoutIsVisible = true;
	private boolean favoLayoutIsVisible = false;
	
	private Handler handler = new Handler(){
		 
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
			case Control.TO_CHAT_ACTIVITY:
				Intent toChatActivity = new Intent(PartyActivity.this, ChatActivity.class);
				startActivity(toChatActivity);
				break;
			case Control.LOOK_IN_MAP:
				Intent tolookMapActivity = new Intent(PartyActivity.this, LookInMapActivity.class);
				startActivity(tolookMapActivity);
			default:
				break;
			}
        }
         
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_party);

		joinLayout = findViewById(R.id.layout_join);
		favoriteLayout = findViewById(R.id.layout_favorite);
		joinBtn = (Button) findViewById(R.id.btn_join);
		favoriteBtn = (Button) findViewById(R.id.btn_favorite);
		
		
		partyJoinLv = (ListView) findViewById(R.id.lv_join);
		partyFavoLv = (ListView) findViewById(R.id.lv_favorite);
		
		initData();
		
		partyJoinListAdapter = new PartyListAdapter(this, partyJoinList);
		partyFavoListAdapter = new PartyListAdapter(this, partyFavoList);

		partyJoinListAdapter.setHandler(handler);
		partyFavoListAdapter.setHandler(handler);
		
		partyJoinLv.setAdapter(partyJoinListAdapter);
		partyFavoLv.setAdapter(partyFavoListAdapter);
		
		joinBtn.setOnClickListener(this);
		favoriteBtn.setOnClickListener(this);
		
	}
	
	
	/**
	 * @author Stone
	 * @param  null
	 *
	 */
	private void initActivity() {
		
	}
	
	/**
	 * @author Stone
	 * @param  null
	 */
	public void initData(){
		partyJoinList = new ArrayList<Party>();
		partyJoinList.add(new Party("旅游", "武大看樱花", "武汉大学", "2014-3-2 14:00"));
		partyJoinList.add(new Party("骑车", "环东湖骑行", "东湖", "2014-3-20 9:00"));
		partyJoinList.add(new Party("登山", "去峨嵋山看日出", "峨嵋山", "2014-5-1 6:00"));
		partyJoinList.add(new Party("春游", "放风筝", "呼伦贝尔大草原", "2014-5-2 10:00"));
		
		partyFavoList = new ArrayList<Party>();
		partyFavoList.add(new Party("登山", "去峨嵋山看日出", "峨嵋山", "2014-5-1 6:00"));
		partyFavoList.add(new Party("春游", "放风筝", "呼伦贝尔大草原", "2014-5-2 10:00"));
		partyFavoList.add(new Party("登山", "去峨嵋山看日出", "峨嵋山", "2014-5-1 6:00"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		//�л����ҲμӵĻ
		case R.id.btn_join:
			if(!joinLayoutIsVisible && favoLayoutIsVisible){
				joinLayout.setVisibility(View.VISIBLE);
				
				favoriteLayout.setVisibility(View.INVISIBLE);
				joinLayoutIsVisible = true;
				favoLayoutIsVisible = false;
			}
			Log.i(TAG, "�л����ҲμӵĻ");
			break;
		//�л����ҹ�ע�Ļ
		case R.id.btn_favorite:
			if(!favoLayoutIsVisible && joinLayoutIsVisible){
				joinLayout.setVisibility(View.INVISIBLE);
				favoriteLayout.setVisibility(View.VISIBLE);
				joinLayoutIsVisible = false;
				favoLayoutIsVisible = true;
			}
			Log.i(TAG, "�л����ҹ�ע�Ļ");
			break;
			
		default:
			break;
		}

	}

}
