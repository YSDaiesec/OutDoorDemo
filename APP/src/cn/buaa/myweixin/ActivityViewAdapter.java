
package cn.buaa.myweixin;

import android.R.integer;
import android.content.Context;
import android.database.DataSetObserver;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ActivityViewAdapter extends BaseAdapter {
	
	public static interface ActivityType
	{
		int ACTIVITY_TYPE0 = 0;
	}	
	
    private static final String TAG = ActivityViewAdapter.class.getSimpleName();

    private List<ActivityEntity> coll;

    private Context ctx;
    
    private LayoutInflater mInflater;

    public ActivityViewAdapter(Context context, List<ActivityEntity> coll) {
        ctx = context;
        this.coll = coll;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return coll.size();
    }

    public Object getItem(int position) {
        return coll.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
    


	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
	 	return ActivityType.ACTIVITY_TYPE0;
	 	
	}


	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	
    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	ActivityEntity entity = coll.get(position);
    		
    	ViewHolder viewHolder = null;
	    if (convertView == null)
	    {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	    	  
	    	  viewHolder = new ViewHolder();
	    	  convertView = mInflater.inflate(R.layout.activity_item, null);
			  viewHolder.tv_activityname = (TextView) convertView.findViewById(R.id.tv_activityname);
			  viewHolder.tv_activityclass = (TextView) convertView.findViewById(R.id.tv_activityclass);
			  viewHolder.tv_activitydate = (TextView) convertView.findViewById(R.id.tv_activitydate);
			  viewHolder.tv_activityplace = (TextView) convertView.findViewById(R.id.tv_activityplace);
			  
			  convertView.setTag(viewHolder);
	    }else{
	        viewHolder = (ViewHolder) convertView.getTag();
	    }
	
	    
	    viewHolder.tv_activityname.setText(entity.getActName());
	    viewHolder.tv_activityclass.setText(entity.getActClass());
	    viewHolder.tv_activitydate.setText(entity.getActDate());
	    viewHolder.tv_activityplace.setText(entity.getActPlace());	    
	    return convertView;
    }
    

    static class ViewHolder { 
        public TextView tv_activityname;
        public TextView tv_activityclass;
        public TextView tv_activitydate;
        public TextView tv_activityplace;
    }


}
