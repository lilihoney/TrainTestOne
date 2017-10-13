package test.up.day.day.com.daytest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import test.up.day.day.com.daytest.testclass.MyListener;

public class AnonymousLeakActivity extends BaseActivity {

    private Button mTest = null;
    private ExecutorService mExecutor = null;
    private Object mLock = null;
    private boolean mStopped = false;
    private long mStartTime = -1L;

    private MyListener mListener = new MyListener() {
        @Override
        public void onSuccess() {
            System.out.println("---------onSuccess is called---------");
        }

        @Override
        public void onError() {
            System.out.println("---------onError is called---------");

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anonymous_leak);

        mStartTime = System.currentTimeMillis();
        mLock = new Object();
        start();
        mTest = (Button)findViewById(R.id.btn_begin);
        mTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStopped = true;
            }
        });
    }

    private void start(){
        mExecutor = Executors.newSingleThreadExecutor();
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    if(mStopped){
                        break;
                    }
                    if(System.currentTimeMillis() - mStartTime > 15 * 1000){
                     break;
                    }
                    doSomething(mListener);
                }
            }
        });
    }

    private void doSomething(MyListener myListener) {
        synchronized (mLock){

        if (mListener != null) {
            mListener.onSuccess();
        }
    }
    }

    @Override
    protected void onPause() {
        synchronized (mLock){
            if(mListener != null){
                mListener.onError();
                System.out.println("release mListener");
                mListener = null;
                mStopped = true;
            }
        }
        super.onPause();
    }
}
