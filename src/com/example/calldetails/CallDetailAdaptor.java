package com.example.calldetails;

//import java.sql.Date;
import java.util.ArrayList;
import java.util.zip.Inflater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CallDetailAdaptor extends BaseAdapter {
	Context cntx;
	ArrayList<String> PHONENO = new java.util.ArrayList<String>();
	ArrayList<String> DATE = new java.util.ArrayList<String>();
	ArrayList<String> TYPE = new java.util.ArrayList<String>();
	ArrayList<String> DURATION = new java.util.ArrayList<String>();
	ArrayList<String> caller_name_list = new java.util.ArrayList<String>();
	Inflater inflater;
	ImageView image;

	public CallDetailAdaptor(MainActivity mainActivity,
			ArrayList<String> phoneno, ArrayList<String> type,
			ArrayList<String> date, ArrayList<String> duration, ArrayList<String> caller_name_value_list) {
		// TODO Auto-generated constructor stub
		cntx = mainActivity;
		PHONENO = phoneno;
		DATE = date;
		TYPE = type;
		DURATION = duration;
		caller_name_list=caller_name_value_list;
		

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return PHONENO.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView =null;
		
		LayoutInflater PHONENOinflater = (LayoutInflater) cntx
				.getSystemService(cntx.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)

		{
			convertView = PHONENOinflater.inflate(R.layout.second, null);

			TextView phon = (TextView) convertView
					.findViewById(R.id.textView1);
//			TextView type = (TextView) convertView
//					.findViewById(R.id.textView2);
			TextView date = (TextView) convertView
					.findViewById(R.id.textView3);
			TextView duration = (TextView) convertView
					.findViewById(R.id.textView4);
			TextView callername=(TextView) convertView.findViewById(R.id.callername1);
			image=(ImageView)convertView.findViewById(R.id.image1);
			if(TYPE.get(position).equalsIgnoreCase("INCOMING"))
			{
				image.setImageResource(R.drawable.incaming);
			}
			else if
				(TYPE.get(position).equalsIgnoreCase("OUTGOING"))
			{
					image.setImageResource(R.drawable.out);
				
			}
			else
			{
				image.setImageResource(R.drawable.mis);
			}

			phon.setText(PHONENO.get(position));
//			type.setText(TYPE.get(position));
			date.setText(DATE.get(position));
			callername.setText(caller_name_list.get(position));
//			Object callDate;
//			Date calldaytime = new Date(Long.valueOf(DATE.get(position)));
			duration.setText(DURATION.get(position)+"sec");
			date.setText(DATE.get(position));

		}
		return convertView;
	}

}
