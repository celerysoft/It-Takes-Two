package com.celerysoft.ittakestwo.valueobjects;

import com.celerysoft.ittakestwo.models.PlayingCard;
import com.celerysoft.ittakestwo.models.Timer;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Game state to save or load.
 */
public class GameState implements Serializable {
    private static final long serialVersionUID = -1234567890666666L;

    private int mScore;
    public int getScore() {
        return mScore;
    }

    private Timer mTimer;
    public Timer getTimer() {
        return mTimer;
    }

    private ArrayList<PlayingCard> mCards;
    public ArrayList<PlayingCard> getCards() {
        return mCards;
    }

    public GameState(ArrayList<PlayingCard> cards, int score, Timer timer) {
        mScore = score;
        mCards = cards;
        mTimer = timer;
    }
}
