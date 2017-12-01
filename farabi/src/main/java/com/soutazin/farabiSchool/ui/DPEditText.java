package com.soutazin.farabiSchool.ui;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.soutazin.farabiSchool.SchoolApp;

/**
 * Created by DP123 on 1/12/15.
 */
public class DPEditText extends EditText {
    private Context context;

    public DPEditText(Context context) {
        super(context);
         context=this.context;
        init();
    }

    public DPEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        context=this.context;
        init();
    }

    public DPEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        context=this.context;
        init();
    }


    public void init() {
        setTypeface(SchoolApp.iransSansTypeFace, Typeface.NORMAL);

   }

}
