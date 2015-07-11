package com.masterofcode.pulse.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.masterofcode.pulse.R;
import com.masterofcode.pulse.models.Vote;
import com.masterofcode.pulse.ui.VoteActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by sonerik on 7/4/2015.
 */
public class VotesListAdapter extends RecyclerView.Adapter<VotesListAdapter.ViewHolder> {

    private Context context;
    private List<Vote> votes;
    private LayoutInflater inflater;

    public VotesListAdapter(Context context, List<Vote> votes) {
        this.context = context;
        this.votes = votes;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.vote, parent, false));
    }

    @Override
    public int getItemCount() {
        if(votes == null){
            return 0;
        }
        return votes.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Vote v = votes.get(position);

        holder.description.setText(v.getName());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.author)
        TextView author;
        @InjectView(R.id.description)
        TextView description;
        @InjectView(R.id.date)
        TextView date;
        @InjectView(R.id.card)
        CardView card;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        @OnClick(R.id.card)
        public void onClicked() {
            Intent intent = new Intent(context, VoteActivity.class);
            intent.putExtra(Vote.class.getSimpleName(), votes.get(getAdapterPosition()));
            context.startActivity(intent);
        }
    }

}
