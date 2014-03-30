package cn.buaa.myweixin;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.amap.LocationSourceActivity;
//import com.example.zouchuqu.MainActivity;
import com.example.zouchuqu.about;
import com.example.zouchuqu.feedback;
import com.example.zouchuqu.info_setting;
import com.outdoor.adapter.PartyListAdapter;
import com.outdoor.model.Party;
import com.outdoor.view.ChatActivity;
import com.outdoor.view.ItemDetailActivity;
import com.outdoor.view.LookInMapActivity;
import com.outdoor.view.PartyActivity;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainWeixin extends Activity{
	
	public static MainWeixin instance = null;
	 
	private ViewPager mTabPager;	
	private ImageView mTabImg;// 动画图片
	private ImageView mTab1,mTab2,mTab3,mTab4;
	private TextView tvTab1,tvTab2,tvTab3,tvTab4;
	private LinearLayout llTab1,llTab2,llTab3,llTab4;
	private int zero = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int one;//单个水平动画位移
	private int two;
	private int three;
	private LinearLayout mClose;
    private LinearLayout mCloseBtn;
    private View layout;	
	private boolean menu_display = false;
	private PopupWindow menuWindow;
	private LayoutInflater inflater;
	private View view_tab1;
	private View view_tab2;
	private View view_tab3;
	private View view_tab4;
	//private Button mRightBtn;
	//主页中部PagerView的变量
	private ViewPager viewPager;
	private ImageView imageView;
	private TextView textView1,textView2,textView3;
	private List<View> views;
	private int offset =0;
	private int currIndex_tab = 0;
	private int bmpW;
	private View view1,view2,view3;
	private ListView mListView;
	
	//------------------------------Tab2-------------------------------
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
	//------------------------------Tab2-------------------------------
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_weixin);
         //启动activity时不自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
        instance = this;
        /*
        mRightBtn = (Button) findViewById(R.id.right_btn);
        mRightBtn.setOnClickListener(new Button.OnClickListener()
		{	@Override
			public void onClick(View v)
			{	showPopupWindow (MainWeixin.this,mRightBtn);
			}
		  });*/
        
        mTabPager = (ViewPager)findViewById(R.id.tabpager);
        mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());
        mTab1 = (ImageView) findViewById(R.id.img_weixin);
        mTab2 = (ImageView) findViewById(R.id.img_address);
        mTab3 = (ImageView) findViewById(R.id.img_friends);
        mTab4 = (ImageView) findViewById(R.id.img_settings);
        mTabImg = (ImageView) findViewById(R.id.img_tab_now);
        llTab1 = (LinearLayout) findViewById(R.id.tab_weixin);
        llTab2 = (LinearLayout) findViewById(R.id.tab_address);
        llTab3 = (LinearLayout) findViewById(R.id.tab_friends);
        llTab4 = (LinearLayout) findViewById(R.id.tab_settings);

        mTabImg = (ImageView) findViewById(R.id.img_tab_now);
        llTab1.setOnClickListener(new MyOnClickListener(0));
        llTab2.setOnClickListener(new MyOnClickListener(1));
        llTab3.setOnClickListener(new MyOnClickListener(2));
        llTab4.setOnClickListener(new MyOnClickListener(3));

        Display currDisplay = getWindowManager().getDefaultDisplay();//获取屏幕当前分辨率
        int displayWidth = currDisplay.getWidth();
        int displayHeight = currDisplay.getHeight();
        one = displayWidth/4; //设置水平动画平移大小
        two = one*2;
        three = one*3;
        //Log.i("info", "获取的屏幕分辨率为" + one + two + three + "X" + displayHeight);
        //将要分页显示的View装入数组中
        LayoutInflater mLi = LayoutInflater.from(this);
        view_tab1 = mLi.inflate(R.layout.main_tab_weixin, null);
        view_tab2 = mLi.inflate(R.layout.activity_party, null);
        view_tab3 = mLi.inflate(R.layout.main_tab_friends, null);
        view_tab4 = mLi.inflate(R.layout.activity_main, null);
        //view_tab1中部ViewPager初始化
		InitImageView();
		InitTextView();
		InitViewPager();
		//view_tab2内容初始化
		/**view_tab3内容初始化
		 * 小石“活动“界面
		 */
		InitTab2Content();
		
		/**view_tab3内容初始化
		 * 杨少东“我的界面”
		 */
		
		
		
		
		
      //每个页面的view数据
        final ArrayList<View> views = new ArrayList<View>();
        views.add(view_tab1);
        views.add(view_tab2);
        views.add(view_tab3);
        views.add(view_tab4);
      //填充ViewPager的数据适配器
        PagerAdapter mPagerAdapter = new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}
			
			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager)container).removeView(views.get(position));
			}
			
			//@Override
			//public CharSequence getPageTitle(int position) {
				//return titles.get(position);
			//}
			
			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager)container).addView(views.get(position));
				return views.get(position);
			}
		};
		
		mTabPager.setAdapter(mPagerAdapter);
    }
    /**
	 * 头标点击监听
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}
		@Override
		public void onClick(View v) {
			mTabPager.setCurrentItem(index);
		}
	};
    
	 /* 页卡切换监听(原作者:D.Winter)
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_pressed));
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
				}
				else if (currIndex == 3) {
					animation = new TranslateAnimation(three, 0, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
				}
				break;
			case 1:
				mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, one, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
				}
				else if (currIndex == 3) {
					animation = new TranslateAnimation(three, one, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
				}
				break;
			case 2:
				mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, two, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
				}
				else if (currIndex == 3) {
					animation = new TranslateAnimation(three, two, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
				}
				break;
			case 3:
				mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, three, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, three, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
				}
				else if (currIndex == 2) {
					animation = new TranslateAnimation(two, three, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
				}
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(150);
			mTabImg.startAnimation(animation);
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  //获取 back键
    		
        	if(menu_display){         //如果 Menu已经打开 ，先关闭Menu
        		menuWindow.dismiss();
        		menu_display = false;
        		}
        	else {
        		Intent intent = new Intent();
            	intent.setClass(MainWeixin.this,Exit.class);
            	startActivity(intent);
        	}
    	}
    	
    	else if(keyCode == KeyEvent.KEYCODE_MENU){   //获取 Menu键			
			if(!menu_display){
				//获取LayoutInflater实例
				inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
				//这里的main布局是在inflate中加入的哦，以前都是直接this.setContentView()的吧？呵呵
				//该方法返回的是一个View的对象，是布局中的根
				layout = inflater.inflate(R.layout.main_menu, null);
				
				//下面我们要考虑了，我怎样将我的layout加入到PopupWindow中呢？？？很简单
				menuWindow = new PopupWindow(layout,LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT); //后两个参数是width和height
				//menuWindow.showAsDropDown(layout); //设置弹出效果
				//menuWindow.showAsDropDown(null, 0, layout.getHeight());
				menuWindow.showAtLocation(this.findViewById(R.id.mainweixin), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
				//如何获取我们main中的控件呢？也很简单
				mClose = (LinearLayout)layout.findViewById(R.id.menu_close);
				mCloseBtn = (LinearLayout)layout.findViewById(R.id.menu_close_btn);
				
				
				//下面对每一个Layout进行单击事件的注册吧。。。
				//比如单击某个MenuItem的时候，他的背景色改变
				//事先准备好一些背景图片或者颜色
				mCloseBtn.setOnClickListener (new View.OnClickListener() {					
					@Override
					public void onClick(View arg0) {						
						//Toast.makeText(Main.this, "退出", Toast.LENGTH_LONG).show();
						Intent intent = new Intent();
			        	intent.setClass(MainWeixin.this,Exit.class);
			        	startActivity(intent);
			        	menuWindow.dismiss(); //响应点击事件之后关闭Menu
					}
				});				
				menu_display = true;				
			}else{
				//如果当前已经为显示状态，则隐藏起来
				menuWindow.dismiss();
				menu_display = false;
				}
			
			return false;
		}
    	return false;
    }
	//设置标题栏右侧按钮的作用
	public void btnmainright(View v) {  
		Intent intent = new Intent (MainWeixin.this,LocationSourceActivity.class);			
		startActivity(intent);	
		//Toast.makeText(getApplicationContext(), "点击了功能按钮", Toast.LENGTH_LONG).show();
      }  	
	public void startchat(View v) {      //小黑  对话界面
		Intent intent = new Intent (MainWeixin.this,ChatActivity.class);			
		startActivity(intent);	
		//Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_LONG).show();
      }  
	public void exit_settings(View v) {                           //退出  伪“对话框”，其实是一个activity
		Intent intent = new Intent (MainWeixin.this,ExitFromSettings.class);			
		startActivity(intent);	
	 }
	
	
	//Onclick方法时间
	public void btn_shake(View v) {                                   //手机摇一摇
		Intent intent = new Intent (MainWeixin.this,ShakeActivity.class);			
		startActivity(intent);	
	}
	
	//----------------------------------Tab2设计到的点击事件----------------------------
	//点击 我参与的 按钮响应事件
	public void btnJoin(View v){
		if(!joinLayoutIsVisible && favoLayoutIsVisible){
			joinLayout.setVisibility(View.VISIBLE);
			
			favoriteLayout.setVisibility(View.INVISIBLE);
			joinLayoutIsVisible = true;
			favoLayoutIsVisible = false;
		}
	}
	
	//点击 我关心的 按钮响应事件
	public void btnFavorite(View v){
		if(!favoLayoutIsVisible && joinLayoutIsVisible){
			joinLayout.setVisibility(View.INVISIBLE);
			favoriteLayout.setVisibility(View.VISIBLE);
			joinLayoutIsVisible = false;
			favoLayoutIsVisible = true;
		}
	}
	
	//点击Tab2中item项时产生的点击事件
	public void itemClick(View v) {
		Intent todetailActivity = new Intent(MainWeixin.this, ItemDetailActivity.class);
		startActivity(todetailActivity);
	}
	
	//跳转到成员聊天的界面
	public void btnToChat(View v){
		Intent toChatActivity = new Intent(MainWeixin.this, ChatActivity.class);
		startActivity(toChatActivity);
	}
	
	//跳转到地图查看界面
	public void btnLookInMap(View v){
		Intent tolookMapActivity = new Intent(MainWeixin.this, LookInMapActivity.class);
		startActivity(tolookMapActivity);
	}
	
	//----------------------------------Tab2设计到的点击事件----------------------------
	/**view_tab3内容初始化
	 * 杨少东“我的界面”
	 */
	//public void btnToChat(View v)
	public void toAbout(View v) {
		Intent intent=new Intent();
		intent.setClass(MainWeixin.this, about.class );
		MainWeixin.this.startActivity(intent);
	}
	
	public void toFeedback(View v) {
		Intent intent=new Intent();
		intent.setClass(MainWeixin.this, feedback.class );
		MainWeixin.this.startActivity(intent);
	}
	
	public void toPersonSetting(View v) {
		Intent intent=new Intent();
		intent.setClass(MainWeixin.this, info_setting.class );
		MainWeixin.this.startActivity(intent);
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~主页中部ViewPager对象调用的方法
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~主页中部ViewPager对象调用的方法
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~主页中部ViewPager对象调用的方法
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~主页中部ViewPager对象调用的方法
	private void InitViewPager() {
		// TODO Auto-generated method stub
		viewPager = (ViewPager) view_tab1.findViewById(R.id.vPager);
		views = new ArrayList<View>();
		LayoutInflater inflater = getLayoutInflater();
		view1 = inflater.inflate(R.layout.layout1, null);
		view2 = inflater.inflate(R.layout.layout2, null);
		view3 = inflater.inflate(R.layout.layout3, null);
		//-----------------------------------------------
		mListView = (ListView)(view1.findViewById(R.id.listview_layout1));
		ActivityViewAdapter mAdapter = new ActivityViewAdapter(this,getData());
		mListView.setAdapter(mAdapter);
		//-----------------------------------------------
		views.add(view1);
		views.add(view2);
		views.add(view3);
		viewPager.setAdapter(new MyViewPagerAdapter(views));
		viewPager.setCurrentItem(0);  
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener_());
	}
	private void InitTextView() {
		// TODO Auto-generated method stub
		textView1 = (TextView) view_tab1.findViewById(R.id.tv_tab_meinv); 
		textView2 = (TextView) view_tab1.findViewById(R.id.tv_tab_luoli); 
		textView3= (TextView) view_tab1.findViewById(R.id.tv_tab_qiche); 
		textView1.setOnClickListener(new MyOnClick(0));
		textView2.setOnClickListener(new MyOnClick(1));
		textView3.setOnClickListener(new MyOnClick(2));
	}
	
	private void InitImageView() {
		// TODO Auto-generated method stub
		imageView = (ImageView) view_tab1.findViewById(R.id.iv_bottom_line);
		Log.e("kuandu", imageView.getWidth() + "");
		bmpW = imageView.getLayoutParams().width;
		//BitmapFactory.decodeResource(getResources(), id);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (screenW/3 - bmpW)/2;
		Matrix matrix = new Matrix();  
        matrix.postTranslate(offset, 0);  
        imageView.setImageMatrix(matrix);// ���ö�����ʼλ��
	}
	
	/**
	 * 初始化Tab2的内容
	 */
	private void InitTab2Content(){
		
		joinLayout = view_tab2.findViewById(R.id.layout_join);
		favoriteLayout = view_tab2.findViewById(R.id.layout_favorite);
		joinBtn = (Button) view_tab2.findViewById(R.id.btn_join);
		favoriteBtn = (Button) view_tab2.findViewById(R.id.btn_favorite);
		
		
		partyJoinLv = (ListView) view_tab2.findViewById(R.id.lv_join);
		partyFavoLv = (ListView) view_tab2.findViewById(R.id.lv_favorite);
		
		//加载数据
		initTab2Date();
		
		partyJoinListAdapter = new PartyListAdapter(this, partyJoinList);
		partyFavoListAdapter = new PartyListAdapter(this, partyFavoList);

		
		//列表设置适配器
		partyJoinLv.setAdapter(partyJoinListAdapter);
		partyFavoLv.setAdapter(partyFavoListAdapter);
		
	}
	
	//模拟Tab2中ListView的数据
	private void initTab2Date() {
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
	
	
	private List<ActivityEntity> getData(){
		List<ActivityEntity> mList = new LinkedList<ActivityEntity>();
		mList.add(new ActivityEntity("旅游", "武大看樱花", "武汉大学", "2014-4-2 14:00"));
		mList.add(new ActivityEntity("旅游", "武大看樱花", "武汉大学", "2014-4-2 14:00"));
		mList.add(new ActivityEntity("旅游", "武大看樱花", "武汉大学", "2014-4-2 14:00"));
		mList.add(new ActivityEntity("旅游", "武大看樱花", "武汉大学", "2014-4-2 14:00"));
		mList.add(new ActivityEntity("旅游", "武大看樱花", "武汉大学", "2014-4-2 14:00"));
		
		return mList;
		}
	class MyOnClick implements OnClickListener
	{
		int index = 0;
		MyOnClick(int i){
			this.index = i ;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			viewPager.setCurrentItem(index);
		}
	}
	class MyViewPagerAdapter extends PagerAdapter{
		private List<View> listViews;
		MyViewPagerAdapter(List<View> listViews){
			this.listViews = listViews ;
			
		}
		
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(listViews.get(position));
		}


		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(listViews.get(position),0);
			return listViews.get(position);
		}


		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}
		
	}
	class MyOnPageChangeListener_ implements OnPageChangeListener
	{
		int one = offset*2 + bmpW ;//ҳ��1-->ҳ��2  ƫ����
		int two = offset*2 ;//ҳ��2-->ҳ��3  ƫ����
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			 /*���ַ����������һ�֣����滹��һ�֣���Ȼ����Ƚ��鷳 
            Animation animation = null; 
            switch (arg0) { 
            case 0: 
                if (currIndex == 1) { 
                    animation = new TranslateAnimation(one, 0, 0, 0); 
                } else if (currIndex == 2) { 
                    animation = new TranslateAnimation(two, 0, 0, 0); 
                } 
                break; 
            case 1: 
                if (currIndex == 0) { 
                    animation = new TranslateAnimation(offset, one, 0, 0); 
                } else if (currIndex == 2) { 
                    animation = new TranslateAnimation(two, one, 0, 0); 
                } 
                break; 
            case 2: 
                if (currIndex == 0) { 
                    animation = new TranslateAnimation(offset, two, 0, 0); 
                } else if (currIndex == 1) { 
                    animation = new TranslateAnimation(one, two, 0, 0); 
                } 
                break; 
                 
            } 
            */  
            Animation animation = new TranslateAnimation(one*currIndex_tab, one*arg0, 0, 0);//��Ȼ����Ƚϼ�ֻ࣬��һ�д��롣  
            currIndex_tab = arg0;  
            animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��  
            animation.setDuration(300);  
            imageView.startAnimation(animation);  
		}
		

	}
}
    
    

