package com.soutazin.farabiSchool.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.soutazin.farabiSchool.Fragments.BaseFragment;
import com.soutazin.farabiSchool.Fragments.MainFragment;
import com.soutazin.farabiSchool.Fragments.SectionsListingFragment;
import com.soutazin.farabiSchool.Model.Part;
import com.soutazin.farabiSchool.R;
import com.soutazin.farabiSchool.SchoolApp;
import com.soutazin.farabiSchool.ui.DPTextView;
import com.soutazin.farabiSchool.utilities.PreferencesHelper;

import java.util.ArrayList;

public class MainActivity extends Activity implements
        View.OnClickListener,
        BaseFragment.BackHandlerInterface        {

    private DrawerLayout mDrawerLayout;
//    private LinearLayout layRohabApp;
//    private DPTextView txtRohabApp;
    private LinearLayout layLanguage;
    private LinearLayout layMusicShop;
    private LinearLayout laySoutAzin;
    private String displayableName = "";
    private LinearLayout layMainActionBar;
    private DPTextView btnBackActionBar;
    private BaseFragment selectedFragment = null;
    private LinearLayout layMainButtonHolder;
    private ArrayList<Part> partsList = new ArrayList<>();
    private LinearLayout lastMainButtonSelected = null;
    private DPTextView txtSoutAzin;
    private DPTextView txtMusicShop;
    private DPTextView txtLanguage;
    private DPTextView txtRefrences;
    private LinearLayout layRefrences;

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        initSections();

        setContentView(R.layout.activity_main);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }


//        layRohabApp = (LinearLayout) findViewById(R.id.lay_menu_rohab_app);
//        txtRohabApp = (DPTextView) findViewById(R.id.txt_menu_rohab_app);
        laySoutAzin = (LinearLayout) findViewById(R.id.lay_menu_sout_azin);
        txtSoutAzin = (DPTextView) findViewById(R.id.txt_menu_sout_azin);
        layMusicShop = (LinearLayout) findViewById(R.id.lay_menu_music_shop);
        txtMusicShop = (DPTextView) findViewById(R.id.txt_menu_music_shop);
        txtRefrences = (DPTextView) findViewById(R.id.txt_menu_rohab_ref);
        layRefrences = (LinearLayout) findViewById(R.id.lay_menu_rohab_ref);
        layLanguage = (LinearLayout) findViewById(R.id.lay_menu_language);
        txtLanguage = (DPTextView) findViewById(R.id.txt_menu_language);
//        layWatchVideo = (LinearLayout) findViewById(R.id.txt_menu_watch_video);
        layMainButtonHolder = (LinearLayout) findViewById(R.id.lay_main_parts_button_holder);

        layMainActionBar = (LinearLayout) findViewById(R.id.main_action_bar);
        btnBackActionBar = (DPTextView) findViewById(R.id.btn_action_bar_back);


        btnBackActionBar.setOnClickListener(this);



        if (PreferencesHelper.getInstance().isNowPersian())
        {
//            txtRohabApp.setText(getString(R.string.rohab_app));
            txtSoutAzin.setText(getString(R.string.sout_azin));
            txtMusicShop.setText(getString(R.string.music_shop));
            txtLanguage.setText(getString(R.string.set_language));
            txtRefrences.setText(getString(R.string.refrences));
        }
        else
        {
//            txtRohabApp.setText(getString(R.string.rohab_app_en));
            txtSoutAzin.setText(getString(R.string.sout_azin_eng));
            txtMusicShop.setText(getString(R.string.music_shop_eng));
            txtLanguage.setText(getString(R.string.set_language_eng));
            txtRefrences.setText(getString(R.string.refrences_en));
        }


        displayableName = "";


//        layRohabApp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.soutazin.turkishtunerdemo")));
//            }
//        });

        layLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferencesHelper.getInstance().setLanguage(!PreferencesHelper.getInstance().isNowPersian());
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        laySoutAzin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.soutazin.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        layMusicShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.musicshop.ir";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        layRefrences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RefrenceActivity.class);
                startActivity(intent);
            }
        });

        arrangeMainButtons();

        getFragmentManager().addOnBackStackChangedListener(new android.app.FragmentManager.OnBackStackChangedListener() {
            public void onBackStackChanged() {
                int backCount = getFragmentManager().getBackStackEntryCount();
                if (backCount == 0) {
                    finish();
                }
            }
        });


        if (findViewById(R.id.fragment_container) != null) {

            MainFragment mainFragment = new MainFragment();
            replaceFragment(mainFragment, true);
        }


    }

    private void arrangeMainButtons() {
        partsList = SchoolApp.farabiDB.getParts();
        layMainButtonHolder.setWeightSum(partsList.size());

        LinearLayout.LayoutParams eachButtonParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        eachButtonParams.setMargins(4, 4, 4, 4);
        eachButtonParams.gravity = Gravity.CENTER;

        for (Part part : partsList)
        {
            LinearLayout tempButtonLayout = new LinearLayout(this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tempButtonLayout.setBackground(getDrawable(R.drawable.main_button_background));
            }
            else
            {
                tempButtonLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.main_button_background));
            }

            tempButtonLayout.setLayoutParams(eachButtonParams);

            DPTextView txtTempCaption = new DPTextView(this);
            txtTempCaption.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            txtTempCaption.setTextColor(ContextCompat.getColor(this, R.color.grey_700));
            txtTempCaption.setText(part.title);
            txtTempCaption.setTypeface(SchoolApp.iransSansTypeFace);
            txtTempCaption.setGravity(Gravity.CENTER);

            tempButtonLayout.setTag(part.id);
            tempButtonLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMainPartSelected((int)v.getTag(), (LinearLayout) v);
                }
            });

            tempButtonLayout.addView(txtTempCaption);


            layMainButtonHolder.addView(tempButtonLayout);
        }
    }

    private void onMainPartSelected(int tag, LinearLayout button) {
        try {
            lastMainButtonSelected.setBackground(ContextCompat.getDrawable(this, R.drawable.main_button_background));
        } catch (Exception e) {
        }
        button.setBackground(ContextCompat.getDrawable(this, R.drawable.main_button_background_selected));
        lastMainButtonSelected = button;
        SectionsListingFragment fragment = SectionsListingFragment.newInstance(tag);
        replaceFragment(fragment, true);
    }

    private void initSections() {
        SchoolApp.allParts = SchoolApp.farabiDB.getParts();
    }

    private BaseFragment getCurrentFragment() {
        return selectedFragment;
    }

    public void openDrawerMenu()
    {
        mDrawerLayout.openDrawer(Gravity.RIGHT, true);
    }



    public void replaceFragment (Fragment fragment, boolean addToBackStack){
//        try {
//            if (getCurrentFragment().getClass().equals(fragment.getClass()))
//            {
//                return;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String backStateName = fragment.getClass().getName();
        FragmentManager manager = getFragmentManager();
//        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

//        if (!fragmentPopped) { //fragment not in back stack, create it.
            android.app.FragmentTransaction ft = manager.beginTransaction();
        ft.setCustomAnimations(R.animator.fragment_slide_left_enter,R.animator.fragment_slide_right_exit
                ,R.animator.fragment_slide_left_exit,R.animator.fragment_slide_right_enter);

//        ft.setCustomAnimations(R.anim.fragment_slide_left_enter,
//                R.anim.fragment_slide_left_exit,
//                R.anim.fragment_slide_right_enter,
//                R.anim.fragment_slide_right_exit);
        ft.replace(R.id.fragment_container, fragment);
//            if (addToBackStack)
//                ft.addToBackStack(backStateName);
            ft.commit();
//        }

        manager.executePendingTransactions();

    }

    @Override
    public void onResume()
    {
        super.onResume();
    }



    @Override
    public void onClick(View view) {
        if (view.equals(btnBackActionBar))
        {
            openDrawerMenu();
        }
    }


    @Override
    public void onBackPressed() {
        if(selectedFragment == null || !selectedFragment.onBackPressed()) {
            // Selected fragment did not consume the back press event.
            super.onBackPressed();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }



    @Override
    public void setSelectedFragment(BaseFragment backHandledFragment) {
        this.selectedFragment = backHandledFragment;
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


}