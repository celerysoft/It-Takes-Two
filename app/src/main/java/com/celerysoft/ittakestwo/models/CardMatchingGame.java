package com.celerysoft.ittakestwo.models;

import android.util.Log;

import java.util.ArrayList;

/**
 * Card matching game module.
 */
public class CardMatchingGame {
    private final String LOG_TAG = this.getClass().getSimpleName();

    private int mGameState;
    public int getGmaeState() {
        return mGameState;
    }
    public static final int GAME_STATE_UNSTART = -1;
    public static final int GAME_STATE_START = 0;
    public static final int GAME_STATE_PAUSE = 1;
    public static final int GAME_STATE_FINISH = 2;

    private final int MATCH_BOUNS = 4;
    private final int MISMATCH_PENALTY = 2;
    private final int COST_TO_CHOOSE = 1;

    private int mScore;
    public int getScore() {
        return mScore;
    }

    private Player mCurrentPlayer;
    private ArrayList<Player> mPlayers = new ArrayList<>();

    private Timer mTimer = new Timer();
    public Timer getTimer() {
        return mTimer;
    }

    private ArrayList<PlayingCard> mCards = new ArrayList<>();
    public ArrayList<PlayingCard> getCards() {
        return mCards;
    }

    public CardMatchingGame(int cardCount, PlayingDeck usingDeck) {
        reset();

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
        if (mGameState == GAME_STATE_UNSTART) {
            start();
        } else if (mGameState == GAME_STATE_PAUSE || mGameState == GAME_STATE_FINISH) {
            return;
        }

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

    /**
     * restart the game with the same cards.
     */
    public void restart() {
        // reset game state
        reset();

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

    /**
     * reset the game state.
     */
    private void reset() {
        mGameState = GAME_STATE_UNSTART;
        mScore = 0;
        mTimer.reset();
        if (mCards != null) {
            for (PlayingCard card : mCards) {
                card.setMatched(false);
                card.setChosen(false);
            }
        }
    }

    /**
     * start the game.
     */
    private void start() {
        mGameState = GAME_STATE_START;
        mTimer.start();
    }

    /**
     * pause the game.
     */
    public void pause() {
        mGameState = GAME_STATE_PAUSE;
        mTimer.pause();
    }

    /**
     * finish the game.
     */
    public void finish() {
        mGameState = GAME_STATE_FINISH;
        mTimer.stop();
    }
}
