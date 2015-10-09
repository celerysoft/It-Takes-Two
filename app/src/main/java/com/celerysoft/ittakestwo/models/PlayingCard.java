package com.celerysoft.ittakestwo.models;

/**
 * Playing card module.
 */
public class PlayingCard extends Card {

    private String mSuit;
    public String getSuit() {
        return mSuit != null ? mSuit : "?";
    }
    public void setSuit(String suit) {
        for (String validSuit : validSuits()) {
            if (validSuit.equals(suit)) {
                this.mSuit = suit;
            }
        }
    }

    private int mRank;
    public void setRank(int rank) {
        if (rank <= this.maxRank()) {
            this.mRank = rank;
        }
    }
    public int getRank() {
        return mRank;
    }

    @Override
    public String getContents() {
        return mSuit + rankString()[mRank];
    }

    public PlayingCard() {
        super();
    }

    public static String[] validSuits() {
        return new String[]{"♥", "♦", "♠", "♣"};
    }

    private static String[] rankString() {
        return new String[]{"?", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    }

    public static int maxRank() {
        return rankString().length - 1;
    }

    @Override
    public int match(Card[] cards) {
        int score = 0;
        for (Card otherCard : cards) {
            if(otherCard instanceof PlayingCard) {
                if (this.mRank == ((PlayingCard) otherCard).getRank()) {
                    score = 4;
                } else if(this.mSuit == ((PlayingCard) otherCard).getSuit()) {
                    score = 1;
                }
            }

        }
        return score;
    }

}
