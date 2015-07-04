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
    @Expose
    private Vote vote;

    public VotesContainer(Vote vote) {
        this.vote = vote;
    }

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }
}
