package test.up.day.day.com.daytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class ThreadCauseLeakActivity extends AppCompatActivity {

    private TextView mContent = null;
    private boolean mIsStop = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threak_cause_leak);

        mContent = (TextView)findViewById(R.id.tv_content);

        findViewById(R.id.btn_has_leak).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            //do something
                        }
                    }
                }).start();

                finish();
            }
        });


        findViewById(R.id.btn_no_leak).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SafeInnerThread(ThreadCauseLeakActivity.this).start();
                finish();
            }
        });
    }


    public void setNewContent(String content){
        mContent.setText(content);
    }

    //解决方法是声明为静态类
    static class SafeInnerThread extends Thread{
        WeakReference<ThreadCauseLeakActivity> mActivityReference = null;

        public SafeInnerThread(ThreadCauseLeakActivity activity){
            mActivityReference = new WeakReference<>(activity);
        }

        @Override
        public void run() {
            if(mActivityReference.get() == null){
                return;
            }
            while(true){
                //do something
                if(mActivityReference.get() != null && mActivityReference.get().mIsStop){
                    mActivityReference.get().setNewContent("change content : " + System.currentTimeMillis());
                }
            }

        }
    }
}
