package com.matthewcannefax.texasginscoretracker.fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.matthewcannefax.texasginscoretracker.MainActivity;
import com.matthewcannefax.texasginscoretracker.R;
import com.matthewcannefax.texasginscoretracker.adapters.PlayerScoreRecyclerAdapter;
import com.matthewcannefax.texasginscoretracker.model.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFrag extends Fragment {

    private TextView tvCurrentRound;
    private RecyclerView rvCurrentPlayerScores;

    private ArrayList<Player> players;

    private FragmentManager mFragmentManager;

    public GameFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        players = new ArrayList<>();
        if(getArguments().containsKey(MainActivity.PLAYER_LIST_KEY)){
            List<String> playerNames = getArguments().getStringArrayList(MainActivity.PLAYER_LIST_KEY);
            for (String name :
                    playerNames) {
                players.add(new Player(name));
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);

        tvCurrentRound = rootView.findViewById(R.id.round_textview);
        rvCurrentPlayerScores = rootView.findViewById(R.id.player_score_recyclerview);

        if(players != null && players.size() != 0){
            PlayerScoreRecyclerAdapter adapter = new PlayerScoreRecyclerAdapter(rootView.getContext(), players);
            rvCurrentPlayerScores.setAdapter(adapter);
            rvCurrentPlayerScores.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        }

        return rootView;
    }

    public static GameFrag newInstance(ArrayList<Player> newPlayers){
        ArrayList<String> playerNames = new ArrayList<>();
        for (Player p :
                newPlayers) {
            playerNames.add(p.getName());
        }

        GameFrag fragment = new GameFrag();
        Bundle arguments = new Bundle();
        arguments.putStringArrayList(MainActivity.PLAYER_LIST_KEY, playerNames);
        fragment.setArguments(arguments);
        return fragment;
    }

}
