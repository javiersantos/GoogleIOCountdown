package com.javiersantos.googleiocountdown.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	
	public TabsPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

	@Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // First fragment activity
        	return new HomeFragment();
        //case 1:
            // Second fragment activity
            //return new LiveFragment();
        case 1:
            // Third fragment activity
            return new AboutFragment();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }
 
}