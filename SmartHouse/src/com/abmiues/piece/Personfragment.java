package com.abmiues.piece;


import com.abmiues.R;
import com.abmiues.R.layout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Personfragment extends Fragment {
private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		view= inflater.inflate(R.layout.person_fragment, container, false);
		return view;
	}
}
