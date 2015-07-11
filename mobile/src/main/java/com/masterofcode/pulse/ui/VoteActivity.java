package com.masterofcode.pulse.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.masterofcode.pulse.App;
import com.masterofcode.pulse.R;
import com.masterofcode.pulse.models.Vote;
import com.masterofcode.pulse.models.containers.VotesContainer;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by sonerik on 04.07.15.
 */
public class VoteActivity extends BaseActivity {

    @InjectView(R.id.tvVoteName)
    TextView tvVoteName;
    @InjectView(R.id.greenPole)
    TextView greenPole;
    @InjectView(R.id.yellowPole)
    TextView yellowPole;
    @InjectView(R.id.redPole)
    TextView redPole;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @InjectView(R.id.llDoVote)
    LinearLayout mLlDoVote;
    @InjectView(R.id.tvVotesCount)
    TextView mTvVotesCount;
    @InjectView(R.id.llVotePoll)
    LinearLayout mLlVotePoll;

    private Vote vote;
    private MODE mode;

    public enum MODE {
        VOTE, RESULT
    }

    int[] results = new int[3];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        ButterKnife.inject(this);
        getExtras();

        initViews();

    }

    private void initViews() {
        //TODO method implementation
        if (vote == null) {
            Log.e(App.TAG, "vote == null");
        } else {
            Log.d(App.TAG, "vote: " + vote.toString());
            tvVoteName.setText(vote.getName());
        }

        switch (mode) {
            case VOTE:
                mLlDoVote.setVisibility(View.VISIBLE);
                mLlVotePoll.setVisibility(View.GONE);
                break;
            case RESULT:
                mLlDoVote.setVisibility(View.GONE);
                mLlVotePoll.setVisibility(View.VISIBLE);
                showResultChart();
                break;
        }
    }

    private void showResultChart() {
       generateMockResult();

        final int total = results[0] + results[1] + results[2];
        mTvVotesCount.setText(String.format(getString(R.string.votes_n), total));

        redPole.setText(String.valueOf(results[0]));
        yellowPole.setText(String.valueOf(results[1]));
        greenPole.setText(String.valueOf(results[2]));

        final LinearLayout.LayoutParams redPoleLP = (LinearLayout.LayoutParams) redPole.getLayoutParams();
        final LinearLayout.LayoutParams yellowPoleLP = (LinearLayout.LayoutParams) yellowPole.getLayoutParams();
        final LinearLayout.LayoutParams greenPoleLP = (LinearLayout.LayoutParams) greenPole.getLayoutParams();

        redPoleLP.weight = (float) results[0]/total*100;
        yellowPoleLP.weight = (float) results[1]/total*100;
        greenPoleLP.weight = (float) results[2]/total*100;

        mLlVotePoll.requestLayout();

    }

    private void generateMockResult() {
        //TODO method implementation
        final Random random = new Random();

        results[0] = random.nextInt(100);//red
        results[1] = random.nextInt(100);//yellow
        results[2] = random.nextInt(100);//green


    }


    private void getExtras() {
        vote = getIntent().getParcelableExtra(Vote.class.getSimpleName());
        final int intExtraMode = getIntent().getIntExtra(MODE.class.getSimpleName(), -1);
        mode = MODE.values()[intExtraMode];
    }

    @OnClick({R.id.greenVote, R.id.yellowVote, R.id.redVote})
    public void onBtnVoteClicked(View v) {

        int voteMark = -1;
        switch (v.getId()) {

            case R.id.redVote: {
                showToast("redVote btn clicked.");
                voteMark = 0;
                break;
            }

            case R.id.yellowVote: {
                showToast("yellowVote btn clicked.");
                voteMark = 1;
                break;
            }

            case R.id.greenVote: {
                showToast("greenVote btn clicked.");
                voteMark = 2;
                break;
            }

        }
        vote.setType(voteMark);

        App.getService().sendVote(new VotesContainer(vote), new Callback<VotesContainer>() {
            @Override
            public void success(VotesContainer votesContainer, Response response) {
                //todo
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

}
