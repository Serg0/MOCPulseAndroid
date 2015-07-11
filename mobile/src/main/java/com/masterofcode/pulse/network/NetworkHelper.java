package com.masterofcode.pulse.network;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.masterofcode.pulse.App;
import com.masterofcode.pulse.ui.LoginActivity;

import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by Serhii Nadolynskyi <serhii.nadolinskyi@gmail.com> on 04.07.15.
 */
public class NetworkHelper {

//    public static final String PULSE_API_ENDPOINT = "http://192.168.4.71:3000";
//    public static final String PULSE_API_ENDPOINT = "192.168.4.63:8080"; //Dima
    public static final String PULSE_API_ENDPOINT = "http://192.168.4.118:3000";// me
//    public static final String PULSE_API_ENDPOINT = "http://192.168.4.81:3000";
    public static final String MOC_ID_API_ENDPOINT = " http://fritzvl.info:80";

    public static PulseApiService pulseApiService;
    public static MOCIDApiService mocIdApiService;
    private static Gson gson;

    public static Context sContext;

    public static void init(Context context){
        sContext = context;

        initGSON();
        initPulseAPI();
        initMOCidAPI();

    }

    private static void initGSON() {

        gson =  new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//                .setPrettyPrinting()
//                .serializeNulls()
                .create();

    }

    private static void initMOCidAPI() {

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                if(!TextUtils.isEmpty(App.getTokenMocId())){
                    request.addHeader("Authorization", "Bearer " + App.getTokenMocId());
                }

            }
        };


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(MOC_ID_API_ENDPOINT)
                .setRequestInterceptor(requestInterceptor)
                .setConverter(new GsonConverter(gson))
                .build();

        mocIdApiService = restAdapter.create(MOCIDApiService.class);


    }

    private static void initPulseAPI() {

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                if(!TextUtils.isEmpty(App.getTokenPulse())){
                    request.addHeader("auth_token", App.getTokenPulse());
                }

            }
        };

        ErrorHandler loginErrorHandler = new ErrorHandler() {
            @Override
            public Throwable handleError(RetrofitError cause) {
                Response r = cause.getResponse();
                if (r != null && r.getStatus() == 401) {
                    NetworkHelper.sContext.startActivity(new Intent(sContext, LoginActivity.class));
                    return cause;
                }
                return cause;
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(PULSE_API_ENDPOINT)
                .setRequestInterceptor(requestInterceptor)
                .setErrorHandler(loginErrorHandler)
                .setConverter(new GsonConverter(gson))
                .build();

        pulseApiService = restAdapter.create(PulseApiService.class);
    }


}
