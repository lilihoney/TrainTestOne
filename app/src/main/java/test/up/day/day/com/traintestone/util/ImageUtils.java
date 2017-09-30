package test.up.day.day.com.traintestone.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/6/14.
 */

public final class ImageUtils {

    public static Bitmap rgba2Bitmap(int[] rgba, int width, int height){
        return Bitmap.createBitmap(rgba, width, height, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap byteToBitmap(byte[] imageData){
        return BitmapFactory.decodeByteArray(imageData, 0 ,imageData.length);
    }

    public static void saveBitmapToFile(Bitmap bitmap, String path){
        try{
            File file = new File(path);
            if(!file.exists()){
                if(!file.getParentFile().mkdirs()){
                    if(!file.createNewFile()){
                        //createNewFile()需要捕获异常
                        return;
                    }
                }
            }
            OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90 ,os);
            os.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
