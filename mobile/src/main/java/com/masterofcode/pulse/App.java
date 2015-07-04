package com.masterofcode.pulse;

import android.app.Application;

import com.masterofcode.pulse.network.MOCIDApiService;
import com.masterofcode.pulse.network.NetworkHelper;
import com.masterofcode.pulse.network.PulseApiService;

/**
 * Created by Serhii Nadolynskyi <serhii.nadolinskyi@gmail.com> on 04.07.15.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkHelper.init();


    }

    public static PulseApiService getService(){
        return NetworkHelper.pulseApiService;
    }

    public static MOCIDApiService getIDService(){
        return NetworkHelper.mocIdApiService;
    }
}
