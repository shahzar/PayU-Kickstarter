package com.shzlabs.payukickstarter;

import android.app.Application;

import com.shzlabs.payukickstarter.injection.component.AppComponent;
import com.shzlabs.payukickstarter.injection.component.DaggerAppComponent;
import com.shzlabs.payukickstarter.injection.module.AppModule;


public class TheApplication extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}