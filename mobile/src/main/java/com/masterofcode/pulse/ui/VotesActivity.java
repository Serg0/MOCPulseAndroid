package com.masterofcode.pulse.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.masterofcode.pulse.App;
import com.masterofcode.pulse.R;
import com.masterofcode.pulse.adapters.VoteTabsAdapter;
import com.masterofcode.pulse.models.Vote;
import com.masterofcode.pulse.models.containers.VotesContainer;
import com.masterofcode.pulse.network.gcm.NotificationHelper;

import java.util.Set;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Serhii Nadolynskyi <serhii.nadolinskyi@gmail.com> on 04.07.15.
 */
public class VotesActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tabs)
    TabLayout tabs;
    @InjectView(R.id.pager)
    ViewPager pager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votes);
        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final Set<Vote> votes = NotificationHelper.getVotes();
        if(votes.isEmpty()){
            //do nothing
        }else if(votes.size() == 1){
            //TODO open single voting
        }else{
            //TODO open vote list
        }
        NotificationHelper.cancelAll();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        App.getService().getVotes(new Callback<VotesContainer>() {
            @Override
            public void success(VotesContainer votesContainer, Response response) {
                PagerAdapter adapter = new VoteTabsAdapter(
                        VotesActivity.this,
                        getSupportFragmentManager(),
                        votesContainer.getVotes()
                );
                pager.setAdapter(adapter);
                tabs.setTabsFromPagerAdapter(adapter);
                pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
            }

            @Override
            public void failure(RetrofitError error) {
                showToast("RetrofitError getting votes");
            }
        });
    }

}
