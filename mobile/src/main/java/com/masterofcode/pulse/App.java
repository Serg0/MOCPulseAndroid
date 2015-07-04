package com.masterofcode.pulse;

import android.app.Application;
import android.text.TextUtils;

import com.masterofcode.pulse.network.MOCIDApiService;
import com.masterofcode.pulse.network.NetworkHelper;
import com.masterofcode.pulse.network.PulseApiService;
import com.masterofcode.pulse.network.gcm.NotificationHelper;

import me.alexrs.prefs.lib.Prefs;

/**
 * Created by Serhii Nadolynskyi <serhii.nadolinskyi@gmail.com> on 04.07.15.
 */
public class App extends Application {

    public static final String TAG = "Pulse";

    public static String TOKEN_MOC_ID = null;
    public static String TOKEN_PULSE = null;
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkHelper.init(this);
        instance = this;
        NotificationHelper.init(this);

    }


    public static String getTokenMocId() {
        if(TextUtils.isEmpty(TOKEN_MOC_ID)){
            TOKEN_MOC_ID = Prefs.with(instance).getString("TOKEN_MOC_ID", "");
        }
        return TOKEN_MOC_ID;
    }

    public static void setTokenMocId(String tokenMocId) {
        Prefs.with(instance).save("TOKEN_MOC_ID", tokenMocId);
        TOKEN_MOC_ID = tokenMocId;
    }

    public static String getTokenPulse() {

        if(TextUtils.isEmpty(TOKEN_PULSE)){
            TOKEN_PULSE = Prefs.with(instance).getString("TOKEN_PULSE", "");
        }
        return TOKEN_PULSE;
    }

    public static void setTokenPulse(String tokenPulse) {
        Prefs.with(instance).save("TOKEN_PULSE", tokenPulse);
        TOKEN_PULSE = tokenPulse;
    }


    public static PulseApiService getService(){
        return NetworkHelper.pulseApiService;
    }

    public static MOCIDApiService getIDService(){
        return NetworkHelper.mocIdApiService;
    }
}
