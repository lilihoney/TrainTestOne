package test.up.day.day.com.traintestone.util;

import android.content.Context;

/**
 * Created by lili on 2017/6/19.
 * dip is device independent pixels, dp equals dip
 * dpi（屏幕像素密度） is dots per inch（每英寸像素的数目）, there are som familiar such as:120,160,240
 * density is 0.75 , 1 , 1.5 , 2 , 3 , 4 ,density = dpi/160 ,以mdpi为标准算出其他
 * ppi is pixels per inch,
 * ppi = 屏幕对角线像素数(px)/屏幕对角线长度(inch)，这是物体的属性
 * dpi = 屏幕对角线像素数(px)/屏幕对角线长度(inch)，这是单位，由 分辨率 和屏幕大小（对角线的长度确定）
 * Android 支持 dp device independent pixels，和 sp 用于设定字体
 * dp与屏幕像素无关
 *
 * 规定 160dpi的显示器上 1dp = 1px,在运行期，系统显示地处理所有通过dp单位的设置，
 * dp与px的转换：px = dp*(dpi/160)
 *
 * mdpi dpi = 160 , 1dp = 1px
 *
 * px = dip * density
 *
 */

public final class DisplayUtil {

    public static int dpToPx(Context context, float dpValue){
        float scale = context.getResources().getDisplayMetrics().density;
        //+0.5f是为了保证四舍五入取整数的正确性，因为(int)取整时是向下取整的，小数点后的所有数都会省略
        return (int)(dpValue*scale + 0.5f);
    }

    public static int pxToDp(Context context, float pxValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }

    public static int spToPx(Context context, float spValue){
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(spValue * scale + 0.5f);
    }

    public static int pxToSp(Context context, float pxValue){
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue / scale + 0.5f);
    }
}
