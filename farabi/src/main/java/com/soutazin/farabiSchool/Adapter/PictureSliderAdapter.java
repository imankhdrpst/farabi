package com.soutazin.farabiSchool.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.androidquery.AQuery;
import com.soutazin.farabiSchool.Model.PictureInSubject;
import com.soutazin.farabiSchool.R;
import com.soutazin.farabiSchool.SchoolApp;
import com.soutazin.farabiSchool.ui.DPTextView;
import com.soutazin.farabiSchool.utilities.AppConstants;

import java.util.ArrayList;


public class PictureSliderAdapter extends PagerAdapter {

    private ArrayList<PictureInSubject> mData;
    Context mContext;
    LayoutInflater mLayoutInflater;
    private ImageView imgMainImage;
    private ProgressBar prgProgress;
    private DPTextView txtDescription;
    private LinearLayout layDescriptionHolder;
    private boolean descritionHidden = false;

    public PictureSliderAdapter(Context context, ArrayList<PictureInSubject> items) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mData = items;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }


//    private void showDescription()
//    {
//        layDescriptionHolder.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_up_enter));
//        layDescriptionHolder.getAnimation().setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                layDescriptionHolder.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                descritionHidden = false;
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//    }
//
//    private void hideDescription()
//    {
//        layDescriptionHolder.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_down_exit));
//        layDescriptionHolder.getAnimation().setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                descritionHidden = true;
//                layDescriptionHolder.setVisibility(View.GONE);
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.big_picture_item, container, false);

        imgMainImage = (ImageView) itemView.findViewById(R.id.img_main_picture_viewer);
        prgProgress = (ProgressBar) itemView.findViewById(R.id.image_loading_progress);
        txtDescription = (DPTextView) itemView.findViewById(R.id.txt_picture_in_subject_description);
        layDescriptionHolder = (LinearLayout) itemView.findViewById(R.id.lay_txt_description_holder);
        descritionHidden = false;
////        showDescription();
//        imgMainImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (descritionHidden) {
//                    showDescription();
//                }
//                else
//                {
//                    hideDescription();
//                }
//            }
//        });

        SchoolApp.aQuery
                .id(imgMainImage)
                .progress(prgProgress)
                .image(
                        AppConstants.baseURL + mData.get(position).url + mData.get(position).id + ".jpg"
                        , true
                        , true
                        , 0
                        , 0
                        , null
                        , AQuery.FADE_IN);
        txtDescription.setText(mData.get(position).description);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
