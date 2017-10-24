package test.up.day.day.com.daytest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class StaticInnerClassLeakActivity extends BaseActivity {
    private Button mLeakTest = null;

    //Activity 持有非静态内部类的静态实例引起内存泄漏
    private static InnerClass innerClass = null;

    //解决办法
    private InnerClass innerClassSolve = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_test);

        findViewById(R.id.btn_start_leak).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //持有非静态内部类引起的内存泄漏
                innerClass = new InnerClass();
                finish();
            }
        });

        findViewById(R.id.btn_no_leak).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                innerClassSolve = new InnerClass();
                finish();
            }
        });

//        //非静态内部类引起的内存泄漏
//        if(innerClass == null){
//            innerClass = new InnerClass();
//        }
//        finish();
    }


    class InnerClass{

    }
}
