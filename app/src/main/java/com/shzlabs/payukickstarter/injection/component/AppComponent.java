package com.shzlabs.payukickstarter.injection.component;


import com.shzlabs.payukickstarter.injection.module.AppModule;
import com.shzlabs.payukickstarter.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by shaz on 14/2/17.
 */

@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity activity);
}
