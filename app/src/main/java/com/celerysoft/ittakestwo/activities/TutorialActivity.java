package com.celerysoft.ittakestwo.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.celerysoft.ittakestwo.R;
import com.gc.materialdesign.views.ButtonRectangle;

/**
 * Created by Celery on 16/1/26.
 * TutorialActivity, touch player how to play.
 */
public class TutorialActivity extends Activity {

    // declare view
    private ButtonRectangle mBtnNext;
    private ButtonRectangle mBtnFinish;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tutorial);

        defineView();
        defineListener();
    }

    private void defineView() {
        mBtnNext = (ButtonRectangle) findViewById(R.id.tutorial_btn_next);
        mBtnFinish = (ButtonRectangle) findViewById(R.id.tutorial_btn_finish);
        mSvTalkToPlayer = (ScrollView) findViewById(R.id.tutorial_scroll_view_talk_to_player);
        mLlTalkToPlayer = (LinearLayout) findViewById(R.id.tutorial_ll_talk_to_player);
        mLlCards = (LinearLayout) findViewById(R.id.tutorial_ll_cards);
        mLlCards.post(new Runnable() {
            @Override
            public void run() {
                adjustDisplayOfCards();
            }
        });

        mCard0 = (Button) findViewById(R.id.card00);
        mCard1 = (Button) findViewById(R.id.card01);
        mCard2 = (Button) findViewById(R.id.card02);
        mCard3 = (Button) findViewById(R.id.card03);
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

    private int mCounter = 0;
    private float mTextSize = 0;
    private void defineListener() {
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextSize = mTextSize == 0 ? getResources().getDimension(R.dimen.text_size_body) : mTextSize;

                TextView textView = new TextView(TutorialActivity.this);
                textView.setText("text text text" + mCounter);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
                mCounter++;

                mLlTalkToPlayer.addView(textView);
                mLlTalkToPlayer.post(new Runnable() {
                    @Override
                    public void run() {
                        mSvTalkToPlayer.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        });
        mBtnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TutorialActivity.this.finish();
            }
        });
    }
}
