package test.up.day.day.com.daytest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * created on 2017.10.10
 * Handler引起的内存泄漏
 * 后果：是虚拟机占用内存过高，导致OOM（内存溢出），程序出错。
 * */
public class HandlerCauseLeakActivity extends BaseActivity {

    private TextView mTitle = null;

    //跨生命周期持有匿名内部类实例引起内存泄漏
    private Handler mHandler = new Handler();

    private MyHandler myHandler = new MyHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_test_two);

        mTitle = (TextView)findViewById(R.id.tv_content);

        //to run leak
        findViewById(R.id.btn_test_handler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mTitle.setText("The title has been changed!");
                    }
                }, 3* 60 * 1000);

                finish();
            }
        });

        //solve leak by remove message
        findViewById(R.id.btn_solve_handler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setNewTitle();
                    }
                }, 3* 60 * 1000);

                mHandler.removeCallbacksAndMessages(null);
                finish();
            }
        });

      //solve leak using static inner class and WeakReference
        findViewById(R.id.btn_use_static).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHandler.sendMessageDelayed(Message.obtain(), 3 * 60 * 1000);
                finish();
            }
        });

    }


    public void setNewTitle(){
        mTitle.setText("you have set a new title!");
    }

    //方法1：声明为静态类，实例化后不再持有外部类的引用，可以增加一个对外问类的弱引用对外部类进行操作
    private static class MyHandler extends Handler{
        WeakReference<HandlerCauseLeakActivity> mActivityReference = null;

        public MyHandler(HandlerCauseLeakActivity activity){
            mActivityReference = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            if (mActivityReference.get() == null) {
                return;
            }
            mActivityReference.get().setNewTitle();
        }
    }

    @Override
    protected void onDestroy() {
        if(myHandler != null){
            myHandler.removeCallbacksAndMessages(null );
        }
        super.onDestroy();
    }

    /*解决方法
    * 1.逻辑保护
    *   （1）在关闭Activity时停止后台线程，切断Handler与外部的联系，保证能在合适的时间回收Activity
    *   （2）Handler被delay的message持有时，使用Handler的removeCallbacks方法，将消息对象从消息队列中移除
    * 2.声明静态Handler类
    * */
}
