package com.celerysoft.ittakestwo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.celerysoft.ittakestwo.R;
import com.celerysoft.ittakestwo.models.CardMatchingGame;

/**
 * Launching activity
 */
public class LaunchingActivity extends Activity {

    // Widget declare
    Button mBtnSinglePlayerGmae;
    Button mBtnMultiPlayerGame;
    Button mBtnHowToPlay;

    // Private Field


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launching);

        setupView();
        setupListener();
    }

    private void setupView() {
        mBtnSinglePlayerGmae = (Button) findViewById(R.id.launching_btn_single_player);
        mBtnMultiPlayerGame = (Button) findViewById(R.id.launching_btn_multi_player);
        mBtnHowToPlay = (Button) findViewById(R.id.launching_btn_how_to_play);
    }

    private void setupListener() {
        mBtnSinglePlayerGmae.setOnClickListener(mOnClickListener);
        mBtnMultiPlayerGame.setOnClickListener(mOnClickListener);
        mBtnHowToPlay.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.launching_btn_single_player:
                    startSinglePlayerGame();
                default:
                    // do nothing
            }
        }
    };

    private void startSinglePlayerGame() {
        Intent intent = new Intent(this, PlayingCardActivity.class);
        startActivity(intent);
    }
}
