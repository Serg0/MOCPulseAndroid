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

    public static final String EXTRA_VOTE = "vote";

    @InjectView(R.id.text)
    TextView text;
    @InjectView(R.id.green)
    View green;
    @InjectView(R.id.yellow)
    View yellow;
    @InjectView(R.id.red)
    View red;

    private Vote vote;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        ButterKnife.inject(this);
        vote = getIntent().getParcelableExtra(EXTRA_VOTE);
        if (vote == null) {
            Log.e(App.TAG, "vote == null");
        } else {
            Log.d(App.TAG, "vote: "+vote.toString());
            text.setText(vote.getName());
        }
    }

    @OnClick({R.id.green, R.id.yellow, R.id.red})
    public void onBtnClicked() {
        showToast("Vote btn clicked.");
    }

}
