package test.up.day.day.com.daytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

public class LeakTestActivity extends AppCompatActivity {
    private Button mStartThread = null;
    private static InnerClass innerClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_test);

        mStartThread = (Button)findViewById(R.id.btn_start_thread);

        //非静态内部类引起的内存泄漏
        if(innerClass == null){
            innerClass = new InnerClass();
        }
        finish();
    }


    class InnerClass{

    }

    @Override
    protected void onDestroy() {
        Log.d("leak","call onDestroy in LeakTestActivity");
        super.onDestroy();
        MyApplication.getRefWatcher().watch(this);
    }
}
