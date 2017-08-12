package com.shzlabs.payukickstarter.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.shzlabs.payukickstarter.TheApplication;


public class BaseActivity extends AppCompatActivity {

    public TheApplication getTheApplication() {
        return ((TheApplication) getApplication());
    }

}