package com.celerysoft.ittakestwo.models;

import android.util.Log;

import java.util.ArrayList;

/**
 * Card matching game module.
 */
public class CardMatchingGame {
    private final String LOG_TAG = this.getClass().getSimpleName();

    private final int MATCH_BOUNS = 4;
    private final int MISMATCH_PENALTY = 2;
    private final int COST_TO_CHOOSE = 1;

    private int mScore;
    public int getScore() {
        return mScore;
    }

    private ArrayList<PlayingCard> mCards = new ArrayList<>();
    public ArrayList<PlayingCard> getCards() {
        return mCards;
    }

    public CardMatchingGame(int cardCount, PlayingDeck usingDeck) {
        for (int i = 0; i < cardCount; ++i) {
            Card card = usingDeck.drawRandomCard();
            if (card != null) {
                if (card instanceof PlayingCard) {
                    mCards.add((PlayingCard) card);
                } else {
                    Log.e(LOG_TAG, "Card draw from PlayingDeck is not an instance of PlayingCard? Fucking kidding me!");
                }
            } else {
                Log.w(LOG_TAG, "Card count of game more than card count of using deck.");
            }
        }
    }

    public CardMatchingGame(ArrayList<PlayingCard> usingCards, int score) {
        mCards = usingCards;
        mScore = score;
    }

    public void chooseCardAtIndex(int index) {
        PlayingCard card = this.cardAtIndex(index);
        if (card != null) {
            if (!card.isMatched()) {
                if (card.isChosen()) {
                    card.setChosen(false);
                } else {
                    for (PlayingCard otherCard : mCards) {
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

    public PlayingCard cardAtIndex(int index) {
        return index < mCards.size() ? mCards.get(index) : null;
    }

    public void restart() {
        // reset game state
        mScore = 0;
        for (PlayingCard card : mCards) {
            card.setMatched(false);
            card.setChosen(false);
        }

        // shuffle cards
        ArrayList<PlayingCard> cards = new ArrayList<>();
        int cardCount = mCards.size();
        for (int i = 0; i < cardCount; ++i) {
            PlayingCard card = mCards.remove((int) (Math.random() * mCards.size()));
            cards.add(card);
        }
        if (mCards.isEmpty()) {
            mCards = cards;
        } else {
            Log.e(LOG_TAG, "shuffle cards error.");
        }
    }
}
