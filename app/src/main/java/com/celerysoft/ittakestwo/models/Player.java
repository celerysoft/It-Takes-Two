package com.celerysoft.ittakestwo.models;

import android.content.Context;
import android.util.Log;

import com.celerysoft.ittakestwo.R;

/**
 * Player is a user to play card matching game.
 */
public class Player {
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

    private float mRankScore;
    public float getRankScore() {
        return mRankScore;
    }

    public Player() {}
    public Player(String name) {
        mName = name;
    }

    private void calculateRankScore() {
        // TODO make a algorithm for calculating rank score, it is about score and playing Duration.
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

}
