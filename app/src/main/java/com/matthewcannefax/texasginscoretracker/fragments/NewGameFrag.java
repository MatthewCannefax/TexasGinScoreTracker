package com.matthewcannefax.texasginscoretracker.fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.matthewcannefax.texasginscoretracker.MainActivity;
import com.matthewcannefax.texasginscoretracker.R;
import com.matthewcannefax.texasginscoretracker.model.Player;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewGameFrag extends Fragment {

    private TextView tvTitleOrCongrats;
    private Button btStartNewGame;

    private boolean isFreshGame = true;

    private String mWinnerName;

    private FragmentManager mFragmentManager;

    public NewGameFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments().containsKey(MainActivity.WINNER_NAME_KEY)){
            isFreshGame = false;
            mWinnerName = (String)getArguments().get(MainActivity.WINNER_NAME_KEY);

        }

        MainActivity.mCurrentFragment = MainActivity.NEW_GAME_FRAG_KEY;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_new_game, container, false);

        tvTitleOrCongrats = rootView.findViewById(R.id.title_or_congrats_textview);
        btStartNewGame = rootView.findViewById(R.id.new_game_button);

        if(!isFreshGame){
            tvTitleOrCongrats.setText(rootView.getContext().getString(R.string.congrats, mWinnerName));
        }

        btStartNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentManager = getFragmentManager();
                AddPlayersFrag fragment = AddPlayersFrag.newInstance();
                mFragmentManager.beginTransaction()
                        .replace(R.id.main_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    //for starting a fresh game
    public static NewGameFrag newInstance(){
        NewGameFrag fragment = new NewGameFrag();
        Bundle arguments = new Bundle();
        fragment.setArguments(arguments);
        return fragment;
    }

    //for starting a new game after finishing a game
    public static NewGameFrag newInstance(Player winner){
        NewGameFrag fragment = new NewGameFrag();
        Bundle arguments = new Bundle();
        arguments.putString(MainActivity.WINNER_NAME_KEY, winner.getName());
        fragment.setArguments(arguments);
        return fragment;
    }
}
