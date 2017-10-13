package test.up.day.day.com.daytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JNITestActivity extends AppCompatActivity {
    static{
        System.loadLibrary("firstJNI");
    }

    public native String testJni();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jnitest);

        ((TextView)findViewById(R.id.tv_jnitest)).setText(testJni());

    }
}
