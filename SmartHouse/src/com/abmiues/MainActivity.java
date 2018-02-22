package com.abmiues;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.abmiues.piece.Collectfragment;
import com.abmiues.piece.Controlfragment;
import com.abmiues.piece.Mainfragment;
import com.abmiues.piece.Personfragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v4.view.ViewPager.LayoutParams;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private DrawerLayout mDrawerLayout = null;
	private ListView mDrawerlist=null;
	private LinearLayout mLeftdraw=null; 
	private TextView tt;
	private SharedPreferences localdata;
	private int gravity;
	private FragmentManager fragmentManager;
	private SimpleAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerlist=(ListView)findViewById(R.id.left_list);
		
		//tt=(TextView)findViewById(R.id.tt);
		localdata=getSharedPreferences("localdata", 0);
		
		Button btn_fulist;
		Button btn_more;
		
		if(localdata.getBoolean("ischange", false))
		{ 
			btn_more=(Button)findViewById(R.id.title_bar_right_menu);
			btn_fulist=(Button)findViewById(R.id.title_bar_left_menu);
			DrawerLayout.LayoutParams lp=new DrawerLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
			//LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);  
			//此处相当于布局文件中的Android:layout_gravity属性  
			lp.gravity = Gravity.LEFT; 
			mDrawerlist.setLayoutParams(lp);
			gravity=Gravity.LEFT;

		}
		else{
			btn_fulist=(Button)findViewById(R.id.title_bar_right_menu);
			btn_more=(Button)findViewById(R.id.title_bar_left_menu);
			DrawerLayout.LayoutParams lp=new DrawerLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
			//此处相当于布局文件中的Android:layout_gravity属性  
			lp.gravity = Gravity.RIGHT; 
			mDrawerlist.setLayoutParams(lp);
			gravity=Gravity.RIGHT;

		}
		btn_fulist.setBackgroundResource(R.drawable.fulist);
		btn_more.setBackgroundResource(R.drawable.more);
		fragmentManager=getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.content_frame, new Mainfragment()).commit();
		//mLeftdraw=(LinearLayout)findViewById(R.id.left_drawer);

		btn_fulist.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				mDrawerLayout.openDrawer(gravity);

			}
		});
		
		adapter = new SimpleAdapter(this, getData(), R.layout.typelist, 
				 new String[]{"title","img"},
				 new int[]{R.id.title,R.id.img});
		mDrawerlist.setAdapter(adapter);
		
		registerclick((ImageButton)findViewById(R.id.btn_bottom1), new Mainfragment());
		registerclick((ImageButton)findViewById(R.id.btn_bottom2), new Collectfragment());
		registerclick((ImageButton)findViewById(R.id.btn_bottom3), new Controlfragment());
		registerclick((ImageButton)findViewById(R.id.btn_bottom4), new Personfragment());

		

	}


	private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
 
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "冰箱");
        map.put("img", R.drawable.frg);
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("title", "空调");
        map.put("img", R.drawable.air);
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("title", "电视");
        map.put("img", R.drawable.tv);
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("title", "微波炉");
        map.put("img", R.drawable.vb);
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("title", "洗衣机");
        map.put("img", R.drawable.wash);
        list.add(map);
        
        return list;
    }
	
	
	private void registerclick(ImageButton mbutton,final Fragment targetFragment) {
		mbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				fragmentManager.beginTransaction().replace(R.id.content_frame, targetFragment).commit();
				
			}
		});
	}
}



