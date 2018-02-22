package com.abmiues.piece;


import com.abmiues.PictureActivity;
import com.abmiues.R;
import com.abmiues.R.layout;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class Controlfragment extends Fragment {
	View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		view= inflater.inflate(R.layout.control_fragment, container, false);
		view.findViewById(R.id.btnface).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),PictureActivity.class));
				
			}
		});
		return view;

	}
}
