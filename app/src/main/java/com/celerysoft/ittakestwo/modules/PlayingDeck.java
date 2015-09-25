package com.celerysoft.ittakestwo.modules;

/**
 * Playing deck module.
 */
public class PlayingDeck extends Deck {
    public PlayingDeck () {
        super();

        for (String suit : PlayingCard.validSuits()) {
            for (int rank = 1; rank <= PlayingCard.maxRank(); ++rank) {
                PlayingCard card = new PlayingCard();
                card.setRank(rank);
                card.setSuit(suit);
            }
        }
    }
}
