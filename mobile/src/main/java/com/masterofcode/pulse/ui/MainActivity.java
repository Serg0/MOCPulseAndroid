package com.masterofcode.pulse.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.masterofcode.pulse.App;
import com.masterofcode.pulse.R;
import com.masterofcode.pulse.models.Vote;
import com.masterofcode.pulse.models.containers.VotesContainer;
import com.masterofcode.pulse.network.gcm.NotificationHelper;

import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends BaseActivity {

    @InjectView(R.id.tvText)
    TextView mTvText;

    @OnClick(R.id.tvText)
    void onTextClick(){
        fetchVotes();
    }

    private void fetchVotes() {

        App.getService().getVotes(new Callback<VotesContainer>() {
            @Override
            public void success(VotesContainer vt, Response response) {
                mTvText.setText(getVotesString(vt.getVotes()));
            }

            @Override
            public void failure(RetrofitError error) {
                showToast("Fail");
            }
        });

    }

    private String getVotesString(List<Vote> votes) {

        String result = "{}";
        if(votes != null){
            StringBuilder sb = new StringBuilder();
            for (Vote vote:votes){
                sb.append(vote.toString());
            }

            result = sb.toString();
        }
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        startActivity(new Intent(this, LoginActivity.class));
//        startActivity(new Intent(this, VotesActivity.class));

    }

    @Override
    protected void onResume() {
        super.onResume();
        NotificationHelper.cancelAll();
    }

    private void testNotifications() {
        final Vote vote = new Vote();
        final int id = new Random().nextInt();
        vote.setId(id);
        vote.setName("Some name " + id);
        showToast(vote.getName());

        NotificationHelper.showNotification(vote);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
