package com.soutazin.farabiSchool.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.soutazin.farabiSchool.R;
import com.soutazin.farabiSchool.SchoolApp;

/**
 * Created by DP123 on 1/12/15.
 */
public class DPTextView extends TextView {
    private static String forntTypeFace = "iransans" ;
    public DPTextView(Context context) {
        super(context);

        init();
    }

    public DPTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DPTextView, 0, 0);
        try {
            forntTypeFace = ta.getString(R.styleable.DPTextView_teleniumTypeFace);
        } finally {
            ta.recycle();
        }
        init();
    }

    public DPTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DPTextView, 0, 0);
        try {
            forntTypeFace = ta.getString(R.styleable.DPTextView_teleniumTypeFace);
        } finally {
            ta.recycle();
        }
        init();
    }

    public void init() {
        if (forntTypeFace == null)
        {
            setTypeface(SchoolApp.iransSansTypeFace, Typeface.NORMAL);

        }
        else if (forntTypeFace.equals("iransans"))
        {
            setTypeface(SchoolApp.iransSansTypeFace, Typeface.NORMAL);

        }
        else if (forntTypeFace.equals("fontawesome"))
        {
            setTypeface(SchoolApp.fontAwesome, Typeface.NORMAL);

        }
        else if (forntTypeFace.equals("title"))
        {
            setTypeface(SchoolApp.iransSansTypeFace, Typeface.BOLD);
        }
//        else if (forntTypeFace.equals("mfglab")) {
//            setTypeface(SchoolApp.mfgLab, Typeface.NORMAL);
//        }
        else if (forntTypeFace.equals("iransans"))
        {
            setTypeface(SchoolApp.iransSansTypeFace, Typeface.BOLD);

        }

    }

}
