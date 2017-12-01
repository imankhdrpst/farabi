package com.soutazin.farabiSchool;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.text.format.DateUtils;
import android.view.LayoutInflater;

import com.androidquery.AQuery;
import com.soutazin.farabiSchool.DataBase.FarabiDataBase;
import com.soutazin.farabiSchool.Model.Part;
import com.soutazin.farabiSchool.Model.PictureInSubject;
import com.soutazin.farabiSchool.utilities.PreferencesHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by a.ebrahiminia on 7/11/2016.
 */
public class SchoolApp extends Application {

    public static LayoutInflater inflater;
    public static volatile Context mContext = null;
    public static Typeface iransSansTypeFace = null;
    public static Typeface fontAwesome = null;
    private static volatile SchoolApp instance = null;
    public static ArrayList<Part> allParts = new ArrayList<>();
    public static AQuery aQuery = null;
    public static FarabiDataBase farabiDB = null;
    public static ArrayList<PictureInSubject> currentPicturesArrayList = new ArrayList<>();


    @Override
    public void onCreate() {
        super.onCreate();

        aQuery = new AQuery(getApplicationContext());
        SchoolApp.inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        SchoolApp.iransSansTypeFace = Typeface.createFromAsset(getAssets(), "iransans.ttf");
        SchoolApp.fontAwesome = Typeface.createFromAsset(getAssets(), "fontawesome.ttf");
        SchoolApp.mContext = getApplicationContext();
        PreferencesHelper.getInstance().init();
        farabiDB = new FarabiDataBase(this);

    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
    }

}
