package com.shzlabs.payukickstarter.ui.main;


import com.shzlabs.payukickstarter.data.model.Kickstarter;
import com.shzlabs.payukickstarter.ui.base.MvpView;

import java.util.List;

/**
 * Created by shaz on 14/2/17.
 */

public interface MainView extends MvpView {
    void displayItems(List<Kickstarter> items);
    void displayLoadingScreen();
}
