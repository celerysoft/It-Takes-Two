package com.celerysoft.ittakestwo.models;

import android.content.Context;
import android.util.Log;

import com.celerysoft.ittakestwo.R;

import java.io.Serializable;
import java.util.Arrays;
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
        final float FIX_FACTOR = 10f;

        float score = (float) mScore;
        float duration =  mPlayingDuration;

        double rankScore = Math.pow(score, 2) / (duration / FIX_FACTOR);

        return mScore > 0 ? (int) rankScore : (int) -rankScore;
    }

    public Player() {}
    public Player(String name) {
        mName = name;
    }

    public static Player[] createPlayers(Context context, int playerCount) {
        Player[] players = new Player[playerCount];

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
            players[i] = player;
        }

        return players;
    }

    public static Player[] sortPlayersByRankScore(Player[] players) {
        Arrays.sort(players, new Comparator<Player>() {
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
