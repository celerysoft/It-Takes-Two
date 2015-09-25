package com.celerysoft.ittakestwo.modules;

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

}
