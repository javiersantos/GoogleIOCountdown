package com.javiersantos.googleiocountdown;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class RobotoFont extends TextView {
	
	public RobotoFont(Context context) {
        super(context);
        style(context);
    }

    public RobotoFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        style(context);
    }

    public RobotoFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        style(context);
    }

    private void style(Context context) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                "RobotoThin.ttf");
        setTypeface(tf);
    }

}
