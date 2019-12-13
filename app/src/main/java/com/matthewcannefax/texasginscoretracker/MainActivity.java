package com.matthewcannefax.texasginscoretracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;

import com.matthewcannefax.texasginscoretracker.fragments.AddPlayersFrag;
import com.matthewcannefax.texasginscoretracker.fragments.GameFrag;
import com.matthewcannefax.texasginscoretracker.fragments.NewGameFrag;
import com.matthewcannefax.texasginscoretracker.model.Player;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String WINNER_NAME_KEY = "winner_name_key";
    public static final String REPEAT_PLAYERS_KEY = "repeat_players";
    public static final String PLAYER_LIST_KEY = "player_list_key";

    public static final int GAME_FRAG_KEY = 2;
    public static final int ADD_PLAYERS_FRAG_KEY = 1;
    public static final int NEW_GAME_FRAG_KEY = 0;

    public static int mCurrentFragment = NEW_GAME_FRAG_KEY;

    public static ArrayList<Player> mPlayers;

    private FragmentManager mFragmentManager;

    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        if(mPlayers == null){
            mPlayers = new ArrayList<>();
        }

        mFragmentManager = getSupportFragmentManager();

        Fragment fragment = new Fragment();

        switch (mCurrentFragment){
            case GAME_FRAG_KEY:
                fragment = GameFrag.newInstance();
                break;
            case ADD_PLAYERS_FRAG_KEY:
                fragment = AddPlayersFrag.newInstance();
                break;
                default:
                    fragment = NewGameFrag.newInstance();
                    break;
        }


        mFragmentManager.beginTransaction()
                .replace(R.id.main_frame, fragment)
                .addToBackStack(null)
                .commit();
    }
}
