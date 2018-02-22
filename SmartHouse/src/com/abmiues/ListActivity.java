package com.abmiues;

import java.util.ArrayList;
import java.util.HashMap;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends Activity {
	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		listView=(ListView)findViewById(R.id.listbyroom);
		MyAdapter adapter=new MyAdapter(this, getdata("0"));
		listView.setAdapter(adapter);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(false);
		
	}
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	@Override//actionbar返回事件
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			ListActivity.this.finish();
		case R.id.action_more:
			MyAdapter adapter;
			if(item.getTitle().equals("编辑"))
			{
			adapter=new MyAdapter(this, getdata("1"));
			item.setTitle("取消");
			}
			else
			{adapter=new MyAdapter(this, getdata("0"));
			item.setTitle("编辑");}
			listView.setAdapter(adapter);
			
		default:
			return super.onOptionsItemSelected(item);
		}
	} 

	public ArrayList<HashMap<String, String>> getdata(String f){
		ArrayList<HashMap<String, String>> list=new ArrayList<HashMap<String,String>>();
		
		HashMap<String, String> map=new HashMap<String, String>();
		
		map.put("txtinfo", "1楼客厅");
		map.put("txtname", "灯1");
		map.put("imgtype", String.valueOf(R.drawable.air));
		map.put("showcheck", f);
		list.add(map);
		
		map=new HashMap<String, String>();
		map.put("txtinfo", "2楼客厅");
		map.put("txtname", "灯3");
		map.put("imgtype", String.valueOf(R.drawable.air));
		map.put("showcheck", f);
		list.add(map);
		
		map=new HashMap<String, String>();
		map.put("txtinfo", "1楼客厅");
		map.put("txtname", "空调1");
		map.put("imgtype", String.valueOf(R.drawable.air));
		map.put("showcheck", f);
		list.add(map);
		
		map=new HashMap<String, String>();
		map.put("txtinfo", "1楼厨房");
		map.put("txtname", "微波炉1");
		map.put("imgtype", String.valueOf(R.drawable.air));
		map.put("showcheck", f);
		list.add(map);
		
		map=new HashMap<String, String>();
		map.put("txtinfo", "1楼厨房");
		map.put("txtname", "冰箱1");
		map.put("imgtype", String.valueOf(R.drawable.air));
		map.put("showcheck", f);
		list.add(map);
		
		map=new HashMap<String, String>();
		map.put("txtinfo", "1楼客厅");
		map.put("txtname", "电视1");
		map.put("imgtype", String.valueOf(R.drawable.air));
		map.put("showcheck", f);
		list.add(map);
		
		map=new HashMap<String, String>();
		map.put("txtinfo", "2楼客厅");
		map.put("txtname", "电视1");
		map.put("imgtype", String.valueOf(R.drawable.air));
		map.put("showcheck", f);
		list.add(map);
		
		map=new HashMap<String, String>();
		map.put("txtinfo", "2楼客厅");
		map.put("txtname", "电视1");
		map.put("imgtype", String.valueOf(R.drawable.air));
		map.put("showcheck", f);
		list.add(map);
		
		map=new HashMap<String, String>();
		map.put("txtinfo", "2楼客厅");
		map.put("txtname", "电视1");
		map.put("imgtype", String.valueOf(R.drawable.air));
		map.put("showcheck", f);
		list.add(map);
		
		map=new HashMap<String, String>();
		map.put("txtinfo", "2楼客厅");
		map.put("txtname", "电视1");
		map.put("imgtype", String.valueOf(R.drawable.air));
		map.put("showcheck", f);
		list.add(map);
		
		map=new HashMap<String, String>();
		map.put("txtinfo", "2楼客厅");
		map.put("txtname", "电视1");
		map.put("imgtype", String.valueOf(R.drawable.air));
		map.put("showcheck", f);
		list.add(map);
		
		map=new HashMap<String, String>();
		map.put("txtinfo", "2楼客厅");
		map.put("txtname", "电视1");
		map.put("imgtype", String.valueOf(R.drawable.air));
		map.put("showcheck", f);
		list.add(map);
		
		return list;
	}
	
	class MyAdapter extends BaseAdapter{

		ArrayList<HashMap<String, String>> mdata;
		private LayoutInflater mInflater;
		 public MyAdapter(Context context,ArrayList<HashMap<String, String>> data) {
			 this.mInflater=LayoutInflater.from(context);
			mdata=data;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mdata.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mdata.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@SuppressLint("ViewHolder")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			viewHolder holder;
			
			if(convertView==null)
			{
				holder=new viewHolder();
				convertView=mInflater.inflate( R.layout.fu_list,null);
				holder.txtname=(TextView) convertView.findViewById(R.id.txt_fuid);
				holder.txtinfo=(TextView) convertView.findViewById(R.id.txt_fuinfo);
				holder.imgtype=(ImageView) convertView.findViewById(R.id.img_futype);
				holder.checkBox=(CheckBox)convertView.findViewById(R.id.checkBox);
				convertView.setTag(holder);
			}
			else
			{
				holder=(viewHolder)convertView.getTag();
			}
			holder.txtinfo.setText(mdata.get(position).get("txtinfo"));
			holder.txtname.setText(mdata.get(position).get("txtname"));
			holder.imgtype.setImageResource(Integer.valueOf(mdata.get(position).get("imgtype")));
			if(mdata.get(position).get("showcheck").equals("1"))
				holder.checkBox.setVisibility(View.VISIBLE);
			return convertView;
		}
		public final class viewHolder
		{
			public ImageView imgtype;
			public TextView txtname;
			public TextView txtinfo;
			public CheckBox checkBox;
		}
		
	}


}
