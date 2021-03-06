package com.javiersantos.googleiocountdown;

import java.util.GregorianCalendar;

import com.javiersantos.googleiocountdown.adapter.TabsPagerAdapter;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.TextView;

@SuppressLint("DefaultLocale")
public class MainActivity extends FragmentActivity implements TabListener {
	
	private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
//    String[] tabs = getResources().getStringArray(R.array.tabs);
	
	TextView text1;
	TextView countdownTimer;
	Button setDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // TABS //
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText(R.string.tab1).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.tab2).setTabListener(this));
//        for (String tab_name : tabs) {
//            actionBar.addTab(actionBar.newTab().setText(tab_name)
//                    .setTabListener(this));
//        }
        
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        	 
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }
         
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
         
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        
        // COUNTDOWN TO GOOGLE I/O WITH CALENDAR EVENT //
        countdownTimer = (TextView) findViewById(R.id.countdownTimer);
        setDate = (Button) findViewById(R.id.setDate);
        setDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent event = new Intent(Intent.ACTION_INSERT);
				event.setData(CalendarContract.Events.CONTENT_URI);
				event.setType("vnd.android.cursor.item/event");
				event.putExtra(Events.TITLE, "Google I/O 2014");
				event.putExtra(Events.EVENT_LOCATION, "San Francisco, CA, USA");
				event.putExtra(Events.DESCRIPTION, "25-26 June, 2014");
				// Setting dates
				GregorianCalendar startDate = new GregorianCalendar(2014, 6, 25);
				event.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
				  startDate.getTimeInMillis());
				GregorianCalendar endDate = new GregorianCalendar(2014, 6, 26);
				event.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
				  endDate.getTimeInMillis());
				// Make it a full day event and shown as busy
				event.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
				event.putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY);
				startActivity(event);
			}
    
        });
        
        Counter timer = new Counter(00, 00, 9, 25, 05, 2014);

        new CountDownTimer(timer.getIntervalMillis(), 1000) {

			@Override
			public void onFinish() {
		        countdownTimer.setText(R.string.started);
			}

			@Override
			public void onTick(long millisUntilFinished) {
				int days = (int) ((millisUntilFinished / 1000) / 86400);
		        int hours = (int) (((millisUntilFinished / 1000)
		                - (days * 86400)) / 3600);
		        int minutes = (int) (((millisUntilFinished / 1000)
		                - (days * 86400) - (hours * 3600)) / 60);
		        int seconds = (int) ((millisUntilFinished / 1000) % 60);

		        String countdown = String.format("%02dd %02dh %02dm %02ds", days,
		                hours, minutes, seconds);
		        countdownTimer.setText(countdown);
			}
        }.start();
        
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// getMenuInflater().inflate(R.menu.main, menu);
		getMenuInflater().inflate(R.menu.share, menu);
		MenuItem item = menu.findItem(R.id.menu_item_share);
		ShareActionProvider myShareActionProvider = (ShareActionProvider) item.getActionProvider();
		Intent myIntent = new Intent();
		myIntent.setAction(Intent.ACTION_SEND);
		myIntent.putExtra(Intent.EXTRA_TEXT, "Google I/O 2014 Countdown for Android (by @fjaviersantos) #GoogleIO");
		myIntent.setType("text/plain");
		myShareActionProvider.setShareIntent(myIntent);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	@Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    	
    }
	
}
