package com.masterofcode.pulse.network.gcm;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.masterofcode.pulse.R;
import com.masterofcode.pulse.ui.BaseActivity;

import java.io.IOException;

/**
 * Created by Serhii Nadolynskyi <serhii.nadolinskyi@gmail.com> on 04.07.15.
 */
public class GCMHelper extends ContextWrapper{

    private static final String PREF_GCM_REG_ID = "9c7afff0c27f5ab2827edc7414f917931025ef4dbd7ed1d44acb1a1e82abd968";
    private SharedPreferences prefs;
    // Your project number and web server url. Please change below.
    private static final String GCM_SENDER_ID = "847bb22841f7950052f719202168e81c1500bde58979e8da7a296312b0b1f1db";
    private static final int ACTION_PLAY_SERVICES_DIALOG = 100;

    private String gcmRegId;

    GoogleCloudMessaging gcm;
    private BaseActivity activity;
    private GCMCallBack cb;
    private String LOG_TAG = "GCMHelper";

    public interface GCMCallBack{
        void onError(String error);
        void onSuccess(String token);
    }

    public GCMHelper(BaseActivity base, GCMCallBack cb) {
        super(base);
        activity = base;
        this.cb = cb;
        if(activity == null || cb == null){
            throw new RuntimeException("Activity and/or CallBack can't be null");
        }
        register();
    }

    public void register(){

        if (isGooglePlayInstalled()) {
            gcm = GoogleCloudMessaging.getInstance(getApplicationContext());

            // Read saved registration id from shared preferences.
            gcmRegId = getSharedPreferences().getString(PREF_GCM_REG_ID, "");

            if (TextUtils.isEmpty(gcmRegId)) {
                new GCMRegistrationTask().execute();
            }else{

                Log.d(LOG_TAG, "PREF_GCM_REG_ID " + gcmRegId);
                cb.onSuccess(gcmRegId);
                showMessage(getString(R.string.already_registerred_gcm));
            }
        }

    }

    private boolean isGooglePlayInstalled() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, activity,
                        ACTION_PLAY_SERVICES_DIALOG).show();
            } else {
                final String message = getString(R.string.gp_not_installed);
                showMessage(message);
                cb.onError(message);
            }
            return false;
        }
        return true;

    }

    private SharedPreferences getSharedPreferences() {
        if (prefs == null) {
            prefs = getApplicationContext().getSharedPreferences(
                    "AndroidSRCDemo", Context.MODE_PRIVATE);
        }
        return prefs;
    }

    public void saveInSharedPref(String result) {

        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(PREF_GCM_REG_ID, result);
        editor.commit();
    }

    private class GCMRegistrationTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            if (gcm == null && isGooglePlayInstalled()) {
                gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
            }
            try {
                gcmRegId = gcm.register(GCM_SENDER_ID);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return gcmRegId;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                cb.onSuccess(gcmRegId);
                showMessage(getString(R.string.registered_gcm));
                saveInSharedPref(result);
                Log.d(LOG_TAG, "PREF_GCM_REG_ID " + gcmRegId);
            }else{
                cb.onError(getString(R.string.gcm_registration_failed));
            }
        }

    }

    private void showMessage(String message) {
        if(activity != null){
            activity.showToast(message);
        }

    }


}
