package test.up.day.day.com.daytest.testclass;

/**
 * Created by Administrator on 2017/10/11.
 */

public class Scan {

    private MyListener mListener = null;
    private long mStartTime = 0L;
    public Scan(MyListener myListener){
        mListener = myListener;
        mStartTime = System.currentTimeMillis();
    }

    public void doing(){
        while(System.currentTimeMillis() - mStartTime < 20 * 1000){

        }
        if(mListener != null){
            mListener.onSuccess();
        }
    }

    public void release(){
        mListener = null;
    }
}
