package com.soutazin.farabiSchool.utilities;
import android.content.Context;
import android.content.SharedPreferences;

import com.soutazin.farabiSchool.SchoolApp;

/**
 * Created by pc on 8/11/2014.
 */
public class PreferencesHelper {

    public static final String PREFS_NAME = "farabi_school_prefrences";
    public static final String LANGUAGE = "school_language";
    private static final String FIRST_USE = "first_use";


    private SharedPreferences mSettings;
    private SharedPreferences.Editor mEditor;



    public PreferencesHelper() {
    }

    private static PreferencesHelper INSTANCE = null;
    public synchronized static PreferencesHelper getInstance() {
        if(INSTANCE == null){
            INSTANCE = new PreferencesHelper();
        }
        return INSTANCE;
    }



    public synchronized void init() {
        if (mSettings == null) {
            mSettings = SchoolApp.mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            mEditor = mSettings.edit();

        }
    }



    public synchronized boolean isNowPersian()
    {
        return mSettings.getBoolean(LANGUAGE, true);
    }
    public synchronized void setLanguage(boolean persian)
    {
        mEditor.putBoolean(LANGUAGE, persian);
        mEditor.commit();
    }


    public boolean isFirstUse() {
        return mSettings.getBoolean(FIRST_USE, true);
    }

    public void cleanFirstUse()
    {
        mEditor.putBoolean(FIRST_USE, false);
        mEditor.commit();
    }
}


