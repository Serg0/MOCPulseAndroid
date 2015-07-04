package com.masterofcode.pulse.network;

import com.masterofcode.pulse.models.Vote;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Serhii Nadolynskyi <serhii.nadolinskyi@gmail.com> on 04.07.15.
 */
public interface PulseApiService {

    @GET("/votes")
    List<Vote> getVotes();


    @POST("/votes")
    List<Vote> sendVote(@Body Vote vote);


}
