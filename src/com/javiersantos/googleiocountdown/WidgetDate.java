package com.javiersantos.googleiocountdown;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.os.CountDownTimer;
import android.widget.RemoteViews;
import android.widget.TextView;

@SuppressLint("DefaultLocale")
public class WidgetDate extends AppWidgetProvider {
	
	TextView countdownTimer;
	AppWidgetManager appWidgetManager;

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		
		ComponentName thisWidget = new ComponentName(context, WidgetDate.class);
		final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
		
		Counter timer = new Counter(00, 00, 9, 25, 05, 2014);
		
		new CountDownTimer(timer.getIntervalMillis(), 1000) {

			@Override
			public void onFinish() {
				remoteViews.setTextViewText(R.id.widget_today, "GOOGLE I/O IS TODAY!");
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
		        remoteViews.setTextViewText(R.id.widget_date, countdown);
			}
		}.start();

	}
    
}
