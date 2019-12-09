package com.matthewcannefax.texasginscoretracker.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private String mName;
    private ArrayList<Integer> mRoundScores;

    public Player(String name){
        mName = name;
        mRoundScores = new ArrayList<>();
    }

    public void setName(String name){
        mName = name;
    }
    public String getName(){
        return mName;
    }


    public void addRoundScore(int roundScore){
        mRoundScores.add(roundScore);
    }


    public int getTotalScore(){
        int totalScore = 0;
        for (int n :
                mRoundScores) {
            totalScore += n;
        }
        return totalScore;
    }
}
