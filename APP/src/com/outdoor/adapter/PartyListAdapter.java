package com.outdoor.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.outdoor.control.Control;
import com.outdoor.model.Party;

import cn.buaa.myweixin.R;

public class PartyListAdapter extends BaseAdapter {

	private final static String TAG = "PartyListAdapter";

	private Context context;
	private ArrayList<Party> partiesList = null;
	private LayoutInflater mInflater = null;
	private Handler handler = null;

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public PartyListAdapter(Context context) {
		// initData();
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
	}

	public PartyListAdapter(Context context, ArrayList<Party> list) {
		// initData();
		this.context = context;
		this.partiesList = list;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return partiesList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return partiesList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		PartyListHolder holder = null;
		if (convertView == null) {
			holder = new PartyListHolder();
			convertView = mInflater.inflate(R.layout.party_list_item, null);
			holder.partyCatagoryNameTv = (TextView) convertView
					.findViewById(R.id.tv_party_catagory_name);
			holder.partyNameTv = (TextView) convertView
					.findViewById(R.id.tv_party_name);
			holder.partyPositionTv = (TextView) convertView
					.findViewById(R.id.tv_party_position);
			holder.partyTimeTv = (TextView) convertView
					.findViewById(R.id.tv_party_time);
			convertView.setTag(holder);
		} else {
			holder = (PartyListHolder) convertView.getTag();
		}

		holder.partyCatagoryNameTv.setText(partiesList.get(position)
				.getPartyCatagoryName());
		holder.partyNameTv.setText(partiesList.get(position).getPartyName());
		holder.partyPositionTv.setText(partiesList.get(position)
				.getPartyPosition());
		holder.partyTimeTv.setText(partiesList.get(position).getPartyTime());

		return convertView;
	}


}
