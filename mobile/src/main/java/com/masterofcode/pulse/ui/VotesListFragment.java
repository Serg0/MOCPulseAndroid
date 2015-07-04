package com.masterofcode.pulse.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.masterofcode.pulse.R;
import com.masterofcode.pulse.models.Vote;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sonerik on 7/4/2015.
 */
public class VotesListFragment extends Fragment {

    public static final String VOTES_ARG = "votes";

    List<Vote> votes;

    @InjectView(R.id.recycler)
    RecyclerView recycler;

    public static void newInstance(List<Vote> votes) {
        Fragment fragment = new VotesListFragment();
        Bundle b = new Bundle();
        b.putParcelableArrayList(VOTES_ARG, (ArrayList<Vote>) votes);
        fragment.setArguments(b);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        votes = (List<Vote>) getArguments().get(VOTES_ARG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_votes, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
