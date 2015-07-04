package com.masterofcode.pulse.models.containers;

import com.google.gson.annotations.Expose;

/**
 * Created by Serhii Nadolynskyi <serhii.nadolinskyi@gmail.com> on 04.07.15.
 */
public class Token {

    @Expose
    private String pushToken;

    @Expose
    private String voteApiToken;

    public Token(String pushToken) {
        this.pushToken = pushToken;
    }

    public Token(String pushToken, String voteApiToken) {
        this.pushToken = pushToken;
        this.voteApiToken = voteApiToken;
    }

    public String getVoteApiToken() {
        return voteApiToken;
    }

    public void setVoteApiToken(String voteApiToken) {
        this.voteApiToken = voteApiToken;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }
}
