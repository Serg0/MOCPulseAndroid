package com.masterofcode.pulse.network;

import android.text.TextUtils;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by Serhii Nadolynskyi <serhii.nadolinskyi@gmail.com> on 04.07.15.
 */
public class NetworkHelper {

    public static final String API_ENDPOINT = "http://192.168.4.71:3000";
    public static final String TOKEN = null;
    public static PulseApiService pulseApiService;


    public static void init(){

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                if(!TextUtils.isEmpty(TOKEN)){
                    request.addHeader("auth_token", TOKEN);
                }

            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_ENDPOINT)
                .setRequestInterceptor(requestInterceptor)
                .build();

        pulseApiService = restAdapter.create(PulseApiService.class);

    }


}
