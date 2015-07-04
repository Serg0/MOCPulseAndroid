package com.masterofcode.pulse.models.containers;

import com.google.gson.annotations.Expose;

/**
 * Created by Serhii Nadolynskyi <serhii.nadolinskyi@gmail.com> on 04.07.15.
 */
public class PushNotificationToken {

    @Expose
    private String pushToken;

    public PushNotificationToken(String pushToken) {
        this.pushToken = pushToken;
    }
}
