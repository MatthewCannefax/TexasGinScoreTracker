package com.matthewcannefax.texasginscoretracker.fragments;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.matthewcannefax.texasginscoretracker.MainActivity;
import com.matthewcannefax.texasginscoretracker.R;
import com.matthewcannefax.texasginscoretracker.adapters.AddedPlayersRecyclerAdapter;
import com.matthewcannefax.texasginscoretracker.database.DataSource;
import com.matthewcannefax.texasginscoretracker.model.Player;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPlayersFrag extends Fragment {

    private RecyclerView rvCurrentPlayers;
//    private EditText etNewPlayer;
    private AutoCompleteTextView etNewPlayer;

    private Button btAdd;
    private Button btStart;

    private DataSource mDataSource;

//    private ArrayList<Player> players;

    private FragmentManager fragmentManager;

    public AddPlayersFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        players = new ArrayList<>();
        MainActivity.mCurrentFragment = MainActivity.ADD_PLAYERS_FRAG_KEY;
        mDataSource = new DataSource(MainActivity.mContext);

    }

    @Override
    public void onResume() {
        super.onResume();
        if(MainActivity.mPlayers.size() != 0){
            AddedPlayersRecyclerAdapter adapter = new AddedPlayersRecyclerAdapter(MainActivity.mContext, MainActivity.mPlayers);
            rvCurrentPlayers.setAdapter(adapter);
            rvCurrentPlayers.setLayoutManager(new LinearLayoutManager(MainActivity.mContext));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_add_players, container, false);

        rvCurrentPlayers = rootView.findViewById(R.id.added_players_recyclerview);
        etNewPlayer = rootView.findViewById(R.id.new_player_edittext);
        btAdd = rootView.findViewById(R.id.add_player_button);
        btStart = rootView.findViewById(R.id.start_game_button);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etNewPlayer.getText().equals("") && etNewPlayer.getText() != null){
                    String name = etNewPlayer.getText().toString();
                    MainActivity.mPlayers.add(new Player(name));
                    mDataSource.addNameToDB(name);
                    etNewPlayer.setText("");
                    AddedPlayersRecyclerAdapter adapter = new AddedPlayersRecyclerAdapter(rootView.getContext(), MainActivity.mPlayers);
                    rvCurrentPlayers.setAdapter(adapter);
                    rvCurrentPlayers.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

                }
            }
        });

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameFrag fragment = GameFrag.newInstance();
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.main_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        etNewPlayer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String name = mDataSource.getSpecificName(charSequence);

                if (!name.equals("")) {
                    String[] names = {name};
                    ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(MainActivity.mContext, android.R.layout.simple_list_item_1, names);
                    etNewPlayer.setAdapter(stringArrayAdapter);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return rootView;
    }

    public static AddPlayersFrag newInstance(){
        return new AddPlayersFrag();
    }

    public static AddPlayersFrag newInstance(ArrayList<String> repeatPlayers){
        AddPlayersFrag fragment = new AddPlayersFrag();
        Bundle arguments = new Bundle();
        arguments.putStringArrayList(MainActivity.REPEAT_PLAYERS_KEY, repeatPlayers);
        fragment.setArguments(arguments);
        return fragment;
    }

}
