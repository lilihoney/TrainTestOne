package test.up.day.day.com.traintestone.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by on 2017/9/14.
 */

public class FileUtil {

    public static void createFolderInLocal(String folderPath, boolean deleOldFiles){
        File file = new File(folderPath);
        if(file.exists()){
            if(deleOldFiles){
                File[] files = file.listFiles();
                for(File f : files){
                    f.delete();
                }
            }
            return;
        }
        file.mkdirs();
    }

    /**
     * 将byte数组存为本地文件
     * @param data byte数据
     * @param filePath 本地文件路径
     * */
    public static void saveBytesToFile(byte[] data, String filePath) {
        if (data == null || data.length < 1) {
            return;
        }
        try {
            File file = new File(filePath);
            if(file.exists()){
                file.delete();
            }
            if(!file.exists()){
                if(file.getParentFile().mkdirs()){
                    if(!file.createNewFile()){
                        return;
                    }
                }
            }
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(data);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
