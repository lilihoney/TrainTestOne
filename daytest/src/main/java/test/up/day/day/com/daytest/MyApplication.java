package test.up.day.day.com.daytest;

import android.app.Application;
import android.content.Context;

/**
 * Created on 2017/10/10.
 */

public class MyApplication extends Application {
    private static Context context;
//    private static RefWatcher mRefWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
//        if(LeakCanary.isInAnalyzerProcess(this)){
//            return;
//        }
//        mRefWatcher = LeakCanary.install(this);
//        mRefWatcher.watch(this); //添加此句MyApplication内存泄漏
        context = getApplicationContext();
    }

//    public static RefWatcher getRefWatcher(){
//        return mRefWatcher;
//    }

    public static Context getContext(){
        return context;
    }
}
