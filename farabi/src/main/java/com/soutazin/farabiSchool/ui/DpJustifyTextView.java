package com.soutazin.farabiSchool.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.soutazin.farabiSchool.R;
import com.soutazin.farabiSchool.SchoolApp;


public class DpJustifyTextView extends TextViewEx {

	public DpJustifyTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public DpJustifyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public DpJustifyTextView(Context context) {
		super(context);
		init();
	}

	public void init() {
		if (!isInEditMode()) {
			setTypeface(SchoolApp.iransSansTypeFace, Typeface.NORMAL);
            this.setPadding(0,0,0, (int) getContext().getResources().getDimension(R.dimen.padding_instnt_msg_bottom_buttons));
		}

	}
}