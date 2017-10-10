package test.up.day.day.com.daytest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.squareup.haha.trove.THash;

public class MultiLeakActivity extends AppCompatActivity {

    private Button mTest = null;

    private Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            while(true){

            }
        }
    };

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

    @Override
    protected void onDestroy() {
        Log.d("leak","call onDestroy in MultiLeakActivity");
        super.onDestroy();
        MyApplication.getRefWatcher().watch(this);
    }
}
