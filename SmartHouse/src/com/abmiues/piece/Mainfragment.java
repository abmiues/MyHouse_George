package com.abmiues.piece;



import com.abmiues.ListActivity;
import com.abmiues.R;
import com.abmiues.R.drawable;
import com.abmiues.R.id;
import com.abmiues.R.layout;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import cn.jpush.a.a.i;
import cn.jpush.a.a.r;

public class Mainfragment extends Fragment {
	private View view;
	int mScreenWidth;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		 view=inflater.inflate(R.layout.main_fragment, container, false);
		 //ScrollView scrollView=(ScrollView)view.findViewById(R.id.mainscroll) ;
		 DisplayMetrics metric = new DisplayMetrics(); 
		 getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
		 mScreenWidth = metric.widthPixels;  // 屏幕宽度（像素）   
	    // int mScreenHeight = metric.heightPixels; 
	    // int magnetHeight=new Magnet(getActivity()).getHeight();
	     LinearLayout ln1=(LinearLayout)view.findViewById(R.id.mainlinear);
	     
		 LinearLayout ln2= addpiece2();
		 
		 //ln2.setLayoutParams(new LinearLayout.LayoutParams(mScreenWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
		
		 for(int i=1;i<11;i++)
		 {
			 
			// Magnet m=new Magnet(getActivity());
			 
			 ln2.addView(addpiece(R.drawable.logo, "haha"));
			 
			 if(i%3==0)
			 {
				 ln1.addView(ln2);
				 ln2=addpiece2();
				 //ln2.setLayoutParams(new LinearLayout.LayoutParams(mScreenWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
				 
			 }
			 
		 }
		// ln2.addView(new Magnet(getActivity()));
		 ln1.addView(ln2);
		 //scrollView.addView(ln);
		 
		 return view;

	}
	public LinearLayout addpiece(int id,String name)
	{
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((mScreenWidth-dip2px(getActivity(), 24))/3, ViewGroup.LayoutParams.WRAP_CONTENT); 
		lp.setMargins(dip2px(getActivity(), 2), 0, dip2px(getActivity(), 2), 0);
		LinearLayout l=(LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.magnet_layout,null);  
		l.setLayoutParams(lp);
		ImageView img=(ImageView) l.findViewById(R.id.roomimg);
		img.setImageResource(id);
		TextView txt=(TextView) l.findViewById(R.id.roomtxt);
		txt.setText(name);
		l.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),ListActivity.class));
				//getActivity().getFragmentManager().beginTransaction().replace(R.id.content_frame, new ListbyRoomfragment()).commit();
				
			}
		});
		return l;
	}
	public LinearLayout addpiece2()
	{
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(mScreenWidth, ViewGroup.LayoutParams.WRAP_CONTENT); 
		//lp.setMargins(dip2px(getActivity(), 3), dip2px(getActivity(), 6), dip2px(getActivity(), 3), dip2px(getActivity(), 6));
		
		LinearLayout l=new LinearLayout(getActivity());  
		l.setLayoutParams(lp);
		l.setBackgroundResource(R.drawable.bottomboder);
		l.setOrientation(LinearLayout.HORIZONTAL);
		l.setPadding(dip2px(getActivity(), 3), dip2px(getActivity(), 6), dip2px(getActivity(), 3), dip2px(getActivity(), 6));
		
		return l;
	}
	public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
}
