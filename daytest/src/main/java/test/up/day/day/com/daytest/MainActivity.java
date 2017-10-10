package test.up.day.day.com.daytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import test.up.day.day.com.daytest.view.TargetAdapter;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "DAY_TEST";

    private RecyclerView mList = null;
    private TargetClassDemo[] mTargets = new TargetClassDemo[]{
            new TargetClassDemo("测试非静态内部类内存泄露", LeakTestActivity.class),
            new TargetClassDemo("测试Handler引起的内存泄漏", TestTwoActivity.class),
            new TargetClassDemo("测试多个内存泄漏", MultiLeakActivity.class)
    };
    private TargetAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = (RecyclerView)findViewById(R.id.list_test_title);
        //设置布局结构
        mList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TargetAdapter(mTargets);
        mAdapter.setOnItemClickListener(new TargetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(MainActivity.this, mTargets[position].targetClass));
            }
        });
        mList.setAdapter(mAdapter);
    }

    public static class TargetClassDemo{
        private String targetName;
        private Class<? extends Activity> targetClass;

        public TargetClassDemo(String targetName, Class<? extends Activity> targetClass){
            if(targetClass == null){
                throw new NullPointerException("targetClass can not be null!");
            }
            this.targetName = targetName;
            this.targetClass = targetClass;
        }
        public String getTargetName(){
            return targetName;
        }
        public Class<? extends Activity> getTargetClass(){
            return targetClass;
        }
    }

}
