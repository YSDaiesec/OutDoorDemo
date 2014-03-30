package com.geniuseoe.demo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import cn.buaa.myweixin.R;

public class LoginActivity extends Activity implements OnClickListener{

	private Button mBtnRegister;
	private Button mBtnLogin;
	
	private View mMoreView;
	private ImageView mMoreImage;
	private View mMoreMenuView;
	
	private boolean mShowMenu = false;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
       
        initView();
    }
    
    
    public void initView()
    {
    	mMoreView = findViewById(R.id.more);
    	mMoreMenuView = findViewById(R.id.moremenu);
    	mMoreImage = (ImageView) findViewById(R.id.more_image);
    	mMoreView.setOnClickListener(this);
    	
    	mBtnRegister = (Button) findViewById(R.id.regist);
    	mBtnRegister.setOnClickListener(this);
    	
    	mBtnLogin = (Button) findViewById(R.id.login);
    	mBtnLogin.setOnClickListener(this);
    }
    
    
    public void showMoreView(boolean bShow)
    {
    	if (bShow)
    	{
    		mMoreMenuView.setVisibility(View.GONE);
    		mMoreImage.setImageResource(R.drawable.login_more_up);
    		mShowMenu = true;
    	}else{
    		mMoreMenuView.setVisibility(View.VISIBLE);
    		mMoreImage.setImageResource(R.drawable.login_more);
    		mShowMenu = false;
    	}
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId())
		{
		case R.id.more:
			showMoreView(!mShowMenu);
			break;
		case R.id.regist:
			goRegisterActivity();
			break;
		case R.id.login:
			showRequestDialog();
			break;
			default:
				break;
		}
	}
    
    public void goRegisterActivity()
    {
    	Intent intent = new Intent();
    	intent.setClass(this, RegisterActivity.class);
    	startActivity(intent);
    }
	   

	private Dialog mDialog = null;
	private void showRequestDialog()
	{
		/*if (mDialog != null)
		{
			mDialog.dismiss();
			mDialog = null;
		}
		mDialog = DialogFactory.creatRequestDialog(this, "提交中...");
		mDialog.show();*/
		Intent intent = new Intent();
		intent.setClass(LoginActivity.this, cn.buaa.myweixin.MainWeixin.class);
		startActivity(intent);
	}
	
	
}
