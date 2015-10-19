package com.celerysoft.ittakestwo.activities;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.celerysoft.ittakestwo.R;
import com.celerysoft.ittakestwo.models.Card;
import com.celerysoft.ittakestwo.models.CardMatchingGame;
import com.celerysoft.ittakestwo.models.PlayingCard;
import com.celerysoft.ittakestwo.models.PlayingDeck;
import com.celerysoft.ittakestwo.models.Timer;

import java.util.ArrayList;

/**
 *
 */
public class PlayingCardActivity extends Activity {
    /** Log tag **/
    private final String LOG_TAG = this.getClass().getSimpleName();

    private final String GAME_STATE_CARDS = "gameStateCards";
    private final String GAME_STATE_SCORE = "gameStateScore";
    private final String GAME_STATE_DURATION = "gameStateDuration";

    private final int SCORE_MISSING = 99999;
    private final String CARDS_MISSING = "cardsMissing";

    private final int CARD_COUNT = 16;

    private boolean mIsNeededAutoAdjustForScreen = true;

    // cards
    private Button card00;
    private Button card01;
    private Button card02;
    private Button card03;
    private Button card04;
    private Button card05;
    private Button card06;
    private Button card07;
    private Button card08;
    private Button card09;
    private Button card10;
    private Button card11;
    private Button card12;
    private Button card13;
    private Button card14;
    private Button card15;
    private ArrayList<Button> cardButtons = new ArrayList<>();

    //declare widgets
    private Button mBtnCommit;
    private Button mBtnRestartGame;
    private Button mBtnShareScore;
    private TextView mTvScroe;
    private TextView mTvDuration;

    private CardMatchingGame mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_playing_card);

        setupView();
        setupListener();
        mGame = new CardMatchingGame(cardButtons.size(), new PlayingDeck());
        mIsNeededAutoAdjustForScreen = true;
    }

    /**
     * adjust cards horizontal gap and vertical gap for portrait screen
     */
    private void autoAdjustForPortraitScreen() {

        //adjust horizontal gap
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        float horizontalMargin = getResources().getDimension(R.dimen.activity_horizontal_margin);

        GridLayout.LayoutParams cardlayoutParams = (GridLayout.LayoutParams) card01.getLayoutParams();
        int cardLayoutWidth = cardlayoutParams.width;
        int cardLayoutHorizontalMargin = (int) ((screenWidth - 2 * horizontalMargin - 4 * cardLayoutWidth) / 3);

        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card02.getLayoutParams();
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card03.getLayoutParams();
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);

        //adjust vertical gap
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        float verticalMargin = getResources().getDimension(R.dimen.activity_vertical_margin);

        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int buttonHeight = mBtnRestartGame.getHeight();

        cardlayoutParams = (GridLayout.LayoutParams) card04.getLayoutParams();
        int cardLayoutHeight = cardlayoutParams.height;

        int cardLayoutVerticalMargin = (int) ((screenHeight - statusBarHeight - 3 * verticalMargin - buttonHeight - 4 * cardLayoutHeight) / 3);

        cardlayoutParams.setMargins(0, cardLayoutVerticalMargin, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card08.getLayoutParams();
        cardlayoutParams.setMargins(0, cardLayoutVerticalMargin, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card12.getLayoutParams();
        cardlayoutParams.setMargins(0, cardLayoutVerticalMargin, 0, 0);
    }

    /**
     * adjust cards horizontal gap and vertical gap for landscape screen
     */
    private void autoAdjustForLandscapeScreen() {

        //adjust horizontal gap
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        float horizontalMargin = getResources().getDimension(R.dimen.activity_horizontal_margin);

        GridLayout.LayoutParams cardlayoutParams = (GridLayout.LayoutParams) card01.getLayoutParams();
        int cardLayoutWidth = cardlayoutParams.width;
        int cardLayoutHorizontalMargin = (int) ((screenWidth - 2 * horizontalMargin - 8 * cardLayoutWidth) / 7);
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card02.getLayoutParams();
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card03.getLayoutParams();
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card04.getLayoutParams();
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card05.getLayoutParams();
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card06.getLayoutParams();
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);
        cardlayoutParams = (GridLayout.LayoutParams) card07.getLayoutParams();
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);

        //adjust vertical gap
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        float verticalMargin = getResources().getDimension(R.dimen.activity_vertical_margin);

        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int buttonHeight = mBtnRestartGame.getHeight();

        cardlayoutParams = (GridLayout.LayoutParams) card09.getLayoutParams();
        int cardLayoutHeight = cardlayoutParams.height;

        int cardLayoutVerticalMargin = (int) ((screenHeight - statusBarHeight - 3 * verticalMargin - buttonHeight - 2 * cardLayoutHeight) / 1);

        cardlayoutParams.setMargins(0, cardLayoutVerticalMargin, 0, 0);
        card09.setLayoutParams(cardlayoutParams);
    }

    /**
     * when all the widget create finished adjust gap of cards.
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            if (mIsNeededAutoAdjustForScreen) {
                if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    autoAdjustForPortraitScreen();
                } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    autoAdjustForLandscapeScreen();
                }

                mIsNeededAutoAdjustForScreen = false;
            }

        }
    }

    private void setupView() {

        card00 = (Button) findViewById(R.id.card00);
        card01 = (Button) findViewById(R.id.card01);
        card02 = (Button) findViewById(R.id.card02);
        card03 = (Button) findViewById(R.id.card03);
        card04 = (Button) findViewById(R.id.card04);
        card05 = (Button) findViewById(R.id.card05);
        card06 = (Button) findViewById(R.id.card06);
        card07 = (Button) findViewById(R.id.card07);
        card08 = (Button) findViewById(R.id.card08);
        card09 = (Button) findViewById(R.id.card09);
        card10 = (Button) findViewById(R.id.card10);
        card11 = (Button) findViewById(R.id.card11);
        card12 = (Button) findViewById(R.id.card12);
        card13 = (Button) findViewById(R.id.card13);
        card14 = (Button) findViewById(R.id.card14);
        card15 = (Button) findViewById(R.id.card15);
        cardButtons.add(card00);
        cardButtons.add(card01);
        cardButtons.add(card02);
        cardButtons.add(card03);
        cardButtons.add(card04);
        cardButtons.add(card05);
        cardButtons.add(card06);
        cardButtons.add(card07);
        cardButtons.add(card08);
        cardButtons.add(card09);
        cardButtons.add(card10);
        cardButtons.add(card11);
        cardButtons.add(card12);
        cardButtons.add(card13);
        cardButtons.add(card14);
        cardButtons.add(card15);

        mBtnCommit = (Button) findViewById(R.id.playingcard_btn_commit);
        mBtnRestartGame = (Button) findViewById(R.id.playingcard_btn_restart);
        mBtnShareScore = (Button) findViewById(R.id.playingcard_btn_share);
        mTvScroe = (TextView) findViewById(R.id.playingcard_tv_score);
        mTvDuration = (TextView) findViewById(R.id.playingcard_tv_duration);
    }

    private void setupListener() {
        card00.setOnClickListener(mOnCardClickListener);
        card01.setOnClickListener(mOnCardClickListener);
        card02.setOnClickListener(mOnCardClickListener);
        card03.setOnClickListener(mOnCardClickListener);
        card04.setOnClickListener(mOnCardClickListener);
        card05.setOnClickListener(mOnCardClickListener);
        card06.setOnClickListener(mOnCardClickListener);
        card07.setOnClickListener(mOnCardClickListener);
        card08.setOnClickListener(mOnCardClickListener);
        card09.setOnClickListener(mOnCardClickListener);
        card10.setOnClickListener(mOnCardClickListener);
        card11.setOnClickListener(mOnCardClickListener);
        card12.setOnClickListener(mOnCardClickListener);
        card13.setOnClickListener(mOnCardClickListener);
        card14.setOnClickListener(mOnCardClickListener);
        card15.setOnClickListener(mOnCardClickListener);

        mBtnCommit.setOnClickListener(mOnBtnClickListener);
        mBtnRestartGame.setOnClickListener(mOnBtnClickListener);
        mBtnShareScore.setOnClickListener(mOnBtnClickListener);

    }

    private View.OnClickListener mOnBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.playingcard_btn_restart:
                    onRestartBtnClick();
                    break;
                case R.id.playingcard_btn_share:
                    onShareScoreBtnClick();
                    break;
                case R.id.playingcard_btn_commit:
                    onCommitBtnClick();
                    break;
                default:
                    break;
            }
        }
    };

    private void onRestartBtnClick() {
        mGame = new CardMatchingGame(cardButtons.size(), new PlayingDeck());
        updateUi();
    }

    private void onShareScoreBtnClick() {
        mGame.restart();
        updateUi();
    }

    private void onCommitBtnClick() {
        mGame.finish();
    }

    /** card buttons onClickListener **/
    private View.OnClickListener mOnCardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view instanceof Button) {
                if (mGame.getGmaeState() == CardMatchingGame.GAME_STATE_UNSTART) {
                    startMeasuringTimeThread(mTimerHandler);
                }
                Button card = (Button) view;
                int chosenButtonIndex = cardButtons.indexOf(card);
                mGame.chooseCardAtIndex(chosenButtonIndex);
                updateUi();
            }
        }
    };

    /** update UI when touch the cards **/
    private void updateUi() {
        for (Button cardButton : cardButtons) {
            int cardButtonIndex = cardButtons.indexOf(cardButton);
            Card card = mGame.cardAtIndex(cardButtonIndex);
            setTextForCard(cardButton, card);
            setBackGroundForCard(cardButton, card);
        }
        mTvScroe.setText(getString(R.string.playingcard_score) + mGame.getScore());
        mTvDuration.setText(mGame.getTimer().getDurationInTimeFormat());
    }

    private void setTextForCard(Button cardButton, Card card) {
        cardButton.setText(card.isChosen() ? card.getContents() : "");
        if (card instanceof PlayingCard) {
            String suit = ((PlayingCard) card).getSuit();
            if (suit.equals("♥") || suit.equals("♦")) {
                cardButton.setTextColor(Color.RED);
            } else if (suit.equals("♠") || suit.equals("♣")) {
                cardButton.setTextColor(Color.BLACK);
            }
        }

    }

    private void setBackGroundForCard(Button cardButton, Card card) {
        if (card.isMatched()) {
            cardButton.setBackgroundColor(0xAAFFFFFF);
            return;
        }
        if (card.isChosen()) {
            cardButton.setBackgroundColor(0xFFFFFFFF);
        } else {
            cardButton.setBackgroundResource(R.drawable.cardback);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        int score = mGame.getScore();
        outState.putInt(GAME_STATE_SCORE, score);

        String saveGame = "";
        ArrayList<PlayingCard> cards = mGame.getCards();
        int cardCount = cards.size();
        for (int i = 0; i < cardCount; ++i) {
            PlayingCard card = cards.get(i);
            saveGame += card.getSuit() + "@" + card.getRank() +  "@" + card.isMatched() +  "@" + card.isChosen() + "#";
        }
        saveGame = saveGame.substring(0, saveGame.length() - 1);
        outState.putString(GAME_STATE_CARDS, saveGame);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            int score = savedInstanceState.getInt(GAME_STATE_SCORE, SCORE_MISSING);
            if (score == SCORE_MISSING) {
                Log.e(LOG_TAG, "Restore game error: game score is missing!!!");
                return;
            }

            String saveGame = savedInstanceState.getString(GAME_STATE_CARDS, CARDS_MISSING);
            if (saveGame.equals(CARDS_MISSING)) {
                Log.e(LOG_TAG, "Restore game error: cards data is missing!!!");
                return;
            } else {
                ArrayList<PlayingCard> restoreCards = new ArrayList<>();
                String[] cardStrings = saveGame.split("#");
                int cardCount = cardStrings.length;
                if (cardCount != CARD_COUNT) {
                    Log.e(LOG_TAG, "Restore game error: card count is abnormal!!!");
                    return;
                }
                for (int i = 0; i < cardCount; ++i) {
                    String cardContent = cardStrings[i];
                    String[] cardContents = cardContent.split("@");
                    if (cardContents.length != 4) {
                        Log.e(LOG_TAG, "Restore game error: card content is lost!!!");
                        return;
                    }
                    PlayingCard card = new PlayingCard();
                    card.setSuit(cardContents[0]);
                    card.setRank(Integer.parseInt(cardContents[1]));
                    card.setMatched(Boolean.parseBoolean(cardContents[2]));
                    card.setChosen(Boolean.parseBoolean(cardContents[3]));
                    restoreCards.add(card);
                }
                this.mGame = new CardMatchingGame(restoreCards, score);
                updateUi();
            }

            super.onRestoreInstanceState(savedInstanceState);
        }
    }

    private void startMeasuringTimeThread(final Handler handler) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(Timer.TIMER_STATE_UNSTART);
                while (true)
                {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Log.e(LOG_TAG, "startMeasuringTimeThread sleep exception:" + e.getMessage());
                    }
                    handler.sendEmptyMessage(Timer.TIMER_STATE_PROGRESS);
                    if (mGame.getGmaeState() == CardMatchingGame.GAME_STATE_FINISH)
                    {
                        break;
                    }
                }
                handler.sendEmptyMessage(Timer.TIMER_STATE_STOP);
            }
        });
        thread.start();
    }

    private Handler mTimerHandler  = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mTvDuration.setText(mGame.getTimer().getDurationInTimeFormat());
            switch (msg.what) {
                case Timer.TIMER_STATE_UNSTART:
                    Log.d(LOG_TAG, "handle TIMER_STATE_UNSTART message");
                    break;
                case Timer.TIMER_STATE_PROGRESS:
                    // do nothing
                    break;
                case Timer.TIMER_STATE_PAUSE:
                    Log.d(LOG_TAG, "handle TIMER_STATE_PAUSE message");
                    break;
                case Timer.TIMER_STATE_STOP:
                    Log.d(LOG_TAG, "handle TIMER_STATE_STOP message");
                    break;
                default:
                    // do nothing.
                    break;
            }
        }
    };


}
