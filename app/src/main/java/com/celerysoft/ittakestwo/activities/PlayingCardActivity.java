package com.celerysoft.ittakestwo.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.celerysoft.ittakestwo.R;
import com.celerysoft.ittakestwo.models.Card;
import com.celerysoft.ittakestwo.models.CardMatchingGame;
import com.celerysoft.ittakestwo.models.PlayingCard;
import com.celerysoft.ittakestwo.models.PlayingDeck;
import com.celerysoft.ittakestwo.models.Timer;
import com.gc.materialdesign.views.ButtonFloat;

import java.util.ArrayList;

/**
 * Playing Card Activity
 */
public class PlayingCardActivity extends Activity {
    /** Log tag **/
    private final String LOG_TAG = this.getClass().getSimpleName();

    // const
    private final String SAVE_GAME = "saveGame";

    @SuppressWarnings("unused")
    private final int CARD_COUNT = 16;

    // fields
    private Context mContext;
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
    private RelativeLayout mTopbar;
    private ButtonFloat mBtnCommit;
    private ButtonFloat mBtnRestartGame;
    private ButtonFloat mBtnShareScore;
    private TextView mTvScroe;
    private TextView mTvDuration;

    private CardMatchingGame mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_playing_card);

        defineView();
        defineListener();

        mContext = this;
        mGame = new CardMatchingGame(cardButtons.size(), new PlayingDeck());
        mIsNeededAutoAdjustForScreen = true;
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




    /**
     * adjust width and height of cards for portrait screen
     */
    private void autoAdjustForPortraitScreen() {

        // calculate card width
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        float horizontalMargin = getResources().getDimension(R.dimen.playing_card_grid_horizontal_margin);

        int cardLayoutWidth = (int) ((screenWidth - 5 * horizontalMargin) / 4);
        cardLayoutWidth = (int) (screenWidth * 0.8 / 4);
        int cardLayoutHorizontalMargin = (int) horizontalMargin;
        cardLayoutHorizontalMargin = (int) ((screenWidth * 0.2 - 2 * horizontalMargin) / 3);

        // calculate card height
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        float verticalMargin = getResources().getDimension(R.dimen.playing_card_grid_vertical_margin);

        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int topbarHeight = mTopbar.getHeight();

        int contentScreenHeight = screenHeight - statusBarHeight - topbarHeight;

        int cardLayoutHeight = (int) ((contentScreenHeight - 5 * verticalMargin) / 4);
        cardLayoutHeight = (int) (contentScreenHeight * 0.8 / 4);
        int cardLayoutVerticalMargin = (int) verticalMargin;
        cardLayoutVerticalMargin = (int) ((contentScreenHeight * 0.2 - 2 * verticalMargin) / 3);

        // adjust width and height of cards
        GridLayout.LayoutParams cardlayoutParams = (GridLayout.LayoutParams) card00.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;

        cardlayoutParams = (GridLayout.LayoutParams) card01.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);

        cardlayoutParams = (GridLayout.LayoutParams) card02.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);

        cardlayoutParams = (GridLayout.LayoutParams) card03.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);

        cardlayoutParams = (GridLayout.LayoutParams) card04.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;
        cardlayoutParams.setMargins(0, cardLayoutVerticalMargin, 0, 0);

        cardlayoutParams = (GridLayout.LayoutParams) card05.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;

        cardlayoutParams = (GridLayout.LayoutParams) card06.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;

        cardlayoutParams = (GridLayout.LayoutParams) card07.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;

        cardlayoutParams = (GridLayout.LayoutParams) card08.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;
        cardlayoutParams.setMargins(0, cardLayoutVerticalMargin, 0, 0);

        cardlayoutParams = (GridLayout.LayoutParams) card09.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;

        cardlayoutParams = (GridLayout.LayoutParams) card10.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;

        cardlayoutParams = (GridLayout.LayoutParams) card11.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;

        cardlayoutParams = (GridLayout.LayoutParams) card12.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;
        cardlayoutParams.setMargins(0, cardLayoutVerticalMargin, 0, 0);

        cardlayoutParams = (GridLayout.LayoutParams) card13.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;

        cardlayoutParams = (GridLayout.LayoutParams) card14.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;

        cardlayoutParams = (GridLayout.LayoutParams) card15.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;
    }

    /**
     * adjust cards horizontal gap and vertical gap for landscape screen
     */
    private void autoAdjustForLandscapeScreen() {

        // calculate card width
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        float horizontalMargin = getResources().getDimension(R.dimen.playing_card_grid_horizontal_margin);

        int cardLayoutWidth = (int) ((screenWidth - 9 * horizontalMargin) / 8);
        cardLayoutWidth = (int) (screenWidth * 0.9 / 8);
        int cardLayoutHorizontalMargin = (int) horizontalMargin;
        cardLayoutHorizontalMargin = (int) ((screenWidth * 0.1 - 2 * horizontalMargin) / 7);

        // calculate card height
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        float verticalMargin = getResources().getDimension(R.dimen.playing_card_grid_vertical_margin);

        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int topbarHeight = mTopbar.getHeight();

        int contentScreenHeight = screenHeight - statusBarHeight - topbarHeight;

        int cardLayoutHeight = (int) ((contentScreenHeight - 3 * verticalMargin) / 2);
        cardLayoutHeight = (int) (contentScreenHeight * 0.75 / 2);
        int cardLayoutVerticalMargin = (int) verticalMargin;
        cardLayoutVerticalMargin = (int) ((contentScreenHeight * 0.25 - 2 * verticalMargin) / 1);

        // adjust width and height of cards
        GridLayout.LayoutParams cardlayoutParams = (GridLayout.LayoutParams) card00.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;
        cardlayoutParams.setMargins(0, 0, 0, 0);

        cardlayoutParams = (GridLayout.LayoutParams) card01.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);

        cardlayoutParams = (GridLayout.LayoutParams) card02.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);

        cardlayoutParams = (GridLayout.LayoutParams) card03.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);

        cardlayoutParams = (GridLayout.LayoutParams) card04.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);

        cardlayoutParams = (GridLayout.LayoutParams) card05.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);

        cardlayoutParams = (GridLayout.LayoutParams) card06.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);

        cardlayoutParams = (GridLayout.LayoutParams) card07.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;
        cardlayoutParams.setMargins(cardLayoutHorizontalMargin, 0, 0, 0);

        cardlayoutParams = (GridLayout.LayoutParams) card08.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;
        cardlayoutParams.setMargins(0, cardLayoutVerticalMargin, 0, 0);

        cardlayoutParams = (GridLayout.LayoutParams) card09.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;

        cardlayoutParams = (GridLayout.LayoutParams) card10.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;

        cardlayoutParams = (GridLayout.LayoutParams) card11.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;

        cardlayoutParams = (GridLayout.LayoutParams) card12.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;

        cardlayoutParams = (GridLayout.LayoutParams) card13.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;

        cardlayoutParams = (GridLayout.LayoutParams) card14.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;

        cardlayoutParams = (GridLayout.LayoutParams) card15.getLayoutParams();
        cardlayoutParams.width = cardLayoutWidth;
        cardlayoutParams.height = cardLayoutHeight;
    }

    private void defineView() {

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

        mTopbar = (RelativeLayout) findViewById(R.id.playingcard_rl_topbar);
        mBtnCommit = (ButtonFloat) findViewById(R.id.playingcard_btn_commit);
        mBtnRestartGame = (ButtonFloat) findViewById(R.id.playingcard_btn_restart);
        mBtnShareScore = (ButtonFloat) findViewById(R.id.playingcard_btn_share);
        mTvScroe = (TextView) findViewById(R.id.playingcard_tv_score);
        mTvDuration = (TextView) findViewById(R.id.playingcard_tv_duration);

        setBtnCommitUnclickable();
    }

    private void defineListener() {
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
            if(!v.isClickable()) {
                return;
            }

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

        setBtnCommitUnclickable();

        setBtnsclickable();
    }

    private void setBtnCommitClickable() {
        mBtnCommit.setClickable(true);
        mBtnCommit.setAlpha(1.0f);
    }

    private void setBtnCommitUnclickable() {
        mBtnCommit.setClickable(false);
        mBtnCommit.setAlpha(0.6f);
    }

    /**
     * hide "Restart" and "Share" button, when in the game progress, u don't want the
     * player slip up to tap this buttons to break off the game.
     */
    private void setBtnsUnclickable() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.btn_fade_out);
        mBtnRestartGame.startAnimation(animation);
        mBtnRestartGame.setClickable(false);
        mBtnShareScore.startAnimation(animation);
        mBtnShareScore.setClickable(false);
    }

    /**
     * show "Restart" and "Share" button
     */
    private void setBtnsclickable() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.btn_fade_in);
        mBtnRestartGame.startAnimation(animation);
        mBtnRestartGame.setClickable(true);
        mBtnShareScore.startAnimation(animation);
        mBtnShareScore.setClickable(true);
    }

    /**
     * on the point of game starting
     */
    private void onGameStart() {
        startMeasuringTimeThread(mTimerHandler);

        setBtnCommitClickable();

        setBtnsUnclickable();
    }

    /** card buttons onClickListener **/
    private View.OnClickListener mOnCardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view instanceof Button) {
                // game is going to start, start time thread and hide buttons.
                if (mGame.getGmaeState() == CardMatchingGame.State.GAME_STATE_UNSTART) {
                    onGameStart();
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

        String scoreText = getString(R.string.playingcard_score) + mGame.getScore();
        mTvScroe.setText(scoreText);

        String durationString = mGame.getTimer().getDurationInTimeFormat();
        durationString = durationString.equals("00 : 00.000") ? mContext.getString(R.string.playingcard_tv_duration_text) : durationString;
        mTvDuration.setText(durationString);
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
            cardButton.setBackgroundResource(R.drawable.cardfront);
            cardButton.setAlpha(0.75f);
            return;
        }
        if (card.isChosen()) {
            cardButton.setBackgroundResource(R.drawable.cardfront);
        } else {
            cardButton.setBackgroundResource(R.drawable.cardback);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(SAVE_GAME, mGame);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mGame = (CardMatchingGame) savedInstanceState.getSerializable(SAVE_GAME);
            if (mGame != null) {
                onGameStart();
                updateUi();
            } else {
                Log.w(LOG_TAG, "Game state lost");
            }
            super.onRestoreInstanceState(savedInstanceState);
        }

    }

    /**
     * start the thread about display of the timer
     * @param handler Handler to update UI thread
     */
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

                    if (mGame.getGmaeState() == CardMatchingGame.State.GAME_STATE_PAUSE) {
                        handler.sendEmptyMessage(Timer.TIMER_STATE_PAUSE);
                        continue;
                    }

                    handler.sendEmptyMessage(Timer.TIMER_STATE_PROGRESS);

                    if (mGame.getGmaeState() == CardMatchingGame.State.GAME_STATE_FINISH)
                    {
                        break;
                    }
                }
                handler.sendEmptyMessage(Timer.TIMER_STATE_STOP);
            }
        });
        thread.start();
    }

    /**
     * Timer Handler, to handle the message about the timer
     */
    @SuppressLint("HandlerLeak")
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
