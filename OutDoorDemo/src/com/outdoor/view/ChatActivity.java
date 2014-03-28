package com.outdoor.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.outdoordemo2.R;
import com.outdoor.adapter.ChatMsgViewAdapter;
import com.outdoor.model.ChatMsgEntity;

/**
 * 
 * @author geniuseoe2012 更多精彩，请关注我的CSDN博客http://blog.csdn.net/geniuseoe2012
 *         android开发交流群：200102476
 */
public class ChatActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	private Button mBtnSend;
	private Button mBtnBack;
	private EditText mEditTextContent;
	// 消息列表视图
	private ListView mListView;
	// 消息列表数据适配器
	private ChatMsgViewAdapter mAdapter;
	// 所有消息存放列表
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		// 启动activity时不自动弹出软键盘
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		// 初始化界面控件
		initView();
		// 初始化界面数据
		initData();
	}

	public void initView() {
		mListView = (ListView) findViewById(R.id.listview);
		mBtnSend = (Button) findViewById(R.id.btn_send);
		mBtnSend.setOnClickListener(this);
		mBtnBack = (Button) findViewById(R.id.btn_back);
		mBtnBack.setOnClickListener(this);

		mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
	}

	// ----------------------------------------模拟数据----------------------------------------
	private String[] msgArray = new String[] { "早上好", "嗯", "去什么地方玩？", "我明天去北京",
			"一起去骑车吧", "光谷今天新开了一个店", "明天一起去潜水", "<>狗日滴<>"};

	private String[] dataArray = new String[] { "2013-9-1 08:00",
			"2013-9-3 12:10", "2013-12-4 18:11", "2013-12-25 00:20",
			"2014-1-3 13:30", "2014-1-14 19:35", "2014-3-1 02:42",
			"2014-3-21 18:54" };
	private final static int COUNT = 8;

	// ----------------------------------------模拟数据----------------------------------------

	public void initData() {
		for (int i = 0; i < COUNT; i++) {
			ChatMsgEntity entity = new ChatMsgEntity();
			entity.setDate(dataArray[i]);
			if (i % 2 == 0) {
				entity.setName("小黑");
				entity.setMsgType(true);
			} else {
				entity.setName("我");
				entity.setMsgType(false);
			}

			entity.setText(msgArray[i]);
			mDataArrays.add(entity);
		}

		mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
		mListView.setAdapter(mAdapter);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_send:
			send();
			break;
		case R.id.btn_back:
			finish();
			break;
		}
	}

	private void send() {
		String contString = mEditTextContent.getText().toString();
		if (contString.length() > 0) {
			// 消息对象
			ChatMsgEntity entity = new ChatMsgEntity();
			// 设置发送日期
			entity.setDate(getDate());
			// 设置用户名称
			entity.setName("我");
			// 设置消息类型为 发送类型
			entity.setMsgType(false);
			// 设置消息文本内容
			entity.setText(contString);

			mDataArrays.add(entity);
			// 内容更改通知， 刷新UI
			mAdapter.notifyDataSetChanged();

			// 输入框内容置空
			mEditTextContent.setText("");
			// ??? 啥作用?
			mListView.setSelection(mListView.getCount() - 1);
		}
	}

	private String getDate() {
		Calendar c = Calendar.getInstance();

		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = String.valueOf(c.get(Calendar.MONTH));
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
		String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String mins = String.valueOf(c.get(Calendar.MINUTE));

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":"
				+ mins);

		return sbBuffer.toString();
	}
	
	/*
	public void head_xiaohei(View v) { // 标题栏 返回按钮
		Intent intent = new Intent(ChatActivity.this, InfoXiaohei.class);
		startActivity(intent);
	} */
	
}