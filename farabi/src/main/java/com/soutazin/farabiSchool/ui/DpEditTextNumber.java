package com.soutazin.farabiSchool.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;

import com.soutazin.farabiSchool.SchoolApp;

/**
 * Created by DP128 on 7/12/2016.
 */
public class DpEditTextNumber extends EditText {
    private Context context;

    public DpEditTextNumber(Context context) {
        super(context);
        context=this.context;
        init();
    }

    public DpEditTextNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
        context=this.context;
        init();
    }

    public DpEditTextNumber(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        context=this.context;
        init();
    }


    public void init() {
        setInputType(InputType.TYPE_CLASS_NUMBER);
        setTypeface(SchoolApp.iransSansTypeFace, Typeface.NORMAL);

    }


}
