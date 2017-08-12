package com.shzlabs.payukickstarter.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;

/**
 * Created by shaz on 12/8/17.
 */

public class PreferenceHelper {

    public static final String PREF_DATA_CACHED = "data_cached";
    private SharedPreferences sPref;

    @Inject
    public PreferenceHelper(Context context) {
        sPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean isDataCached() {
        return sPref.getBoolean(PREF_DATA_CACHED, false);
    }

    public void setDataCached(boolean status) {
        sPref.edit().putBoolean(PREF_DATA_CACHED, status).apply();
    }
}
