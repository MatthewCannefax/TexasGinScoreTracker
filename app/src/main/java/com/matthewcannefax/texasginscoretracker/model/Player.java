package com.matthewcannefax.texasginscoretracker.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private String mName;
    private HashMap<WildRound, Integer> mRoundScores;
    private WildRound mCurrentRound;

    public Player(String name){
        mName = name;
    }

    public void setName(String name){
        mName = name;
    }
    public String getName(){
        return mName;
    }

    public void setRoundScore(int score){
        if(mRoundScores == null){
            mRoundScores = new HashMap<>();
            mRoundScores.put(WildRound.ACE, score);
            mCurrentRound = WildRound.ACE;
        }else if(mRoundScores.size() == 0){
            mRoundScores.put(WildRound.ACE, score);
            mCurrentRound = WildRound.ACE;
        }else {
            int currentRoundNum = mCurrentRound.getRoundNumber() + 1;
            mCurrentRound = WildRound.getRound(currentRoundNum);
            mRoundScores.put(mCurrentRound, score);
        }
    }

    public HashMap<WildRound, Integer> getRoundScores(){
        if(mRoundScores == null){
            mRoundScores = new HashMap<>();
        }

        return mRoundScores;
    }

    public int getTotalScore(){
        int total = 0;
        for (int score :
                mRoundScores.values()) {
            total += score;
        }
        return total;
    }
}
