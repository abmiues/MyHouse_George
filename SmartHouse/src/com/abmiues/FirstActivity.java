package com.abmiues;


import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class FirstActivity extends Activity {
	SharedPreferences localdata;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_first);
		localdata=getSharedPreferences("localdata", 0);
		if(localdata.getBoolean("isfirst", true))
		{
			localdata.edit().putBoolean("isfirst", false).commit();
			localdata.edit().putString("ip", "139.129.119.93").commit();
			localdata.edit().putString("host", "80").commit();
			//SQLiteDatabase db = openOrCreateDatabase("localdata.db", Context.MODE_PRIVATE, null); 
			//db.execSQL("DROP TABLE IF EXISTS room");
		//	db.execSQL("CREATE TABLE room (id INTEGER PRIMARY KEY AUTOINCREMENT, roomtype VARCHAR, name SMALLINT)");
		}
		
		Handler x=new Handler();//设置定时跳转并加载数
		x.postDelayed(new mainhandler() , 500);
		
		
		
	}
	
	class mainhandler implements Runnable {
		public void run() {
			
		 if(localdata.getBoolean("islogin", false))
			{
				 JPushInterface.setDebugMode(true);
				 JPushInterface.init(getApplicationContext());
				JPushInterface.setAlias(getApplicationContext(), localdata.getString("userid", "all"), new TagAliasCallback() {
					@Override
					public void gotResult(int arg0, String arg1, Set<String> arg2) {
						//Log.d("User", "set" + arg0);
					}
				});
				
				Set<String> sets=new HashSet<String>();
				sets.add(localdata.getString("group", "all"));
				JPushInterface.setTags(getApplicationContext(), sets, new TagAliasCallback() {
					
					@Override
					public void gotResult(int arg0, String arg1, Set<String> arg2) {
						//Log.d("TAG", "set" + arg0);
						
					}
				});
				
			}
		 startActivity(new Intent(FirstActivity.this,MainActivity.class));
			FirstActivity.this.finish();
		
		}
	}
}
