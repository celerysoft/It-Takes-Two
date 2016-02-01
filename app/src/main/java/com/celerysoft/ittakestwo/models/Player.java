package com.celerysoft.ittakestwo.models;

import android.content.Context;
import android.util.Log;

import com.celerysoft.ittakestwo.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Player is a user to play card matching game.
 */
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String TAG = Player.class.getSimpleName();

    public static final int DEFAULT_PLAYER_1_NAME = R.string.player_default_name_01;
    public static final int DEFAULT_PLAYER_2_NAME = R.string.player_default_name_02;
    public static final int DEFAULT_PLAYER_3_NAME = R.string.player_default_name_03;
    public static final int DEFAULT_PLAYER_4_NAME = R.string.player_default_name_04;

    private String mName;
    public void setName(String name) {
        mName = name;
    }
    public String getName() {
        return mName;
    }

    private boolean mFlippedAllCards;
    public boolean isFlippedAllCards() {
        return mFlippedAllCards;
    }
    public void setFlippedAllCards(boolean isFlippedAllCards) {
        mFlippedAllCards = isFlippedAllCards;
    }

    private int mScore;
    public void setScore(int score) {
        mScore = score;
    }
    public int getScore() {
        return mScore;
    }

    private float mPlayingDuration;
    public void setPlayingDuration(float playingDuration) {
        mPlayingDuration = playingDuration;
    }
    public float getPlayingDuration() {
        return mPlayingDuration;
    }

    private int mRankScore;
    public int getRankScore() {
        mRankScore = calculateRankScore();

        return mRankScore;
    }

    private int calculateRankScore() {
        float SCORE_FIX_FACTOR;
        if (mFlippedAllCards) {
            SCORE_FIX_FACTOR = 1f;
        } else {
            SCORE_FIX_FACTOR = 0.75f;
        }
        final float DURATION_FIX_FACTOR = 0.1f;

        float score = (float) mScore;
        float duration =  mPlayingDuration;

        double rankScore = Math.pow(score * SCORE_FIX_FACTOR, 2) / (duration * DURATION_FIX_FACTOR);

        return mScore > 0 ? (int) rankScore : (int) -rankScore;
    }

    public Player() {}
    public Player(String name) {
        mName = name;
    }

    public static ArrayList<Player> createPlayers(Context context, int playerCount) {
        ArrayList<Player> players = new ArrayList<>();

        for (int i = 0; i < playerCount; ++i) {
            String name = "undefine";
            switch (i) {
                case 0:
                    name = context.getString(Player.DEFAULT_PLAYER_1_NAME);
                    break;
                case 1:
                    name = context.getString(Player.DEFAULT_PLAYER_2_NAME);
                    break;
                case 2:
                    name = context.getString(Player.DEFAULT_PLAYER_3_NAME);
                    break;
                case 3:
                    name = context.getString(Player.DEFAULT_PLAYER_4_NAME);
                    break;
                default:
                    Log.w(TAG, "naming player error");
                    break;
            }
            Player player = new Player(name);
            players.add(player);
        }

        return players;
    }

    public static ArrayList<Player> sortPlayersByRankScore(ArrayList<Player> players) {
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player lhs, Player rhs) {
                if (lhs.getRankScore() < rhs.getRankScore()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        return players;
    }

}
