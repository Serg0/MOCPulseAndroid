package com.masterofcode.pulse.network;

import com.masterofcode.pulse.models.Vote;
import com.masterofcode.pulse.models.containers.VotesContainer;

import java.util.List;
import java.util.Objects;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Serhii Nadolynskyi <serhii.nadolinskyi@gmail.com> on 04.07.15.
 */
public interface PulseApiService {

    @GET("/votes")
    void getVotes(Callback<VotesContainer> cb);

    @POST("/votes")
    void sendVote(@Body VotesContainer votesContainer, Callback<VotesContainer> cb);

    @GET("/votes/{voteId}")
    void getVote(@Path("voteId") Integer voteId, Callback<VotesContainer> cb);

    @PUT("/votes/{voteId}")
    void updateVote(@Path("voteId") Integer voteId, @Body VotesContainer votesContainer, Callback<VotesContainer> cb);


}
