package test.up.day.day.com.traintestone.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;


/**
 * Created by Administrator on 2017/7/26.
 */

public final class CheckPermission {

    public static boolean checkPermission(Context context, String... permissions){
        if(permissions == null || permissions.length < 1){
            return true;
        }
        if(Build.VERSION.SDK_INT < 23){
            return true;
        }
        for(String permission : permissions){
            if(context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }
}
