package com.masterofcode.pulse.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

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
        initActionBar();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_votes, menu);
        return true;
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_add: {
                                addNewVote();
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                        return true;
                    }
                });

    }

    private void addNewVote() {
        //TODO method implementation

        showToastUnderConstruction();

    }

    @Override
    protected void onResume() {
        super.onResume();

        final Set<Vote> votes = NotificationHelper.getVotes();
        if(votes.isEmpty()){
            //do nothing
        }else if(votes.size() == 1){
            //TODO open single voting
            Intent intent = new Intent(this, VoteActivity.class);
            intent.putExtra(VoteActivity.EXTRA_VOTE, votes.iterator().next());
            startActivity(intent);
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
