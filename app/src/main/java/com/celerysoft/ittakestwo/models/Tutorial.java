package com.celerysoft.ittakestwo.models;

import android.content.Context;

import com.celerysoft.ittakestwo.R;

import java.util.ArrayList;

/**
 * Created by Celery on 16/1/27.
 *
 */
public class Tutorial {

    private ArrayList<Process> mProcesses = new ArrayList<>();

    private Context mContext;

    private CardMatchingGame mGame;

    private int mCurrentProcessIndex = 0;
    private Process mProcess;

    public Tutorial(Context context, CardMatchingGame game) {
        mContext = context;

        mGame = game;

        initData();
    }

    private void initData() {
        Process process0 = new Process();
        process0.talkToPlayer = mContext.getString(R.string.social_sharing_dialog_title);
        process0.talkToPlayerWhenFlipWrongCard = mContext.getString(R.string.social_sharing_send_to_qq_friend);
        process0.cardIndexToTriggerNextProcess = 0;
        process0.state.cardState0 = CardState.ENABLE;
        process0.state.cardState1 = CardState.ENABLE;
        process0.state.cardState2 = CardState.ENABLE;
        process0.state.cardState3 = CardState.ENABLE;
        process0.state.talkToPlayer = process0.talkToPlayer;
        mProcesses.add(process0);


        Process process1 = new Process();
        process1.talkToPlayer = mContext.getString(R.string.social_sharing_send_to_wechat_friend);
        process1.talkToPlayerWhenFlipWrongCard = mContext.getString(R.string.social_sharing_send_to_qzone);
        process1.cardIndexToTriggerNextProcess = 0;
        process1.state.cardState0 = CardState.ENABLE;
        process1.state.cardState1 = CardState.ENABLE;
        process1.state.cardState2 = CardState.ENABLE;
        process1.state.cardState3 = CardState.ENABLE;
        process1.state.talkToPlayer = process1.talkToPlayer;
        mProcesses.add(process1);


    }

    public class Process {
        public boolean isLastProcess = false;
        private String talkToPlayerWhenFlipWrongCard;
        private String talkToPlayer;
        public int cardIndexToTriggerNextProcess;
        public State state = new State();

        public Process() {

        }

        private Process flipCard(int cardIndex) {
            if (cardIndex == cardIndexToTriggerNextProcess) {
                return turnToNextProcess();
            } else {
                state.talkToPlayer = talkToPlayerWhenFlipWrongCard;
                return this;
            }
        }
    }

    public enum CardState {
        ENABLE,
        DISABLE
    }

    public class State {
        public CardState cardState0;
        public CardState cardState1;
        public CardState cardState2;
        public CardState cardState3;

        public String talkToPlayer;
    }

    public Process startTutorial() {
        return turnToProcess(0);
    }

    public Process tryToFlipCard(int cardIndex) {
        return mProcess.flipCard(cardIndex);
    }

    private Process turnToNextProcess() {
        mCurrentProcessIndex++;
        if (mCurrentProcessIndex < mProcesses.size()) {
            mProcess = mProcesses.get(mCurrentProcessIndex);
        } else {
            mProcess = new Process();
            mProcess.isLastProcess = true;
            mProcess.talkToPlayer = mContext.getString(R.string.tutorial_last_process_text);
            mProcess.talkToPlayerWhenFlipWrongCard = mContext.getString(R.string.tutorial_last_process_wrong_text);
            mProcess.cardIndexToTriggerNextProcess = 5;
            mProcess.state.cardState0 = CardState.ENABLE;
            mProcess.state.cardState1 = CardState.ENABLE;
            mProcess.state.cardState2 = CardState.ENABLE;
            mProcess.state.cardState3 = CardState.ENABLE;
            mProcess.state.talkToPlayer = mProcess.talkToPlayer;
        }
        return mProcess;
    }

    private Process turnToProcess(int processIndex) {
        mCurrentProcessIndex = processIndex;
        mProcess = mProcesses.get(processIndex);

        return mProcess;
    }

    public static PlayingDeck createTutorialDeck() {
        String[] suits = PlayingCard.validSuits();

        PlayingCard card0 = new PlayingCard();
        card0.setRank(2);
        card0.setSuit(suits[0]);

        PlayingCard card1 = new PlayingCard();
        card1.setRank(6);
        card1.setSuit(suits[0]);

        PlayingCard card2 = new PlayingCard();
        card2.setRank(1);
        card2.setSuit(suits[1]);

        PlayingCard card3 = new PlayingCard();
        card3.setRank(1);
        card3.setSuit(suits[2]);

        PlayingDeck deck = new PlayingDeck();
        deck.addCard(card3);
        deck.addCard(card2);
        deck.addCard(card1);
        deck.addCard(card0);

        return deck;
    }
}
