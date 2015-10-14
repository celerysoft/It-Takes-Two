package com.celerysoft.ittakestwo.models;

/**
 * Player is a user to play card matching game.
 */
public class Player {

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

}
