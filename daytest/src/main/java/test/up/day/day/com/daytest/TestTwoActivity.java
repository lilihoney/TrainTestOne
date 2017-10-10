package test.up.day.day.com.daytest;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class TestTwoActivity extends AppCompatActivity {
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
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 30 * 1000);

                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        Log.d("leak","call onDestroy in TestTwoActivity");
        super.onDestroy();
        MyApplication.getRefWatcher().watch(this);
    }
}
