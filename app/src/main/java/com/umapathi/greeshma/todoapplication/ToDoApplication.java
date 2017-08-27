package com.umapathi.greeshma.todoapplication;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Greeshma on 8/21/17.
 */

public class ToDoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // This instantiates DBFlow
        FlowManager.init(new FlowConfig.Builder(this).build());
        Stetho.initializeWithDefaults(this);
        // add for verbose logging
        // FlowLog.setMinimumLoggingLevel(FlowLog.level.V);
    }
}
