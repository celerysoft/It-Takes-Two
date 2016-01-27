package com.celerysoft.ittakestwo.models;

import java.util.ArrayList;

/**
 * Common deck module.
 */
public class Deck {

    private ArrayList<Card> mCards = new ArrayList<>();

    public Deck() {

    }

    public int getCardCount() {
        return mCards.size();
    }

    public void addCard(Card card, boolean atTop) {
        if (atTop) {
            mCards.add(0, card);
        } else {
            mCards.add(card);
        }
    }

    public void addCard(Card card) {
        addCard(card, false);
    }

    public Card drawCard() {
        Card card = null;

        if (this.mCards.size() > 0) {
            card = mCards.remove(mCards.size() - 1);
        }

        return card;
    }

    public Card drawRandomCard() {
        Card randomCard = null;

        if (this.mCards.size() > 0) {
            int index = (int) (Math.random() * this.mCards.size());
            randomCard = mCards.remove(index);
        }

        return randomCard;
    }
}
