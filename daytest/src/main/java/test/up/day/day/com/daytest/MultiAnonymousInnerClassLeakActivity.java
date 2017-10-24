package test.up.day.day.com.daytest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
/**
 * created on 2017.10.10
 * 测试多种内存泄漏的情况下LeakCanary的检测情况
 * 测试结果：只报出了anonymous implementation of java.lang.Runnable
 * */
public class MultiAnonymousInnerClassLeakActivity extends BaseActivity {

    private Button mTest = null;

    /*private Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            while(true){

            }
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_leak);

        mTest = (Button)findViewById(R.id.btn_test);
        mTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLeakTest();
                finish();
            }
        });
    }

    void startLeakTest(){
        //AsyncTask
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                while(true){

                }
            }
        }.execute();

        //Thread leak
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){

                }
            }
        }).start();
    }

}
