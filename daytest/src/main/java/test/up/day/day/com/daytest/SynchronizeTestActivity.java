package test.up.day.day.com.daytest;

import android.os.Bundle;

import test.up.day.day.com.daytest.testclass.Bank;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class SynchronizeTestActivity extends BaseActivity {
    private static final String TAG = "SynchronizeTest";
    private Bank bank = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchronize_test);

        bank = new Bank(1);
        SaveThread saveThread = new SaveThread(bank);
//        GetThread getThread = new GetThread(bank);

        saveThread.start();
//        getThread.start();
    }

    @Override
    protected void onPause() {
        if(bank != null){
            bank.clear();
        }
        super.onPause();
    }

    private static class SaveThread extends Thread{
        private Bank bank;
        public SaveThread(Bank bank){
            this.bank = bank;
        }

        @Override
        public void run() {
            try{
                Thread.sleep(10L);
                while(true){
                    bank.toSave(1);
                }
                /*for(int i = 0;i < 5;i++){
                    bank.toSave(10);
                }*/
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    private static class GetThread extends Thread{
        private Bank bank;
        public GetThread(Bank bank){
            this.bank = bank;
        }

        @Override
        public void run() {
            try{
                Thread.sleep(10L);
                for(int i = 0;i < 5; i++){
                    bank.toGet(10);
                }
                Thread.sleep(1000L);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }



}
