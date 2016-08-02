package com.celerysoft.ittakestwo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.celerysoft.ittakestwo.R;

/**
 * Choose how many players to play the game.
 */
public class MultiPlayerChoosePlayerCountActivity extends Activity {
    // Const
    private static final int MIN_PLAYER_COUNT = 2;
    private static final int MAX_PLAYER_COUNT = 4;

    // Declare view
    private TextView mTvPlayerCount;
    private View mBtnConfirm;
    private View mBtnIncreasePlayerCount;
    private View mBtnDecreasePlayerCount;

    // Private field
    private Integer mPlayerCount = MIN_PLAYER_COUNT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_multi_player_choose_player_count);

        init();
    }

    private void init() {
        bindView();
        bindListener();

        updatePlayerCount();
    }

    private void bindView() {
        mTvPlayerCount = (TextView) findViewById(R.id.choose_player_count_tv_player_count);
        mBtnConfirm = findViewById(R.id.choose_player_count_btn_confirm);
        mBtnIncreasePlayerCount = findViewById(R.id.choose_player_count_btn_increase);
        mBtnDecreasePlayerCount = findViewById(R.id.choose_player_count_btn_decrease);
    }

    private void bindListener() {
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
        mBtnIncreasePlayerCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increasePlayerCount();
            }
        });
        mBtnDecreasePlayerCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreasePlayerCount();
            }
        });
    }

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
        mTvPlayerCount.setText(String.format("%s", mPlayerCount.toString()));
    }

    private void startGame() {
        int playerCount = mPlayerCount;

        Intent intent = new Intent(this, PlayingCardActivity.class);
        intent.putExtra(PlayingCardActivity.KEY_PLAYER_COUNT, playerCount);
        startActivity(intent);
        this.finish();
    }
}
