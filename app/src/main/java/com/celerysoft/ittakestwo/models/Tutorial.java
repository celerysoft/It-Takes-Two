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
        process0.talkToPlayer = mContext.getString(R.string.tutorial_last_process_0_text);
        process0.talkToPlayerWhenFlipWrongCard = mContext.getString(R.string.tutorial_last_process_0_wrong_text);
        process0.cardIndexToTriggerNextProcess = 0;
        process0.state.cardState0 = CardState.ENABLE;
        process0.state.cardState1 = CardState.ENABLE;
        process0.state.cardState2 = CardState.ENABLE;
        process0.state.cardState3 = CardState.ENABLE;
        process0.state.talkToPlayer = process0.talkToPlayer;
        mProcesses.add(process0);

        Process process1 = new Process();
        process1.talkToPlayer = mContext.getString(R.string.tutorial_last_process_1_text);
        process1.talkToPlayerWhenFlipWrongCard = mContext.getString(R.string.tutorial_last_process_1_wrong_text);
        process1.cardIndexToTriggerNextProcess = 0;
        process1.state.cardState0 = CardState.ENABLE;
        process1.state.cardState1 = CardState.ENABLE;
        process1.state.cardState2 = CardState.ENABLE;
        process1.state.cardState3 = CardState.ENABLE;
        process1.state.talkToPlayer = process1.talkToPlayer;
        mProcesses.add(process1);

        Process process2 = new Process();
        process2.talkToPlayer = mContext.getString(R.string.tutorial_last_process_2_text);
        process2.talkToPlayerWhenFlipWrongCard = mContext.getString(R.string.tutorial_last_process_2_wrong_text);
        process2.cardIndexToTriggerNextProcess = 1;
        process2.state.cardState0 = CardState.ENABLE;
        process2.state.cardState1 = CardState.ENABLE;
        process2.state.cardState2 = CardState.ENABLE;
        process2.state.cardState3 = CardState.ENABLE;
        process2.state.talkToPlayer = process2.talkToPlayer;
        mProcesses.add(process2);

        Process process3 = new Process();
        process3.talkToPlayer = mContext.getString(R.string.tutorial_last_process_3_text);
        process3.talkToPlayerWhenFlipWrongCard = mContext.getString(R.string.tutorial_last_process_3_wrong_text);
        process3.cardIndexToTriggerNextProcess = 0;
        process3.state.cardState0 = CardState.ENABLE;
        process3.state.cardState1 = CardState.ENABLE;
        process3.state.cardState2 = CardState.ENABLE;
        process3.state.cardState3 = CardState.ENABLE;
        process3.state.talkToPlayer = process3.talkToPlayer;
        mProcesses.add(process3);

        Process process4 = new Process();
        process4.talkToPlayer = mContext.getString(R.string.tutorial_last_process_4_text);
        process4.talkToPlayerWhenFlipWrongCard = mContext.getString(R.string.tutorial_last_process_4_wrong_text);
        process4.cardIndexToTriggerNextProcess = 2;
        process4.state.cardState0 = CardState.DISABLE;
        process4.state.cardState1 = CardState.DISABLE;
        process4.state.cardState2 = CardState.ENABLE;
        process4.state.cardState3 = CardState.ENABLE;
        process4.state.talkToPlayer = process4.talkToPlayer;
        mProcesses.add(process4);

        Process process5 = new Process();
        process5.talkToPlayer = mContext.getString(R.string.tutorial_last_process_5_text);
        process5.talkToPlayerWhenFlipWrongCard = mContext.getString(R.string.tutorial_last_process_5_wrong_text);
        process5.cardIndexToTriggerNextProcess = 2;
        process5.state.cardState0 = CardState.DISABLE;
        process5.state.cardState1 = CardState.DISABLE;
        process5.state.cardState2 = CardState.ENABLE;
        process5.state.cardState3 = CardState.ENABLE;
        process5.state.talkToPlayer = process5.talkToPlayer;
        mProcesses.add(process5);

        Process process6 = new Process();
        process6.talkToPlayer = mContext.getString(R.string.tutorial_last_process_6_text);
        process6.talkToPlayerWhenFlipWrongCard = mContext.getString(R.string.tutorial_last_process_6_wrong_text);
        process6.cardIndexToTriggerNextProcess = 3;
        process6.state.cardState0 = CardState.DISABLE;
        process6.state.cardState1 = CardState.DISABLE;
        process6.state.cardState2 = CardState.ENABLE;
        process6.state.cardState3 = CardState.ENABLE;
        process6.state.talkToPlayer = process6.talkToPlayer;
        mProcesses.add(process6);

        Process process7 = new Process();
        process7.talkToPlayer = mContext.getString(R.string.tutorial_last_process_7_text);
        process7.talkToPlayerWhenFlipWrongCard = mContext.getString(R.string.tutorial_last_process_7_wrong_text);
        process7.cardIndexToTriggerNextProcess = 2;
        process7.state.cardState0 = CardState.DISABLE;
        process7.state.cardState1 = CardState.DISABLE;
        process7.state.cardState2 = CardState.ENABLE;
        process7.state.cardState3 = CardState.ENABLE;
        process7.state.talkToPlayer = process7.talkToPlayer;
        mProcesses.add(process7);

        Process process8 = new Process();
        process8.talkToPlayer = mContext.getString(R.string.tutorial_last_process_8_text);
        process8.talkToPlayerWhenFlipWrongCard = mContext.getString(R.string.tutorial_last_process_8_wrong_text);
        process8.cardIndexToTriggerNextProcess = -1;
        process8.state.cardState0 = CardState.ENABLE;
        process8.state.cardState1 = CardState.ENABLE;
        process8.state.cardState2 = CardState.ENABLE;
        process8.state.cardState3 = CardState.ENABLE;
        process8.state.talkToPlayer = process8.talkToPlayer;
        mProcesses.add(process8);
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
            if (cardIndex == cardIndexToTriggerNextProcess || cardIndexToTriggerNextProcess == -1) {
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
