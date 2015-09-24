package com.celerysoft.ittakestwo.modules;

/**
 * Common card module.
 */
public class Card {

    private String mContents;
    public String getContents() {
        return mContents;
    }
    public void setContents(String mContents) {
        this.mContents = mContents;
    }

    private boolean mChosen;
    public boolean isChosen() {
        return mChosen;
    }
    public void setChosen(boolean mChosen) {
        this.mChosen = mChosen;
    }

    private boolean mMatched;
    public boolean isMatched() {
        return mMatched;
    }
    public void setMatched(boolean mMatched) {
        this.mMatched = mMatched;
    }

    public Card() {
        super();
    }

    public int match(Card[] cards) {
        int score = 0;

        for (Card card : cards) {
            if (this.mContents.equals(card.getContents())) {
                score = 1;
            }
        }

        return score;
    }
}
