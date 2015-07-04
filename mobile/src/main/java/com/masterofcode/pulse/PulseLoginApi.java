package com.masterofcode.pulse;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class PulseLoginApi
{
  // TODO: replace with variables in some file, referenced from buildscript
  private static final String CLIENT_ID = "9c7afff0c27f5ab2827edc7414f917931025ef4dbd7ed1d44acb1a1e82abd968";
  private static final String CLIENT_SECRET = "847bb22841f7950052f719202168e81c1500bde58979e8da7a296312b0b1f1db";
  private static final String REDIRECT_URI = "http://localhost/Callback";

  private static final String AUTHORIZE_URL = "http://192.168.4.121:3000/oauth/authorize?client_id=%s&response_type=code&redirect_uri=%s";
  private static final String REQUEST_TOKEN_URL = "http://192.168.4.121:3000/oauth/token";

  private WebView mWebView;
  private Callback callback;

  public PulseLoginApi(@NonNull WebView webView, Callback callback) {
    this.mWebView = webView;
    this.callback = callback;
    mWebView.clearCache(true);
    mWebView.getSettings().setJavaScriptEnabled(true);
    mWebView.getSettings().setBuiltInZoomControls(true);
    mWebView.getSettings().setDisplayZoomControls(false);
    mWebView.setWebViewClient(mWebViewClient);

//    clearCookies(webView);
  }

  private void clearCookies(@NonNull WebView webView) {
    CookieSyncManager.createInstance(webView.getContext());
    CookieManager cookieManager = CookieManager.getInstance();
    cookieManager.removeAllCookie();
    cookieManager.setAcceptCookie(true);
  }

  public interface Callback {
    void onTokenParsed(String accessToken, String refreshToken);
    void onError();
  }

  public void authorize() {
    mWebView.loadUrl(String.format(AUTHORIZE_URL, CLIENT_ID, REDIRECT_URI));
  }

  private WebViewClient mWebViewClient = new WebViewClient() {
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
      if ((url != null) && (url.startsWith(REDIRECT_URI))) { // Override webview when user came back to REDIRECT_URI
        mWebView.stopLoading();
        mWebView.setVisibility(View.INVISIBLE); // Hide webview if necessary
        Uri uri = Uri.parse(url);
        String code = uri.getQueryParameter("code");
        Ion.with(mWebView.getContext())
                .load(REQUEST_TOKEN_URL)
                .setBodyParameter("client_id", CLIENT_ID)
                .setBodyParameter("client_secret", CLIENT_SECRET)
                .setBodyParameter("redirect_uri", REDIRECT_URI)
                .setBodyParameter("grant_type", "authorization_code")
                .setBodyParameter("code", code)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                  @Override
                  public void onCompleted(Exception e, JsonObject result) {
                    if (e != null) {
                      callback.onError();
                    } else {
                      Log.d("x", result.toString());
                      String accessToken = result.get("access_token").getAsString();
                      String refreshToken = result.get("refresh_token").getAsString();
                      callback.onTokenParsed(accessToken, refreshToken);
                    }
                  }
                });
      }
    }
  };


}