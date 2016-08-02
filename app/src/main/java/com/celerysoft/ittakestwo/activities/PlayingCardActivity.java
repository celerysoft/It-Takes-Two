package com.celerysoft.ittakestwo.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.celerysoft.ittakestwo.R;
import com.celerysoft.ittakestwo.models.Card;
import com.celerysoft.ittakestwo.models.CardMatchingGame;
import com.celerysoft.ittakestwo.models.Player;
import com.celerysoft.ittakestwo.models.PlayingCard;
import com.celerysoft.ittakestwo.models.PlayingDeck;
import com.celerysoft.ittakestwo.models.Timer;
import com.celerysoft.ittakestwo.utils.SocialSharingUtil;
import com.celerysoft.materialdesigndialog.MaterialDesignDialog;

import java.util.ArrayList;

/**
 * Playing Card Activity
 */
public class PlayingCardActivity extends Activity {
    /** Log tag **/
    private final String TAG = this.getClass().getSimpleName();

    // const
    private static final String SAVE_GAME = "saveGame";

    public static final String KEY_PLAYER_COUNT = "KEY_PLAYER_COUNT";

    @SuppressWarnings("unused")
    private final int CARD_COUNT = 16;

    // fields
    private CardMatchingGame mGame;

    private MaterialDesignDialog mRestartGameDialog;
    private MaterialDesignDialog mSocialSharingDialog;

    private int mPlayerCount = 1;

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
    private ArrayList<Button> mCardButtons = new ArrayList<>();
    private int mCardWidth;
    private int mCardHeight;
    private int mCardLayoutHorizontalMargin;
    private int mCardLayoutVerticalMargin;

    //declare widgets
    private RelativeLayout mTopBar;
    private FloatingActionButton mBtnCommit;
    private FloatingActionButton mBtnRestartGame;
    private FloatingActionButton mBtnShareScore;
    private TextView mTvScore;
    private TextView mTvDuration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_playing_card);

        defineView();
        defineListener();

        initData(savedInstanceState);
    }

    private int getPlayerCount() {
        int playerCount = 1;

        if (getIntent() != null) {
            Intent intent = getIntent();
            playerCount = intent.getIntExtra(KEY_PLAYER_COUNT, 1);
        }

        return playerCount;
    }

    private void createPlayers() {
        mGame.setPlayers(Player.createPlayers(this, mPlayerCount));
    }

    private void showMultiPlayerGameDialog() {
        String message = getString(R.string.playing_card_multi_player_game_dialog_message_paragraph_1)
                + "\n\n";
        for (Player player : mGame.getPlayers()) {
            message += player.getName() + "\n";
        }
        message += "\n" + getString(R.string.playing_card_multi_player_game_dialog_message_paragraph_2);

        final MaterialDesignDialog dialog = new MaterialDesignDialog(this);
        dialog.setTitle(getString(R.string.playing_card_multi_player_game_dialog_title))
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        showPreGameDialog();
                    }
                });
        dialog.show();
    }

    private void showPreGameDialog() {
        String message = getString(R.string.playing_card_pre_game_dialog_message_paragraph_1)
                + mGame.getCurrentPlayer().getName() + "\n\n" + mGame.getCurrentPlayer().getName()
                + getString(R.string.playing_card_pre_game_dialog_message_paragraph_2);

        final MaterialDesignDialog dialog = new MaterialDesignDialog(this);
        dialog.setTitle(getString(R.string.playing_card_pre_game_dialog_title))
                .setMessage(message)
                .setPositiveButton(getString(R.string.playing_card_pre_game_dialog_message_btn_text), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }


    /**
     * adjust width and height of cards for portrait screen
     */
    private void autoAdjustForPortraitScreen() {
        calculateCardSize(true);

        // adjust width and height of cards
        GridLayout.LayoutParams cardLayoutParams = (GridLayout.LayoutParams) card00.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;

        cardLayoutParams = (GridLayout.LayoutParams) card01.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
        cardLayoutParams.setMargins(mCardLayoutHorizontalMargin, 0, 0, 0);

        cardLayoutParams = (GridLayout.LayoutParams) card02.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
        cardLayoutParams.setMargins(mCardLayoutHorizontalMargin, 0, 0, 0);

        cardLayoutParams = (GridLayout.LayoutParams) card03.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
        cardLayoutParams.setMargins(mCardLayoutHorizontalMargin, 0, 0, 0);

        cardLayoutParams = (GridLayout.LayoutParams) card04.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
        cardLayoutParams.setMargins(0, mCardLayoutVerticalMargin, 0, 0);

        cardLayoutParams = (GridLayout.LayoutParams) card05.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;

        cardLayoutParams = (GridLayout.LayoutParams) card06.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;

        cardLayoutParams = (GridLayout.LayoutParams) card07.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;

        cardLayoutParams = (GridLayout.LayoutParams) card08.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
        cardLayoutParams.setMargins(0, mCardLayoutVerticalMargin, 0, 0);

        cardLayoutParams = (GridLayout.LayoutParams) card09.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;

        cardLayoutParams = (GridLayout.LayoutParams) card10.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;

        cardLayoutParams = (GridLayout.LayoutParams) card11.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;

        cardLayoutParams = (GridLayout.LayoutParams) card12.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
        cardLayoutParams.setMargins(0, mCardLayoutVerticalMargin, 0, 0);

        cardLayoutParams = (GridLayout.LayoutParams) card13.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;

        cardLayoutParams = (GridLayout.LayoutParams) card14.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;

        cardLayoutParams = (GridLayout.LayoutParams) card15.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
    }

    /**
     * adjust cards horizontal gap and vertical gap for landscape screen
     */
    private void autoAdjustForLandscapeScreen() {
        calculateCardSize(false);

        // adjust width and height of cards
        GridLayout.LayoutParams cardLayoutParams = (GridLayout.LayoutParams) card00.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
        cardLayoutParams.setMargins(0, 0, 0, 0);

        cardLayoutParams = (GridLayout.LayoutParams) card01.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
        cardLayoutParams.setMargins(mCardLayoutHorizontalMargin, 0, 0, 0);

        cardLayoutParams = (GridLayout.LayoutParams) card02.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
        cardLayoutParams.setMargins(mCardLayoutHorizontalMargin, 0, 0, 0);

        cardLayoutParams = (GridLayout.LayoutParams) card03.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
        cardLayoutParams.setMargins(mCardLayoutHorizontalMargin, 0, 0, 0);

        cardLayoutParams = (GridLayout.LayoutParams) card04.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
        cardLayoutParams.setMargins(mCardLayoutHorizontalMargin, 0, 0, 0);

        cardLayoutParams = (GridLayout.LayoutParams) card05.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
        cardLayoutParams.setMargins(mCardLayoutHorizontalMargin, 0, 0, 0);

        cardLayoutParams = (GridLayout.LayoutParams) card06.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
        cardLayoutParams.setMargins(mCardLayoutHorizontalMargin, 0, 0, 0);

        cardLayoutParams = (GridLayout.LayoutParams) card07.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
        cardLayoutParams.setMargins(mCardLayoutHorizontalMargin, 0, 0, 0);

        cardLayoutParams = (GridLayout.LayoutParams) card08.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
        cardLayoutParams.setMargins(0, mCardLayoutVerticalMargin, 0, 0);

        cardLayoutParams = (GridLayout.LayoutParams) card09.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;

        cardLayoutParams = (GridLayout.LayoutParams) card10.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;

        cardLayoutParams = (GridLayout.LayoutParams) card11.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;

        cardLayoutParams = (GridLayout.LayoutParams) card12.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;

        cardLayoutParams = (GridLayout.LayoutParams) card13.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;

        cardLayoutParams = (GridLayout.LayoutParams) card14.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;

        cardLayoutParams = (GridLayout.LayoutParams) card15.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
    }

    /**
     * calculate the size of card
     * @param isPortrait Screen Orientation
     */
    private void calculateCardSize(boolean isPortrait) {
        final int CARD_COUNT_PER_ROW_IN_PORTRAIT = 4;
        final int CARD_COUNT_PER_COLUMN_IN_PORTRAIT = 4;
        final int CARD_COUNT_PER_ROW_IN_LANDSCAPE = 8;
        final int CARD_COUNT_PER_COLUMN_IN_LANDSCAPE = 2;

        int cardPerRow;
        int cardPerColumn;
        if (isPortrait) {
            cardPerRow = CARD_COUNT_PER_ROW_IN_PORTRAIT;
            cardPerColumn = CARD_COUNT_PER_COLUMN_IN_PORTRAIT;
        } else {
            cardPerRow = CARD_COUNT_PER_ROW_IN_LANDSCAPE;
            cardPerColumn = CARD_COUNT_PER_COLUMN_IN_LANDSCAPE;
        }

        calculateCardSize(cardPerRow, cardPerColumn);
    }

    /**
     * calculate the size of card
     * @param cardPerRow card count per row
     * @param cardPerColumn card count per column
     */
    private void calculateCardSize(int cardPerRow, int cardPerColumn) {

        // pre calculate card width
        int screenWidth = getResources().getDisplayMetrics().widthPixels;

        float horizontalMargin = getResources().getDimension(R.dimen.playing_card_grid_horizontal_margin);

        int contentWidth = screenWidth - (int) (2 * horizontalMargin);

        int cardLayoutWidth = (int) (contentWidth * 0.9 / cardPerRow);

        // pre calculate card height
        int screenHeight = getResources().getDisplayMetrics().heightPixels;

        float verticalMargin = getResources().getDimension(R.dimen.playing_card_grid_vertical_margin);

        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int topBarHeight = mTopBar.getHeight();

        int contentHeight = screenHeight - statusBarHeight - topBarHeight - (int) (2 * verticalMargin);

        int cardLayoutHeight = (int) (contentHeight * 0.9 / cardPerColumn);

        // calculate the size of card
        Card card = adjustCardSizeToMaintainAspectRatio(cardLayoutWidth, cardLayoutHeight);
        mCardWidth = cardLayoutWidth = card.getWidth();
        mCardHeight = cardLayoutHeight = card.getHeight();

        // calculate the margin of card layout
        mCardLayoutHorizontalMargin = (contentWidth - cardPerRow * cardLayoutWidth) / (cardPerRow - 1);
        mCardLayoutVerticalMargin = (contentHeight - cardPerColumn * cardLayoutHeight) / (cardPerColumn - 1);
    }

    private Card adjustCardSizeToMaintainAspectRatio(int width, int height) {
        /** width / height = 2 / 3 **/
        final float CARD_ASPECT_RATIO = 2f / 3f;

        Card card = new Card();

        float originWidth = (float) width;
        float originHeight = (float) height;

        if ((originWidth / originHeight) > CARD_ASPECT_RATIO) {
            width = height * 2 / 3;
        } else {
            height =  width * 3 / 2;
        }

        card.setWidth(width);
        card.setHeight(height);

        return card;
    }

    private void initData(Bundle savedInstanceState) {
        mGame = new CardMatchingGame(mCardButtons.size(), new PlayingDeck());

        mPlayerCount = getPlayerCount();
        if (mPlayerCount > 1) {
            createPlayers();
            if (savedInstanceState == null) {
                showMultiPlayerGameDialog();
            }
        }
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
        mCardButtons.add(card00);
        mCardButtons.add(card01);
        mCardButtons.add(card02);
        mCardButtons.add(card03);
        mCardButtons.add(card04);
        mCardButtons.add(card05);
        mCardButtons.add(card06);
        mCardButtons.add(card07);
        mCardButtons.add(card08);
        mCardButtons.add(card09);
        mCardButtons.add(card10);
        mCardButtons.add(card11);
        mCardButtons.add(card12);
        mCardButtons.add(card13);
        mCardButtons.add(card14);
        mCardButtons.add(card15);

        GridLayout gridLayoutCards = (GridLayout) findViewById(R.id.playingcard_gridlayout);
        gridLayoutCards.post(new Runnable() {
            /**
             * when GridLayout create finished adjust gap of cards.
             */
            @Override
            public void run() {
                if (PlayingCardActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    autoAdjustForPortraitScreen();
                } else if (PlayingCardActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    autoAdjustForLandscapeScreen();
                }
            }
        });
        mTopBar = (RelativeLayout) findViewById(R.id.playingcard_rl_topbar);
        mBtnCommit = (FloatingActionButton) findViewById(R.id.playingcard_btn_commit);
        mBtnRestartGame = (FloatingActionButton) findViewById(R.id.playingcard_btn_restart);
        mBtnShareScore = (FloatingActionButton) findViewById(R.id.playingcard_btn_share);
        mTvScore = (TextView) findViewById(R.id.playingcard_tv_score);
        mTvDuration = (TextView) findViewById(R.id.playingcard_tv_duration);

        hideBtnCommit();
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
//            if(!v.isClickable()) {
//                return;
//            }

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
        if (mRestartGameDialog == null) {
            createRestartGameDialog();
        }
        mRestartGameDialog.show();
    }

    private void createRestartGameDialog() {
        String title = this.getString(R.string.playing_card_restart_game_dialog_title);
        String[] items = new String[2];
        items[0] = this.getString(R.string.playing_card_restart_game_dialog_item_use_old_deck);
        items[1] = this.getString(R.string.playing_card_restart_game_dialog_item_create_new_deck);

        mRestartGameDialog = new MaterialDesignDialog(this)
                .setTitle(title)
                .setItems(items, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                mGame = new CardMatchingGame(mCardButtons.size(), new PlayingDeck());
                                updateUi();
                                break;
                            case 1:
                                mGame.restart();
                                updateUi();
                                break;
                        }
                        mRestartGameDialog.dismiss();
                    }
                })
                .setCanceledOnTouchOutside(true);
    }

    private void onShareScoreBtnClick() {
        if (mSocialSharingDialog == null) {
            mSocialSharingDialog = SocialSharingUtil.getInstance().createSocialSharingDialog(this);
        }
        mSocialSharingDialog.show();
    }

    private void onCommitBtnClick() {
        mGame.finish();

        hideBtnCommit();

        showButtons();

        if (mPlayerCount > 1) {
            if (!mGame.isLastPlayer()) {
                mGame.turnToNextPlayer();
                showPreGameDialog();
            } else {
                showScoreboardDialog();
            }

            mGame.restart();
            updateUi();
        }
    }

    private void showScoreboardDialog() {
        ArrayList<Player> players = Player.sortPlayersByRankScore(mGame.getPlayers());

        String message = "";
        for (int i = 0; i < mPlayerCount; ++i) {
            message += (i + 1) + ". " + players.get(i).getName() + " - " + players.get(i).getRankScore() + "\n";
        }

        final MaterialDesignDialog dialog = new MaterialDesignDialog(this, MaterialDesignDialog.Style.STACKED_FULL_WIDTH_BUTTONS);
        dialog.setTitle(getString(R.string.playing_card_score_board_dialog_title))
                .setMessage(message)
                .setNegativeButton(getString(R.string.playing_card_score_board_dialog_btn_return_text), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        PlayingCardActivity.this.finish();
                    }
                })
                .setPositiveButton(R.string.playing_card_score_board_dialog_btn_restart_text, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        mGame = new CardMatchingGame(mCardButtons.size(), new PlayingDeck());
                        updateUi();
                    }
                })
                .setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void showBtnCommit() {
        mBtnCommit.show();
    }

    private void hideBtnCommit() {
        mBtnCommit.hide();
    }

    /**
     * hide "Restart" and "Share" button, when in the game progress, u don't want the
     * player slip up to tap this buttons to break off the game.
     */
    private void hideButtons() {
        mBtnRestartGame.hide();
        mBtnShareScore.hide();
    }

    /**
     * show "Restart" and "Share" button
     */
    private void showButtons() {
        mBtnRestartGame.show();
        mBtnShareScore.show();
    }

    /**
     * on the point of game starting
     */
    private void onGameStart() {
        startMeasuringTimeThread(mTimerHandler);

        showBtnCommit();

        hideButtons();
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
                int chosenButtonIndex = mCardButtons.indexOf(card);
                mGame.chooseCardAtIndex(chosenButtonIndex);
                updateUi();
            }
        }
    };

    /** update UI when touch the cards **/
    private void updateUi() {
        for (Button cardButton : mCardButtons) {
            int cardButtonIndex = mCardButtons.indexOf(cardButton);
            Card card = mGame.cardAtIndex(cardButtonIndex);
            setTextForCard(cardButton, card);
            setBackGroundForCard(cardButton, card);
        }

        String scoreText = getString(R.string.playing_card_score) + mGame.getScore();
        mTvScore.setText(scoreText);

        String durationString = mGame.getTimer().getDurationInTimeFormat();
        durationString = durationString.equals("00 : 00.000") ? this.getString(R.string.playing_card_tv_duration_text) : durationString;
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
            cardButton.setAlpha(1.00f);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState");
        outState.putSerializable(SAVE_GAME, mGame);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mGame = (CardMatchingGame) savedInstanceState.getSerializable(SAVE_GAME);
            if (mGame != null) {
                if (mGame.getGmaeState() == CardMatchingGame.State.GAME_STATE_START) {
                    onGameStart();
                }
                updateUi();
            } else {
                Log.w(TAG, "Game state lost");
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
                        Log.e(TAG, "startMeasuringTimeThread sleep exception:" + e.getMessage());
                    }

                    if (mGame.getGmaeState() == CardMatchingGame.State.GAME_STATE_PAUSE) {
                        handler.sendEmptyMessage(Timer.TIMER_STATE_PAUSE);
                        continue;
                    }

                    handler.sendEmptyMessage(Timer.TIMER_STATE_PROGRESS);

                    if (mGame.getGmaeState() == CardMatchingGame.State.GAME_STATE_FINISH) {
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
                    Log.d(TAG, "handle TIMER_STATE_NOT_START message");
                    break;
                case Timer.TIMER_STATE_PROGRESS:
                    // do nothing
                    break;
                case Timer.TIMER_STATE_PAUSE:
                    Log.d(TAG, "handle TIMER_STATE_PAUSE message");
                    break;
                case Timer.TIMER_STATE_STOP:
                    Log.d(TAG, "handle TIMER_STATE_STOP message");
                    break;
                default:
                    // do nothing.
                    break;
            }
        }
    };


}
