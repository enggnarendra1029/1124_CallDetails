package com.example.calldetails;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
//import android.util.Log;
//import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
TextView t1;
ListView list;


public static ArrayList<String>phoneno;
public static ArrayList<String>type;
public static ArrayList<String>date;
public static ArrayList<String>caller_name;
public static ArrayList<String>duration;
CallDetailAdaptor adapter ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		list=(ListView)findViewById(R.id.list_view1);
		
//		getCallDetails(MainActivity.this);
//	
//		//t1=(TextView)findViewById(R.id.tv);
//		//t1.setText(getCallDetails(MainActivity.this)); 
//         adapter=new CallDetailAdaptor(MainActivity.this,phoneno,type,date,duration);
//		Log.d("test", "call details:"+getCallDetails(MainActivity.this));
//		list.setAdapter(adapter);
		
			}
 
 
	@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			
			phoneno=new ArrayList<String>();
			type=new ArrayList<String>();
			date=new ArrayList<String>();
			duration=new ArrayList<String>();
			caller_name=new ArrayList<String>();
			 
			
			
			getCallDetails(MainActivity.this);
			
			//t1=(TextView)findViewById(R.id.tv);
			//t1.setText(getCallDetails(MainActivity.this)); 
	         adapter=new CallDetailAdaptor(MainActivity.this,phoneno,type,date,duration,caller_name);
//			Log.d("test", "call details:"+getCallDetails(MainActivity.this));
			list.setAdapter(adapter);
			
			list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					String phno= phoneno.get(arg2);
					// TODO Auto-generated method stub
					Toast.makeText(MainActivity.this, phno, Toast.LENGTH_SHORT).show();
					Intent callIntent = new Intent(Intent.ACTION_CALL);
					callIntent.setData(Uri.parse("tel:123456789"));
					startActivity(callIntent);
				}
			});
			              
	      
			

	}			 private void  getCallDetails(Context context) {
		         StringBuffer stringBuffer = new StringBuffer();
		         Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
		                 null, null, null, CallLog.Calls.DATE + " DESC");
		     	while (cursor.moveToNext()) {
		             String phNumber = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
		             phoneno.add(phNumber);
		             
//		             type.add(cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE)));
		             String callType = cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE));
		             String callerName = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
		         if(callerName==null)
		         {
		        	 caller_name.add("Unsaved");
		         }
		         else
		         {
		        	 caller_name.add(callerName); 
		         }
		         
		             
		             String callDate = cursor.getString(cursor
		         			.getColumnIndex(android.provider.CallLog.Calls.DATE));
		         			SimpleDateFormat formatter = new SimpleDateFormat(
		         			"dd-MMM-yyyy"
		         			+ " HH:mm");
		         			
		         			String dateString = formatter.format(new Date(Long
		         			.parseLong(callDate)));
		         			date.add(dateString);


		             
//		             date.add( cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE)));
		             
		             Date callDayTime = new Date(Long.valueOf(cursor.getColumnIndex(CallLog.Calls.DURATION)));
		             duration.add( cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION)));
		             
		             String dir = null;
		             int dircode = Integer.parseInt(callType);
		             switch (dircode) {
		             case CallLog.Calls.OUTGOING_TYPE:
		                 dir = "OUTGOING";
		                 break;
		             case CallLog.Calls.INCOMING_TYPE:
		                 dir = "INCOMING";
		                 break;

		             case CallLog.Calls.MISSED_TYPE:
		                 dir = "MISSED";
		                 break;
		                 
		             }  
//		             stringBuffer.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- "
//		                     + dir + " \nCall Date:--- " + callDayTime
//		                     + " \nCall duration in sec :--- " );
//		             stringBuffer.append("\n--------------------------------");
		            type.add(dir);
		     	}
		             cursor.close();
		             
		         }
		         
//		         return stringBuffer.toString();

		     }
		

