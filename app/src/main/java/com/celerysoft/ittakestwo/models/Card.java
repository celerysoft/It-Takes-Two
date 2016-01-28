package com.celerysoft.ittakestwo.models;

import java.io.Serializable;

/**
 * Common card module.
 */
public class Card implements Serializable {
    private static final long serialVersionUID = 1L;

    private int width;
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    private int height;
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

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
