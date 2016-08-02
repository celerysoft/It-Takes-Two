package com.celerysoft.ittakestwo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.celerysoft.ittakestwo.R;

/**
 * Launching activity
 */
public class LaunchingActivity extends Activity {

    // Widget declare
    private View mBtnSinglePlayerGame;
    private View mBtnMultiPlayerGame;
    private View mBtnHowToPlay;

    // Private Field

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launching);

        defineView();
        defineListener();
    }

    private void defineView() {
        mBtnSinglePlayerGame = findViewById(R.id.launching_btn_single_player);
        mBtnMultiPlayerGame = findViewById(R.id.launching_btn_multi_player);
        mBtnHowToPlay = findViewById(R.id.launching_btn_how_to_play);
    }

    private void defineListener() {
        mBtnSinglePlayerGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSinglePlayerGame();
            }
        });
        mBtnMultiPlayerGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMultiPlayerGame();
            }
        });
        mBtnHowToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTutorial();
            }
        });
    }

    private void startSinglePlayerGame() {
        Intent intent = new Intent(this, PlayingCardActivity.class);
        startActivity(intent);
    }

    private void startMultiPlayerGame() {
        Intent intent = new Intent(this, MultiPlayerChoosePlayerCountActivity.class);
        startActivity(intent);
    }

    private void startTutorial() {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }
}
