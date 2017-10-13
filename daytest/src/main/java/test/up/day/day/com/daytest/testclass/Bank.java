package test.up.day.day.com.daytest.testclass;

import android.util.Log;

/**
 * Created by Administrator on 2017/10/11.
 */

public class Bank {

    private static final String TAG = "Bank";

    //共享资源为private
    private int mCount;
    private Object mLock;

    public Bank(int originalCount){
        mCount = originalCount;
        mLock = new Object();
    }

    public void toSave(int saveCount){
        synchronized (mLock){
            System.out.println("call toSave, mCount = " + mCount + ", currentTime = "+ System.currentTimeMillis());
            Log.d(TAG,"save money to bank");
            mCount += saveCount;
            System.out.println("after save , mCount = " + mCount);
        }
    }

    public void toGet(int getCount){
        synchronized (mLock){
            System.out.println("call toGet, mCount = "+ mCount + ", currentTime = "+ System.currentTimeMillis());
            mCount -= getCount;
            System.out.println("after get , mCount = "+ mCount);
        }
    }

    public void clear(){
        synchronized (mLock){
            System.out.println("call clear, mCount = " + mCount +  ", currentTime = "+ System.currentTimeMillis());
            mCount = 0;
            System.out.println("after clear,mCount = " + mCount);
        }
    }

    public int getCount(){
        return mCount;
    }
}
