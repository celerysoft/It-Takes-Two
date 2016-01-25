package com.celerysoft.ittakestwo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.celerysoft.ittakestwo.R;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonIcon;

/**
 * Choose how many players to play the game.
 */
public class MultiPlayerChoosePlayerCountActivity extends Activity {
    // Const
    private static final int MIN_PLAYER_COUNT = 2;
    private static final int MAX_PLAYER_COUNT = 4;

    // Declare view
    private TextView mTvPlayerCount;
    private ButtonFlat mBtnConfirm;
    private ButtonIcon mBtnIncreasePlayerCount;
    private ButtonIcon mBtnDecreasePlayerCount;

    // Private field
    private Integer mPlayerCount = MIN_PLAYER_COUNT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_multi_player_choose_player_count);

        init();
    }

    private void init() {
        defineView();
        defineListener();

        updatePlayerCount();
    }

    private void defineView() {
        mTvPlayerCount = (TextView) findViewById(R.id.choose_player_count_tv_player_count);
        mBtnConfirm = (ButtonFlat) findViewById(R.id.choose_player_count_btn_confirm);
        mBtnIncreasePlayerCount = (ButtonIcon) findViewById(R.id.choose_player_count_btn_increase);
        mBtnDecreasePlayerCount = (ButtonIcon) findViewById(R.id.choose_player_count_btn_decrease);
    }

    private void defineListener() {
        mBtnConfirm.setOnClickListener(mOnClickListener);
        mBtnIncreasePlayerCount.setOnClickListener(mOnClickListener);
        mBtnDecreasePlayerCount.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.equals(mBtnConfirm)) {
                startGame();
            } else if (v.equals(mBtnIncreasePlayerCount)) {
                increasePlayerCount();
            } else if (v.equals(mBtnDecreasePlayerCount)) {
                decreasePlayerCount();
            } else {

            }
        }
    };

    private void increasePlayerCount() {
        if (mPlayerCount < MAX_PLAYER_COUNT) {
            mPlayerCount++;
            updatePlayerCount();
        }
    }

    private void decreasePlayerCount() {
        if (mPlayerCount > MIN_PLAYER_COUNT) {
            mPlayerCount--;
            updatePlayerCount();
        }
    }

    private void updatePlayerCount() {
        mTvPlayerCount.setText(mPlayerCount.toString());
    }

    private void startGame() {
        int playerCount = mPlayerCount;

        Intent intent = new Intent(this, PlayingCardActivity.class);
        intent.putExtra(PlayingCardActivity.KEY_PLAYER_COUNT, playerCount);
        startActivity(intent);
        this.finish();
    }
}
