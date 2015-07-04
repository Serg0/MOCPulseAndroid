package com.masterofcode.pulse.models.containers;

import com.google.gson.annotations.Expose;
import com.masterofcode.pulse.models.Vote;

import java.util.ArrayList;

/**
 * Created by Serhii Nadolynskyi <serhii.nadolinskyi@gmail.com> on 04.07.15.
 */
public class VotesContainer {

    @Expose
    private ArrayList<Vote> votes;

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }
}
