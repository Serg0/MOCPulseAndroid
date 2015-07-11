package com.masterofcode.pulse.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.masterofcode.pulse.App;
import com.masterofcode.pulse.R;
import com.masterofcode.pulse.models.Vote;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by sonerik on 04.07.15.
 */
public class VoteActivity extends BaseActivity {

    @InjectView(R.id.tvVoteName)
    TextView tvVoteName;
    @InjectView(R.id.greenPole)
    View greenPole;
    @InjectView(R.id.yellowPole)
    View yellowPole;
    @InjectView(R.id.redPole)
    View redPole;

    private Vote vote;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        ButterKnife.inject(this);
        vote = getIntent().getParcelableExtra(Vote.class.getSimpleName());
        if (vote == null) {
            Log.e(App.TAG, "vote == null");
        } else {
            Log.d(App.TAG, "vote: "+vote.toString());
            tvVoteName.setText(vote.getName());
        }
    }

    @OnClick({R.id.greenVote, R.id.yellowVote, R.id.redVote})
    public void onBtnVoteClicked(View v) {
        switch (v.getId()){
            case R.id.greenVote:{
                showToast("greenVote btn clicked.");
                break;
            }
            case R.id.yellowVote:{
                showToast("yellowVote btn clicked.");
                break;
            }
            case R.id.redVote:{
                showToast("redVote btn clicked.");
                break;
            }
        }

    }

}
