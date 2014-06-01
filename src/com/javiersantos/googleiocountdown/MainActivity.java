package com.javiersantos.googleiocountdown;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ShareActionProvider;
import android.widget.TextView;

@SuppressLint("DefaultLocale")
public class MainActivity extends Activity {
	
	// COUNTDOWN TO GOOGLE I/O //
	
	TextView text1;
	TextView countdownTimer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countdownTimer = (TextView) findViewById(R.id.countdownTimer);
        text1 = (TextView) findViewById(R.id.text1);
        
        Counter timer = new Counter(00, 00, 9, 25, 05, 2014);

        new CountDownTimer(timer.getIntervalMillis(), 1000) {

			@Override
			public void onFinish() {
		        countdownTimer.setText("GOOGLE I/O IS HERE!");
		        text1.setVisibility(View.INVISIBLE);
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
		        text1.setText("Next Google I/O");
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
		myIntent.putExtra(Intent.EXTRA_TEXT, "Google I/O 2014 Countdown for Android. By @fjaviersantos");
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
	
}
