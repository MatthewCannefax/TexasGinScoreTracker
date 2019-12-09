package com.matthewcannefax.texasginscoretracker.model;

public enum WildRound {
    ACE("Ace", 1),
    KING("King", 2),
    QUEEN("Queen", 3),
    JACK("Jack", 4),
    TEN("10", 5),
    NINE("9", 6),
    EIGHT("8", 7),
    SEVEN("7", 8),
    SIX("6", 9),
    FIVE("5", 10),
    FOUR("4", 11),
    THREE("3", 12),
    TWO("2", 13);


    private WildRound(String name, int roundNumber){
        mName = name;
        mRoundNumber = roundNumber;
    }

    public int getRoundNumber(){
        return mRoundNumber;
    }

    private String mName;
    private int mRoundNumber;

    @Override
    public String toString() {
        return mName;
    }

    public static WildRound getRound(int roundNumber){
        switch (roundNumber){
            case 1:
                return ACE;
            case 2:
                return KING;
            case 3:
                return QUEEN;
            case 4:
                return JACK;
            case 5:
                return TEN;
            case 6:
                return NINE;
            case 7:
                return EIGHT;
            case 8:
                return SEVEN;
            case 9:
                return SIX;
            case 10:
                return FIVE;
            case 11:
                return FOUR;
            case 12:
                return THREE;
            case 13:
                return TWO;
                default:
                    return ACE;
        }
    }
}
