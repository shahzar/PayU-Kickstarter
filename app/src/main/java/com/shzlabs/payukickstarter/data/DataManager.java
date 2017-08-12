package com.shzlabs.payukickstarter.data;

import android.util.Log;

import com.shzlabs.payukickstarter.data.local.DatabaseHelper;
import com.shzlabs.payukickstarter.data.local.PreferenceHelper;
import com.shzlabs.payukickstarter.data.model.Kickstarter;
import com.shzlabs.payukickstarter.data.remote.KickstarterApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by shaz on 12/8/17.
 */

public class DataManager {

    private static final String TAG = DataManager.class.getSimpleName();

    private PreferenceHelper prefHelper;
    private DatabaseHelper databaseHelper;

    @Inject
    public DataManager(PreferenceHelper prefHelper, DatabaseHelper databaseHelper) {
        this.prefHelper = prefHelper;
        this.databaseHelper = databaseHelper;
    }

    public Observable<List<Kickstarter>> getData() {

        if (!prefHelper.isDataCached()) {

            Log.d(TAG, "getData: Fetching data from remote source ...");
            KickstarterApi.Factory.getInstance().getProjects()
                    .enqueue(new Callback<List<Kickstarter>>() {
                        @Override
                        public void onResponse(Call<List<Kickstarter>> call, Response<List<Kickstarter>> response) {
                            Log.d(TAG, "onResponse: Received data of size " + response.body().size()) ;
                            Log.d(TAG, "onResponse: Adding to database ...");
                            databaseHelper.setProjects(response.body()).subscribe(new Subscriber<Kickstarter>() {
                                @Override
                                public void onCompleted() {
                                    prefHelper.setDataCached(true);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.e(TAG, "onError: Error adding to database!!" + e );
                                }

                                @Override
                                public void onNext(Kickstarter kickstarter) {

                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<Kickstarter>> call, Throwable t) {
                            Log.e(TAG, "onFailure: Error fetching data!");
                        }
                    });

        }

        return databaseHelper.getProjects().distinct();
    }
}
