package com.masterofcode.pulse;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.masterofcode.pulse.models.containers.PushNotificationToken;
import com.masterofcode.pulse.network.gcm.GCMHelper;
import com.masterofcode.pulse.ui.BaseActivity;
import com.masterofcode.pulse.ui.VotesActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends BaseActivity {
    @InjectView(R.id.webView)
    WebView mWebView;

    private String gcmToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        new GCMHelper(this, new GCMHelper.GCMCallBack() {
            @Override
            public void onError(String error) {
                showToast("Got GCM error: "+error);
            }

            @Override
            public void onSuccess(final String token) {
                showToast("Got GCM token: "+token);
                new PulseLoginApi(mWebView, new PulseLoginApi.Callback() {
                    @Override
                    public void onTokenParsed(String accessToken, String refreshToken) {
                        Log.d("x", String.format("accessToken: %s; refreshToken: %s", accessToken, refreshToken));
                        App.setTokenMocId(accessToken);
                        App.getIDService().setPushNotificationToken(new PushNotificationToken(token), new Callback<Object>() {
                            @Override
                            public void success(Object o, Response response) {
                                showToast("Push notification token successfully set!");
                                startActivity(new Intent(LoginActivity.this, VotesActivity.class));
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                showToast("Push notification set error!");
                            }
                        });

                    }

                    @Override
                    public void onError() {
                        Log.d("x", "Error");
                    }
                }).authorize();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
