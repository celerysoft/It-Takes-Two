package com.celerysoft.ittakestwo.modules;

import java.util.ArrayList;

/**
 * Card matching game module.
 */
public class CardMatchingGame {

    private final int MATCH_BOUNS = 4;
    private final int MISMATCH_PENALTY = 2;
    private final int COST_TO_CHOOSE = 1;

    private int mScore;
    public int getScore() {
        return mScore;
    }

    private ArrayList<Card> mCards = new ArrayList<>();

    public CardMatchingGame(int cardCount, Deck usingDeck) {
        for (int i = 0; i < cardCount; i++) {
            Card card = usingDeck.drawRandomCard();
            if (card != null) {
                mCards.add(card);
            } else {

            }
        }
    }

    public void chooseCardAtIndex(int index) {
        Card card = this.cardAtIndex(index);
        if (card != null) {
            if (!card.isMatched()) {
                if (card.isChosen()) {
                    card.setChosen(false);
                } else {
                    for (Card otherCard : mCards) {
                        if (otherCard.isChosen() && !otherCard.isMatched()) {
                            int matchScore = card.match(new Card[]{otherCard});
                            if (matchScore > 0) {
                                mScore += matchScore * MATCH_BOUNS;
                                otherCard.setMatched(true);
                                card.setMatched(true);
                            } else {
                                mScore -= MISMATCH_PENALTY;
                                otherCard.setChosen(false);
                            }
                            break;
                        }
                    }
                    mScore -= COST_TO_CHOOSE;
                    card.setChosen(true);
                }
            }
        }
    }

    public Card cardAtIndex(int index) {
        return index < mCards.size() ? mCards.get(index) : null;
    }
}
