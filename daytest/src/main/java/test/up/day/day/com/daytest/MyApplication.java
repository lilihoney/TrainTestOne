package test.up.day.day.com.daytest;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created on 2017/10/10.
 */

public class MyApplication extends Application {
    private RefWatcher mRefWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        mRefWatcher = LeakCanary.install(this);
        mRefWatcher.watch(this);
    }

}
