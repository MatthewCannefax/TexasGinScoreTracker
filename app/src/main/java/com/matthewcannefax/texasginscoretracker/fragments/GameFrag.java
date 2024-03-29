package com.matthewcannefax.texasginscoretracker.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.matthewcannefax.texasginscoretracker.MainActivity;
import com.matthewcannefax.texasginscoretracker.R;
import com.matthewcannefax.texasginscoretracker.adapters.PlayerScoreRecyclerAdapter;
import com.matthewcannefax.texasginscoretracker.model.Player;
import com.matthewcannefax.texasginscoretracker.model.WildRound;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFrag extends Fragment {

    private TextView tvCurrentRound;
    private RecyclerView rvCurrentPlayerScores;
    private Button btNextRound;

    public static ArrayList<Player> players;

    private FragmentManager mFragmentManager;

    private WildRound mCurrentRound = WildRound.ACE;
    private boolean isRoundOver;

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

        MainActivity.mCurrentFragment = MainActivity.GAME_FRAG_KEY;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_game, container, false);

        tvCurrentRound = rootView.findViewById(R.id.round_textview);
        rvCurrentPlayerScores = rootView.findViewById(R.id.player_score_recyclerview);
        btNextRound = rootView.findViewById(R.id.next_round_button);

        setTvCurrentRound(rootView);
        btNextRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextRoundClick(rootView);
            }
        });

        if(players != null && players.size() != 0){
            PlayerScoreRecyclerAdapter adapter = new PlayerScoreRecyclerAdapter(rootView.getContext(), players, false);
            rvCurrentPlayerScores.setAdapter(adapter);
            rvCurrentPlayerScores.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        }

        return rootView;
    }

    private void nextRoundClick(View rootView){
        if(!isRoundOver){
            isRoundOver = true;
            btNextRound.setText(rootView.getContext().getString(R.string.next_round));
            setRvCurrentPlayerScoresAdapter(rootView, isRoundOver);
            for (int i = 0; i < players.size(); i++) {
                final int playerIndex = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(rootView.getContext())
                        .setTitle(rootView.getContext().getString(R.string.player_score, players.get(i).getName()));

                View playerScoreView = LayoutInflater.from(rootView.getContext()).inflate(R.layout.enter_score_layout,
                        (ViewGroup)rootView.findViewById(android.R.id.content), false);

                final EditText etRoundScore = playerScoreView.findViewById(R.id.player_score_edittext);

                builder.setView(playerScoreView)
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int clickI) {
                                players.get(playerIndex).addRoundScore(Integer.parseInt(etRoundScore.getText().toString()));
                            }
                        });
                builder.show();
            }
        }else {
            if (mCurrentRound != WildRound.TWO) {
                isRoundOver = false;
                mCurrentRound = WildRound.getRound(mCurrentRound.getRoundNumber() + 1);
                setTvCurrentRound(rootView);
                btNextRound.setText(rootView.getContext().getString(R.string.round_over));
                setRvCurrentPlayerScoresAdapter(rootView, isRoundOver);
            } else {
                Player winner = MainActivity.mPlayers.get(0);
                for(int position = 1; position < MainActivity.mPlayers.size(); position++){
                    Player positionPlayer = MainActivity.mPlayers.get(position);
                    if(positionPlayer.getTotalScore() > winner.getTotalScore()){
                        winner = positionPlayer;
                    }
                }

                NewGameFrag fragment = NewGameFrag.newInstance(winner);
                MainActivity.mPlayers = new ArrayList<>();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.main_frame, fragment)
                        .addToBackStack(null)
                        .commit();
                }

            }
        }


    private void setRvCurrentPlayerScoresAdapter(View rootView, boolean editTextsEnabled){
        PlayerScoreRecyclerAdapter adapter = new PlayerScoreRecyclerAdapter(rootView.getContext(), players, editTextsEnabled);
        rvCurrentPlayerScores.setAdapter(adapter);
        rvCurrentPlayerScores.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
    }

    private void setTvCurrentRound(View rootView){
        tvCurrentRound.setText(rootView.getContext().getString(R.string.current_round_string, mCurrentRound));
    }

    public static GameFrag newInstance(){
        ArrayList<String> playerNames = new ArrayList<>();
        for (Player p :
                MainActivity.mPlayers) {
            playerNames.add(p.getName());
        }

        GameFrag fragment = new GameFrag();
        Bundle arguments = new Bundle();
        arguments.putStringArrayList(MainActivity.PLAYER_LIST_KEY, playerNames);
        fragment.setArguments(arguments);
        return fragment;
    }

}
