package com.celerysoft.ittakestwo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.celerysoft.ittakestwo.R;
import com.gc.materialdesign.views.ButtonRectangle;

/**
 * Launching activity
 */
public class LaunchingActivity extends Activity {

    // Widget declare
    ButtonRectangle mBtnSinglePlayerGmae;
    ButtonRectangle mBtnMultiPlayerGame;
    ButtonRectangle mBtnHowToPlay;

    // Private Field

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launching);

        defineView();
        defineListener();
    }

    private void defineView() {
        mBtnSinglePlayerGmae = (ButtonRectangle) findViewById(R.id.launching_btn_single_player);
        mBtnMultiPlayerGame = (ButtonRectangle) findViewById(R.id.launching_btn_multi_player);
        mBtnHowToPlay = (ButtonRectangle) findViewById(R.id.launching_btn_how_to_play);
    }

    private void defineListener() {
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
                    break;
                case R.id.launching_btn_multi_player:
                    startMultiPlayerGame();
                    break;
                default:
                    // do nothing
                    break;
            }
        }
    };

    private void startSinglePlayerGame() {
        Intent intent = new Intent(this, PlayingCardActivity.class);
        startActivity(intent);
    }

    private void startMultiPlayerGame() {
        Intent intent = new Intent(this, MultiPlayerChoosePlayerCountActivity.class);
        startActivity(intent);
    }
}
