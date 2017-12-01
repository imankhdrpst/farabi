package com.soutazin.farabiSchool.Fragments;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soutazin.farabiSchool.DataBase.FarabiDataBase;
import com.soutazin.farabiSchool.Model.Part;
import com.soutazin.farabiSchool.R;
import com.soutazin.farabiSchool.SchoolApp;
import com.soutazin.farabiSchool.ui.DPTextView;
import com.soutazin.farabiSchool.ui.DpJustifyTextView;
import com.soutazin.farabiSchool.utilities.PreferencesHelper;

import java.util.ArrayList;


public class MainFragment extends BaseFragment implements View.OnClickListener {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;
//    private EasyTabs tabs;
    private FarabiDataBase farabiDB;
    private ArrayList<Part> parstList;
    private DPTextView txtIntroduction;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        View view = inflater.inflate(R.layout.fragment_farabi_main, container, false);
//        tabs = (EasyTabs) view.findViewById(R.id.main_tabs);

        txtIntroduction = (DPTextView) view.findViewById(R.id.txt_main_school_introduction);

        return view;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstances  )
    {
        if (PreferencesHelper.getInstance().isNowPersian()) {
            txtIntroduction.setText(Html.fromHtml("اپلیکیشن مدرسه فارابی شامل مجموعه ای گردآوری شده از متون تحقیقی و آموزشی همچنین اسناد صوتی و تصویری درباره موسیقی ایران و جهان می باشد.\n" +
                    "این اپلیکیشن قبلا به صورت یک بسته نرم افزاری فاخر در شان محصول و نام فارابی توسط شرکت صوت آذین به بازار عرضه شده بود و اینک با گسترس فن آوری های جدید و فراگیر شدن آنها با فنا آوری جدید به دوستداران موسیقی عرضه می شود.\n" +
                    "برای دریافت آثار صوتی و کلیپ ها و همچنین ابراز کمک آموزشی علاقه مندان می توانند بسته نرم افزاری مدرسه فارابی را از سایت <a href=\"http://www.musicshop.ir/\">فروشگاه ما  </a> یا از فروشگاههای  معتبر موسیقی تهیه نمایند.\n"));
            txtIntroduction.setMovementMethod(LinkMovementMethod.getInstance());
        }
        else
        {
            txtIntroduction.setText(getString(R.string.farabi_introduction_en));
        }

//        ArrayList<TabItem> allTabs = new ArrayList<>();
//        farabiDB = new FarabiDataBase(getContext());
//        parstList = farabiDB.getParts();
//        EasyTabsBuilder.with(tabs)
//                .addTabs(
//                        new TabItem(new SectionsListingFragment(),getString( R.string.instruments)),
//                        new TabItem(new TheoryOfMusicFragment(), getString(R.string.theoryOfMusic)),
//                        new TabItem(new GenerologyFragment(), getString(R.string.generology)))
//                .setTabsBackgroundColor(getResources().getColor(R.color.main_red))
//                .setIndicatorColor(getResources().getColor(R.color.app_white))
//                .setTextColors(EasyTabsColors.White, EasyTabsColors.GreenYellow)
//                .setCustomTypeface(SchoolApp.iransSansTypeFace)
//                .setTabLayoutScrollable(true)
//                .Build();

    }




    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
//            updateArticleView(args.getInt(ARG_CONV_ID));
        } else if (mCurrentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
//            updateArticleView(conversationId);
        }

        updateUI();

    }

    @Override
    public void updateUI() {

    }


    @Override
    public void filterFragment(String filter)
    {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

    @Override
    public void onClick(View view) {
    }



}