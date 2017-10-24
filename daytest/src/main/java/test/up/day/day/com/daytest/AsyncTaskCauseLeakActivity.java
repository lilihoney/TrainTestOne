package test.up.day.day.com.daytest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AsyncTaskCauseLeakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_cause_leak);
    }


    private void startLeakTask(){
        //AsyncTask
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                while(true){

                }
            }
        }.execute();
    }
}
