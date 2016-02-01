package com.celerysoft.ittakestwo.models;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Card matching game module.
 */
public class CardMatchingGame implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String TAG = "CardMatchingGame";

    private State mGameState;
    public State getGmaeState() {
        return mGameState;
    }
    public enum State {
        GAME_STATE_UNSTART,
        GAME_STATE_START,
        GAME_STATE_PAUSE,
        GAME_STATE_FINISH
    }

    private final int MATCH_BONUS = 4;
    private final int MISMATCH_PENALTY = 2;
    private final int COST_TO_CHOOSE = 1;

    private int mScore;
    public int getScore() {
        return mScore;
    }

    private Player mCurrentPlayer;
    public Player getCurrentPlayer() {
        return mCurrentPlayer;
    }

    private ArrayList<Player> mPlayers = new ArrayList<>();
    public ArrayList<Player> getPlayers() {
        return mPlayers;
    }

    public void setPlayers(ArrayList<Player> players) {
        mPlayers = players;
        if (players.size() > 0) {
            mCurrentPlayer = players.get(0);
        } else {
            Log.w(TAG, "the size of player collection is not more than 0.");
        }
    }

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
                    Log.e(TAG, "Card draw from PlayingDeck is not an instance of PlayingCard? Fucking kidding me!");
                }
            } else {
                Log.w(TAG, "Card count of game more than card count of using deck.");
            }
        }
    }

    public CardMatchingGame(PlayingDeck usingDeck) {
        if (mCards.size() > 0) {
            mCards = new ArrayList<>();
        }

        int cardCount = usingDeck.getCardCount();

        for (int i = 0; i < cardCount; ++i) {
            Card card = usingDeck.drawCard();
            mCards.add((PlayingCard) card);
        }
    }

    public void chooseCardAtIndex(int index) {
        if (mGameState == State.GAME_STATE_UNSTART) {
            start();
        } else if (mGameState == State.GAME_STATE_PAUSE || mGameState == State.GAME_STATE_FINISH) {
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
                                mScore += matchScore * MATCH_BONUS;
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
            Log.e(TAG, "shuffle cards error.");
        }
    }

    /**
     * reset the game state.
     */
    private void reset() {
        mGameState = State.GAME_STATE_UNSTART;
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
        mGameState = State.GAME_STATE_START;
        mTimer.start();
    }

    /**
     * pause the game.
     */
    public void pause() {
        mGameState = State.GAME_STATE_PAUSE;
        mTimer.pause();
    }

    /**
     * finish the game.
     */
    public void finish() {
        mGameState = State.GAME_STATE_FINISH;
        mTimer.stop();
    }

    public boolean isLastPlayer() {
        if (mCurrentPlayer != null) {
            if (mPlayers != null && mPlayers.size() > 0) {
                return (mPlayers.indexOf(mCurrentPlayer) == mPlayers.size() - 1);
            }
        }

        return true;
    }

    public void turnToNextPlayer() {
        if (!isLastPlayer()) {
            int currentPlayerIndex = mPlayers.indexOf(mCurrentPlayer);
            mCurrentPlayer = mPlayers.get(currentPlayerIndex + 1);
        }
    }
}
