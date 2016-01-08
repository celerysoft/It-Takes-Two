package com.celerysoft.ittakestwo.application;

import android.app.Application;

/**
 * Created by Celery on 16/1/8.
 * Custom Application
 */
public class AdvancedApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }
}
