package test.up.day.day.com.daytest;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import test.up.day.day.com.daytest.view.TargetAdapter;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "DAY_TEST";
    byte[] mData = null;
    private Button mTestBase = null;
    private TextView mHeapInfo = null;

    private RecyclerView mList = null;
    private TargetClassDemo[] mTargets = new TargetClassDemo[]{
            new TargetClassDemo("测试非静态内部类静态实例引起的内存泄露", StaticInnerClassLeakActivity.class),
            new TargetClassDemo("测试Handler引起的内存泄漏", HandlerCauseLeakActivity.class),
            new TargetClassDemo("测试多个内存泄漏", MultiAnonymousInnerClassLeakActivity.class),
            new TargetClassDemo("测试Thread引起的内存泄漏", ThreadCauseLeakActivity.class),
            new TargetClassDemo("测试同步锁", SynchronizeTestActivity.class),
            new TargetClassDemo("测试Listener导致内存泄漏", AnonymousLeakActivity.class),
            new TargetClassDemo("测试JNI", JNITestActivity.class)
    };
    private TargetAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityManager activityManager = (ActivityManager)this.getSystemService(Context.ACTIVITY_SERVICE);
        int heap = activityManager.getMemoryClass(); //heapgrowthlimit
        int largeHeap = activityManager.getLargeMemoryClass();
        mHeapInfo = (TextView)findViewById(R.id.tv_heap);
        mHeapInfo.setText("HEAP = " + heap+", largeHeap = " + largeHeap);
        mTestBase = (Button)findViewById(R.id.button);
        mTestBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Base64.encodeToString(mData, Base64.DEFAULT);
            }
        });

        mList = (RecyclerView)findViewById(R.id.list_test_title);
        //设置布局结构
        mList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TargetAdapter(mTargets);
        mAdapter.setOnItemClickListener(new TargetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //此处有内存泄漏
//                Toast.makeText(getApplicationContext(), mTargets[position].targetName, Toast.LENGTH_SHORT).show();
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
