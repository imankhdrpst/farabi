package com.soutazin.farabiSchool.Activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.soutazin.farabiSchool.Adapter.PictureSliderAdapter;
import com.soutazin.farabiSchool.R;
import com.soutazin.farabiSchool.SchoolApp;

public class PictureViewerActivity extends AppCompatActivity {


    private PictureSliderAdapter mAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_viewer);



        Bundle b = getIntent().getExtras();

        if (b == null)
        {
            finish();
        }
        else {
            int position = b.getInt("position");
            mAdapter = new PictureSliderAdapter(this, SchoolApp.currentPicturesArrayList);
            mViewPager = (ViewPager) findViewById(R.id.pager);
            mViewPager.setAdapter(mAdapter);
            mViewPager.setCurrentItem(position);

        }

    }



}
