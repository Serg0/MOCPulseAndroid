package com.masterofcode.pulse.network;

import com.masterofcode.pulse.models.containers.PushNotificationToken;
import com.masterofcode.pulse.models.containers.User;
import com.masterofcode.pulse.models.containers.VotesContainer;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Serhii Nadolynskyi <serhii.nadolinskyi@gmail.com> on 04.07.15.
 */
public interface MOCIDApiService {

    @POST("/api/me/data")
    void setPushNotificationToken(@Body PushNotificationToken token, Callback<Object> cb);


    @GET("/api/me.json")
    void getUser(Callback<User> cb);


}
