package com.celerysoft.ittakestwo.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.celerysoft.ittakestwo.R;
import com.celerysoft.ittakestwo.models.Card;
import com.celerysoft.ittakestwo.models.CardMatchingGame;
import com.celerysoft.ittakestwo.models.PlayingCard;
import com.celerysoft.ittakestwo.models.PlayingDeck;
import com.celerysoft.ittakestwo.models.Tutorial;

import java.util.ArrayList;

/**
 * Created by Celery on 16/1/26.
 * TutorialActivity, touch player how to play.
 */
public class TutorialActivity extends BaseActivity {
    private static final String TAG = "TutorialActivity";

    // declare view
    private RelativeLayout mRoot;
    private TextView mTvScore;
    private Button mBtnFinish;
    private ScrollView mSvTalkToPlayer;
    private LinearLayout mLlTalkToPlayer;
    private LinearLayout mLlCards;

    private Button mCard0;
    private Button mCard1;
    private Button mCard2;
    private Button mCard3;

    // field
    private int mCardWidth;
    private int mCardHeight;
    private int mCardLayoutHorizontalMargin;
    private ArrayList<Button> mCardButtons = new ArrayList<>();

    private CardMatchingGame mGame;
    private Tutorial mTutorial;
    private Tutorial.Process mTutorialProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tutorial);

        initData();
        defineView();
        defineListener();

        mRoot.post(new Runnable() {
            @Override
            public void run() {
                mTutorialProcess = mTutorial.startTutorial();
                updateUi();
            }
        });
    }

    private void defineView() {
        mRoot = (RelativeLayout) findViewById(R.id.tutorial_root);
        mTvScore = (TextView) findViewById(R.id.tutorial_tv_score);
        mBtnFinish = (Button) findViewById(R.id.tutorial_btn_finish);
        mSvTalkToPlayer = (ScrollView) findViewById(R.id.tutorial_scroll_view_talk_to_player);
        mLlTalkToPlayer = (LinearLayout) findViewById(R.id.tutorial_ll_talk_to_player);
        mLlCards = (LinearLayout) findViewById(R.id.tutorial_ll_cards);
        mLlCards.post(new Runnable() {
            @Override
            public void run() {
                adjustDisplayOfCards();
                mLlCards.requestLayout();
            }
        });

        mCard0 = (Button) findViewById(R.id.card00);
        mCard1 = (Button) findViewById(R.id.card01);
        mCard2 = (Button) findViewById(R.id.card02);
        mCard3 = (Button) findViewById(R.id.card03);

        mCardButtons.add(mCard0);
        mCardButtons.add(mCard1);
        mCardButtons.add(mCard2);
        mCardButtons.add(mCard3);
    }

    private void initData() {
        PlayingDeck deck = Tutorial.createTutorialDeck();

        mGame = new CardMatchingGame(deck);

        mTutorial = new Tutorial(this, mGame);
    }

    private void adjustDisplayOfCards() {
        calculateCardSize();

        LinearLayout.LayoutParams cardLayoutParams = (LinearLayout.LayoutParams) mCard0.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;

        cardLayoutParams = (LinearLayout.LayoutParams) mCard1.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
        cardLayoutParams.setMargins(mCardLayoutHorizontalMargin, 0, 0, 0);

        cardLayoutParams = (LinearLayout.LayoutParams) mCard2.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
        cardLayoutParams.setMargins(mCardLayoutHorizontalMargin, 0, 0, 0);

        cardLayoutParams = (LinearLayout.LayoutParams) mCard3.getLayoutParams();
        cardLayoutParams.width = mCardWidth;
        cardLayoutParams.height = mCardHeight;
        cardLayoutParams.setMargins(mCardLayoutHorizontalMargin, 0, 0, 0);
    }

    /**
     * calculate the size of card
     */
    private void calculateCardSize() {
        final int CARD_PER_ROW = 4;

        // calculate card size
        int screenWidth = getResources().getDisplayMetrics().widthPixels;

        float horizontalMargin = getResources().getDimension(R.dimen.playing_card_grid_horizontal_margin);

        int contentWidth = screenWidth - (int) (2 * horizontalMargin);

        mCardWidth = (int) (contentWidth * 0.9 / CARD_PER_ROW);
        mCardHeight = mCardWidth * 3 / 2;

        // calculate the margin of card layout
        mCardLayoutHorizontalMargin = (contentWidth - CARD_PER_ROW * mCardWidth) / (CARD_PER_ROW - 1);
    }

    private float mTextSize = 0;
    private void defineListener() {
        mBtnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TutorialActivity.this.finish();
            }
        });

        mCard0.setOnClickListener(mOnCardClickListener);
        mCard1.setOnClickListener(mOnCardClickListener);
        mCard2.setOnClickListener(mOnCardClickListener);
        mCard3.setOnClickListener(mOnCardClickListener);
    }

    private View.OnClickListener mOnCardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof Button) {
                Button card = (Button) v;
                int chosenButtonIndex = mCardButtons.indexOf(card);
                if (chosenButtonIndex == mTutorialProcess.cardIndexToTriggerNextProcess) {
                    mGame.chooseCardAtIndex(chosenButtonIndex);
                }
                mTutorialProcess = mTutorial.tryToFlipCard(chosenButtonIndex);
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

        String scoreText = getString(R.string.tutorial_tv_score_text) + mGame.getScore();
        mTvScore.setText(scoreText);

        setCardButtonState(mCard0, mTutorialProcess.state.cardState0);
        setCardButtonState(mCard1, mTutorialProcess.state.cardState1);
        setCardButtonState(mCard2, mTutorialProcess.state.cardState2);
        setCardButtonState(mCard3, mTutorialProcess.state.cardState3);

        mTextSize = mTextSize == 0 ? getResources().getDimension(R.dimen.text_size_subhead) : mTextSize;
        TextView textView = new TextView(TutorialActivity.this);
        textView.setText(mTutorialProcess.state.talkToPlayer);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        textView.setTextColor(getResources().getColor(R.color.primary_text));
        mLlTalkToPlayer.addView(textView);
        mLlTalkToPlayer.post(new Runnable() {
            @Override
            public void run() {
                mSvTalkToPlayer.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

        if (mTutorialProcess.isLastProcess) {
            if (!mBtnFinish.getText().equals(getString(R.string.tutorial_btn_finish_tutorial_text_when_graduate))) {
                mBtnFinish.setText(getString(R.string.tutorial_btn_finish_tutorial_text_when_graduate));
            }
        }
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

    private void setCardButtonState(Button cardButton, Tutorial.CardState cardState) {
        if (cardState == Tutorial.CardState.ENABLE) {
            cardButton.setEnabled(true);
        } else if (cardState == Tutorial.CardState.DISABLE) {
            cardButton.setEnabled(false);
        } else {
            Log.w(TAG, "left a card button state to handle");
        }
    }
}
