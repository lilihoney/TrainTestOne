package test.up.day.day.com.traintestone.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/6/14.
 */

public final class FileUtils {
    public static boolean createFile(String path){
        File file = new File(path);
        if(!file.exists()){
            if(path.endsWith("/")){
                file.mkdirs();
            }else {
                try{
                    file.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                    System.out.println("create file failed");
                }
            }
        }else{
            System.out.println("file exits");
        }
        return file.exists();
    }

    public static File[] getFiles(String path){

        File file = new File(path);
        if(!file.exists()){
            System.out.println("not found");
            return null;
        }else{
            File[] files = file.listFiles();
            return files;
        }
    }

    public static List<String> getFilesName(String path){
        List<String> filesName = new ArrayList<String>();
        File[] files = getFiles(path);
        if(files != null){
            for(File f : files){
                filesName.add(f.getName());
            }
        }
        return filesName;
    }
}
