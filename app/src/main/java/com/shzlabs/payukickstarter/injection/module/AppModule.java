package com.shzlabs.payukickstarter.injection.module;

import android.app.Application;
import android.content.Context;

import com.shzlabs.payukickstarter.data.local.PreferenceHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shaz on 14/2/17.
 */

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    public Application provideApplication() {
        return application;
    }

    @Provides
    public Context provideContext() {
        return application;
    }

}
