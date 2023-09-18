package com.test.utils;


/**
 * Created by khang.nguyen on 10/8/2016.
 */
public class WebCommonUtils {

    public static String getNameWithoutExtension(String fileName){
        Integer lastDot = fileName.lastIndexOf('.');
        if(lastDot > 0){
            return fileName.substring(0, lastDot);
        }
        return fileName;
    }

    public static String getExtension(String fileName){
        Integer lastDot = fileName.lastIndexOf('.');
        if(lastDot > 0){
            return fileName.substring(lastDot + 1, fileName.length());
        }
        return fileName;
    }
}
