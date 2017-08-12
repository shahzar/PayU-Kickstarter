package com.shzlabs.payukickstarter.ui.main;

import android.util.Log;


import com.shzlabs.payukickstarter.data.DataManager;
import com.shzlabs.payukickstarter.data.local.DatabaseHelper;
import com.shzlabs.payukickstarter.data.model.Kickstarter;
import com.shzlabs.payukickstarter.data.remote.KickstarterApi;
import com.shzlabs.payukickstarter.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shaz on 14/2/17.
 */

public class MainPresenter extends BasePresenter<MainView> {

    private static final String TAG = MainPresenter.class.getSimpleName();
    private DataManager dataManager;
    private Subscription subscription;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    public void loadItems() {

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        Log.d(TAG, "loadItems: Fetching data for view ...");
        subscription = dataManager.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Kickstarter>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: Completed retrieving data!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: Eror fetching data", e);
                    }

                    @Override
                    public void onNext(List<Kickstarter> kickstarters) {
                        Log.d(TAG, "onNext: Data of size " + kickstarters.size() + " retrieved. Displaying now ...");
                        if (kickstarters.size() > 0) {
                            getMvpView().displayItems(kickstarters);
                        }else{
                            getMvpView().displayLoadingScreen();
                        }
                    }
                });
    }
}
