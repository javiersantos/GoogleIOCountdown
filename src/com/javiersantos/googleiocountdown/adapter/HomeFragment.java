package com.javiersantos.googleiocountdown.adapter;

import com.javiersantos.googleiocountdown.R;
import com.javiersantos.googleiocountdown.R.layout;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
         
        return rootView;
    }

}
