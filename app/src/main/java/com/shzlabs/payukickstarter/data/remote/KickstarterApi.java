package com.shzlabs.payukickstarter.data.remote;

import com.shzlabs.payukickstarter.data.model.Kickstarter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by shaz on 12/8/17.
 */

public interface KickstarterApi {

    String BASE_URL = "http://starlord.hackerearth.com/";

    @GET("kickstarter")
    Call<List<Kickstarter>> getProjects();

    class Factory{
        private static KickstarterApi service;
        public static KickstarterApi getInstance(){
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                service = retrofit.create(KickstarterApi.class);
                return service;
            }else{
                return service;
            }
        }
    }

}
