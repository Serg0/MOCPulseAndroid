package com.masterofcode.pulse.models.containers;

import com.google.gson.annotations.Expose;

/**
 * Created by Serhii Nadolynskyi <serhii.nadolinskyi@gmail.com> on 04.07.15.
 */
public class Token {

    @Expose
    private String pushToken;

    public Token(String pushToken) {
        this.pushToken = pushToken;
    }
}
