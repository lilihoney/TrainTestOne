package test.up.day.day.com.daytest;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
/**
 * created on 2017.10.10
 * Handler引起的内存泄漏
 * */
public class TestTwoActivity extends BaseActivity {
    private Handler mHandler = new Handler();
    private Button mTest = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_test_two);

        mTest = (Button)findViewById(R.id.btn_test_handler);
        mTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 3* 60 * 1000);

        finish();

    }

    /*@Override
    protected void onDestroy() {
        Log.d("leak","call onDestroy in TestTwoActivity");
        super.onDestroy();
        MyApplication.getRefWatcher().watch(this);
    }*/
}
