package com.masterofcode.pulse.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.masterofcode.pulse.R;
import com.masterofcode.pulse.models.Vote;
import com.masterofcode.pulse.ui.VotesListFragment;

import java.util.List;

/**
 * Created by sonerik on 7/4/2015.
 */
public class VoteTabsAdapter extends FragmentPagerAdapter {

    private List<Vote> votes;
    private Resources resources;

    public VoteTabsAdapter(Context context, FragmentManager fm, List<Vote> votes) {
        super(fm);
        resources = context.getResources();
        this.votes = votes;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Pending
                return VotesListFragment.newInstance(votes);
            case 1: // Voted
                return VotesListFragment.newInstance(votes);
        }
        return VotesListFragment.newInstance(votes);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: // Pending
                return resources.getString(R.string.pending);
            case 1: // Voted
                return resources.getString(R.string.voted);
        }
        return "";
    }
}
