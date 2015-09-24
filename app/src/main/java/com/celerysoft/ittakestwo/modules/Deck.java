package com.celerysoft.ittakestwo.modules;

import java.util.ArrayList;

/**
 * Common deck module.
 */
public class Deck {

    private ArrayList<Card> cards = new ArrayList<>();

    public Deck() {

    }

    public void addCard(Card card, boolean atTop) {
        if (atTop) {
            this.cards.add(0, card);
        } else {
            this.cards.add(card);
        }
    }

    public void addCard(Card card) {
        addCard(card, false);
    }

    public Card drawRandomCard() {
        Card randomCard = null;

        if (this.cards.size() > 0) {
            int index = (int) (Math.random() * this.cards.size());
            randomCard = this.cards.remove(index);
        }

        return randomCard;
    }
}
