package test.up.day.day.com.traintestone.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created on 2017/9/19.
 *
 * @author qll
 */

public final class DeviceUtil {

    private static final String TAG = "device_util";

   public static Size getRealSize(Context applicationContext){
        WindowManager manager = (WindowManager)applicationContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        //getRealSize
        display.getRealSize(point);
        return new Size(point.x, point.y);
    }

    public static Size getWindowSize(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        display.getRealSize(point);
        return new Size(point.x, point.y);
    }

    public static DisplayMetrics getDensity(Activity activity){
        return activity.getResources().getDisplayMetrics();
        //densityDpi是每英寸的点数（dots-per-inch）是打印机常用单位（因而也被称为打印分辨率），而不是每英寸的像素数
    }
}
