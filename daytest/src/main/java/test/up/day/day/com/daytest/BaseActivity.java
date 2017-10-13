package test.up.day.day.com.daytest;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Administrator on 2017/10/11.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onDestroy() {
        Log.d("leak","call onDestroy in currentActivity");
        super.onDestroy();
//        MyApplication.getRefWatcher().watch(this);
    }
}
