package org.agoenka.golist;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Author: agoenka
 * Created At: 9/29/2016
 * Version: ${VERSION}
 */

public class GoListApplication extends Application {

    private static final boolean DEBUG = false;

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).build());
        if(DEBUG)
            FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
    }

}
