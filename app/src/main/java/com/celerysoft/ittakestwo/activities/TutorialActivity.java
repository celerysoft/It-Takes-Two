package com.celerysoft.ittakestwo.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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

    private ButtonRectangle mBtnNext;
    private ButtonRectangle mBtnFinish;
    private ScrollView mSvTalkToPlayer;
    private LinearLayout mLlTalkToPlayer;

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
    }

    private int mCounter = 0;
    private void defineListener() {
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = new TextView(TutorialActivity.this);
                textView.setText("text text text" + mCounter);
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
