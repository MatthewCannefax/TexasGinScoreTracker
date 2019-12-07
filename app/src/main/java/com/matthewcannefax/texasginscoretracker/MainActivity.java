package com.matthewcannefax.texasginscoretracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.matthewcannefax.texasginscoretracker.fragments.NewGameFrag;

public class MainActivity extends AppCompatActivity {

    public static final String WINNER_NAME_KEY = "winner_name_key";
    public static final String REPEAT_PLAYERS_KEY = "repeat_players";
    public static final String PLAYER_LIST_KEY = "player_list_key";

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();

        NewGameFrag fragment = NewGameFrag.newInstance();
        mFragmentManager.beginTransaction()
                .replace(R.id.main_frame, fragment)
                .addToBackStack(null)
                .commit();
    }
}
