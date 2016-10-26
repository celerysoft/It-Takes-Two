package com.celerysoft.ittakestwo.activities;

import android.app.Activity;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by admin on 16/10/25.
 *
 */

public class BaseActivity extends Activity {
    @Override
    protected void onResume() {
        super.onResume();

        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        MobclickAgent.onPause(this);
    }
}
